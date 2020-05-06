package myPackage;

import java.util.ArrayList;
import java.security.Security;
import java.util.Base64;
import com.google.gson.GsonBuilder;

import com.google.gson.*;


public class TestChain {

	
	public static ArrayList<Block> blockchain= new ArrayList<Block>();
	
	public static int difficulty = 6;
	
	public static Wallet walletA;
	
	public static Wallet walletB;
	
	
	
	
	public static void main(String[] args) throws Exception
	{		 
		
		
		
		
		
		
		//Setup Bouncey castle as a Security Provider
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
				
		
		
		
		// Creating 2 wallets
		walletA = new Wallet();
		walletB = new Wallet();
		
		
		
		
		//Testing public and private keys by printing them
		System.out.println("The Private key and the Public key are :");
		System.out.println(StringUtil.getStringFromKey(walletA.privateKey));
		System.out.println(StringUtil.getStringFromKey(walletA.publicKey));
		
		
		//Create a test transaction from walletA to walletB 
		Transaction transaction = new Transaction(walletA.publicKey, walletB.publicKey, 5, null);
		
		
		transaction.generateSignature(walletA.privateKey);   // generating the signature of transactions
		
		
		//Verifying that the signature works and verifying it from the public key
		System.out.println("Is signature verified");
		System.out.println(transaction.verifySignature());
				
		
		
		
		
		 /*
		 blockchain.add(new Block("This is the 1st block", "0"));
		 
		 System.out.println("Trying to mine block 1 :   .......");
		 
		 blockchain.get(0).mineBlock(difficulty);
		 
		 System.out.println("\n\n");
		 
		 
		 
		 blockchain.add(new Block("This is the 2nd block", blockchain.get(blockchain.size()-1).hash));
		 
		 System.out.println("Trying to mine block 2 :   .......");
		 
		 blockchain.get(1).mineBlock(difficulty);

		 System.out.println("\n\n");
		 
		 
		 blockchain.add(new Block("This is the 3rd block",  blockchain.get(blockchain.size()-1).hash));
		 
		 System.out.println("Trying to mine block 3 :   .......");
		 
		 blockchain.get(2).mineBlock(difficulty);

		 System.out.println("\n\n");
		

		 
		 
		 
		 
		 System.out.println("Is blockchain valid valid  :  " + isChainValid());
		 
		 
		 
		 
		 Gson gson = new GsonBuilder().setPrettyPrinting().create();
		 
		
		String blockchainJson = gson.toJson(blockchain);
		 
		 
		 
		
		System.out.println(blockchainJson);
		
		*/
	}
	
	
	
	public static boolean isChainValid()
	{
		
		Block previousBlock;
		Block currentBlock;
		
		
		for(int i=1;i<blockchain.size();i++)
		{
			
			previousBlock = blockchain.get(i-1);
			currentBlock = blockchain.get(i);
			
			if(!(currentBlock.hash.equals(currentBlock.calculateHash())))
			{
				System.out.println("The blockchain is invalid because Current Hashes are not equal");
				return false;
			}
			
			if(!(previousBlock.hash.equals(currentBlock.previousHash)))
			{
				System.out.println("The blockchain is invalid because Previous Hashes are not equal");
				return false;
			}
			
		}
		
		return true;
		
	}

}


