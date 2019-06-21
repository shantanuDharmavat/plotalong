package com.plotalong.android.firebase;

/**
 * Created by shantanu on 17/5/17.
 */

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;
import com.plotalong.android.activity.MainActivity;
import com.plotalong.android.util.GlobalConstant;


/**
 * Created by Belal on 5/27/2016.
 */

public class FirebaseMessageService extends com.google.firebase.messaging.FirebaseMessagingService {
    private static final String TAG = FirebaseMessageService.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    String message, title;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Data Payload body: " + remoteMessage.getNotification().getBody().toString());
            Log.e(TAG, "Data Payload title : " + remoteMessage.getNotification().getTitle().toString());
            Log.e(TAG, "Data Payload tag : " + remoteMessage.getNotification().getIcon().toString());
//            Log.e(TAG, "Data Payload colour : " + remoteMessage.getNotification().getColor().toString());
            Log.e(TAG, "Data Payload Data" + remoteMessage.getData().toString());
            try {
//                JSONArray json = //new JSONArray(remoteMessage.getData());
//                JSONObject jsonObj = new JSONObject(remoteMessage.getData().get("")).getJSONObject(0);
                sendPushNotification(remoteMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /*private void sendPushNotification(JSONObject json) {
        //optionally we can display the json into log
        Log.e(TAG, "Notification JSON " + json.toString());
        try {
            //getting the json data

            String imageUrl = "null";
            title = json.getString("title");
            message = json.getString("message");

            NotificationManaging mNotificationManager = new NotificationManaging(getApplicationContext());

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);

            Intent newIntent = new Intent();
            newIntent.setAction("notification");
            newIntent.putExtra("incident",true);
            LocalBroadcastManager.getInstance(getBaseContext()).sendBroadcast(newIntent);

            //if there is no image
            if (imageUrl.equals("null")) {//"null"
                //displaying small notification
                mNotificationManager.showSmallNotification(title, message, intent);
            } else {
                //if there is an image
                //displaying a big notification
                mNotificationManager.showBigNotification(title, message, imageUrl, intent);
            }
        } catch (JSONException e) {
            Log.e(TAG, "Json Exception: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
            e.printStackTrace();
    }*/

    private void sendPushNotification(RemoteMessage json) {
        //optionally we can display the json into log
        Log.e(TAG, "Notification JSON " + json.toString());
        try {
            //getting the json data

            String imageUrl = "null";
            title = json.getNotification().getTitle();
            message = json.getNotification().getBody();

            NotificationManaging mNotificationManager = new NotificationManaging(getApplicationContext());

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);

            Intent newIntent = new Intent();
            newIntent.setAction("notification");
            newIntent.putExtra("incident", true);
            LocalBroadcastManager.getInstance(getBaseContext()).sendBroadcast(newIntent);

            //if there is no image
            if (imageUrl.equals("null")) {//"null"
                //displaying small notification
                mNotificationManager.showSmallNotification(title, message, intent);
            } else {
                //if there is an image
                //displaying a big notification
                mNotificationManager.showBigNotification(title, message, imageUrl, intent);
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /*private void sendPushNotification(JSONArray json) {
        //optionally we can display the json into log
        Log.e(TAG, "Notification JSON " + json.toString());
        try {
            //getting the json data

            String imageUrl = "null";
            title = json.getString(1);//("title");
            message = json.getString(2);//("message");

            NotificationManaging mNotificationManager = new NotificationManaging(getApplicationContext());

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);

            Intent newIntent = new Intent();
            newIntent.setAction("notification");
            newIntent.putExtra("incident",true);
            LocalBroadcastManager.getInstance(getBaseContext()).sendBroadcast(newIntent);

            //if there is no image
            if (imageUrl.equals("null")) {//"null"
                //displaying small notification
                mNotificationManager.showSmallNotification(title, message, intent);
            } else {
                //if there is an image
                //displaying a big notification
                mNotificationManager.showBigNotification(title, message, imageUrl, intent);
            }
        } catch (JSONException e) {
            Log.e(TAG, "Json Exception: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }*/
}
