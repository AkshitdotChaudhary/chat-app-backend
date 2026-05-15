package com.example.demo.utils;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

@Component
public class AESEncryptionUtil
{
    private static final String secretEncrypt = "B4m1Vd48d4c42c4dfdc0e19cg9e2GjDn";
    private static Key generateKey( String secret )
    {
        byte[] decoded = Base64.decodeBase64( secret.getBytes() );
        return new SecretKeySpec( decoded, "AES" );
    }

    public static String encrypt( String plaintext )
        throws Exception
    {
        String secretKey = new String( Base64.encodeBase64( secretEncrypt.getBytes() ) );
        Key key = generateKey( secretKey );
        Cipher cipher = Cipher.getInstance( "AES" );
        cipher.init( 1, key );
        byte[] encryptedText = cipher.doFinal( plaintext.getBytes() );
        return Base64.encodeBase64String( encryptedText );
    }

    public static String decrypt( String encryptedText )
        throws Exception
    {
        String secretKey = new String( Base64.encodeBase64( secretEncrypt.getBytes() ) );
        Key key = generateKey( secretKey );
        Cipher cipher = Cipher.getInstance( "AES" );
        cipher.init( Cipher.DECRYPT_MODE, key );
        byte[] decodedEncryptedText = Base64.decodeBase64( encryptedText );
        byte[] decryptedBytes = cipher.doFinal( decodedEncryptedText );
        return new String( decryptedBytes );
    }

    public static void main( String[] args )
    {
        try
        {
            System.out.println( decrypt( "" ) );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }
}
