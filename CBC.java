import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.SecretKey;

import java.util.Base64;

import javax.crypto.Cipher;

public class CBC {
     public static void main(String[] args) {
          String key = "123456789";
          try{
               DESKeySpec key_spec = new DESKeySpec(key.getBytes());
               SecretKey sec_key = SecretKeyFactory.getInstance("DES").generateSecret(key_spec);

               // KeyGenerator key_gen = KeyGenerator. getInstance ("DES") ;
               // SecretKey sec_key = key_gen.generateKey();
               
               byte[] key_byte = sec_key.getEncoded();
               String str_key = new String(key_byte);
               System.out.println("Key : " + str_key);
               System.out.println("Key size : " + str_key.length());

               String iv_str= "AAAAAAAA";
               IvParameterSpec iv_spec= new IvParameterSpec(iv_str.getBytes());

               Cipher des = Cipher.getInstance("DES/CBC/NOPadding");
               des.init(Cipher.ENCRYPT_MODE, sec_key, iv_spec);

               String msg = "DearDear";
               System.out.println("Plain text: " + msg);
               byte[] encrypted = des.doFinal(msg.getBytes());
               System.out.println("Cipher text: " + Base64.getEncoder().encodeToString(encrypted));

               des.init(Cipher.DECRYPT_MODE, sec_key, iv_spec);
               byte[] decrypted = des.doFinal(encrypted);
               System.out.println("Decypted text: " + new String(decrypted));
          } catch(Exception e) {
               System.out.println(e.getMessage());
          }
     }
}
