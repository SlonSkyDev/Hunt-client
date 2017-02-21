package trueteam.org.hunt;

/**
 * Created by Slon on 21.02.2017.
 */
public class Core {

    private static Core instance = new Core();

    public static Core getInstance() {
        return instance;
    }

    private Core() {
        // ...
    }

    public boolean requestRegister(String login, String password) {

        // ...
        return false;
    }

    interface ResultCallback {
        void call(boolean success);
    }
}
