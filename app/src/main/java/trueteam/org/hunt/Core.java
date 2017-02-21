package trueteam.org.hunt;

import trueteam.org.hunt.messaging.Messenger;

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

    public Messenger getMessenger() {

        return null;
    }

    interface ResultCallback {
        void call(boolean success);
    }
}
