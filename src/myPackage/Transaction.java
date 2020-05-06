package myPackage;

import java.security.*;
import java.util.ArrayList;



public class Transaction {
	
	public String transactionID;
	public PublicKey sender;
	public PublicKey reciever;
	public float value;
	public byte[] signature;
	
	
	
	ArrayList<TransactionInput> input = new ArrayList<TransactionInput>();
	ArrayList<TransactionOutput> output = new ArrayList<TransactionOutput>();
	
	
	public static int sequence = 0;
	
	public Transaction(PublicKey from, PublicKey to, float value, ArrayList<TransactionInput> input)
	{
		reciever=to;
		sender=from;
		this.input=input;
		this.value=value;
	}
	
	
	
	// calculate hash to be used as ID
	
	private String calculateHash()
	{
		
		sequence++;   // increase seq. no. so that no two transactions are same
		
		
		
		String inputForHash = StringUtil.getStringFromKey(sender) + 
							  StringUtil.getStringFromKey(reciever) + 
							  Float.toString(value) + 
							  sequence;
		
		return StringUtil.appySha256(inputForHash);
		
	}
	
	
	
	
	
	// Function to get the digital signature
	public void generateSignature(PrivateKey privatekey)
	{
		String inputForHash = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciever) + Float.toString(value);
		signature = StringUtil.applyECDSASig(privatekey, inputForHash);
	}
	
	
	
	
	
	public boolean verifySignature()
	{
		String inputForHash = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciever) + Float.toString(value);
		return StringUtil.verifyECDSASig(sender, inputForHash, signature);
	}

}
