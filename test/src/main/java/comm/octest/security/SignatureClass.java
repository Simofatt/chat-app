package comm.octest.security;

import java.security.PrivateKey;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import java.util.Base64;

public class SignatureClass {
	
	
	

    public static String sign(String message, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(message.getBytes());
        byte[] signBytes = signature.sign();
        return Base64.getEncoder().encodeToString(signBytes);
    }

    public static boolean verify(String message, String signature, PublicKey publicKey) throws Exception {
        Signature verifier = Signature.getInstance("SHA256withRSA");
        verifier.initVerify(publicKey);
        verifier.update(message.getBytes());
        byte[] signatureBytes = Base64.getDecoder().decode(signature);
        return verifier.verify(signatureBytes);
    }
    
    public static PrivateKey getPrivateKeyFromKeystore() {
    	PrivateKey privateKey = null;
    	try {
            // Load the keystore
            InputStream fis = SignatureClass.class.getResourceAsStream("keystore.jks");
            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            keystore.load(fis, "chatapp".toCharArray());

            // Get the private key
            Key key = keystore.getKey("mykey", "chatapp".toCharArray());
            if (key instanceof PrivateKey) {
                privateKey = (PrivateKey) key;
                System.out.println("Private Key: " + privateKey.toString());
            }

            fis.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    	
    	return privateKey;
    }
    
    public static PublicKey getPublicKeyFromKeystore() {
    	PublicKey publicKey = null;
    	try {
            // Load the keystore
            InputStream fis = SignatureClass.class.getResourceAsStream("keystore.jks");
            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            keystore.load(fis, "chatapp".toCharArray());
            
            // Get the public key
            java.security.cert.Certificate cert = keystore.getCertificate("mykey");
            publicKey = cert.getPublicKey();
            System.out.println("Public Key: " + publicKey.toString());


            fis.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    	
    	return publicKey;
    }
    
    public static String cryptKey(String key){
        char[] chars = key.toCharArray();

        for (int i = 0; i < chars.length; i++) {
        		if (i%2 == 0) chars[i] = (char) (chars[i]/2);
            
        }

        return new String(chars);
    }

    public static String decryptKey(String encryptedKey){
        char[] chars = encryptedKey.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (i%2 == 0) chars[i] = (char) (chars[i]*2);
        }

        return new String(chars);
    }
}
