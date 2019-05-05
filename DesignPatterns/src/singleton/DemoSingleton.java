package singleton;

import java.util.Calendar;

/**
 *
 * @author Pranav
 * @since 2019-05-05
 */
public class DemoSingleton {

    public static void main(String[] args) {
        SingletonClass singleton1 = SingletonClass.getSingletonClass();
        SingletonClass singleton2 = SingletonClass.getSingletonClass();
        System.out.println("Are the objects Same? \n" + singleton1.equals(singleton2));
    }

}
