package trueteam.org.hunt.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;

import trueteam.org.hunt.Location;
import trueteam.org.hunt.Role;

/**
 * Created by Slon on 22.02.2017.
 */
public class ClientMessageFactory {
    public static Message makeRegisterRequest(String login, String password) {
        Message message = new Message(Type.REQUEST, Target.REGISTRATION);
        try {
            message.addExtraField(MessageFields.LOGIN, login);
            message.addExtraField(MessageFields.PASSWORD, password);
        } catch (MessageFormatException e) {
            e.printStackTrace();
        }
        return message;
    }

    public static Message makeAuthorisationRequest(String login, String password, Role role) {
        Message message = new Message(Type.REQUEST, Target.AUTHORIZATION);
        try {
            message.addExtraField(MessageFields.LOGIN, login);
            message.addExtraField(MessageFields.PASSWORD, password);
            message.addExtraField(MessageFields.ROLE, role.getName());
        } catch (MessageFormatException e) {
            e.printStackTrace();
        }
        return message;
    }

    public static Message makeLocationResponse(Location location) {
        Message message = new Message(Type.RESPONSE, Target.LOCATION);
        try {
            String locJSON = new ObjectMapper().writeValueAsString(location);
            message.addExtraField(MessageFields.LOCATION, locJSON);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MessageFormatException e) {
            e.printStackTrace();
        }
        return message;
    }
}
