package Security;

import java.util.Base64;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import myApp.MovieDao;

public class GenerateSecretKey 
{	
	static String encodedKey;
	static String clientId;
	public static void GenerateKey(String id) throws Exception
	{
		KeyGenerator kg = KeyGenerator.getInstance("HmacSHA256");
		SecretKey sk = kg.generateKey();
		
		encodedKey = Base64.getEncoder().encodeToString(sk.getEncoded());
		//System.out.println("Encoded Key: " + encodedKey);
		
		clientId = id;
		MovieDao.instance.insertKey(clientId,encodedKey);
	}
}
