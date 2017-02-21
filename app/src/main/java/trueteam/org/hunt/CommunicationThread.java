package trueteam.org.hunt;

import android.os.Handler;

import java.io.IOException;

import trueteam.org.hunt.messaging.Message;
import trueteam.org.hunt.messaging.MessageFields;
import trueteam.org.hunt.messaging.Messenger;
import trueteam.org.hunt.messaging.Target;
import trueteam.org.hunt.messaging.Type;

import static trueteam.org.hunt.messaging.Target.ENCOUNTER;

/**
 * Created by Slon on 21.02.2017.
 */
public class CommunicationThread extends Thread {

    private Handler trackingHandler;
    private Handler encounterHandler;
    private Handler catchingHandler;
    private Handler resultsHandler;



    @Override
    public void run() {
        // 1. take a messenger
        // 2. get message
        // 3. if target is location
        //       if request
        //          send it's location
        //       if response
        //          apply locations
        // 4. if target is encounter
        //       if start_encounter
        //          talk to encounterHandler
        //       if catching
        //          talk to catchingHandler
        //       if end_encounter
        //          talk to resultsHandler
        Messenger messenger = Core.getInstance().getMessenger();
        while (true) {
            try {
                Message message = messenger.receive(0);
                String target = message.getValue(MessageFields.TARGET);
                String type = message.getValue(MessageFields.TYPE);

                if(target.equals(Target.LOCATION.getName())) {
                    if(type.equals(Type.REQUEST.getName())){
                        // getLocation ?
                        // Core.getLocation()
                        // messenger.send(its location)
                    } else if(type.equals(Type.RESPONSE.getName())){
                        // make locations from field location
                        // handler.let map manager apply ->
                        //      MapManager.apply()
                    }
                } else if(target.equals(Target.ENCOUNTER.getName())) {
                    String messageField = message.getValue(MessageFields.)
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
