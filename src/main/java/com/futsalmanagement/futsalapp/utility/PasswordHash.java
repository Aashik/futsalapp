package com.futsalmanagement.futsalapp.utility;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHash {

    public static String getpasswordhash(String password, String salt){
        String generatepassword = null;
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<bytes.length ; i++){
                sb.append(Integer.toString((bytes[i] & 0Xff) + 0x100,16).substring(1));
            }
            generatepassword = sb.toString();
        }catch(NoSuchAlgorithmException  e){
            System.out.println(e.getMessage());
        }

        return  generatepassword;
    }

}
