package hashingAlgos;

import java.util.Date;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Pranav Kumar
 * @date 2019-10-06
 */
public class SHA256Impl extends BaseImpl implements Runnable {

    public SHA256Impl(String password) {
        super(password);
    }

    public SHA256Impl(String password, String salt) {
        super(password, salt);
    }

    @Override
    public void run() {
        Date start = new Date();
        DigestUtils.sha256Hex(password);
        Date end = new Date();
        System.out.println(" Time taken for SHA256 based hashing including salt: " + (end.getTime() - start.getTime()) + " for thread: " + Thread.currentThread().getName());
    }

}
