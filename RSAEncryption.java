import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;
import java.util.Base64;

public class RSAEncryption {
    public static void main(String[] args) {
        try {
            // Generate RSA key pair (2048 bits)
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            KeyPair keyPair = keyGen.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            String message = "TopSecretMessage";
            System.out.println("Original Message: " + message);

            // Encrypt with Public Key
            Cipher encryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptedBytes = encryptCipher.doFinal(message.getBytes());
            String encryptedBase64 = Base64.getEncoder().encodeToString(encryptedBytes);
            System.out.println("Encrypted (Base64): " + encryptedBase64);

            // Decrypt with Private Key
            Cipher decryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decryptedBytes = decryptCipher.doFinal(Base64.getDecoder().decode(encryptedBase64));
            String decryptedMessage = new String(decryptedBytes);
            System.out.println("Decrypted Message: " + decryptedMessage);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
