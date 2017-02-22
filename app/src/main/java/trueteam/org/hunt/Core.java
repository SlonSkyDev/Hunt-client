package trueteam.org.hunt;

import android.content.pm.PackageManager;
import android.location.*;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.net.Socket;

import trueteam.org.hunt.messaging.ClientMessageFactory;
import trueteam.org.hunt.messaging.Message;
import trueteam.org.hunt.messaging.MessageFields;
import trueteam.org.hunt.messaging.Messenger;

/**
 * Created by Slon on 21.02.2017.
 */
public class Core {

    private static final String HOST = "192.168.1.103";
    private static final int PORT = 1234;
    public static final int TIMEOUT = 15000;

    private static Core instance = new Core();

    private Messenger messenger;
    private GoogleApiClient googleApiClient;
    private MapsActivity mapsActivity;
    private Location lastLocation = null;

    public void setMapsActivity(MapsActivity mapsActivity) {
        this.mapsActivity = mapsActivity;
    }

    public static Core getInstance() {
        return instance;
    }

    private Core() {
        try {
            Socket socket = new Socket(HOST, PORT);
            messenger = new Messenger(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public boolean requestRegister(String login, String password) {
        try {
            messenger.send(ClientMessageFactory.makeRegisterRequest(login, password));

            Message response = messenger.receive(TIMEOUT);
            if (response.getValue(MessageFields.STATUS) != null
                    && response.getValue(MessageFields.STATUS).equals("success")) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean requestAuthorization(String login, String password, Role role) {
        try {
            messenger.send(ClientMessageFactory.makeAuthorisationRequest(login, password, role));

            Message response = messenger.receive(TIMEOUT);
            if (response.getValue(MessageFields.STATUS) != null
                    && response.getValue(MessageFields.STATUS).equals("success")) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void sendLocation() {

//        if (ActivityCompat.checkSelfPermission(mapsActivity, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mapsActivity, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        android.location.Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
//        if(location != null) {
//
//
//            Log.i("LOC", String.valueOf(lastLocation.getLatitude()+lastLocation.getLongitude()));
//        }
        try {
            messenger.send(ClientMessageFactory.makeLocationResponse(lastLocation));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Messenger getMessenger() {
        return messenger;
    }

    public void setGoogleApiClient(GoogleApiClient googleApiClient) {
        this.googleApiClient = googleApiClient;
    }

    public void setLastLocation(android.location.Location lastLocation) {
        this.lastLocation = new Location(lastLocation.getLatitude(), lastLocation.getLongitude());
    }
}
