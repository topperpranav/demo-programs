
import hashingAlgos.BCryptImpl;
import hashingAlgos.PBKDF2Impl;
import hashingAlgos.SHA256Impl;
import java.util.Date;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author Pranav
 */
public class CompareAlgorithms {
//
//    static BCryptImpl b1 = new BCryptImpl("iuttiininfkjdkjf1212u1!@");
//    static Thread tb1 = new Thread(b1);
//    static BCryptImpl b2 = new BCryptImpl("helloworld");
//    static Thread tb2 = new Thread(b2);
//    static BCryptImpl b3 = new BCryptImpl("die in this junction");
//    static Thread tb3 = new Thread(b3);
//    static BCryptImpl b4 = new BCryptImpl("checkekjdkj!2^%@()(!");
//    static Thread tb4 = new Thread(b4);

    public static void main(String Args[]) {
//        tb1.start();
//        tb2.start();
//        tb3.start();
//        tb4.start();

//BCRYpt
//        for (int i = 0; i < 5; i++) {
//            BCryptImpl bi = new BCryptImpl("checkekjdkj!2^%@()(!");
//            Thread tbi = new Thread(bi);
//            tbi.start();
//        }
//SHA-256
//        for (int i = 0; i <=5000; i++) {
//            Thread tbi = new Thread(new SHA256Impl("checkekjdkj!2^%@()("+i));
//            tbi.start();
//        }
        //PBKDF
        for (int i = 0; i <= 5000; i++) {
            Thread tbi = new Thread(new PBKDF2Impl(16 ,("checkekjdkj!2^%@()(" + i)));
            tbi.start();
        }

//        String salt = BCrypt.gensalt();
//
//        System.out.println(" Salt is: " + salt);
//
//        String password = "!%rfathetrwiuagh^X";
//        Date start = new Date();
//        String hashedPassword = BCrypt.hashpw(password, salt);
//        System.out.println(" Hashed password is: " + hashedPassword);
//        Date end = new Date();
//        System.out.println(" Time taken: " + (end.getTime() - start.getTime()));
//
//        System.out.println(" " + BCrypt.checkpw(password, hashedPassword));
//
//        start = new Date();
//        String sha256HashedPw = DigestUtils.sha256Hex(DigestUtils.sha256Hex(password) + salt);
//        end = new Date();
//
//        System.out.println(" time in ms " + (end.getTime() - start.getTime()));
    }
}
