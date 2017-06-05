/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coop.synthro.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author thorsten
 */
public class PasswordCryptor {

    private static final String strPassword = "OurSecretSynthro$$$Password";
    private static final String salt = "e8ffc7e56311679f12b6fc91aa77a5eb";
    private static final byte[] ivBytes = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

    public static String encrypt(String Data) {
        try {
            AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            KeySpec spec = new PBEKeySpec(strPassword.toCharArray(), salt.getBytes("UTF-8"), 65536, 128);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secret, ivSpec);

            return Base64.getEncoder().encodeToString(cipher.doFinal(Data.getBytes("UTF8")));
        } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException |InvalidKeySpecException| InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public static String decrypt(String encrypted) {
        try {
            byte[] encryptedData = Base64.getDecoder().decode(encrypted);
            AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            KeySpec spec = new PBEKeySpec(strPassword.toCharArray(), salt.getBytes("UTF-8"), 65536, 128);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secret, ivSpec);
            byte[] decrypted = cipher.doFinal(encryptedData);
            return new String(decrypted);
        } catch (UnsupportedEncodingException |InvalidKeySpecException| InvalidAlgorithmParameterException| NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            throw new RuntimeException(ex.getMessage());
        }

    }
    
    public static void main(String[] args) {
      if(args.length == 2) {
        if(args[0].equals("enc") && args[1] != null){
          System.out.println(PasswordCryptor.encrypt(args[1]));
        }else if(args[0].equals("dec") && args[1] != null){
          System.out.println(PasswordCryptor.decrypt(args[1]));
        }
      }
    }
}

