package hashingAlgos;

import java.util.Date;

/**
 *
 * @author Pranav
 * @since 2019-09-09
 */
public class BCryptImpl extends BaseImpl implements Runnable {

    public BCryptImpl(String password) {
        super(password);
    }

    @Override
    public void run() {

        Date start = new Date();
        String salt = org.springframework.security.crypto.bcrypt.BCrypt.gensalt();
        //System.out.println(" Salt is: " + salt);
        Date afterSalt = new Date();

        String hashedPassword = org.springframework.security.crypto.bcrypt.BCrypt.hashpw(password, salt);
        //System.out.println(" Hashed password is: " + hashedPassword);
        Date end = new Date();
        //System.out.println(" Time taken for hashing excluding salt: " + (end.getTime() - afterSalt.getTime()) + " for thread: " + Thread.currentThread().getName());
        System.out.println(" Time taken for BCRYPT hashing including salt: " + (end.getTime() - start.getTime()) + " for thread: " + Thread.currentThread().getName());

       // System.out.println("Do the hashed password and password match? " + org.springframework.security.crypto.bcrypt.BCrypt.checkpw(password, hashedPassword) + " for thread: " + Thread.currentThread().getName());
    }

}
