package comm.octest.beans;


import comm.octest.db.Pseudo;
import comm.octest.security.Hash;
import comm.octest.security.SignatureClass;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.PublicKey;
import java.util.Hashtable;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.ArrayList;
import java.util.Base64;

import javax.crypto.spec.SecretKeySpec;
import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;

@ServerEndpoint(value="/chatroom/{pseudo}",
                configurator=ChatRoom.EndpointConfigurator.class)
public class ChatRoom {
    
    private static ChatRoom singleton = new ChatRoom();
    private Hashtable<String, Session> sessions = new Hashtable<>();
    public static ArrayList<String> sessionsO = new ArrayList<String>() ;
    private ArrayList<Message> messages_list = new ArrayList<Message>();
    
    public ChatRoom() {
    }

   
    public static ChatRoom getInstance() {
        return ChatRoom.singleton;
    }

   
    
   @OnOpen
   public void open(Session session, @PathParam("pseudo") String pseudo) {
   session.getUserProperties().put( "pseudo", pseudo );
   sessions.put(session.getId(), session);
    }

   
    @OnClose
    public void close(Session session) {
        String pseudo = (String) session.getUserProperties().get( "pseudo" );
        sessions.remove(session.getId());
       // sendMessage( "Admin >>> Connection closed for " + pseudo, "admin", pseudo );
    }

    @OnError
    public void onError(Throwable error) {
        System.out.println( "Error: " + error.getMessage() );
    }
    
    public void setSessions(String email) {
        System.out.println("zazazaz") ;
    	sessionsO.add(email) ;
    	 for (String s : sessionsO) {
        System.out.println(s) ;
    	 }
    }
    public void removeSession(String email){
        sessionsO.remove(email);
    }

   public ArrayList<String> connected(String email) {
        Pseudo pseudo = new Pseudo(email) ;
        ArrayList<String> onlineFriends = new ArrayList<String>() ;
        ArrayList<String> contacts = pseudo.getContacts() ;

        for(String c : contacts) {
            System.out.println(c) ;
            for (String s : sessionsO) {
                if(c.equals(s)) {
                    onlineFriends.add(c);
                    System.out.println("online friend" +c) ;
                }
            }
        }
        return onlineFriends;
}

   @OnMessage
   public void handleMessage(String msg, Session session, @PathParam("pseudo") String pseudo, @PathParam("withClientId") String withClientId) throws IOException, Exception {
       JsonObject json = Json.createReader(new StringReader(msg)).readObject();
       String to = json.getString("to");
       String from = json.getString("from");
       String publicKey = json.getString("publicKey");
       String signature = json.getString("signature") ;

       AtomicBoolean isConnected = new AtomicBoolean(false);
       int x =0;
       sessions.forEach((key,value) -> {
           if (value.getUserProperties().get("pseudo").equals(to)) {
               isConnected.set(true);
           }
       });

       if (isConnected.get() == false) {
           System.out.println("************Message Inserted in DB*************");
           System.out.println("************************************************");
           System.out.println(pseudo +" &&&&&&&&&&&&&&&&&&& " + withClientId);
           addMessageToDb(json);
       } else {
               String messageContent = json.getString("message");
               String expediteur = (String) session.getUserProperties().get("pseudo");
               String fullMessage = messageContent;
   
               if (json.containsKey("file")) {
                   String[] fileInfos = new String[4];
                   JsonObject fileJson = json.getJsonObject("file");
                   String fileName = fileJson.getString("name");
                   String fileType = fileJson.getString("type");
                   String fileData = fileJson.getString("data");
                   // Generate an unique ID for the file
                   UUID fileId=UUID.randomUUID();

                   fileInfos[0] = fileId.toString();
                   fileInfos[1] = fileName;
                   fileInfos[2] = fileType;
                   fileInfos[3] = fileData;


                   byte[] fileBytes = Base64.getDecoder().decode(fileData);

                   // save file to disk
                   Path filePath = Paths.get("C:\\xampp\\htdocs\\php\\chatApp-ressources\\" + fileId+"_"+fileName);
                   Files.write(filePath, fileBytes);

                   sendFile(fileInfos, expediteur, to);
                   messages_list.add(new Message(expediteur,to,fullMessage,"file"));
               }
               else {
           
            	   SignatureClass signatureClass = new SignatureClass() ;
            	   byte[] publicKeyBytes = Base64.getDecoder().decode(publicKey);
                   ByteArrayInputStream byteIn = new ByteArrayInputStream(publicKeyBytes);
                   ObjectInputStream objectIn = new ObjectInputStream(byteIn);
                   PublicKey deserializedPublicKey = (PublicKey) objectIn.readObject();
                   objectIn.close();
            	   boolean isSignatureValid = signatureClass.verify(fullMessage, signature, deserializedPublicKey); 
            	   if(isSignatureValid) {
            	   
                   sendMessage(fullMessage, expediteur, to, publicKey);
                   messages_list.add(new Message(expediteur,to,fullMessage,"text"));
            	   }else { 
            		   System.out.println("++++++++++++Signature is not valid++++++++++++++");
            	   }
                   
               }
       }
       
   }



   private void sendMessage(String message, String expediteur, String destinataire, String publicKey) {
       System.out.println(expediteur + " >>> " + message);
     
   
       // Recherche de la session du destinataire
       Session sessionDestinataire = null;
       for (Session s : sessions.values()) {
           String email = (String) s.getUserProperties().get("pseudo");
           if (email.equals(destinataire)) {
               sessionDestinataire = s;
               break;
           }
       }

      
       if (sessionDestinataire != null) {
           try {
        /*	   SecretKeySpec key = new SecretKeySpec("MaCleChiffrement".getBytes(), "AES");
       		Hash hash = new Hash() ;
       		String messageDechiffre = hash.dechiffrementAES(message, key);*/
       	
        	    
               sessionDestinataire.getBasicRemote().sendText(expediteur + "," + destinataire + "," + publicKey + "," + "Msg:" + message);
           } catch (Exception exception) {
               System.out.println("ERROR: cannot send message to " + sessionDestinataire.getId());
           }
       }
   }


   private void sendFile(String[] fileInfos, String expediteur, String destinataire) {

       // Recherche de la session du destinataire
       Session sessionDestinataire = null;
       for (Session s : sessions.values()) {
           String email = (String) s.getUserProperties().get("pseudo");
           if (email.equals(destinataire)) {
               sessionDestinataire = s;
               break;
           }
       }


       if (sessionDestinataire != null) {
           try {
               sessionDestinataire.getBasicRemote().sendText(expediteur + "," + destinataire + "," + "File:" + fileInfos[0] + "|" + fileInfos[1] + "|" + fileInfos[2] + "|" + fileInfos[3]);
           } catch (Exception exception) {
               System.out.println("ERROR: cannot send message to " + sessionDestinataire.getId());
           }
       }
   }

   public void addMessageToDb (JsonObject json) throws Exception{
       String to = json.getString("to");
       String from = json.getString("from");
       String messageContent = json.getString("message");
       String type = "text";
       if(json.containsKey("file")) {
           type = "file";
           String[] fileInfos = new String[4];
           JsonObject fileJson = json.getJsonObject("file");
           String fileName = fileJson.getString("name");
           String fileType = fileJson.getString("type");
           String fileData = fileJson.getString("data");
           // Generate an unique ID for the file
           UUID fileId=UUID.randomUUID();
           messageContent = fileId+"_"+fileName;
           fileInfos[0] = fileId.toString();
           fileInfos[1] = fileName;
           fileInfos[2] = fileType;
           fileInfos[3] = fileData;


           byte[] fileBytes = Base64.getDecoder().decode(fileData);

           // save file to disk
           Path filePath = Paths.get("C:\\xampp\\htdocs\\php\\chatApp-ressources\\"+ fileId+"_"+fileName);
           Files.write(filePath, fileBytes);

       }
       Pseudo pseudo = new Pseudo();
       pseudo.insertMsg(to,from,messageContent,type);
   }
   
    public void removeMsg(String pseudo, String withClientId){
        System.out.println("Msg removed*****************************" + pseudo + " & " + withClientId);
    }

    public void disconnectUser(String email)
    {
        sessions.remove(email);
    }

    
   
    public static class EndpointConfigurator extends ServerEndpointConfig.Configurator {
        @Override 
        @SuppressWarnings("unchecked")
        public <T> T getEndpointInstance(Class<T> endpointClass) {
            return (T) ChatRoom.getInstance();
        }
    }
}