package com.plotalong.android.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.ViewSwitcher;

import com.github.barteksc.pdfviewer.PDFView;
import com.gjiazhe.panoramaimageview.GyroscopeObserver;
import com.gjiazhe.panoramaimageview.PanoramaImageView;
import com.google.gson.Gson;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;
import com.lespinside.simplepanorama.view.SphericalView;
import com.plotalong.android.R;
import com.plotalong.android.adapter.AdapterForLikeDislikePlot;
import com.plotalong.android.adapter.CustomerAdapter;
import com.plotalong.android.adapter.DirectionListAdapter;
import com.plotalong.android.adapter.DrawerItemCustomAdapter;
import com.plotalong.android.adapter.HorizontalAdapter;
import com.plotalong.android.adapter.NearbyListAdapter;
import com.plotalong.android.adapter.PhaseListAdapter;
import com.plotalong.android.adapter.ProposalAdapter;
import com.plotalong.android.adapter.SpinnerAdapterForPlot;
import com.plotalong.android.dataSource.CfgAddressDataSource;
import com.plotalong.android.dataSource.CfgContentDataSource;
import com.plotalong.android.dataSource.CfgCustomerGroupDataSource;
import com.plotalong.android.dataSource.MstCustomerDataSource;
import com.plotalong.android.dataSource.MstPhaseDataSource;
import com.plotalong.android.dataSource.MstPlotDataSource;
import com.plotalong.android.dataSource.MstProposalHeaderDataSource;
import com.plotalong.android.dataSource.MstUserDetailsDataSource;
import com.plotalong.android.dataSource.TrnActivityDataSource;
import com.plotalong.android.dataSource.TrnSessionDataSource;
import com.plotalong.android.databinding.LayoutForgotPasswordBinding;
import com.plotalong.android.databinding.LayoutUserAuthBinding;
import com.plotalong.android.databinding.LayoutUserLoginBinding;
import com.plotalong.android.dialogManager.AlertManager;
import com.plotalong.android.dialogManager.DialogSettings;
import com.plotalong.android.fragment.MapFragment;
import com.plotalong.android.helper.ConnectivityReceiver;
import com.plotalong.android.helper.GPSBindService;
import com.plotalong.android.helper.GPSUnbindService;
import com.plotalong.android.listener.CustomerRecyclerListener;
import com.plotalong.android.listener.CustomerRecyclerListenerForLikePlot;
import com.plotalong.android.listener.DeviceRegistrationListener;
import com.plotalong.android.listener.DialogBackListener;
import com.plotalong.android.listener.ForgotPasswordListener;
import com.plotalong.android.listener.FullSyncListener;
import com.plotalong.android.listener.ProjectExpandOptionListener;
import com.plotalong.android.listener.ProposalRecyclerListener;
import com.plotalong.android.listener.QuickSyncListener;
import com.plotalong.android.listener.UnzipListener;
import com.plotalong.android.listener.UserLoginListener;
import com.plotalong.android.listener.YesNoAlertListener;
import com.plotalong.android.mediaDownloadManager.DownloadAnyFile;
import com.plotalong.android.mediaDownloadManager.DownloadZipAndUnzip;
import com.plotalong.android.model.commonModel.DataModel;
import com.plotalong.android.model.commonModel.DirectionFromDatabaseModel;
import com.plotalong.android.model.commonModel.UserAuthModel;
import com.plotalong.android.model.deviceRegistrationModel.UserModel;
import com.plotalong.android.model.geoGalleryModel.ContentDataModel;
import com.plotalong.android.model.loginModel.LoginModel;
import com.plotalong.android.model.nearbyModel.CategoryResult;
import com.plotalong.android.model.nearbyModel.Nearby;
import com.plotalong.android.model.quickSyncModel.ActivityDataModel;
import com.plotalong.android.model.quickSyncModel.AddressDataModel;
import com.plotalong.android.model.quickSyncModel.CustomerDataModel;
import com.plotalong.android.model.quickSyncModel.CustomerGroupDataModel;
import com.plotalong.android.model.quickSyncModel.PhaseDataModel;
import com.plotalong.android.model.quickSyncModel.PlotModel;
import com.plotalong.android.model.quickSyncModel.ProposalHeaderDataModel;
import com.plotalong.android.model.quickSyncModel.SessionDataModel;
import com.plotalong.android.requestResponseManager.DeviceRegistration;
import com.plotalong.android.requestResponseManager.ForgotPassword;
import com.plotalong.android.requestResponseManager.FullSync;
import com.plotalong.android.requestResponseManager.UserLogin;
import com.plotalong.android.util.DatabaseBackupRestoreManager;
import com.plotalong.android.util.DateUtil;
import com.plotalong.android.util.FileHandlingUtil;
import com.plotalong.android.util.GlobalConstant;
import com.plotalong.android.util.MCrypt;
import com.plotalong.android.util.MarshMallowPermission;
import com.plotalong.android.util.MarshmallowIntentId;
import com.plotalong.android.util.NetworkUtil;
import com.plotalong.android.util.SharedPreference;
import com.plotalong.android.util.Singleton;
import com.plotalong.android.util.Utility;
import com.squareup.picasso.Picasso;

import net.cachapa.expandablelayout.ExpandableLayout;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;


/**
 * Created by shantanu on 5/6/17.
 */

public class MainActivity extends FragmentActivity implements ListView.OnItemClickListener,
        DeviceRegistrationListener, YesNoAlertListener, QuickSyncListener, UserLoginListener,
        FullSyncListener, DialogBackListener, CustomerRecyclerListener,
        ProjectExpandOptionListener, ProposalRecyclerListener, ForgotPasswordListener, CustomerRecyclerListenerForLikePlot, UnzipListener {
    private static final String TAG = MainActivity.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    public static Location mLocation;
    private final int REQUEST_CAMERA = 111;
    public Map<Integer, Set<CustomerDataModel>> mapForPlotLikeByCustomerList;
    boolean mBound = false, galleryOpenBool;
    private LayoutUserAuthBinding layoutUserAuthBinding;
    private LayoutUserLoginBinding layoutUserLoginBinding;
    private MapFragment mapFragment;
    private Dialog authDialog, loginDialog, passwordDialog, projectListDialog, singleGalleryDialog;
    private DialogSettings dialogSettings;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private Context context;
    private AlertManager alertManager;
    private String zipFilePath;
    private ListView mDrawerListLeft, mDrawerListRight;
    private CoordinatorLayout coordinatorLayoutProjectDetail;
    private CoordinatorLayout coordinatorLayoutCustomerSearch;
    private ArrayList<CustomerDataModel> customerDataModelArrayList;
    private CustomerAdapter customerAdapter;
    private BottomSheetBehavior mBottomSheetBehaviorProjectDetails;
    private BottomSheetBehavior mBottomSheetBehaviorSearchCustomer;
    private View bottomSheetProjectDetails;
    private ArrayList<CustomerDataModel> selectedCustomerItemList;
    private PhaseDataModel phaseDataModel;
    private RecyclerView customerRecyclerView;
    private ArrayList<PhaseDataModel> phaseDataModelArrayList;
    private ProposalAdapter proposalAdapter;
    private Toolbar toolbar;
    private QuickSyncListener quickSyncListener;
    private ImageView imageViewUserProfile, imageViewGallerySingle;

    private TextView textViewUsername;
    private TextView textViewUserEmailId;
    private LinearLayout rightDrawerListView, rightDrawerPlotInfo, rightDrawerImage, rightDrawerRecyclerView;
    private RecyclerView recyclerViewForViewLikeCustomer, galleryRecyclerView;
    private PlotModel currentSelectedPlotModel;
    private ArrayList<CustomerDataModel> customerListForPlotLike;
    private ImageSwitcher imageSwitcher;
    private VideoView videoView, videoViewSingle;
    private ArrayList<ContentDataModel> contentDataModelArrayList;
    private int plotId;
    private ExpandableLayout expandableLayoutOfProjectOption;
    private ExpandableLayout expandableLayoutForSyncOption;
    private ExpandableLayout tempExpandableLayoutForProjectOption;
    private CustomerDataModel removeCustomer;
    private TextView textViewProjectName;
    private PDFView pdfView, pdfViewSingle;
    private PanoramaImageView panoramaImageView;
    private SphericalView sphericalView;
    private GyroscopeObserver gyroscopeObserver;
    private VrPanoramaView panoramaView;

    private Animation in;
    private Animation out;
    private Uri galleryContentUri;

    private Dialog galleryDialog;
    private LinearLayout imageGalleryLinearLayout1, imageGalleryLinearLayout2;

    private UnzipListener unzipListener;
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            mLocation = intent.getParcelableExtra("location");
            Log.e(TAG, "onReceive: " + mLocation.getLongitude());
        }
    };

    @Override
    protected void onResume() {
        Log.e(TAG, "onResume: ");
        super.onResume();
        LocalBroadcastManager.getInstance(context).registerReceiver(mMessageReceiver, new IntentFilter("GPSLocationUpdates"));
    }

    @Override
    protected void onStart() {
        Log.e(TAG, "onStart: ");
        super.onStart();
//        Intent intent = new Intent(this, GPSBindService.class);
//        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
//        EventBus.getDefault().register(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG, "onCreate: FIREBASE TOKEN: " + SharedPreference.getInstance(getApplicationContext()).getDeviceToken());
        Log.e(TAG, "onCreate: TIME: " + System.currentTimeMillis());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        context = MainActivity.this;

        File f = Environment.getExternalStorageDirectory();
        File file = new File(GlobalConstant.PROJECT_FOLDER_PATH);
        Log.e(TAG, "onCreate: FILE EXIST: " + file.exists());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        makeScreenFullScreen();
        initRequiredControlsAndClasses();
        initAllDrawer();
        replaceWithMapFragment();
        initLoginOrRegistrationDialog();
        boundGpsServiceImplementation();
        checkLocationPermission();
    }


    private void makeScreenFullScreen() {
        Log.e(TAG, "makeScreenFullScreen: ");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void initRequiredControlsAndClasses() {
        Log.e(TAG, "initRequiredControlsAndClasses: ");
        alertManager = new AlertManager(context, this);
        dialogSettings = new DialogSettings(this);
        mapFragment = new MapFragment();
        toolbar = findViewById(R.id.my_toolbar);
        mapForPlotLikeByCustomerList = new HashMap<>();
    }

    private void initAllDrawer() {
        Log.e(TAG, "initAllDrawer: ");
        drawer = findViewById(R.id.drawer_layout);

        rightDrawerListView = findViewById(R.id.linearLayoutRightDrawerListView);
        rightDrawerPlotInfo = findViewById(R.id.linearLayoutRightDrawerPlotInfo);
        rightDrawerImage = findViewById(R.id.linearLayoutRightDrawerImageView);
        rightDrawerRecyclerView = findViewById(R.id.linearLayoutRightDrawerRecyclerView);

        imageViewUserProfile = findViewById(R.id.imageViewUserProfile);
        textViewUsername = findViewById(R.id.textViewUsername);
        textViewUserEmailId = findViewById(R.id.textViewUserEmailId);

        recyclerViewForViewLikeCustomer = findViewById(R.id.recyclerViewForViewLikeCustomer);

        mDrawerListLeft = findViewById(R.id.listViewForLeftNavDrawer);
        mDrawerListRight = findViewById(R.id.listViewForRightNavDrawer);


        DataModel[] drawerItem = new DataModel[3];
        drawerItem[0] = new DataModel(R.drawable.ic_sync_black_24dp, "Quick Sync");
        drawerItem[1] = new DataModel(R.drawable.ic_sd_storage_black_24dp, "Backup Database");
        drawerItem[2] = new DataModel(R.drawable.ic_close_black_24dp, "Terminate Sessions");


        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.list_view_item_row, drawerItem);
        mDrawerListLeft.setAdapter(adapter);
        mDrawerListLeft.setBackgroundColor(ActivityCompat.getColor(context, R.color.transparent));
        mDrawerListLeft.setOnItemClickListener(this);

        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this, drawer, toolbar, R.string.app_name, R.string.app_name);
        mDrawerToggle.syncState();
        drawer.addDrawerListener(mDrawerToggle);

        in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
    }

    private void replaceWithMapFragment() {
        Log.e(TAG, "replaceWithMapFragment: ");
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, mapFragment, "MapFragment").commit();
        fragmentManager.executePendingTransactions();
    }

    private void boundGpsServiceImplementation() {
        Log.e(TAG, "boundGpsServiceImplementation: ");
        ServiceConnection serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder iBinder) {
                Log.e(TAG, "onServiceConnected: =" + name);
                GPSBindService.GpsBinder binder = (GPSBindService.GpsBinder) iBinder;
                GPSBindService gpsBindService = binder.getService();
                mBound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.e(TAG, "onServiceDisconnected: =" + name);
                mBound = false;
            }
        };
    }

    private void checkLocationPermission() {
        Log.e(TAG, "checkLocationPermission: ");
        if (ContextCompat.checkSelfPermission(context, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(context, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            MarshMallowPermission.requestLocationPermission(context);
        } else {
            startLocationService();
        }
    }

    private void startLocationService() {
        Log.e(TAG, "startLocationService: ");
        Intent intent = new Intent(this, GPSUnbindService.class);
        startService(intent);
    }

    private void initLoginOrRegistrationDialog() {
        Log.e(TAG, "initLoginOrRegistrationDialog: ");
        String isDeviceRegistered = SharedPreference.getInstance(context).getStringSharedPreference(GlobalConstant.STRING_IsDeviceRegistered);
        if (isDeviceRegistered != null) {
            if (!isDeviceRegistered.equalsIgnoreCase(GlobalConstant.STRING_YES)) {
                initRegistrationDialog();
            } else {
                initLoginDialog();
            }
        } else {
            initRegistrationDialog();
        }
    }

    @Override
    public void onBackPressed() {
        Log.e(TAG, "onBackPressed: ");
        alertManager = new AlertManager(context, this);
        alertManager.informationDialog(GlobalConstant.STRING_Session_Exit, getResources().getString(R.string.please_close_customer_session));
//        alertManager.confirmationDialog(GlobalConstant.EXIT_CONFIRMATION, getResources().getString(R.string.exit_message));
    }

    private void selectItemLeft(int position) {
        Log.e(TAG, "selectItemLeft: ");
        switch (position) {
            case 0:
                if (selectedCustomerItemList.size() > 0) {
                    alertManager.informationDialog("Customer Session", "Please close current customer session first");
                } else {
                    SyncCall syncCall = new SyncCall();
                    syncCall.callSync(context, this);
                }
                break;
            case 1:
                DatabaseBackupRestoreManager databaseBackupRestoreManager = new DatabaseBackupRestoreManager(context);
                databaseBackupRestoreManager.backupDatabase();
                break;
            case 2:
                alertManager.confirmationDialog(GlobalConstant.END_CUSTOMER_SESSION, getResources().getString(R.string.sure_end_customer_session));
                break;
        }
    }

    private void terminateAllSessions() {
        Log.e(TAG, "terminateAllSessions: ");
        mapFragment.onLoseFocus();
        ArrayList<CustomerDataModel> tempCustomerDataModelArrayList = new ArrayList<>();
        tempCustomerDataModelArrayList.addAll(selectedCustomerItemList);
        closeDrawers();
        for (int i = 0; i < tempCustomerDataModelArrayList.size(); i++) {
            CustomerDataModel customerDataModel = tempCustomerDataModelArrayList.get(i);
            TrnSessionDataSource trnSessionDataSource = new TrnSessionDataSource(context);
            SessionDataModel sessionDataModel = new SessionDataModel();
            if (mLocation != null) {
                sessionDataModel.setSess_end_lat(String.valueOf(mLocation.getLatitude()));
                sessionDataModel.setSess_end_long(String.valueOf(mLocation.getLongitude()));
            }
            sessionDataModel.setSess_end_flag("Terminated");
            sessionDataModel.setSess_key(customerDataModel.getCustSessKey());
            trnSessionDataSource.closeSession(sessionDataModel);
            customerDataModel.setCustomerSelectedFlag(0);
            mapFragment.closeCustomerBottomSheet();
            mapFragment.searchCustomerButton.setVisibility(View.GONE);
            mapFragment.navigationButton.setVisibility(View.GONE);
            initiateProjectListDialog();
            mapForPlotLikeByCustomerList.clear();
            mapFragment.arcMenu.setVisibility(View.GONE);
            mapFragment.selectedCustomerRecyclerView.setVisibility(View.GONE);
            mapFragment.setMapEnableFalse();
        }
        Toast.makeText(context, "Sessions Terminated Successfully !", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        Log.e(TAG, "onPostCreate: ");
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    public void onAuthButtonClick() {
        Log.e(TAG, "onAuthButtonClick: ");
        layoutUserAuthBinding.editText.setError(null);
        layoutUserAuthBinding.editText2.setError(null);
        layoutUserAuthBinding.editText3.setError(null);

        UserAuthModel userAuthModel = layoutUserAuthBinding.getUserAuth();

        if (userAuthModel.getUserName().length() < 1) {
            layoutUserAuthBinding.editText.setError("enter a valid username");
        } else if (userAuthModel.getUserPassword().length() < 1) {
            layoutUserAuthBinding.editText2.setError("enter a valid password ");
        } else if (userAuthModel.getUserPassphrase().length() < 1) {
            layoutUserAuthBinding.editText3.setError("enter a valid passphrase");
        } else {
            if (NetworkUtil.isNetworkAvailable(context)) {
                DeviceRegistration deviceRegistration = new DeviceRegistration(this, this, userAuthModel.getUserName(), userAuthModel.getUserPassword(), userAuthModel.getUserPassphrase());
                deviceRegistration.requestForDeviceRegistration();
            } else {
                alertManager.informationDialog(GlobalConstant.NETWORK_NOT_AVAILABLE, getResources().getString(R.string.network_not_available));
            }
        }
    }

    public void onLoginClick() {
        Log.e(TAG, "onLoginClick: ");
        LoginModel loginModel = layoutUserLoginBinding.getLoginModel();
        layoutUserLoginBinding.editTextLoginId.setError(null);
        layoutUserLoginBinding.editTextLoginPassword.setError(null);

        if (loginModel.getLoginId() == null) {
            layoutUserLoginBinding.editTextLoginId.setError("enter a valid login id");
        } else if (loginModel.getLoginPassword() == null) {
            layoutUserLoginBinding.editTextLoginPassword.setError("enter a valid password");
        } else if (ConnectivityReceiver.isConnected()) {
            loginFromServer(loginModel);
        } else {
            loginFromLocalDatabase(loginModel);
        }
    }


    public void onChangeUserClick() {
        Log.e(TAG, "onChangeUserClick: ");
        if (NetworkUtil.isNetworkAvailable(context)) {
            loginDialog.dismiss();
            initRegistrationDialog();
        } else {
            alertManager.informationDialog(GlobalConstant.NETWORK_NOT_AVAILABLE, getResources().getString(R.string.network_not_available));
        }

        // TODO: 4/12/17 following logic may required
//        loginDialog.findViewById(R.id.editTextLoginId).setEnabled(true);
//        loginDialog.findViewById(R.id.editTextLoginId).setBackgroundColor(ContextCompat.getColor(context, R.color.white));
//        ((EditText) loginDialog.findViewById(R.id.editTextLoginId)).setText("");
//        loginDialog.findViewById(R.id.editTextLoginId).requestFocus();
    }

    private void loginFromLocalDatabase(LoginModel loginModel) {
        Log.e(TAG, "loginFromLocalDatabase: ");
        MCrypt mcrypt = new MCrypt();
        String encryptedPassword = null;
        try {
            encryptedPassword = MCrypt.bytesToHex(mcrypt.encrypt(loginModel.getLoginPassword(), loginModel.getLoginId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        MstUserDetailsDataSource userDetailsDataSource = new MstUserDetailsDataSource(context);
        userDetailsDataSource.validateUser(this, loginModel.getLoginId(), encryptedPassword);
    }


    // TODO: 1/9/17 do not delete this code, it may required in future
//    @org.greenrobot.eventbus.Subscribe(threadMode = ThreadMode.MAIN)
//    public void onLoginEvent(LoginEvent loginEvent) {
//        Log.e(TAG, "onLoginEvent: " + loginEvent.getLoginMessage());
//        if (loginEvent.getLoginStatus() == 1) {
//            new Utility().hideKeyboard(loginDialog.getCurrentFocus());
//            QuickSync quickSync = new QuickSync(context, this);
//            quickSync.callQuickSync();
//        } else {
//            alertManager.informationDialog(GlobalConstant.STRING_LOGIN_ALERT, loginEvent.getLoginMessage());
//        }
//    }

    private void loginFromServer(LoginModel loginModel) {
        Log.e(TAG, "loginFromServer: ");
        UserLogin userLogin = new UserLogin(context, this, loginModel.getLoginId(), loginModel.getLoginPassword());
        userLogin.requestForUserLogin();
    }

    public void onSubmitForgotPass() {
        Log.e(TAG, "onResetPasswordClick: ");
        if (passwordDialog != null) {
            String email = ((EditText) passwordDialog.findViewById(R.id.editTextEmailForForgotPass)).getText().toString();
            if (email.isEmpty()) {
                alertManager.informationDialog(GlobalConstant.FORGOT_PASSWORD, "Please enter email id");
            } else if (!Utility.isValidEmail(email)) {
                alertManager.informationDialog(GlobalConstant.FORGOT_PASSWORD, "Please enter valid email id");
            } else {
                ForgotPassword forgotPassword = new ForgotPassword(context, this, email);
                forgotPassword.requestForForgotPass();
            }
        }
    }

    public void onCancelForgotPass() {
        Log.e(TAG, "onCancelForgotPass: ");
        passwordDialog.cancel();
        initLoginDialog();
    }

    public void onCancelClick() {
        Log.e(TAG, "onCancelClick: ");
//        SendMedia sendMedia = new SendMedia(context);
//        sendMedia.requestForSendMedia();

        DatabaseBackupRestoreManager databaseBackupRestoreManager = new DatabaseBackupRestoreManager(context);
        databaseBackupRestoreManager.backupDatabase();

//        if (loginDialog != null) {
//            loginDialog.dismiss();
//            finish();
//        } else if (authDialog != null) {
//            authDialog.dismiss();
//            finish();
//        }
    }


    public void openRightToolbar() {
        Log.e(TAG, "openRightToolbar: ");
        drawer.openDrawer(Gravity.RIGHT, true);
    }

    public void openLeftToolbar() {
        Log.e(TAG, "openLeftToolbar: ");
        drawer.openDrawer(Gravity.LEFT, true);

        Gson gson = new Gson();
        String json = SharedPreference.getInstance(context).getStringSharedPreference(GlobalConstant.CURRENT_USER);
        UserModel userModel = gson.fromJson(json, UserModel.class);

        textViewUsername.setText(userModel.getUser_first_name());
        textViewUserEmailId.setText(userModel.getUser_email_id());
        Picasso.with(context)
                .load(userModel.getUser_pic())
                .placeholder(R.drawable.ic_customer_icon)
                .into(imageViewUserProfile);
    }

    public void closeLeftToolbar() {
        Log.e(TAG, "closeLeftToolbar: ");
        drawer.closeDrawer(Gravity.LEFT, true);
    }

    public void closeRightToolBar() {
        Log.e(TAG, "closeRightToolBar: ");
        drawer.closeDrawer(Gravity.RIGHT, true);
    }

    private void initRegistrationDialog() {
        Log.e(TAG, "initRegistrationDialog: ");
        authDialog = dialogSettings.getTransparentDialogSettings(R.layout.layout_user_auth, this);

        layoutUserAuthBinding = DataBindingUtil.inflate(authDialog.getLayoutInflater(), R.layout.layout_user_auth, null, false);
        layoutUserAuthBinding.setMain(this);
//        layoutUserAuthBinding.setUserAuth(new UserAuthModel("demo@plotalong.com", "Pass@123", "dc59e5ec05bb111"));
        layoutUserAuthBinding.setUserAuth(new UserAuthModel("nvikhe@belebentech.com", "Pass@123", "98d6b58b2897afc"));
//        layoutUserAuthBinding.setUserAuth(new UserAuthModel("amitb@belebentech.com", "Pass@123", "76ec93941f471b8"));
//        layoutUserAuthBinding.setUserAuth(new UserAuthModel("", "", ""));

        authDialog.setContentView(layoutUserAuthBinding.getRoot());
        if (!authDialog.isShowing()) {
            authDialog.show();
        }

        NestedScrollView bottomSheetUserAuth = layoutUserAuthBinding.bottomSheetUserAuth;
        BottomSheetBehavior mBottomSheetBehaviorUserAuth = BottomSheetBehavior.from(bottomSheetUserAuth);
        mBottomSheetBehaviorUserAuth.setPeekHeight(200);
        mBottomSheetBehaviorUserAuth.setState(BottomSheetBehavior.PEEK_HEIGHT_AUTO);
        mBottomSheetBehaviorUserAuth.setHideable(false);
    }

    private void initLoginDialog() {
        Log.e(TAG, "initLoginDialog: ");
        Gson gson = new Gson();
        String json = SharedPreference.getInstance(context).getStringSharedPreference(GlobalConstant.CURRENT_USER);
        UserModel userModel = gson.fromJson(json, UserModel.class);

        loginDialog = dialogSettings.getTransparentDialogSettings(R.layout.layout_user_login, this);

        layoutUserLoginBinding = DataBindingUtil.inflate(loginDialog.getLayoutInflater(), R.layout.layout_user_login, null, false);
        layoutUserLoginBinding.setLogin(this);
        layoutUserLoginBinding.setLoginModel(new LoginModel(userModel.getUser_email_id(), "Pass@123"));
//        layoutUserLoginBinding.setLoginModel(new LoginModel(userModel.getUser_email_id(), ""));


        loginDialog.setContentView(layoutUserLoginBinding.getRoot());
        if (!loginDialog.isShowing()) {
            loginDialog.show();
            loginDialog.findViewById(R.id.editTextLoginId).setBackgroundColor(ContextCompat.getColor(context, R.color.gray));
            loginDialog.findViewById(R.id.editTextLoginId).setEnabled(false);
            loginDialog.findViewById(R.id.editTextLoginPassword).requestFocus();
        }

        NestedScrollView bottomSheetUserLogin = layoutUserLoginBinding.bottomSheetUserLogin;
        BottomSheetBehavior mBottomSheetBehaviorUserLogin = BottomSheetBehavior.from(bottomSheetUserLogin);
        mBottomSheetBehaviorUserLogin.setPeekHeight(200);
        mBottomSheetBehaviorUserLogin.setState(BottomSheetBehavior.PEEK_HEIGHT_AUTO);
        mBottomSheetBehaviorUserLogin.setHideable(false);
    }


    @Override
    protected void onPause() {
        Log.e(TAG, "onPause: ");
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Log.e(TAG, "onDestroy: ");
        if (loginDialog != null) {
            loginDialog.dismiss();
            loginDialog = null;
        }
        if (authDialog != null && authDialog.isShowing()) {
            authDialog.dismiss();
            authDialog = null;
        }
        if (projectListDialog != null && projectListDialog.isShowing()) {
            projectListDialog.dismiss();
            projectListDialog = null;
        }
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        Log.e(TAG, "onStop: ");
        super.onStop();
        // TODO: 1/9/17  do not delete it, may require in future
//        if (mBound) {
//            unbindService(serviceConnection);
//            mBound = false;
//        }
//        EventBus.getDefault().unregister(this);
    }


    public void initiateForgotPasswordDialog() {
        Log.e(TAG, "initiateForgotPasswordDialog: ");
        if (loginDialog.isShowing()) {
            loginDialog.dismiss();
            projectListDialog = dialogSettings.getTransparentDialogSettings(R.layout.layout_user_auth, this);
            projectListDialog.findViewById(R.id.coordinatorLayoutOfRegistrationGuidelines).setVisibility(View.GONE);
        }

        passwordDialog = dialogSettings.getTransparentDialogSettings(R.layout.layout_forgot_password, this);
        passwordDialog.show();

        LayoutForgotPasswordBinding layoutForgotPasswordBinding = DataBindingUtil.inflate(passwordDialog.getLayoutInflater(), R.layout.layout_forgot_password, null, false);
        layoutForgotPasswordBinding.setHandle(this);

        passwordDialog.setContentView(layoutForgotPasswordBinding.getRoot());
        if (!passwordDialog.isShowing()) {
            passwordDialog.show();
        }

        NestedScrollView bottomSheetForgotPassword = layoutForgotPasswordBinding.bottomSheetForgotPassword;
        BottomSheetBehavior mBottomSheetBehaviorUserLogin = BottomSheetBehavior.from(bottomSheetForgotPassword);
        mBottomSheetBehaviorUserLogin.setPeekHeight(200);
        mBottomSheetBehaviorUserLogin.setState(BottomSheetBehavior.PEEK_HEIGHT_AUTO);
        mBottomSheetBehaviorUserLogin.setHideable(false);
    }

    public void initiateProjectListDialog() {
        Log.e(TAG, "initiateProjectListDialog");
        if (projectListDialog != null && projectListDialog.isShowing()) {
            projectListDialog.dismiss();
        }

        projectListDialog = dialogSettings.getTransparentDialogSettings(R.layout.layout_project_list, this);
        projectListDialog.show();

        initProjectDetailsBottomSheet();
        initSearchCustomerBottomSheet();

        MstPhaseDataSource mstPhaseDataSource = new MstPhaseDataSource(context);
        phaseDataModelArrayList = mstPhaseDataSource.getAllPhase();

        if (phaseDataModelArrayList.size() > 0) {
            Singleton.getInstance().setDeveloperId(phaseDataModelArrayList.get(0).getPhas_devl_id());
        }

        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerView projectListRecyclerView = projectListDialog.findViewById(R.id.projectListRecyclerView);

        FloatingActionButton menuOption = projectListDialog.findViewById(R.id.menuOptions);
        menuOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: ");
                if (expandableLayoutOfProjectOption != null) {
                    expandableLayoutOfProjectOption.collapse();
                    if (textViewProjectName != null)
                        textViewProjectName.setVisibility(View.VISIBLE);
                }
                expandableLayoutForSyncOption = projectListDialog.findViewById(R.id.expandableLayoutForMenu);
                expandableLayoutForSyncOption.toggle();

                projectListDialog.findViewById(R.id.imageViewQuickSync).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e(TAG, "onClick: ");
                        expandableLayoutForSyncOption.collapse();
                        callQuickSync();
                    }
                });

                projectListDialog.findViewById(R.id.imageViewFullSync).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e(TAG, "onClick: ");
                        expandableLayoutForSyncOption.collapse();
                        alertManager.confirmationDialog(GlobalConstant.FullSync, "This process may take longer time and needs internet connectivity. Do you want to start now ?");


                    }
                });
            }
        });
        projectListRecyclerView.setLayoutManager(llm);
        PhaseListAdapter adapter = new PhaseListAdapter(phaseDataModelArrayList, context, this);
        projectListRecyclerView.setAdapter(adapter);
    }

    private void callFullSync() {
        Log.e(TAG, "callFullSync: ");
        FullSync fullSync = new FullSync(context, this);
        fullSync.callFullSync();
    }

    private void callQuickSync() {
        Log.e(TAG, "callQuickSync: ");
        SyncCall syncCall = new SyncCall();
        syncCall.callSync(context, this);
    }

    private void jumpToFragmentFromActivityForSession(PhaseDataModel phaseDataModel) {
        Log.e(TAG, "jumpToFragmentFromActivityForSession: ");
        try {
            projectListDialog.cancel();
            Singleton.getInstance().setSelectedProjectPhase(phaseDataModel);
            mapFragment.onFocus();
            mapFragment.onProjectListDoubleClick(phaseDataModel, selectedCustomerItemList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showProjectInfo(ArrayList<PhaseDataModel> phaseDataModelArrayList, int position) {
        Log.e(TAG, "showProjectInfo: ");
        try {
            coordinatorLayoutProjectDetail.setVisibility(View.VISIBLE);
            coordinatorLayoutCustomerSearch.setVisibility(View.GONE);
            mBottomSheetBehaviorProjectDetails.setState(BottomSheetBehavior.STATE_EXPANDED);

            TextView TextViewProjectName = bottomSheetProjectDetails.findViewById(R.id.TextViewProjectName);
            TextView textViewProjectDescription = bottomSheetProjectDetails.findViewById(R.id.textViewProjectDescription);
            ImageView imageViewProjectLogo = bottomSheetProjectDetails.findViewById(R.id.imageViewProjectLogo);
            TextView textViewReraCode = bottomSheetProjectDetails.findViewById(R.id.textViewReraCode);
            TextView textViewStartDate = bottomSheetProjectDetails.findViewById(R.id.textViewStartDate);
            TextView textViewExpiryDate = bottomSheetProjectDetails.findViewById(R.id.textViewExpiryDate);
            TextView textViewAddress = bottomSheetProjectDetails.findViewById(R.id.textViewAddress);

            CfgAddressDataSource cfgAddressDataSource = new CfgAddressDataSource(context);
            AddressDataModel addressDataModel = cfgAddressDataSource.getAddressOfPhase(phaseDataModel.getPhas_id());
            if (addressDataModel != null) {
                textViewAddress.setText(addressDataModel.getAddr_line1().concat(" ").concat(addressDataModel.getAddr_line2()
                        .concat(" ").concat(addressDataModel.getAddr_locality().concat(" ").concat(addressDataModel.getAddr_city()))));
            }
            phaseDataModel = phaseDataModelArrayList.get(position);
            TextViewProjectName.setText(phaseDataModel.getPhas_name());
            textViewProjectDescription.setText(phaseDataModel.getPhas_details());
            textViewReraCode.setText(phaseDataModel.getPhas_rera_code());
            textViewStartDate.setText(phaseDataModel.getPhas_launch_date());
            textViewExpiryDate.setText(phaseDataModel.getPhas_rera_renew_date());


            String absolutePath = GlobalConstant.PROJECT_FOLDER_PATH + phaseDataModel.getPhas_logo().replace("assets/user/", "");
            Uri uri = Uri.fromFile(new File(absolutePath));
            Picasso.with(getBaseContext())
                    .load(uri)
//                    .resize(500, 500)
//                    .centerCrop()
                    .into(imageViewProjectLogo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initSearchCustomerBottomSheet() {
        Log.e(TAG, "initSearchCustomerBottomSheet: ");
        final View bottomSheetSearchCustomer = projectListDialog.findViewById(R.id.bottomSheetCustomerSearch);
        coordinatorLayoutCustomerSearch = projectListDialog.findViewById(R.id.customerSearchCoordinatorLayout);

        mBottomSheetBehaviorSearchCustomer = BottomSheetBehavior.from(bottomSheetSearchCustomer);
        mBottomSheetBehaviorSearchCustomer.setPeekHeight(200);
        mBottomSheetBehaviorSearchCustomer.setState(BottomSheetBehavior.STATE_HIDDEN);
        mBottomSheetBehaviorSearchCustomer.setHideable(false);

        mBottomSheetBehaviorSearchCustomer.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                Log.e(TAG, "onStateChanged: " + newState);
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    coordinatorLayoutProjectDetail.setVisibility(View.VISIBLE);
                    coordinatorLayoutCustomerSearch.setVisibility(View.GONE);
                    mBottomSheetBehaviorSearchCustomer.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.e(TAG, "onSlide: ");
            }
        });
    }

    private void initProjectDetailsBottomSheet() {
        Log.e(TAG, "initProjectDetailsBottomSheet: ");
        bottomSheetProjectDetails = projectListDialog.findViewById(R.id.bottomSheetProjectDetails);
        coordinatorLayoutProjectDetail = projectListDialog.findViewById(R.id.projectDetailsCoordinatorLayout);

        mBottomSheetBehaviorProjectDetails = BottomSheetBehavior.from(bottomSheetProjectDetails);
        mBottomSheetBehaviorProjectDetails.setPeekHeight(200);
        mBottomSheetBehaviorProjectDetails.setState(BottomSheetBehavior.PEEK_HEIGHT_AUTO);
        mBottomSheetBehaviorProjectDetails.setHideable(false);
    }

    private void initCustomerList(final int layoutPosition, final String sessionType) {
        Log.e(TAG, "initCustomerList: ");
        MstCustomerDataSource customerDataSource = new MstCustomerDataSource(context);
        customerDataModelArrayList = customerDataSource.getAllCustomer(SharedPreference.getInstance(context).getIntSharedPreference(GlobalConstant.DeveloperId));
        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        customerRecyclerView = projectListDialog.findViewById(R.id.customerRecyclerView);

        customerRecyclerView.setHasFixedSize(true);
        customerRecyclerView.setItemViewCacheSize(20);
        customerRecyclerView.setDrawingCacheEnabled(true);

        customerRecyclerView.setLayoutManager(llm);
        customerAdapter = new CustomerAdapter(customerDataModelArrayList, context, this);
        customerRecyclerView.setAdapter(customerAdapter);

        selectedCustomerItemList = new ArrayList<>();


        ((EditText) projectListDialog.findViewById(R.id.searchCustomerEditText)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.e(TAG, "beforeTextChanged: ");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e(TAG, "onTextChanged: ");
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.e(TAG, "afterTextChanged: " + editable);
                filter(editable.toString());
            }
        });

        projectListDialog.findViewById(R.id.addNewCustomerImageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddCustomerActivity.class);
                startActivity(intent);
            }
        });

        projectListDialog.findViewById(R.id.goToProjectImageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCustomerSessions(layoutPosition, sessionType);
            }
        });
    }

    private void startCustomerSessions(int layoutPosition, String sessionType) {
        Log.e(TAG, "startCustomerSessions: ");
        if (selectedCustomerItemList != null && selectedCustomerItemList.size() > 0) {
            ArrayList<SessionDataModel> sessionDataModelArrayList = new ArrayList<>();
            for (int i = 0; i < selectedCustomerItemList.size(); i++) {
                selectedCustomerItemList.get(i).setCustSessKey(Utility.createSessionKey(context, selectedCustomerItemList.get(i).getCust_unique_id(), selectedCustomerItemList.get(i).getCust_first_name()));
                SessionDataModel sessionDataModel = new SessionDataModel();
                sessionDataModel.setSess_key(selectedCustomerItemList.get(i).getCustSessKey());
                sessionDataModel.setSess_start_timestamp(DateUtil.getCurrentFormatDateAndTime());
                if (mLocation != null) {
                    sessionDataModel.setSess_start_lat(String.valueOf(mLocation.getLatitude()));
                    sessionDataModel.setSess_start_long(String.valueOf(mLocation.getLongitude()));
                }
                sessionDataModel.setSess_type(sessionType);
                sessionDataModel.setSess_group_id(selectedCustomerItemList.get(i).getCustomerTempGroupId());
                sessionDataModel.setSess_cust_unique_id(selectedCustomerItemList.get(i).getCust_unique_id());
                sessionDataModel.setSess_spid(SharedPreference.getInstance(context).getIntSharedPreference(GlobalConstant.UserId));
                sessionDataModel.setSess_project_id(phaseDataModel.getPhas_proj_id());
                sessionDataModel.setSess_phase_id(phaseDataModel.getPhas_id());
                sessionDataModel.setSess_customer_feedback("Nice Plot, Ready to buy");
                sessionDataModel.setSess_user_feedback("Customer willing to buy plot");
                sessionDataModel.setSess_image_location("");
                sessionDataModel.setCreated_by(String.valueOf(SharedPreference.getInstance(context).getIntSharedPreference(GlobalConstant.UserId)));
                sessionDataModel.setUpdated_by(String.valueOf(SharedPreference.getInstance(context).getIntSharedPreference(GlobalConstant.UserId)));
                sessionDataModel.setCreated_at(DateUtil.getCurrentFormatDateAndTime());
                sessionDataModel.setUpdated_at(DateUtil.getCurrentFormatDateAndTime());
                sessionDataModel.setSess_end_flag("a");
                sessionDataModelArrayList.add(sessionDataModel);
            }
            TrnSessionDataSource trnSessionDataSource = new TrnSessionDataSource(context);
            trnSessionDataSource.insertSession(sessionDataModelArrayList);
            jumpToFragmentFromActivityForSession(phaseDataModelArrayList.get(layoutPosition));
        } else {
            alertManager.informationDialog(getResources().getString(R.string.search_customer), getResources().getString(R.string.please_select_customer));
        }
    }

    private void refreshCustomerListByAddingNewCustomer(CustomerDataModel customerDataModel) {
        Log.e(TAG, "refreshCustomerListByAddingNewCustomer: ");
        customerDataModelArrayList.add(customerDataModel);
        customerAdapter = new CustomerAdapter(customerDataModelArrayList, context, this);
        customerRecyclerView.setAdapter(customerAdapter);
    }


    private void removeCustomerFromList(ArrayList<CustomerDataModel> customerDataModelArrayList, CustomerDataModel customerDataModel) {
        Log.e(TAG, "removeRepeatCustomer: ");
        for (int i = 0; i < customerDataModelArrayList.size(); i++) {
            if (customerDataModelArrayList.get(i).getCust_unique_id() == customerDataModel.getCust_unique_id()) {
                customerDataModelArrayList.remove(i);
            }
        }
    }

    private void filter(String text) {
        Log.e(TAG, "filter: =" + text);
        if (!text.equalsIgnoreCase("")) {
            ArrayList<CustomerDataModel> tempCustomerDataModelArrayList = new ArrayList<>();
            for (CustomerDataModel customerDataModel : customerDataModelArrayList) {
                if (customerDataModel.getCust_first_name().toLowerCase().contains(text.toLowerCase()) || customerDataModel.getCust_last_name().toLowerCase().contains(text.toLowerCase()) || customerDataModel.getCust_mobile().contains(text)) {
                    tempCustomerDataModelArrayList.add(customerDataModel);
                }
            }
            customerAdapter.filterList(tempCustomerDataModelArrayList);
        } else {
            customerAdapter.filterList(customerDataModelArrayList);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.e(TAG, "onItemClick: ");
        if (parent.getId() == R.id.listViewForLeftNavDrawer) {
            selectItemLeft(position);
            mDrawerListLeft.clearChoices();
        } else if (parent.getId() == R.id.listViewForRightNavDrawer) {
            Log.e(TAG, "onItemClick: class name: " + parent.getItemAtPosition(position).getClass().getSimpleName());
            if (parent.getItemAtPosition(position).getClass().equals(Nearby.class)) {
                selectNearbyItemRight((Nearby) parent.getItemAtPosition(position));
            } else if (parent.getItemAtPosition(position).getClass().equals(DirectionFromDatabaseModel.class)) {
                selectDirectionItemRight((DirectionFromDatabaseModel) parent.getItemAtPosition(position));
            }
            mDrawerListRight.clearChoices();
            closeDrawers();
        }
        view.requestLayout();
    }

    @Override
    public void onDeviceRegistrationSuccess() {
        Log.e(TAG, "onDeviceRegistrationSuccess: ");

        FullSync fullSync = new FullSync(context, this);
        fullSync.callFullSync();

//        SharedPreference.getInstance(this).saveStringSharedPreference(GlobalConstant.STRING_IsDeviceRegistered, GlobalConstant.STRING_YES);
//        authDialog.cancel();
//        initLoginDialog();
//        new Utility().hideKeyboard(authDialog.getCurrentFocus());
    }

    @Override
    public void onDeviceRegistrationFailed(String message) {
        Log.e(TAG, "onDeviceRegistrationFailed: " + message);
        alertManager.informationDialog(GlobalConstant.STRING_DEVICE_REGISTRATION_ALERT, message);
    }

    @Override
    public void onYesResponse(String title) {
        Log.e(TAG, "onYesResponse: ");
        switch (title) {
            case GlobalConstant.STRING_DEVICE_REGISTRATION_ALERT:
                break;
            case GlobalConstant.EXIT_CONFIRMATION:
                finish();
                break;
            case GlobalConstant.END_CUSTOMER_SESSION:
                terminateAllSessions();
                break;
            case GlobalConstant.UNLIKE_PLOT:
                boolean test = mapForPlotLikeByCustomerList.get(plotId).remove(removeCustomer);
                Log.e(TAG, "onYesResponse: REMOVED CUSTOMER" + test);
                initiateRightDrawer(mapFragment.removeLastElement(selectedCustomerItemList), currentSelectedPlotModel);
                break;

            case GlobalConstant.FullSync:
                callFullSync();
                break;
        }
    }

    @Override
    public void onNoResponse(String title) {
        Log.e(TAG, "onNoResponse: ");
        switch (title) {
            case GlobalConstant.STRING_DEVICE_REGISTRATION_ALERT:
                break;
            case GlobalConstant.EXIT_CONFIRMATION:
                break;
            case GlobalConstant.FullSync:
                break;
        }
    }

    @Override
    public void onOkResponse(String title) {
        Log.e(TAG, "onOkResponse: ");
        switch (title) {
            case GlobalConstant.STRING_DEVICE_REGISTRATION_ALERT:
                break;
            case GlobalConstant.FORGOT_PASSWORD:
                if (passwordDialog != null) {
                    passwordDialog.dismiss();
                    initLoginDialog();
                }
                break;
        }
    }

    @Override
    public void onQuickSyncSuccess(ArrayList<ContentDataModel> cfg_content) {
        Log.e(TAG, "onQuickSyncSuccess: ");
        if (cfg_content.size() > 0) {
            for (int i = 0; i < cfg_content.size(); i++) {
                String sourceFileLocation = cfg_content.get(i).getCont_file_location();
                sourceFileLocation = GlobalConstant.BASE_URL.concat(sourceFileLocation);
                String destinationFileLocation = cfg_content.get(i).getCont_file_location();
                destinationFileLocation = GlobalConstant.PROJECT_FOLDER_PATH + destinationFileLocation.replace("/assets/user/", "");
                downloadAnyFile(sourceFileLocation, destinationFileLocation);
                if (i == cfg_content.size() - 1) {
                    loginDialog.dismiss();
                    initiateProjectListDialog();
                }
            }
        } else {
            loginDialog.dismiss();
            initiateProjectListDialog();
        }
    }

    @Override
    public void onQuickSyncFailed(String message) {
        Log.e(TAG, "onQuickSyncFailed: ");
        alertManager.informationDialog(GlobalConstant.STRING_QUICK_SYNC_ALERT, message);
    }

    @Override
    public void onProjectChange() {
        Log.e(TAG, "onProjectChange: ");
//        FileHandlingUtil.deleteFolder(GlobalConstant.NEW_PLOT_ALONG_FOLDER_NAME);
        MstUserDetailsDataSource userDetailsDataSource = new MstUserDetailsDataSource(context);
        userDetailsDataSource.deleteAllTablesExceptUserTable();

        FullSync fullSync = new FullSync(context, this);
        fullSync.callFullSync();
    }

    @Override
    public void onCallQuickSyncAgain() {
        Log.e(TAG, "onCallQuickSyncAgain: ");
        SyncCall syncCall = new SyncCall();
        syncCall.callSync(context, this);
    }

    @Override
    public void onLoginSuccess() {
        Log.e(TAG, "onLoginSuccess: ");
        new Utility().hideKeyboard(loginDialog.getCurrentFocus());
        if (SharedPreference.getInstance(context).getStringSharedPreference(GlobalConstant.LastQuickSyncTime) == null) {
            FullSync fullSync = new FullSync(context, this);
            fullSync.callFullSync();
        } else {
            SyncCall syncCall = new SyncCall();
            syncCall.callSync(context, this);
//            loginDialog.dismiss();
//            initiateProjectListDialog();
        }
    }

    @Override
    public void onLoginFail(String message) {
        Log.e(TAG, "onLoginFail: " + message);
//        alertManager.informationDialog(GlobalConstant.STRING_LOGIN_ALERT, message);
        loginDialog.dismiss();
        initiateProjectListDialog();
    }

    @Override
    public void onLoginSuccessFromLocal() {
        Log.e(TAG, "onLoginSuccessFromLocal: ");
        new Utility().hideKeyboard(loginDialog.getCurrentFocus());
        if (SharedPreference.getInstance(context).getStringSharedPreference(GlobalConstant.LastQuickSyncTime) == null) {
            onLoginFail("Device is not configure, Please connect to network and try again !");
        } else {
            MstPhaseDataSource mstPhaseDataSource = new MstPhaseDataSource(context);
            phaseDataModelArrayList = mstPhaseDataSource.getAllPhase();
            if (phaseDataModelArrayList.size() > 0) {
                loginDialog.dismiss();
                initiateProjectListDialog();
            }
        }
    }

    private void downloadMedia(String zipFilePath, UnzipListener unzipListener) {
        Log.e(TAG, "downloadMedia: ");
        this.unzipListener = unzipListener;
        if (NetworkUtil.isNetworkAvailable(context)) {
            if (FileHandlingUtil.isExternalStorageWritable()) {
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    MarshMallowPermission.requestStoragePermission(context);
                } else {
                    downloadZipAndUnzip(zipFilePath, unzipListener);
                }
            } else {
                Toast.makeText(context, getResources().getString(R.string.storage_not_available), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, getResources().getString(R.string.network_not_available), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.e(TAG, "onRequestPermissionsResult: ");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MarshmallowIntentId.WRITE_EXTERNAL_STORAGE_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    downloadZipAndUnzip(zipFilePath, unzipListener);
                } else {
                    Toast.makeText(context, getResources().getString(R.string.storage_permission_denied), Toast.LENGTH_SHORT).show();
                }
                break;
            case MarshmallowIntentId.ACCESS_FINE_LOCATION_INTENT_ID:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startLocationService();
                } else {
                    Toast.makeText(context, getResources().getString(R.string.location_permission_denied), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void downloadAnyFile(String sourcePath, String destinationPath) {
        Log.e(TAG, "downloadAnyFile: " + sourcePath);
        DownloadAnyFile downloadAnyFile = new DownloadAnyFile(context, destinationPath);
        downloadAnyFile.execute(sourcePath);
    }

    private void downloadZipAndUnzip(String urlForZip, UnzipListener unzipListener) {
        Log.e(TAG, "downloadZipAndUnzip: " + urlForZip);
        DownloadZipAndUnzip downloadZipAndUnzip = new DownloadZipAndUnzip(unzipListener, context, GlobalConstant.NEW_PLOT_ALONG_FOLDER_NAME, FileHandlingUtil.getFileNameFromUrl(urlForZip));
        downloadZipAndUnzip.execute(urlForZip);
    }

    @Override
    public void onFullSyncSuccess(String zipFilePath) {
        Log.e(TAG, "onFullSyncSuccess: ");
        this.zipFilePath = zipFilePath;
        downloadMedia(zipFilePath, this);
    }

    @Override
    public void onFullSyncFailed(String message) {
        Log.e(TAG, "onFullSyncFailed: " + message);
        alertManager.informationDialog(GlobalConstant.STRING_FULL_SYNC_ALERT, message);
    }

    public void initiateRightDrawer(Nearby[] nearbies) {
        Log.e(TAG, "initiateRightDrawer: ");
        NearbyListAdapter adapterLeft = new NearbyListAdapter(this, R.layout.list_view_item_row, nearbies);
        mDrawerListRight.setAdapter(adapterLeft);
        mDrawerListRight.setBackgroundColor(ActivityCompat.getColor(context, R.color.transparent));
        mDrawerListRight.setOnItemClickListener(this);
        rightDrawerListView.setVisibility(View.VISIBLE);
    }

    public void initiateRightDrawer(DirectionFromDatabaseModel[] model) {
        Log.e(TAG, "initiateRightDrawer: ");
        DirectionListAdapter directionAdapter = new DirectionListAdapter(this, R.layout.direction_list_view_row, model);
        mDrawerListRight.setAdapter(directionAdapter);
        mDrawerListRight.setBackgroundColor(ActivityCompat.getColor(context, R.color.transparent));
        mDrawerListRight.setOnItemClickListener(MainActivity.this);
        rightDrawerListView.setVisibility(View.VISIBLE);
    }


    public void initiateRightDrawer(ArrayList<CustomerDataModel> tempSelectedCustomerItemList, PlotModel plotModel) {
        Log.e(TAG, "initiateRightDrawer: ");
        this.currentSelectedPlotModel = plotModel;
        plotId = currentSelectedPlotModel.getPlot_id();
        if (mapForPlotLikeByCustomerList != null && mapForPlotLikeByCustomerList.size() > 0 && mapForPlotLikeByCustomerList.containsKey(plotId)) {
            Set<CustomerDataModel> customersWhoLikedThisPlot;
            customersWhoLikedThisPlot = mapForPlotLikeByCustomerList.get(plotId);
            if (customersWhoLikedThisPlot != null) {
                customerListForPlotLike.clear();
                customerListForPlotLike = new ArrayList<>();
                ArrayList<CustomerDataModel> temp = new ArrayList<>(customersWhoLikedThisPlot);

                for (int i = 0; i < selectedCustomerItemList.size() - 1; i++) {
                    if (temp.contains(selectedCustomerItemList.get(i))) {
                        CustomerDataModel model = temp.get(temp.indexOf(selectedCustomerItemList.get(i)));
                        model.setPlotLikeFlag(1);
                        customerListForPlotLike.add(model);
                    } else {
                        CustomerDataModel model = selectedCustomerItemList.get(i);
                        model.setPlotLikeFlag(0);
                        customerListForPlotLike.add(model);
                    }
                }

            } else {
                customerListForPlotLike = addFreshList(selectedCustomerItemList);
            }
        } else {
            customerListForPlotLike = addFreshList(selectedCustomerItemList);
        }
        customerListForPlotLike = new ArrayList<>();
        customerListForPlotLike.addAll(tempSelectedCustomerItemList);

        rightDrawerListView.setVisibility(View.GONE);
        rightDrawerImage.setVisibility(View.VISIBLE);
        rightDrawerPlotInfo.setVisibility(View.VISIBLE);
        rightDrawerRecyclerView.setVisibility(View.VISIBLE);

        CfgContentDataSource cfgContentDataSource = new CfgContentDataSource(this);

        contentDataModelArrayList = cfgContentDataSource.getAllGeoGalleryModelsPlotId(plotModel.getPlot_id());

        String absolutePath = GlobalConstant.PROJECT_FOLDER_PATH + phaseDataModel.getPhas_logo().replace("assets/user/", "");
        Uri uri = Uri.fromFile(new File(absolutePath));
        Picasso.with(context)
                .load(uri)
                .into((ImageView) findViewById(R.id.imageViewProjectIcon));

        ((TextView) findViewById(R.id.textViewProjectNo)).setText(String.valueOf(plotId));
        ((TextView) findViewById(R.id.textViewPlotFrontage)).setText(String.valueOf(plotModel.getPlot_length()));
        ((TextView) findViewById(R.id.textViewPlotArea)).setText(String.valueOf(plotModel.getPlot_size()));
        ((TextView) findViewById(R.id.textViewPlotStatus)).setText(String.valueOf(plotModel.getPlot_status().toUpperCase()));
        ((TextView) findViewById(R.id.textViewPlotType)).setText(String.valueOf(plotModel.getPlot_type()));

        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewForViewLikeCustomer.setLayoutManager(llm);
        AdapterForLikeDislikePlot adapterForLikeDislikePlot = new AdapterForLikeDislikePlot(customerListForPlotLike, context, this);
        recyclerViewForViewLikeCustomer.setAdapter(adapterForLikeDislikePlot);

        rightDrawerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contentDataModelArrayList.size() > 0) {
                    initiateGalleryDialog(contentDataModelArrayList);
                }
            }
        });
    }


    private void selectNearbyItemRight(Nearby object) {
        Log.e(TAG, "selectNearbyItemRight: " + object.getCategoryType());
        String category = object.getCategoryType();
        List<CategoryResult> categoryResultList = object.getCategoryResult();
        mapFragment.addCategoryMarker(categoryResultList, category);
    }

    private void selectDirectionItemRight(DirectionFromDatabaseModel object) {
        Log.e(TAG, "selectDirectionItemRight: " + object.getOverview_polyline().length());
        mapFragment.addDatabaseDirection(object);
    }

    public void closeDrawers() {
        Log.e(TAG, "closeDrawers: ");
        closeLeftToolbar();
        closeRightToolBar();
    }

    @Override
    public void onDialogBackClick(DialogInterface dialog) {
        Log.e(TAG, "onDialogBackClick: ");
        if (dialog.equals(galleryDialog))dialog.dismiss();
        else finish();
    }

    @Override
    public void onCustomerClicked(int position, RecyclerView recyclerViewForProposals, ExpandableLayout expandableLayout) {
        Log.e(TAG, "onCustomerClicked: ");
        CustomerDataModel customerDataModel = customerAdapter.getCustomerModel(position);
        if (customerDataModel.getCustomerSelectedFlag() == 1) {
            alertManager.informationDialog("Customer Selection", "Customer Already Selected");
        } else if (expandableLayout.isExpanded()) {
            expandableLayout.collapse();
        } else {
            ArrayList<CustomerGroupDataModel> customerGroupDataModelArrayList;
            CfgCustomerGroupDataSource cfgCustomerGroupDataSource = new CfgCustomerGroupDataSource(context);
            customerGroupDataModelArrayList = cfgCustomerGroupDataSource.getAllCustGroupHavingOpps(customerDataModel.getCust_unique_id());
            ArrayList<ProposalHeaderDataModel> proposalHeaderDataModelArrayList = new ArrayList<>();
            MstProposalHeaderDataSource mstProposalHeaderDataSource = new MstProposalHeaderDataSource(context);
            if (customerGroupDataModelArrayList.size() > 0) {
                for (int i = 0; i < customerGroupDataModelArrayList.size(); i++) {
                    ProposalHeaderDataModel proposalHeaderDataModel;
                    proposalHeaderDataModel = mstProposalHeaderDataSource.getProposalDataModel(customerGroupDataModelArrayList.get(i).getCgc_group_id(), phaseDataModel.getPhas_id());
                    if (proposalHeaderDataModel != null)
                        proposalHeaderDataModelArrayList.add(proposalHeaderDataModel);
                }
                expandableLayout.expand();
                LinearLayoutManager llm = new LinearLayoutManager(context);
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewForProposals.setLayoutManager(llm);

                proposalHeaderDataModelArrayList.add(new ProposalHeaderDataModel(0, 0, 0, "", "", "", "", 0, 0, 0, "", "", "", "", 0, "", "", "", "", "", "", 0, ""));
                proposalAdapter = new ProposalAdapter(proposalHeaderDataModelArrayList, context, this, customerDataModel, position);
                recyclerViewForProposals.setAdapter(proposalAdapter);
            } else {
                useOldGroupOrCreateNewGroup(customerDataModel, position);
            }
        }
    }

    @Override
    public void onRemoveCustomer(int position) {
        Log.e(TAG, "onRemoveCustomer: ");
        CustomerDataModel customerDataModel = customerAdapter.getCustomerModel(position);
        if (customerDataModel.getCustomerSelectedFlag() == 0) {
            alertManager.informationDialog("Customer Selection", "This customer is not selected yet!");
        } else {
            customerDataModel.setCustomerSelectedFlag(0);
            customerAdapter.onItemDismiss(position);
            removeCustomerFromList(selectedCustomerItemList, customerDataModel);
            removeCustomerFromList(customerDataModelArrayList, customerDataModel);
            refreshCustomerListByAddingNewCustomer(customerDataModel);
        }
    }

    @Override
    public void onEditCustomer(int position) {
        Log.e(TAG, "onEditCustomer: ");
        CustomerDataModel customerDataModel = customerAdapter.getCustomerModel(position);
        Gson gson = new Gson();
        String customerObjectJson = gson.toJson(customerDataModel);
        Intent intent = new Intent(context, CustomerActivity.class);
        intent.putExtra("customer_model", customerObjectJson);
        startActivity(intent);
    }


    private void useOldGroupOrCreateNewGroup(CustomerDataModel customerDataModel, int position) {
        Log.e(TAG, "useOldGroupOrCreateNewGroup: ");
        String customerGroupId;
        CfgCustomerGroupDataSource cfgCustomerGroupDataSource = new CfgCustomerGroupDataSource(context);
        CustomerGroupDataModel hotLeadCustomerGroupModel = cfgCustomerGroupDataSource.getHotLeadCustomerGroupId(customerDataModel.getCust_unique_id());
        CustomerGroupDataModel warmLeadCustomerGroupModel = cfgCustomerGroupDataSource.getWarmLeadCustomerGroupId(customerDataModel.getCust_unique_id());
        int lastCgcId = cfgCustomerGroupDataSource.getLastCgcId();

        if (hotLeadCustomerGroupModel != null) {
            customerGroupId = hotLeadCustomerGroupModel.getCgc_group_id();
            addCustomerIntoSelectedCustomerList(customerGroupId, customerDataModel, position);
        } else if (warmLeadCustomerGroupModel != null) {
            customerGroupId = warmLeadCustomerGroupModel.getCgc_group_id();
            addCustomerIntoSelectedCustomerList(customerGroupId, customerDataModel, position);
        } else {
            customerGroupId = Utility.createCustomerGroup(customerDataModel.getCust_unique_id());

            CustomerGroupDataModel customerGroupDataModel = new CustomerGroupDataModel();
            customerGroupDataModel.setCreated_at(DateUtil.getCurrentFormatDateAndTime());
            customerGroupDataModel.setUpdated_at(DateUtil.getCurrentFormatDateAndTime());
            customerGroupDataModel.setCgc_member_type(GlobalConstant.po);
            customerGroupDataModel.setCgc_status(GlobalConstant.STRING_warmLead);
            customerGroupDataModel.setCgc_group_id(customerGroupId);
            customerGroupDataModel.setCgc_phas_id(0);
            customerGroupDataModel.setCgc_cust_id(customerDataModel.getCust_unique_id());
            customerGroupDataModel.setCgc_id(lastCgcId);
            CfgCustomerGroupDataSource tempCfgCustomerGroupDataSource = new CfgCustomerGroupDataSource(context);
            tempCfgCustomerGroupDataSource.insertCustomerGroup(customerGroupDataModel);
            addCustomerIntoSelectedCustomerList(customerGroupId, customerDataModel, position);
        }
    }

    private void addCustomerIntoSelectedCustomerList(String customerGroupId, CustomerDataModel customerDataModel, int position) {
        Log.e(TAG, "addCustomerIntoSelectedCustomerList: ");
        MstProposalHeaderDataSource mstProposalHeaderDataSource = new MstProposalHeaderDataSource(context);
        ProposalHeaderDataModel proposalHeaderDataModel = mstProposalHeaderDataSource.getProposalDataModel(customerGroupId);
        if (proposalHeaderDataModel != null) {
            customerDataModel.setTempProposalId(String.valueOf(proposalHeaderDataModel.getProp_id()));
        }
        customerDataModel.setCustomerTempGroupId(customerGroupId);
        customerDataModel.setCustomerSelectedFlag(1);
        selectedCustomerItemList.add(customerDataModel);
        customerAdapter.onItemDismiss(position);
        removeCustomerFromList(customerDataModelArrayList, customerDataModel);

        customerDataModelArrayList.add(0, customerDataModel);
        customerAdapter = new CustomerAdapter(customerDataModelArrayList, context, this);
        customerRecyclerView.setAdapter(customerAdapter);
        ((EditText) projectListDialog.findViewById(R.id.searchCustomerEditText)).setText("");

        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(projectListDialog.findViewById(R.id.searchCustomerEditText).getWindowToken(), 0);
    }

    @Override
    public void onProjectOptionClick(int optionPosition, int projectPosition) {
        Log.e(TAG, "onProjectOptionClick: ");
        phaseDataModel = phaseDataModelArrayList.get(projectPosition);
        switch (optionPosition) {
            case 0:
                if (expandableLayoutForSyncOption != null) {
                    expandableLayoutForSyncOption.collapse();
                }
                showProjectInfo(phaseDataModelArrayList, projectPosition);
                break;
            case 1:
                if (expandableLayoutForSyncOption != null) {
                    expandableLayoutForSyncOption.collapse();
                }
                try {
                    phaseDataModel = phaseDataModelArrayList.get(projectPosition);
                    mapFragment.onProjectListSingleClick(phaseDataModel);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                if (expandableLayoutForSyncOption != null) {
                    expandableLayoutForSyncOption.collapse();
                }
                openCustomerBottomSheet(projectPosition, "Visit Summary");
                break;
            case 3:
                if (expandableLayoutForSyncOption != null) {
                    expandableLayoutForSyncOption.collapse();
                }
                openCustomerBottomSheet(projectPosition, "Meeting Summary");
                break;
            case 4:
                if (expandableLayoutForSyncOption != null) {
                    expandableLayoutForSyncOption.collapse();
                }
                openCamera();
                break;
        }
    }

    @Override
    public void onProjectClick(ExpandableLayout expandableLayout, TextView textViewProjectName) {
        Log.e(TAG, "onProjectClick: ");
        this.textViewProjectName = textViewProjectName;
        if (tempExpandableLayoutForProjectOption != null) {
            if (tempExpandableLayoutForProjectOption == expandableLayout && expandableLayout.isExpanded()) {
                tempExpandableLayoutForProjectOption.collapse();
                textViewProjectName.setVisibility(View.VISIBLE);
            } else {
                tempExpandableLayoutForProjectOption.collapse();
                textViewProjectName.setVisibility(View.GONE);
                expandableLayout.expand();
                this.expandableLayoutOfProjectOption = expandableLayout;
                this.tempExpandableLayoutForProjectOption = expandableLayout;
            }
        } else {
            textViewProjectName.setVisibility(View.GONE);
            expandableLayout.expand();
            this.expandableLayoutOfProjectOption = expandableLayout;
            this.tempExpandableLayoutForProjectOption = expandableLayout;
        }

        if (expandableLayoutForSyncOption != null && expandableLayoutForSyncOption.isExpanded()) {
            expandableLayoutForSyncOption.collapse();
            textViewProjectName.setVisibility(View.GONE);
        }
    }

    private void openCamera() {
        Log.e(TAG, "openCamera: ");
        if (FileHandlingUtil.isExternalStorageWritable()) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                MarshMallowPermission.requestCameraPermission(context);
            } else {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CAMERA);
            }
        } else {
            Toast.makeText(context, getResources().getString(R.string.storage_not_available), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e(TAG, "onActivityResult: ");
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK && data != null) {
            savePhotoInProjectFolder(data);
        }
    }

    private void savePhotoInProjectFolder(Intent data) {
        Log.e(TAG, "savePhotoInProjectFolder: ");
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        int developerId = SharedPreference.getInstance(context).getIntSharedPreference(GlobalConstant.DeveloperId);
        String path = GlobalConstant.PROJECT_FOLDER_PATH + developerId + "/" + phaseDataModel.getPhas_proj_id() + "/" + phaseDataModel.getPhas_id() + "/ProjectPhotosClient";
        FileHandlingUtil.createDirectoryOfPath(path);
        String fileName = DateUtil.getCurrentFormatDateAndTime() + ".png";
        String fileNameWithPath = String.valueOf(path + "/" + fileName);
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bytes);
            File file = new File(path, fileName);
            FileOutputStream fo = new FileOutputStream(file);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        new AndroidBmpUtil().save(bitmap, fileNameWithPath);

        ContentDataModel contentDataModel = new ContentDataModel();
        contentDataModel.setCont_devc_id(Utility.getDeviceId(context));
        contentDataModel.setCont_devl_id(developerId);
        contentDataModel.setCont_project_id(phaseDataModel.getPhas_proj_id());
        contentDataModel.setCont_phase_id(phaseDataModel.getPhas_id());
        contentDataModel.setCont_file_type("png");
        contentDataModel.setCont_file_location(fileNameWithPath);
        contentDataModel.setCont_description("Picture from device.");
        if (MainActivity.mLocation != null) {
            contentDataModel.setCont_latitude(String.valueOf(MainActivity.mLocation.getLatitude()));
            contentDataModel.setCont_longitude(String.valueOf(MainActivity.mLocation.getLongitude()));
        }
        contentDataModel.setCont_status(1);
        contentDataModel.setCont_type("geogallery");
        contentDataModel.setCreated_at(DateUtil.getCurrentFormatDateAndTime());
        contentDataModel.setUpdated_at(DateUtil.getCurrentFormatDateAndTime());
        contentDataModel.setCont_sync_status(GlobalConstant.INSERTED);
        contentDataModel.setCont_file_name(fileName);

        CfgContentDataSource contentDataSource = new CfgContentDataSource(context);
        contentDataSource.insertProjectPhotoPath(contentDataModel);
        Toast.makeText(context, "Photo saved successfully", Toast.LENGTH_SHORT).show();
    }


    // TODO: 22/11/17 dont delete following function it may required in future

    private void openPlotList(int proj_phas_id, Spinner spinnerPlot) {
        Log.e(TAG, "openPlotList: ");
        ArrayList<PlotModel> plotModelArrayList;
        MstPlotDataSource mstPlotDataSource = new MstPlotDataSource(context);
        plotModelArrayList = mstPlotDataSource.getAllPlots(proj_phas_id);

        SpinnerAdapterForPlot spinnerAdapterForPlot = new SpinnerAdapterForPlot(plotModelArrayList, R.layout.row_text_with_card, context);
        spinnerPlot.setAdapter(spinnerAdapterForPlot);
    }

    private void openCustomerBottomSheet(int layoutPosition, String sessionType) {
        Log.e(TAG, "openCustomerBottomSheet: ");
        coordinatorLayoutProjectDetail.setVisibility(View.GONE);
        coordinatorLayoutCustomerSearch.setVisibility(View.VISIBLE);
        mBottomSheetBehaviorSearchCustomer.setState(BottomSheetBehavior.STATE_EXPANDED);
        initCustomerList(layoutPosition, sessionType);
    }

    @Override
    public void onProposalClick(int layoutPosition, int proposalPosition, CustomerDataModel customerDataModel) {
        Log.e(TAG, "onProposalClick: ");
        if (proposalAdapter.getProposalModel(layoutPosition).getProp_id() != 0) {
            addCustomerIntoSelectedCustomerList(proposalAdapter.getProposalModel(layoutPosition).getProp_contact_id(), customerDataModel, proposalPosition);
        } else {
            useOldGroupOrCreateNewGroup(customerDataModel, proposalPosition);
        }
    }

    @Override
    public void onForgotPassResponse(String message) {
        Log.e(TAG, "onForgotPassResponse: ");
        alertManager.informationDialog(GlobalConstant.FORGOT_PASSWORD, message);
    }

    @Override
    public void onLikeClick(int position) {
        Log.e(TAG, "onLikeClick: " + position);
        PlotModel model = currentSelectedPlotModel;
        int plotId = model.getPlot_id();

        if (customerListForPlotLike.get(position).getPlotLikeFlag() == 0) {
            customerListForPlotLike.get(position).setPlotLikeFlag(1);
//            PlotModel model = currentSelectedPlotModel;

            Set<CustomerDataModel> customerDataModelList = new HashSet<>();

            if (mapForPlotLikeByCustomerList != null && mapForPlotLikeByCustomerList.size() > 0 && mapForPlotLikeByCustomerList.containsKey(plotId))
                customerDataModelList.addAll(mapForPlotLikeByCustomerList.get(plotId));

            if (!customerDataModelList.contains(selectedCustomerItemList.get(position)))
                customerDataModelList.add(selectedCustomerItemList.get(position));

            mapForPlotLikeByCustomerList.put(plotId, customerDataModelList);
            refreshCustomerListByLikePlot();

            insertToTrnActivity(selectedCustomerItemList.get(position), plotId, "like");
//            updatePlotStatus(currentSelectedPlotModel, getResources().getString(R.string.LIKED));

//            mapFragment.removePlotPolygons();
//            mapFragment.drawPlotPolygons();

            mapFragment.updatePlot(model);

            if (mapForPlotLikeByCustomerList.get(plotId) != null) {
                customerDataModelList.addAll(mapForPlotLikeByCustomerList.get(plotId));
            }
        } else {
            removeCustomer = customerListForPlotLike.get(position);
            alertManager.confirmationDialog(GlobalConstant.UNLIKE_PLOT, "Are you sure " + customerListForPlotLike.get(position).getCust_first_name() + " wants to unlike this plot?");

            insertToTrnActivity(selectedCustomerItemList.get(position), plotId, "unlike");
//            updatePlotStatus(currentSelectedPlotModel, getResources().getString(R.string.AVAILABLE));
            mapFragment.updatePlot(model);

        }
    }

    private void refreshCustomerListByLikePlot() {
        Log.e(TAG, "refreshCustomerListByLikePlot: ");
        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewForViewLikeCustomer.setLayoutManager(llm);
        AdapterForLikeDislikePlot adapterForLikeDislikePlot = new AdapterForLikeDislikePlot(customerListForPlotLike, context, this);
        recyclerViewForViewLikeCustomer.setAdapter(adapterForLikeDislikePlot);
    }


    public void initiateGalleryDialog(ArrayList<ContentDataModel> contentDataModelArrayList) {
        try {
            Bitmap bitmap;
            BitmapDrawable drawable = null;
            galleryOpenBool = false;
            HorizontalAdapter horizontalAdapter = new HorizontalAdapter(contentDataModelArrayList, this);
            galleryDialog = dialogSettings.getSimpleDialogCancalable(R.layout.layout_image_gallery);
            galleryDialog.show();

            imageSwitcher = galleryDialog.findViewById(R.id.imageGalleryImageSwitcher);
            videoView = galleryDialog.findViewById(R.id.imageGalleryVideoView);
            pdfView = galleryDialog.findViewById(R.id.imageGalleryPdfView);
//            panoramaImageView = galleryDialog.findViewById(R.id.imageGalleryPanoramaImageView);
            sphericalView = galleryDialog.findViewById(R.id.imageGallerySphericalView);
//            panoramaView = galleryDialog.findViewById(R.id.pano_view);
            imageGalleryLinearLayout1 = galleryDialog.findViewById(R.id.imageGalleryLinearLayout1);
            imageGalleryLinearLayout2 = galleryDialog.findViewById(R.id.imageGalleryLinearLayout2);

            LinearLayoutManager llm = new LinearLayoutManager(context);
            llm.setOrientation(LinearLayoutManager.HORIZONTAL);

            if (contentDataModelArrayList.size() > 0) {
                drawable = Utility.scaleBitmapToDrawable(this,contentDataModelArrayList.get(0));
            }

            galleryRecyclerView = galleryDialog.findViewById(R.id.horizontal_recycler_view);
            galleryRecyclerView.setAdapter(horizontalAdapter);
            galleryRecyclerView.setLayoutManager(llm);

            imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
                public View makeView() {
                    // Create a new ImageView and set it's properties
                    ImageView imageView = new ImageView(getApplicationContext());
                    // set Scale type of ImageView to Fit Center
                    imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    // set the Height And Width of ImageView To FIll PARENT
                    imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
                    return imageView;
                }
            });

            imageSwitcher.setInAnimation(in);
            imageSwitcher.setOutAnimation(out);

            imageSwitcher.setImageDrawable(drawable);

            setImageAndPanoramaListeners(contentDataModelArrayList.get(0));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void imageGalleryToggle(final ArrayList<ContentDataModel> list, final int position) {
        try {
            final ContentDataModel contentDataModel = list.get(position);
            if (contentDataModel.getCont_file_type().equalsIgnoreCase("jpg") || contentDataModel.getCont_file_type().equalsIgnoreCase("png") || contentDataModel.getCont_file_type().equalsIgnoreCase("jpeg")) {
                imageSwitcher.setVisibility(View.VISIBLE);
                videoView.setVisibility(View.GONE);
                pdfView.setVisibility(View.GONE);
//                panoramaImageView.setVisibility(View.GONE);
                sphericalView.setVisibility(View.GONE);
//                panoramaView.setVisibility(View.GONE);

//                Log.e(TAG, "imageGalleryToggle: BEFORE URI" );
//                galleryContentUri = Uri.fromFile(new File(Utility.validateURIForGallery(contentDataModel.getCont_file_location())));
                Utility.scaleBitmapToDrawable(this,contentDataModel);
                imageSwitcher.setImageDrawable(Utility.scaleBitmapToDrawable(this,contentDataModel));

                setImageAndPanoramaListeners(contentDataModel);


            } else if (contentDataModel.getCont_file_type().equalsIgnoreCase("mp4")) {
                imageSwitcher.setVisibility(View.GONE);
                videoView.setVisibility(View.VISIBLE);
                pdfView.setVisibility(View.GONE);
//                panoramaImageView.setVisibility(View.GONE);
                sphericalView.setVisibility(View.GONE);

                final MediaController mediaController = new MediaController(this);
                mediaController.setAnchorView(videoView);
                mediaController.setMediaPlayer(videoView);

                galleryContentUri = Uri.fromFile(new File(Utility.validateURIForGallery(contentDataModel.getCont_file_location())));

                videoView.setVideoURI(galleryContentUri);
                videoView.setMediaController(mediaController);
                videoView.requestFocus();
                videoView.start();

            } else if (contentDataModel.getCont_file_type().equalsIgnoreCase("pdf")) {
                imageSwitcher.setVisibility(View.GONE);
                videoView.setVisibility(View.GONE);
                pdfView.setVisibility(View.VISIBLE);
//                panoramaImageView.setVisibility(View.GONE);
                sphericalView.setVisibility(View.GONE);

                galleryContentUri = Uri.fromFile(new File(Utility.validateURIForGallery(contentDataModel.getCont_file_location())));
                pdfView.fromUri(galleryContentUri)
                        .enableSwipe(true)
                        .swipeHorizontal(true)
                        .enableAnnotationRendering(true)
                        .load();

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private ArrayList<CustomerDataModel> addFreshList(ArrayList<CustomerDataModel> selectedCustomerItemList) {
        Log.e(TAG, "addFreshList: ");
        ArrayList<CustomerDataModel> customerListForPlotLike = new ArrayList<>();
        for (int i = 0; i < selectedCustomerItemList.size() - 1; i++) {
            selectedCustomerItemList.get(i).setCustomerSelectedFlag(0);
            selectedCustomerItemList.get(i).setPlotLikeFlag(0);
            customerListForPlotLike.add(selectedCustomerItemList.get(i));
        }
        return customerListForPlotLike;
    }

    private void insertToTrnActivity(CustomerDataModel model, int plotId, String like) {
        TrnActivityDataSource trnActivityDataSource = new TrnActivityDataSource(context);
        if (trnActivityDataSource.isPlotAlreadyLiked(model.getCust_unique_id(), plotId)) {
            alertManager.informationDialog("Plot Status", "Your like is already recorded.");
            return;
        }

        ActivityDataModel activityDataModel = new ActivityDataModel();
        activityDataModel.setActi_customer_id(String.valueOf(model.getCust_id()));
        activityDataModel.setActi_key("");
        activityDataModel.setActi_plot_id(String.valueOf(plotId));
        activityDataModel.setActi_session_id(model.getCustSessKey());
        activityDataModel.setActi_type("like");
        activityDataModel.setCreated_at(DateUtil.getCurrentFormatDateAndTime());

        trnActivityDataSource.insertActivity(activityDataModel);
    }

    private void updatePlotStatus(PlotModel currentSelectedPlotModel, String status) {
        MstPlotDataSource mstPlotDataSource = new MstPlotDataSource(context);
        mstPlotDataSource.updatePlotStatus(currentSelectedPlotModel, status);
    }

    public void initiateGalleryDialog(ContentDataModel model) {
        singleGalleryDialog = dialogSettings.getSimpleDialogCancalable(R.layout.layout_image_gallery_single);
        singleGalleryDialog.create();
        singleGalleryDialog.show();

        imageViewGallerySingle = singleGalleryDialog.findViewById(R.id.singleGalleryImageView);
        Uri uri = Uri.fromFile(new File(Utility.validateURIForGallery(model.getCont_file_location())));
        Picasso.with(getApplicationContext())
                .load(uri)
                .into(imageViewGallerySingle);
    }

    @Override
    public void onUnzipSuccess() {
        Log.e(TAG, "onUnzipSuccess: ");
        SharedPreference.getInstance(this).saveStringSharedPreference(GlobalConstant.STRING_IsDeviceRegistered, GlobalConstant.STRING_YES);
        if (authDialog != null) {
            authDialog.cancel();
            initLoginDialog();
            new Utility().hideKeyboard(authDialog.getCurrentFocus());
        }
    }

    @Override
    public void onUnzipFail(String error) {
        Log.e(TAG, "onUnzipFail: ");
        alertManager.informationDialog("Unzip Error", error);
    }

    private void setImageAndPanoramaListeners(final ContentDataModel model) {
        imageSwitcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onClick: IMAGESWITCHER"  );
                imageSwitcher.setVisibility(View.GONE);
                sphericalView.setVisibility(View.VISIBLE);

                sphericalView.setInertiaEnabled(true);
                sphericalView.setAccelerometerEnabled(true);
                sphericalView.setPanorama(Utility.scaleBitmapToBitmap(MainActivity.this,model),true);
//                VrPanoramaView.Options option = new VrPanoramaView.Options();
//                option.inputType = VrPanoramaView.Options.TYPE_MONO;
//                panoramaView.loadImageFromBitmap(Utility.scaleBitmapToBitmap(MainActivity.this,model),option);
                Log.e(TAG, "onClick: ACCELEROMETER: " + sphericalView.isAccelerometerEnabled()  );

                imageGalleryLinearLayout2.setVisibility(View.GONE);
            }
        });

        sphericalView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onClick: PANORAMA"  );
                imageSwitcher.setVisibility(View.VISIBLE);
                sphericalView.setVisibility(View.GONE);
//                gyroscopeObserver.unregister();

                Utility.scaleBitmapToDrawable(MainActivity.this,model);
                imageSwitcher.setImageDrawable(Utility.scaleBitmapToDrawable(MainActivity.this,model));
                imageGalleryLinearLayout2.setVisibility(View.VISIBLE);
            }
        });
    }
}