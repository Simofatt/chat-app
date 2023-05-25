package comm.octest.security;

import java.security.Key;
import java.security.KeyPair;
import java.security.PublicKey;

public class MessageAndKey {
	public String encryptedMessage;
	public String publicKey;
	public String signature;
	
	public MessageAndKey(String encryptedMessage, String publicKey,String signature) {
		this.encryptedMessage = encryptedMessage;
		this.publicKey = publicKey;
		this.signature = signature;
	}

	
}

