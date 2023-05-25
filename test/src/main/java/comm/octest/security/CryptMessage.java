package comm.octest.security;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/CryptMessage")
public class CryptMessage extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CryptMessage() {
        super();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        System.setProperty("illegal-access", "warn");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ana dkhult +++++++++++++++++++++++++++++++++++++");
        KeyPairGenerator keyPairGenerator;
        try {
            String message = request.getParameter("message");
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            KeyPair senderKeyPair = keyPairGenerator.generateKeyPair();

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, senderKeyPair.getPrivate());    
            byte[] encryptedMessage = cipher.doFinal(message.getBytes());
            
            
            SignatureClass signatureClass = new SignatureClass() ;
           
            
            
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream objectOut = new ObjectOutputStream(byteOut);
            objectOut.writeObject(senderKeyPair.getPublic());
            objectOut.close();
            byte[] publicKeyBytes = byteOut.toByteArray();
            String publicKeyString = Base64.getEncoder().encodeToString(publicKeyBytes);
            String encryptedMessageString = Base64.getEncoder().encodeToString(encryptedMessage);
            
            Gson gson = new Gson();
            String signature = signatureClass.sign(encryptedMessageString, senderKeyPair.getPrivate());
            MessageAndKey msgAndKey = new MessageAndKey(encryptedMessageString, publicKeyString,signature);
            String json = gson.toJson(msgAndKey);
            
            response.getWriter().write(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
