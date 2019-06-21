package com.plotalong.android.util;

import android.os.Environment;

/**
 * Created by kbhakade on 31/5/17.
 */

public class GlobalConstant {
        public static final String BASE_URL = "http://192.168.1.55:8000";
//    public static final String BASE_URL = "https://sandbox.plotalong.in";
    public static final String DIRECTION_URL = "https://maps.googleapis.com/";
    public static final String STRING_PlotAlong = " = PlotAlong";
    public static final String STRING_IsDeviceRegistered = "IsDeviceRegistered";
    public static final String STRING_success = "success";
    public static final String STRING_request = "request";
    public static final String STRING_register = "register";
    public static final String STRING_login = "login";
    public static final String STRING_forgotPass = "forgotPass";
    public static final String STRING_quicksync = "quicksync";
    public static final String STRING_fullsync = "fullsync";
    public static final String STRING_DEVICE_REGISTRATION_ALERT = "Device Registration";
    public static final String STRING_QUICK_SYNC_ALERT = "Quick Sync";
    public static final String STRING_FULL_SYNC_ALERT = "Full Sync";
    public static final String UserId = "UserId";
    public static final String DeveloperId = "DeveloperId";
    public static final String NEW_PLOT_ALONG_FOLDER_NAME = "New Plot Along";
    public static final String PROJECT_FOLDER_PATH = Environment.getExternalStorageDirectory() + "/" + NEW_PLOT_ALONG_FOLDER_NAME + "/";
    public static final String STRING_YES = "YES";
    public static final String STRING_NO = "NO";
    public static final String STRING_OK = "OK";
    public static final String EXIT_CONFIRMATION = "Exit Message";
    public static final String STRING_Session_Exit = "Session Exit";
    public static final String END_CUSTOMER_SESSION = "End Customer Session";
    public static final String po = "po";
    public static final String STRING_opps = "opps";
    public static final String STRING_hotLead = "hotLead";
    public static final String STRING_warmLead = "warmLead";
    public static final String CURRENT_USER = "Current Login User";
    public static final String FORGOT_PASSWORD = "Forgot Password";
    public static final String UNLIKE_PLOT = "Unlike Plot";
    public static final String String_NEW = "NEW";
    public static final String String_PROPOSAL = "PROPOSAL ";
    public static final String SelectedCustomerModel = "Selected Customer Model";
    public static final String SelectedProjectModel = "Selected Project Model";
    public static final String SelectedCustomerSessionModel = "Selected Customer Session Model";
    public static final String ArrayListOfPlotNo = "Array List Of Plot Nos";
    public static final String LastQuickSyncTime = "Last Quick Sync Time";
    public static final String STRING_LOGIN_ALERT = "Login Alert";
    public static final String PLOT_LIKE_STATUS = "Plot like status";
    public static final int VIDEO_FEEDBACK_REQUEST_CODE = 101;
    public static final int FEEDBACK_ACTIVITY_REQUEST_CODE = 102;
    public static final int FEEDBACK_ACTIVITY_RESULT_CODE = 102;
    public static final String ADD_PROPOSAL_CONFIRMATION = "Proposal Confirmation";
    public static final String INSERTED = "i";
    public static final String UPDATED = "u";
    public static final String EmptyDateForFullSync = "0000-00-00 00:00:00";
    public static final String ROLE_LICENSE = "Role License";

    public static final int LOCATION_UPDATE_TIME = 5000;
    public static final String NETWORK_NOT_AVAILABLE = "Network Info";
    public static final String PLOT_STATUS_AVAILABLE = "a";
    public static final String TRN_ACTIVITY_TYPE_LIKE = "like";
    public static final String FullSync = "Full Sync";
    public static final String ADDRESS_TYPE_PHAS = "phas";
}