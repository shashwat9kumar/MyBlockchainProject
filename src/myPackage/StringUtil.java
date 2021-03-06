package myPackage;

import java.security.*;
import java.util.Base64;


public class StringUtil {
	
	
	//This function applies ECDSA signature on an input and returns the result as an array of bytes 
	public static byte[] applyECDSASig(PrivateKey privateKey, String input) 
	{
		Signature dsa;
		byte output[] = new byte[0];   // To reduce the Null-Checks
		
		try 
		{
			dsa = Signature.getInstance("ECDSA", "BC");
			dsa.initSign(privateKey);
			
			byte[] strByte = input.getBytes();
			dsa.update(strByte);
			
			byte[] realSig = dsa.sign();
			output = realSig;
		}
		
		catch (Exception e) // Chances of NoSuchAlgorithmException (Checked)
		{
			throw new RuntimeException(e);
		}
		
		
		return output;
	}
	
	
	
	
	
	
	//Verifies a String signature 
	public static boolean verifyECDSASig(PublicKey publicKey, String data, byte[] signature) 
	{
			
		try 
		{
			Signature ecdsaVerify = Signature.getInstance("ECDSA", "BC");
			ecdsaVerify.initVerify(publicKey);
			
			ecdsaVerify.update(data.getBytes());
			return ecdsaVerify.verify(signature);
			
		}
		catch(Exception e) 
		{
				throw new RuntimeException(e);
		}
	}
	
	
	
	
	
	public static String getStringFromKey(Key key) 
	{
		return Base64.getEncoder().encodeToString(key.getEncoded());
	}
	
	
	
	
	
	
	
	
	
	
	public static String appySha256(String input)
	{
		
		try
		{
			
			MessageDigest digest=MessageDigest.getInstance("SHA-256");
			
			
			byte[] hash=digest.digest(input.getBytes("UTF-8"));
			
			StringBuffer hexString= new StringBuffer();
			
			for(int i=0;i<hash.length;i++)
			{
				String hex=Integer.toHexString(0xff & hash[i]);
				if(hex.length()==1)
				{
					hexString.append('0');
				}
				hexString.append(hex);
			}
			
			return hexString.toString();
			
			
		}
		
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
		
	}
	

}
