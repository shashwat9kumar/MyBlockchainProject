package myPackage;

import java.util.Date;

public class Block {
	
	
	public String hash;
	public String previousHash;
	public String data;
	private long timeStamp;
	private int nonce;
	
	
	
	public Block(String data, String previousHash)
	{
		
		this.data=data;
		this.previousHash=previousHash;
		this.timeStamp= new Date().getTime();     // Anonymous object of Date class
		this.hash=calculateHash();         // Calling this function after setting values for other data members
		
	}
	
	
	
	public String calculateHash()
	{
		
		String inputForSha256 = previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + data;
		
		String calculatedHash = StringUtil.appySha256(inputForSha256);
		
		return calculatedHash;
		
	}
	
	
	public void mineBlock(int difficulty)
	{
		
		String target = new String(new char[difficulty]);
		target = target.replace('\0', '0');
		
		while(!(hash.substring(0,difficulty).equals(target)))
		{
			nonce++;
			hash=calculateHash();
		}
		
		System.out.println("Block is mined :  " + hash);
		
	}
	

}
