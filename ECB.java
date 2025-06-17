import javax.crypto.spec.DESKeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;
import java.util.Base64;

public class ECB {
    public static void main(String[] args) {
        String key = "12345678";
        try {
            DESKeySpec keySpec = new DESKeySpec(key.getBytes());
            SecretKey secKey = SecretKeyFactory.getInstance("DES").generateSecret(keySpec);

            byte[] keyBytes = secKey.getEncoded();
            System.out.println("Key: " + Base64.getEncoder().encodeToString(keyBytes));
            System.out.println("Key size: " + keyBytes.length + " bytes");

            Cipher des = Cipher.getInstance("DES/ECB/PKCS5Padding");
            des.init(Cipher.ENCRYPT_MODE, secKey);

            String msg = "Hello DES!";
            System.out.println("Plain text: " + msg);

            byte[] encrypted = des.doFinal(msg.getBytes());
            System.out.println("Cipher text (Base64): " + Base64.getEncoder().encodeToString(encrypted));

            des.init(Cipher.DECRYPT_MODE, secKey);
            byte[] decrypted = des.doFinal(encrypted);
            System.out.println("Decrypted text: " + new String(decrypted));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
