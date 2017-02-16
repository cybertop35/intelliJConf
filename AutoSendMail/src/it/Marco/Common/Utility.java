package it.Marco.Common;

import java.io.UnsupportedEncodingException;
import java.util.Hashtable;

public class Utility {
	
	/**
	 * Metodo per cifrare una Stringa
	 * 
	 * prendo 
	 * 
	 * @param s
	 * @return cifrata
	 */
	
	public String cifraStringa(String s) throws UnsupportedEncodingException{
		String cifrata ="";
		if (s != null && !s.equals("")){
			for (int i = 0; i<s.length();i++){
				int let = s.charAt(i);
				if(i%2==0){
					let=let+10;
					cifrata=cifrata+(char)let;
				}
				else{
					let=let+15;
					cifrata=cifrata+(char)let;
				}
				let=let-12;
			
				char a= (char)let;
				cifrata=cifrata+a;
				System.out.println("Char "+a);
				
				
			}
			
			
			System.out.println("Cifrata "+cifrata);
		
		}
		
		
		return cifrata;
		
	}
	public String decifraStringa(String s){
		String decifrata ="";
		if (s != null && !s.equals("")){
			for (int i = 1; i<s.length();i++){
				int let = s.charAt(i);
				if(i%2==0){
					let=let-10;
					i++;
					
				}
				else{
					let=let-15;
					i++;
				}
				let=let+12;
				
				
				char a= (char)let;
				decifrata=decifrata+a;
				//System.out.println("Char "+a);
				
				
			}
			
			
			System.out.println("deCifrata "+decifrata);
		
		}
		
		
		return decifrata;
		
	}

	public static void main(String Args[])
	{
		Utility u = new Utility();
		try {
			u.decifraStringa(u.cifraStringa("ciao"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

