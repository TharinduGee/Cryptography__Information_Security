public import java.security.*;
import java.util.Base64;

import javax.crypto.Cipher;

public class RSASignAndEncrypt {
    public static void main(String[] args) {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);

            KeyPair senderKeyPair = keyGen.generateKeyPair();
            KeyPair receiverKeyPair = keyGen.generateKeyPair();

            PrivateKey senderPrivateKey = senderKeyPair.getPrivate();
            PublicKey senderPublicKey = senderKeyPair.getPublic();
            PrivateKey receiverPrivateKey = receiverKeyPair.getPrivate();
            PublicKey receiverPublicKey = receiverKeyPair.getPublic();

            String message = "Confidential Message";
            System.out.println("Original Message: " + message);

            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(senderPrivateKey);
            signature.update(message.getBytes());
            byte[] digitalSignature = signature.sign();

            System.out.println("Signature (Base64): " + Base64.getEncoder().encodeToString(digitalSignature));

            // Step 3: Combine message + signature
            byte[] messageBytes = message.getBytes();
            byte[] combined = new byte[messageBytes.length + digitalSignature.length];
            System.arraycopy(messageBytes, 0, combined, 0, messageBytes.length);
            System.arraycopy(digitalSignature, 0, combined, messageBytes.length, digitalSignature.length);

            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, receiverPublicKey);
            byte[] encryptedData = cipher.doFinal(combined);

            System.out.println("Encrypted Data (Base64): " + Base64.getEncoder().encodeToString(encryptedData));

            cipher.init(Cipher.DECRYPT_MODE, receiverPrivateKey);
            byte[] decryptedCombined = cipher.doFinal(encryptedData);

            //Separate message and signature
            int signatureLength = 256;
            int messageLength = decryptedCombined.length - signatureLength;
            byte[] decryptedMessageBytes = new byte[messageLength];
            byte[] decryptedSignature = new byte[signatureLength];

            System.arraycopy(decryptedCombined, 0, decryptedMessageBytes, 0, messageLength);
            System.arraycopy(decryptedCombined, messageLength, decryptedSignature, 0, signatureLength);

            String decryptedMessage = new String(decryptedMessageBytes);
            System.out.println("Decrypted Message: " + decryptedMessage);

            //Verify signature with sender's public key
            Signature verifySig = Signature.getInstance("SHA256withRSA");
            verifySig.initVerify(senderPublicKey);
            verifySig.update(decryptedMessageBytes);
            boolean isVerified = verifySig.verify(decryptedSignature);

            System.out.println("Signature Verified: " + isVerified);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
 {
     
}
