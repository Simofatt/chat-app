package comm.octest.security;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import comm.octest.beans.Message;

/**
 * Servlet implementation class DecryptMessage
 */
@WebServlet("/DecryptMessage")
public class DecryptMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DecryptMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ana dkhult +++++++++++++++++++++++++++++++++++++");
        KeyPairGenerator keyPairGenerator;
        try {
            String message = request.getParameter("message");
            String publicKey = request.getParameter("publicKey");
            String signature = request.getParameter("signature");

            
            byte[] messageByte = Base64.getDecoder().decode(message);
            byte[] publicKeyBytes = Base64.getDecoder().decode(publicKey);
            ByteArrayInputStream byteIn = new ByteArrayInputStream(publicKeyBytes);
            ObjectInputStream objectIn = new ObjectInputStream(byteIn);
            PublicKey deserializedPublicKey = (PublicKey) objectIn.readObject();
            objectIn.close();
            
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, deserializedPublicKey);    
            byte[] decryptedMessage = cipher.doFinal(messageByte);
            
     	   SignatureClass signatureClass = new SignatureClass() ;
     	   PublicKey publicKeyForSign = signatureClass.getPublicKeyFromKeystore();
	   	   boolean isSignatureValid = signatureClass.verify(message, signature, publicKeyForSign); 
	   	   System.out.println("verification signature");
	   	   if(isSignatureValid) {
		   	 System.out.println("Signature validé");
	   		response.getWriter().write(new String(decryptedMessage));
	   	   }
	   	   else {
			System.out.println("Signature non validé");
	   		response.getWriter().write("Contenu invisible ! l'application soupconne une tentative de piratage");
	   	   }
                        
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}

}
