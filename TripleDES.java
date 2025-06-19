import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class TripleDES {
     public static void main(String[] args) {
          
        String key = "123456789012345678901234"; // Must be 24 characters
        String ivStr = "12345678"; // 8-byte IV for DES

        try {
            // Prepare key and IV
            DESedeKeySpec keySpec = new DESedeKeySpec(key.getBytes());
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
            SecretKey secretKey = factory.generateSecret(keySpec);
            IvParameterSpec ivSpec = new IvParameterSpec(ivStr.getBytes());

            // Message
            String msg = "ConfidentialData"; // can be any length with PKCS5Padding
            System.out.println("Plain text: " + msg);

            // Encrypt
            Cipher encryptCipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
            byte[] encrypted = encryptCipher.doFinal(msg.getBytes());
            String cipherText = Base64.getEncoder().encodeToString(encrypted);
            System.out.println("Cipher text: " + cipherText);

            // Decrypt
            Cipher decryptCipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            decryptCipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
            byte[] decrypted = decryptCipher.doFinal(Base64.getDecoder().decode(cipherText));
            System.out.println("Decrypted text: " + new String(decrypted));

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
     }
}
