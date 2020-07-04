package com.pensionrun.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author chosh
 * 비밀번호 암호화에 사용되고 있어유
 */
public class EncryptionSha256Util {
	
	public static String getEncSHA256(String txt) {
	    StringBuffer sbuf = new StringBuffer();
	     
	    MessageDigest mDigest;
		try {
			mDigest = MessageDigest.getInstance("SHA-256");
			mDigest.update(txt.getBytes());
		     
		    byte[] msgStr = mDigest.digest() ;
		     
		    for(int i=0; i < msgStr.length; i++){
		        byte tmpStrByte = msgStr[i];
		        String tmpEncTxt = Integer.toString((tmpStrByte & 0xff) + 0x100, 16).substring(1);	         
		        sbuf.append(tmpEncTxt) ;
		    }	     		    
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sbuf.toString();	    
	}
}
