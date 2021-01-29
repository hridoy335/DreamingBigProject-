package org.sr.project.core.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class DBPasswordProtectorUtil {

    //region PRIVATE FIELDS
    private static final String ALGORITHM ="AES";
    private static final byte[] keyValue = new byte[]{'H','A','S','S','A','N','S','A','K','I','B','R','U','P','A','M'};

    private static Key generateKey(){
        return new SecretKeySpec(keyValue,ALGORITHM);
    }
    //endregion

    //region PUBLIC METHODS
    public static String encrypt(String value){
        try{
            Key key = generateKey();
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE,key);
            byte[] byteValue = cipher.doFinal(value.getBytes());
            return new BASE64Encoder().encode(byteValue);
        }catch (Exception ex){
            return null;
        }
    }

    public static String decrypt(String value){
        try{
            Key key = generateKey();
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE,key);
            byte[] byteValue = new BASE64Decoder().decodeBuffer(value);
            byteValue = cipher.doFinal(byteValue);
            return new String(byteValue);
        }catch(Exception ex){
            return null;
        }
    }
    //endregion
}
