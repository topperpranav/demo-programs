package hashingAlgos;

/**
 *
 * @author Pranav Kumar
 * @since 2019-10-06
 */
public class BaseImpl {

    String password;
    String salt;

    public BaseImpl() {
    }

    public BaseImpl(String password) {
        this.password = password;
        salt = null;
    }

    public BaseImpl(String password, String salt) {
        this.password = password;
        this.salt = salt;
    }
}
