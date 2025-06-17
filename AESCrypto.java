import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;
import java.util.Base64;

public class AESCrypto {
    public static void main(String[] args) {
        String key = "1234567812345678";
        try {
            SecretKey secKey = new SecretKeySpec(key.getBytes(), "AES");

            byte[] keyBytes = secKey.getEncoded();
            System.out.println("Key: " + Base64.getEncoder().encodeToString(keyBytes));
            System.out.println("Key size: " + keyBytes.length + " bytes");

            String iv_str= "AAAAAAAAAAAAAAAA";
            IvParameterSpec iv_spec= new IvParameterSpec(iv_str.getBytes());
            Cipher aes = Cipher.getInstance("AES/CBC/PKCS5Padding");
            aes.init(Cipher.ENCRYPT_MODE, secKey, iv_spec);

            String msg = "Hello AES!";
            System.out.println("Plain text: " + msg);

            byte[] encrypted = aes.doFinal(msg.getBytes());
            System.out.println("Cipher text (Base64): " + Base64.getEncoder().encodeToString(encrypted));

            aes.init(Cipher.DECRYPT_MODE, secKey, iv_spec);
            byte[] decrypted = aes.doFinal(encrypted);
            System.out.println("Decrypted text: " + new String(decrypted));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
