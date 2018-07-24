package Security;

import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import myApp.MovieDao;
import myApp.UserDao;

public class Client 
{
	String secretKey;
	String clientId;
	String signature;
	
	public Client() throws Exception
	{
		secretKey = MovieDao.instance.getKey(UserDao.User);
		clientId = UserDao.User;
		
		byte[] decodedKey = Base64.getDecoder().decode(secretKey);
		SecretKey sk = new SecretKeySpec(decodedKey, 0, decodedKey.length,"HmacSHA256");
		
		Mac mac = Mac.getInstance("HmacSHA256");
		mac.init(sk);
		
		byte[] result = mac.doFinal(clientId.getBytes());
		signature = Base64.getEncoder().encodeToString(result);
		
	}

	public String getSecretKey() {
		return secretKey;
	}
	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getClientId() {
		return clientId;
	}

}
