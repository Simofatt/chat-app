package comm.octest.security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Hash {
	
	
	public  static byte[] chiffrementAES(String message, SecretKeySpec cle) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, cle);
		byte[] messageChiffre = cipher.doFinal(message.getBytes());
		// permet de terminer l'opération de chiffrement ou de déchiffrement d'un bloc de données et de récupérer le résultat final sous forme de tableau d'octets (byte[])
		return messageChiffre;
		}

		public static String dechiffrementAES(byte[] messageChiffre, SecretKeySpec cle) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, cle);
		byte[] messageDechiffre = cipher.doFinal(messageChiffre);
		return new String(messageDechiffre);
		}




		/*
		// Chiffrer le message avant de l'envoyer
		String message = "Le message à chiffrer";
		//hna kan3arfu la clé de chiffrement dyalna construite à partir de la chaîne 
		//de caractères "MaCleDeChiffrement" qui est convertie en tableau d'octets
		SecretKeySpec cle = new SecretKeySpec("MaCleDeChiffrement".getBytes(), "AES");
		// hna kan3eytu l la methode dyalna en donnant comme parametre le message
		//qu'on veut chiffrer et aussi la clé de chiffrement
		byte[] messageChiffre = chiffrementAES(message, cle);

		// Envoyer le message chiffré avec la clé de chiffrement
		String destinataire = "utilisateur2";
		Message messageEnvoye = new Message(messageChiffre, cle, destinataire);
		envoyerMessage(messageEnvoye);

		// Recevoir le message et le déchiffrer
		Message messageRecu = recevoirMessage();
		byte[] messageChiffreRecu = messageRecu.getMessageChiffre();
		SecretKeySpec cleRecue = messageRecu.getCle();
		String messageDechiffre = dechiffrementAES(messageChiffreRecu, cleRecue);

		// Afficher le message en clair
		System.out.println("Message reçu : " + messageDechiffre);
		
		*/
}

