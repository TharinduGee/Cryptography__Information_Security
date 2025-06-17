import java.math.BigInteger;

public class Basics {

     public static boolean isPrime(int n) {
          if (n <= 1) return false;
          if (n == 2 || n == 3) return true;
          if (n % 2 == 0 || n % 3 == 0) return false;
  
          for (int i = 5; i * i <= n; i += 6) {
              if (n % i == 0 || n % (i + 2) == 0)
                  return false;
          }
          return true;
     }

     public static boolean isCoPrime(BigInteger x, BigInteger y) {
          BigInteger c = x.gcd(y);
          if (c.intValue() == 1) {
               return true;
          } else {
               return false;
          }
     }

     public static int totient(BigInteger x){
          int val = 0;
          for(int i = 1; i < x.intValue(); i++){
               if(isCoPrime(new BigInteger(String.valueOf(i)), x)){
                    val+=1;
               }
          }
          System.out.println("Totient value is " + val);
          return val;
     }

     // gcd(a,b)=gcd(b,amodb)
     public static int gcd_euclidian(BigInteger a, BigInteger b) {
          while (b.byteValue() != 0) {
               BigInteger temp = b;
               b = a.mod(b);
               a = temp;
          }
          return Math.abs(a.byteValue());
     }

     public static void main(String[] args){
          int result = gcd_euclidian(BigInteger.valueOf(30), BigInteger.valueOf(20));
          System.out.println("Greatest common divisor is: " + result);
     }
}