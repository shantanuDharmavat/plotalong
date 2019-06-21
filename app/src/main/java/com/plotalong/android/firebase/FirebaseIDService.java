package com.plotalong.android.firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import com.plotalong.android.requestResponseManager.FcmTokenRegistration;
import com.plotalong.android.util.SharedPreference;

/**
 * Created by shantanu on 18/5/17.
 */

public class FirebaseIDService extends FirebaseInstanceIdService {
    private static final String TAG = "FirebaseIDService";

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.e(TAG, "Refreshed token: " + refreshedToken);
        if (refreshedToken != null) {
            saveFirebaseId(refreshedToken);
            // TODO: Implement this method to send any registration to your app's servers.
//            sendRegistrationToServer(refreshedToken);
        }
    }

    /**
     * Persist token to third-party servers.
     * <p>
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        // Add custom implementation, as needed.
        FcmTokenRegistration fcmTokenRegistration = new FcmTokenRegistration(token);
        fcmTokenRegistration.tokenRegistration();
    }

    private void saveFirebaseId(String token) {
        SharedPreference.getInstance(getApplicationContext()).saveDeviceToken(token);
    }
}

