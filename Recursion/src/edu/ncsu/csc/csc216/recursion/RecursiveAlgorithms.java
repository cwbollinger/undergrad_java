package edu.ncsu.csc.csc216.recursion;

import java.io.File;

public class RecursiveAlgorithms {
	
	public static void main(String [] args) {
		File f = new File (".");
		System.out.println(f.getAbsolutePath());
		
		RecursiveAlgorithms r = new RecursiveAlgorithms();
		r.crawl(f);
	}
	
	public void crawl(File f) {
	}
	
	private void crawl(File f, String indent) {
	}

	public String generateBinary(int n) {
		if(n == 0) {
			return "0";
		} else {
			return generateBinary(n/2) + String.valueOf(n%2);
		}
	}	
	
	public int pow(int base, int exp) {
		return 0;
	}
		
	public boolean isPalindrome(String s) {
		return false;
	}

}
