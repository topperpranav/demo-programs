package singleton;

/**
 * Demonstrates a singleton class layout
 *
 * @author Pranav
 * @since 2019-05-05
 */
public class SingletonClass {

    private static SingletonClass singletonObj;
    private String name;

    /**
     * Make the constructor private so that no class is able to call the default
     * constructor.
     */
    private SingletonClass() {
    }

    /**
     * If the object is not already created, create the object else give the
     * original object.
     *
     * @return An instance of this class
     */
    public static SingletonClass getSingletonClass() {
        if (singletonObj == null) {
            singletonObj = new SingletonClass();
        }
        singletonObj.setName("Name-1");
        return singletonObj;
    }

    /**
     * Used to set tha name of the singleton object. Can be used by this class
     * only.
     *
     * @param name
     */
    private void setName(String name) {
        this.name = name;
    }

    /**
     * Getting of the name is publicly exposed
     *
     * @return
     */
    public String getName() {
        return singletonObj.name;
    }
}
