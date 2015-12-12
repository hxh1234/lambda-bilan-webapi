package com.lambda.bilan.web.helpers;

import java.util.Random;

public class RandomGenerator {

	static final String CHAR_LIST = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static Random rnd = new Random();

	public static String randomString(){
	   StringBuilder sb = new StringBuilder(6);
	   for( int i = 0; i < 6; i++ ) 
	      sb.append( CHAR_LIST.charAt( rnd.nextInt(CHAR_LIST.length()) ) );
	   return sb.toString();
	}

}
