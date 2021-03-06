package myPackage;

import java.security.*;
import java.security.spec.ECGenParameterSpec;

public class Wallet {
	
	public PrivateKey privateKey;
	
	public PublicKey publicKey;
	
	
	public Wallet()
	{
		
		generateKeyPair();
		
	}
	
	
	public void generateKeyPair()
	{
		try
		{
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA","BC");
			
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			
			ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
			
			keyGen.initialize(ecSpec, random);
			
			KeyPair keyPair = keyGen.generateKeyPair();
			
			publicKey = keyPair.getPublic();
			
			privateKey = keyPair.getPrivate();
			
			
			
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}

}
