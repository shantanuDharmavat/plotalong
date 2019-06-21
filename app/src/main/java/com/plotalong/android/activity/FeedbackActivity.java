package com.plotalong.android.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.plotalong.android.R;
import com.plotalong.android.adapter.AdapterForFeedback;
import com.plotalong.android.adapter.LikePlotByCustomerAdapter;
import com.plotalong.android.dataSource.CfgContentDataSource;
import com.plotalong.android.dataSource.CfgCustomerGroupDataSource;
import com.plotalong.android.dataSource.TrnFeedbackDataSource;
import com.plotalong.android.dataSource.MstFeedbackMasterDataSource;
import com.plotalong.android.dataSource.MstPlotDataSource;
import com.plotalong.android.dataSource.MstProposalDetailsDataSource;
import com.plotalong.android.dataSource.MstProposalHeaderDataSource;
import com.plotalong.android.dialogManager.AlertManager;
import com.plotalong.android.listener.PlotSelectionListener;
import com.plotalong.android.listener.YesNoAlertListener;
import com.plotalong.android.model.deviceRegistrationModel.UserModel;
import com.plotalong.android.model.feedback.AnswerDataModel;
import com.plotalong.android.model.feedback.QuestionsDataModel;
import com.plotalong.android.model.geoGalleryModel.ContentDataModel;
import com.plotalong.android.model.quickSyncModel.CustomerDataModel;
import com.plotalong.android.model.quickSyncModel.PhaseDataModel;
import com.plotalong.android.model.quickSyncModel.PlotModel;
import com.plotalong.android.model.quickSyncModel.ProposalDetailsDataModel;
import com.plotalong.android.model.quickSyncModel.ProposalHeaderDataModel;
import com.plotalong.android.model.quickSyncModel.SessionDataModel;
import com.plotalong.android.util.DateUtil;
import com.plotalong.android.util.FileHandlingUtil;
import com.plotalong.android.util.GlobalConstant;
import com.plotalong.android.util.MarshMallowPermission;
import com.plotalong.android.util.SharedPreference;
import com.plotalong.android.util.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * Created by kbhakade on 11/10/17.
 */

public class FeedbackActivity extends AppCompatActivity implements PlotSelectionListener, View.OnClickListener, YesNoAlertListener {
    private String TAG = FeedbackActivity.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    private Context context;
    private TextView textViewFeedbackType;
    private TextView textViewFeedbackCustomerName;
    private TextView textViewFeedbackCustomerEmail;
    private TextView textViewFeedbackMobile;
    private TextView textViewFeedbackSalesPerson;
    private TextView textViewFeedbackProjectName;
    private TextView textViewFeedbackDate;
    private TextView textViewFeedbackLocation;
    private RecyclerView recyclerViewFeedbackPlotLikes;
    private ArrayList<PlotModel> plotModelArrayList;
    private BottomSheetBehavior bottomSheetBehaviorForAudioFeedback, bottomSheetBehaviorForVideoFeedback, bottomSheetBehaviorForTextFeedback;
    private Button imageButtonStartAudioRecording;
    private ProgressBar progressBarForAudio;
    private String audioSavePathInDevice = null;
    private String videoSavePathInDevice = null;
    private MediaRecorder mediaRecorder;
    private Button imageButtonStopAudioRecording;
    private Button imageButtonPlayAudioRecording;
    private Button imageButtonStopPlayRecord;
    private MediaPlayer mediaPlayer;
    private int progressStatus = 0;
    private TextView textViewCounter;
    private MyCountDownTimer myCountDownTimer;
    private RecyclerView recyclerViewForFeedbackQuestions;
    private AdapterForFeedback adapterForFeedback;
    private LikePlotByCustomerAdapter likePlotByCustomerAdapter;
    private TextView textViewFeedbackProposalName;
    private AlertManager alertManager;
    private CustomerDataModel customerDataModel;
    private PhaseDataModel phaseDataModel;
    private boolean isTextFeedbackTaken = false;
    private String audioFileName;
    private String videoFileName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: ");
        setContentView(R.layout.feedback_layout);
        context = FeedbackActivity.this;
        initViews();
        initToolBar();
        initAudioBottomSheet();
        initVideoBottomSheet();
        initTextBottomSheet();
        getCustomerModel();
        prepareAudioFeedbackPath();
        prepareVideoFeedbackPath();
    }

    private void prepareVideoFeedbackPath() {
        Log.e(TAG, "prepareVideoFeedbackPath: ");
        int developerId = SharedPreference.getInstance(context).getIntSharedPreference(GlobalConstant.DeveloperId);
        String path = GlobalConstant.PROJECT_FOLDER_PATH + developerId + "/" + phaseDataModel.getPhas_proj_id() + "/" + phaseDataModel.getPhas_id() + "/FeedbackMedia";
        FileHandlingUtil.createDirectoryOfPath(path);
        videoFileName = customerDataModel.getCustSessKey() + ".mp4";
        videoSavePathInDevice = GlobalConstant.PROJECT_FOLDER_PATH + developerId + "/" + phaseDataModel.getPhas_proj_id() + "/" + phaseDataModel.getPhas_id() + "/FeedbackMedia/" + videoFileName;
    }

    private void prepareAudioFeedbackPath() {
        Log.e(TAG, "prepareAudioFeedbackPath: ");
        int developerId = SharedPreference.getInstance(context).getIntSharedPreference(GlobalConstant.DeveloperId);
        String path = GlobalConstant.PROJECT_FOLDER_PATH + developerId + "/" + phaseDataModel.getPhas_proj_id() + "/" + phaseDataModel.getPhas_id() + "/FeedbackMedia";
        FileHandlingUtil.createDirectoryOfPath(path);
        audioFileName = customerDataModel.getCustSessKey() + ".mp3";
        audioSavePathInDevice = GlobalConstant.PROJECT_FOLDER_PATH + developerId + "/" + phaseDataModel.getPhas_proj_id() + "/" + phaseDataModel.getPhas_id() + "/FeedbackMedia/" + audioFileName;
    }

    private void initTextBottomSheet() {
        Log.e(TAG, "initTextBottomSheet: ");
        isTextFeedbackTaken = false;
        View bottomSheet = findViewById(R.id.bottomSheetForVideo);
        bottomSheetBehaviorForVideoFeedback = BottomSheetBehavior.from(bottomSheet);
    }

    private void initVideoBottomSheet() {
        Log.e(TAG, "initVideoBottomSheet: ");
        View bottomSheet = findViewById(R.id.bottomSheetForText);
        bottomSheetBehaviorForTextFeedback = BottomSheetBehavior.from(bottomSheet);
    }

    private void initViews() {
        Log.e(TAG, "initViews: ");
        textViewFeedbackType = findViewById(R.id.textViewFeedbackType);
        textViewFeedbackCustomerName = findViewById(R.id.textViewFeedbackCustomerName);
        textViewFeedbackCustomerEmail = findViewById(R.id.textViewFeedbackCustomerEmail);
        textViewFeedbackMobile = findViewById(R.id.textViewFeedbackMobile);
        textViewFeedbackSalesPerson = findViewById(R.id.textViewFeedbackSalesPerson);
        textViewFeedbackProjectName = findViewById(R.id.textViewFeedbackProjectName);
        textViewFeedbackProposalName = findViewById(R.id.textViewFeedbackProposalName);
        textViewFeedbackDate = findViewById(R.id.textViewFeedbackDate);
        textViewFeedbackLocation = findViewById(R.id.textViewFeedbackLocation);
        recyclerViewFeedbackPlotLikes = findViewById(R.id.recyclerViewFeedbackPlotLikes);
        progressBarForAudio = findViewById(R.id.progressBarForAudio);
        textViewCounter = findViewById(R.id.textViewCounter);
        Button buttonAudioNext = findViewById(R.id.buttonAudioNext);
        Button buttonVideoNext = findViewById(R.id.buttonVideoNext);

        Button buttonAudioFeedback = findViewById(R.id.buttonAudioFeedback);
        Button buttonVideoFeedback = findViewById(R.id.buttonVideoFeedback);

        Button buttonAddToProposal = findViewById(R.id.buttonAddToProposal);

        imageButtonStartAudioRecording = findViewById(R.id.imageButtonStartAudioRecording);
        imageButtonStopAudioRecording = findViewById(R.id.imageButtonStopAudioRecording);
        imageButtonPlayAudioRecording = findViewById(R.id.imageButtonPlayAudioRecording);
        imageButtonStopPlayRecord = findViewById(R.id.imageButtonStopPlayRecord);

        recyclerViewForFeedbackQuestions = findViewById(R.id.recyclerViewForFeedbackQuestions);
        Button buttonSaveAndClose = findViewById(R.id.buttonSaveAndClose);

        Button imageButtonStartVideoRecording = findViewById(R.id.imageButtonStartVideoRecording);
        imageButtonStartVideoRecording.setOnClickListener(this);

        imageButtonStartAudioRecording.setOnClickListener(this);
        imageButtonStopAudioRecording.setOnClickListener(this);
        imageButtonPlayAudioRecording.setOnClickListener(this);
        imageButtonStopPlayRecord.setOnClickListener(this);
        buttonAudioNext.setOnClickListener(this);
        buttonSaveAndClose.setOnClickListener(this);
        buttonVideoNext.setOnClickListener(this);
        buttonAddToProposal.setOnClickListener(this);

        buttonAudioFeedback.setOnClickListener(this);
        buttonVideoFeedback.setOnClickListener(this);
        plotModelArrayList = new ArrayList<>();
        alertManager = new AlertManager(context, this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GlobalConstant.VIDEO_FEEDBACK_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                FileInputStream fis = new FileInputStream(getRealPathFromURI(uri));
                FileOutputStream fos = new FileOutputStream(videoSavePathInDevice);
                byte[] buf = new byte[1024];
                int len;
                while ((len = fis.read(buf)) > 0) {
                    fos.write(buf, 0, len);
                }
                fis.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast.makeText(this, "Video Recorded Successfully !", Toast.LENGTH_LONG).show();
        }
    }


    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }


    private void initAudioBottomSheet() {
        Log.e(TAG, "initAudioBottomSheet: ");
        View bottomSheet = findViewById(R.id.bottomSheetForAudio);
        bottomSheetBehaviorForAudioFeedback = BottomSheetBehavior.from(bottomSheet);
    }

    private void initToolBar() {
        Log.e(TAG, "initToolBar: ");
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void onBackPressed() {
        Log.e(TAG, "onBackPressed: ");
        setResult(0);
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.e(TAG, "onOptionsItemSelected: ");
        switch (item.getItemId()) {
            case android.R.id.home:
                setResult(0);
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getCustomerModel() {
        Log.e(TAG, "getCustomerDataModel: ");
        Gson gson = new Gson();
        customerDataModel = gson.fromJson(getIntent().getStringExtra(GlobalConstant.SelectedCustomerModel), CustomerDataModel.class);
        phaseDataModel = gson.fromJson(getIntent().getStringExtra(GlobalConstant.SelectedProjectModel), PhaseDataModel.class);
        SessionDataModel sessionDataModel = gson.fromJson(getIntent().getStringExtra(GlobalConstant.SelectedCustomerSessionModel), SessionDataModel.class);
        ArrayList<Integer> integerArrayList = getIntent().getIntegerArrayListExtra(GlobalConstant.ArrayListOfPlotNo);


        MstPlotDataSource mstPlotDataSource = new MstPlotDataSource(context);
        plotModelArrayList = mstPlotDataSource.getAllPlotsOf(integerArrayList);

        if (customerDataModel != null && phaseDataModel != null && sessionDataModel != null) {
            setCustomerDetailsToControls(customerDataModel, phaseDataModel, sessionDataModel, plotModelArrayList);
        }
    }

    private void setCustomerDetailsToControls(CustomerDataModel customerDataModel, PhaseDataModel phaseDataModel, SessionDataModel sessionDataModel, ArrayList<PlotModel> plotModelArrayList) {
        Log.e(TAG, "setCustomerDetailsToControls: ");
        String currentUser = SharedPreference.getInstance(context).getStringSharedPreference(GlobalConstant.CURRENT_USER);
        Gson gson = new Gson();
        UserModel userModel = gson.fromJson(currentUser, UserModel.class);

        textViewFeedbackType.setText(sessionDataModel.getSess_type());
        textViewFeedbackCustomerName.setText(customerDataModel.getCust_first_name().concat(" ").concat(customerDataModel.getCust_last_name()));
        textViewFeedbackCustomerEmail.setText(customerDataModel.getCust_email());
        textViewFeedbackMobile.setText(customerDataModel.getCust_mobile());
        textViewFeedbackSalesPerson.setText(userModel.getUser_first_name());
        textViewFeedbackProjectName.setText(phaseDataModel.getPhas_name());
        textViewFeedbackProposalName.setText(customerDataModel.getTempProposalId());
        textViewFeedbackDate.setText(sessionDataModel.getCreated_at());
        if (MainActivity.mLocation != null) {
            textViewFeedbackLocation.setText(String.valueOf(MainActivity.mLocation.getLatitude()).concat(",").concat(String.valueOf(MainActivity.mLocation.getLongitude())));
        }

        setListToLikedPlotAdapters(plotModelArrayList);
    }

    private void setListToLikedPlotAdapters(ArrayList<PlotModel> plotModelArrayList) {
        Log.e(TAG, "setListToLikedPlotAdapters: ");
        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewFeedbackPlotLikes.setLayoutManager(llm);
        likePlotByCustomerAdapter = new LikePlotByCustomerAdapter(plotModelArrayList, context, this);
        recyclerViewFeedbackPlotLikes.setAdapter(likePlotByCustomerAdapter);
    }

    @Override
    public void onPlotSelect(int position) {
        Log.e(TAG, "onPlotSelect: ");
        MstPlotDataSource mstPlotDataSource = new MstPlotDataSource(context);
        if (!mstPlotDataSource.isPlotAvailable(plotModelArrayList.get(position).getPlot_id())) {
            alertManager.informationDialog("Plot Status", "Plot is not available");
            return;
        }

        MstProposalDetailsDataSource mstProposalDetailsDataSource = new MstProposalDetailsDataSource(context);
        if (mstProposalDetailsDataSource.isPlotInProposal(plotModelArrayList.get(position).getPlot_id(), customerDataModel.getTempProposalId())) {
            alertManager.informationDialog("Plot Status", "Plot is already added in this proposal.");
            return;
        }
        if (plotModelArrayList.get(position).getAddToPropasalFlag() == 0) {
            plotModelArrayList.get(position).setAddToPropasalFlag(1);
            setListToLikedPlotAdapters(plotModelArrayList);
        } else {
            plotModelArrayList.get(position).setAddToPropasalFlag(0);
            setListToLikedPlotAdapters(plotModelArrayList);
        }
    }

    @Override
    public void onClick(View v) {
        Log.e(TAG, "onClick: ");
        switch (v.getId()) {
            case R.id.buttonAudioFeedback:
                bottomSheetBehaviorForVideoFeedback.setState(BottomSheetBehavior.STATE_COLLAPSED);
                bottomSheetBehaviorForTextFeedback.setState(BottomSheetBehavior.STATE_COLLAPSED);

                if (bottomSheetBehaviorForAudioFeedback.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    bottomSheetBehaviorForAudioFeedback.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    bottomSheetBehaviorForAudioFeedback.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                imageButtonStopAudioRecording.setBackgroundColor(Color.LTGRAY);
                imageButtonStopAudioRecording.setEnabled(false);
                imageButtonPlayAudioRecording.setBackgroundColor(Color.LTGRAY);
                imageButtonPlayAudioRecording.setEnabled(false);
                imageButtonStartAudioRecording.setBackground(ContextCompat.getDrawable(context, R.drawable.ripple_effect));
                imageButtonStartAudioRecording.setEnabled(true);

                if (myCountDownTimer != null) {
                    myCountDownTimer.cancel();
                    progressBarForAudio.setProgress(0);
                    textViewCounter.setText(String.valueOf(0));
                }
                break;

            case R.id.buttonVideoFeedback:
                bottomSheetBehaviorForAudioFeedback.setState(BottomSheetBehavior.STATE_COLLAPSED);
                bottomSheetBehaviorForTextFeedback.setState(BottomSheetBehavior.STATE_COLLAPSED);

                if (bottomSheetBehaviorForVideoFeedback.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    bottomSheetBehaviorForVideoFeedback.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    bottomSheetBehaviorForVideoFeedback.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                break;

            case R.id.imageButtonStartAudioRecording:
                if (ContextCompat.checkSelfPermission(context, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(context, RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                    MarshMallowPermission.requestRecordAudioPermission(context);
                    MarshMallowPermission.requestStoragePermission(context);
                } else {
                    startRecording();
                }
                break;

            case R.id.imageButtonStopAudioRecording:
                stopRecording();
                break;


            case R.id.imageButtonPlayAudioRecording:
                playRecording();
                break;

            case R.id.imageButtonStopPlayRecord:
                stopPlayRecord();
                break;

            case R.id.buttonAudioNext:
                openTextFeedbackBottomSheet();
                break;

            case R.id.buttonSaveAndClose:
                saveAndCloseTextFeedback();
                break;

            case R.id.buttonVideoNext:
                openTextFeedbackBottomSheet();
                break;

            case R.id.buttonAddToProposal:
                if (isTextFeedbackTaken) {
                    if (likePlotByCustomerAdapter.getItemCount() > 0) {
                        addPlotsToProposal();
                    } else {
                        alertManager.informationDialog("Plot Like Status", "There is no plot for add to proposal");
                    }
                } else {
                    alertManager.informationDialog("Feedback", "Take Any one feedback first !");
                }
                break;

            case R.id.imageButtonStartVideoRecording:
                startVideoIntent();
                break;
        }
    }

    private void startVideoIntent() {
        Log.e(TAG, "startVideo: ");
        try {
            startActivityForResult(new Intent(MediaStore.ACTION_VIDEO_CAPTURE), GlobalConstant.VIDEO_FEEDBACK_REQUEST_CODE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void addPlotsToProposal() {
        Log.e(TAG, "addPlotsToProposal: ");
        /*
        1. get current plot id
        2. check status of the plot
        3. if available add to proposal
        4. if not available show an error

         */

        /*
         * 1. get current plot id
         * 2. get proposal id.
         * 3. get proposal details
         * 4. search current plot id in proposal details
         * 5. if found show an error.
         * 6. if not found add to proposal
         *
         */


        ArrayList<PlotModel> plotWhichHaveToAddIntoProposal = likePlotByCustomerAdapter.getAddablePlots();
        if (plotWhichHaveToAddIntoProposal.size() > 0) {
            if (customerDataModel.getTempProposalId() == null) {
                String insertedProposalId = addProposalHeader();
                MstProposalHeaderDataSource mstProposalHeaderDataSource = new MstProposalHeaderDataSource(context);
                mstProposalHeaderDataSource.updateProposalHeaderPropId(insertedProposalId);
                String prdtInsertedId = addProposalDetails(insertedProposalId, plotWhichHaveToAddIntoProposal);
                MstProposalDetailsDataSource mstProposalDetailsDataSource = new MstProposalDetailsDataSource(context);
                mstProposalDetailsDataSource.updatePrdtId(prdtInsertedId);
                Toast.makeText(context, "Proposal Added Successfully", Toast.LENGTH_SHORT).show();
                setResult(GlobalConstant.FEEDBACK_ACTIVITY_RESULT_CODE);
                finish();
            } else {
                MstProposalHeaderDataSource mstProposalHeaderDataSource = new MstProposalHeaderDataSource(context);
                mstProposalHeaderDataSource.updateProposalHeaderAsUpdated(customerDataModel.getTempProposalId());
                addProposalDetails(mstProposalHeaderDataSource.getProposalDataModelByProposalId(customerDataModel.getTempProposalId()), plotWhichHaveToAddIntoProposal);
            }
        } else {
            alertManager.informationDialog("Plot Selection", "Select plot for add to proposal");
        }
    }

    private String addProposalDetails(String insertedProposalHeaderClientId, ArrayList<PlotModel> plotWhichHaveToAddIntoProposal) {
        Log.e(TAG, "addProposalDetails: ");
        String prdtInsertedId = null;
        for (int i = 0; i < plotWhichHaveToAddIntoProposal.size(); i++) {
            MstProposalDetailsDataSource mstProposalDetailsDataSource = new MstProposalDetailsDataSource(context);
            ProposalDetailsDataModel proposalDetailsDataModel = new ProposalDetailsDataModel();
            proposalDetailsDataModel.setPrdt_propid(Integer.parseInt(insertedProposalHeaderClientId));
            proposalDetailsDataModel.setPrdt_charge_id("");
            proposalDetailsDataModel.setPrdt_base_price("");
            proposalDetailsDataModel.setPrdt_disc_amount("");
            proposalDetailsDataModel.setPrdt_sale_price("");
            proposalDetailsDataModel.setPrdt_disc_reason("");
            proposalDetailsDataModel.setPrdt_notes("");
            proposalDetailsDataModel.setCreated_at(DateUtil.getCurrentFormatDateAndTime());
            proposalDetailsDataModel.setUpdated_at(DateUtil.getCurrentFormatDateAndTime());
            proposalDetailsDataModel.setDeleted_at("");
            proposalDetailsDataModel.setPrdt_prod_id(plotWhichHaveToAddIntoProposal.get(i).getPlot_id());
            proposalDetailsDataModel.setPrdt_prod_type("");
            proposalDetailsDataModel.setPrdt_parent_id(0);
            proposalDetailsDataModel.setPrdt_disc_id(0);
            proposalDetailsDataModel.setPrdt_rate(plotWhichHaveToAddIntoProposal.get(i).getCat_rate());
            prdtInsertedId = mstProposalDetailsDataSource.insertProducts(proposalDetailsDataModel);
        }
        CfgCustomerGroupDataSource cfgCustomerGroupDataSource = new CfgCustomerGroupDataSource(context);
        if (cfgCustomerGroupDataSource.isCustomerNewlyAdded(customerDataModel.getCustomerTempGroupId())) {
            cfgCustomerGroupDataSource.updateGroupWithSyncStatusAsInserted(customerDataModel, phaseDataModel);
        } else {
            cfgCustomerGroupDataSource.updateGroupWithSyncStatusAsUpdated(customerDataModel, phaseDataModel);
        }
        return prdtInsertedId;
    }

    private void addProposalDetails(ProposalHeaderDataModel proposalHeaderDataModel, ArrayList<PlotModel> plotWhichHaveToAddIntoProposal) {
        Log.e(TAG, "addProposalDetails: ");
        for (int i = 0; i < plotWhichHaveToAddIntoProposal.size(); i++) {
            MstProposalDetailsDataSource mstProposalDetailsDataSource = new MstProposalDetailsDataSource(context);
            ProposalDetailsDataModel proposalDetailsDataModel = new ProposalDetailsDataModel();
            proposalDetailsDataModel.setPrdt_prop_client_id(proposalHeaderDataModel.getProp_client_id());
            proposalDetailsDataModel.setPrdt_propid(proposalHeaderDataModel.getProp_id());
            proposalDetailsDataModel.setPrdt_charge_id("");
            proposalDetailsDataModel.setPrdt_base_price("");
            proposalDetailsDataModel.setPrdt_disc_amount("");
            proposalDetailsDataModel.setPrdt_sale_price("");
            proposalDetailsDataModel.setPrdt_disc_reason("");
            proposalDetailsDataModel.setPrdt_notes("");
            proposalDetailsDataModel.setCreated_at(DateUtil.getCurrentFormatDateAndTime());
            proposalDetailsDataModel.setUpdated_at(DateUtil.getCurrentFormatDateAndTime());
            proposalDetailsDataModel.setDeleted_at("");
            proposalDetailsDataModel.setPrdt_prod_id(plotWhichHaveToAddIntoProposal.get(i).getPlot_id());
            proposalDetailsDataModel.setPrdt_prod_type("");
            proposalDetailsDataModel.setPrdt_parent_id(0);
            proposalDetailsDataModel.setPrdt_disc_id(0);
            proposalDetailsDataModel.setPrdt_rate(plotWhichHaveToAddIntoProposal.get(i).getCat_rate());
            mstProposalDetailsDataSource.insertProducts(proposalDetailsDataModel);
        }
        CfgCustomerGroupDataSource cfgCustomerGroupDataSource = new CfgCustomerGroupDataSource(context);
        if (cfgCustomerGroupDataSource.isCustomerNewlyAdded(customerDataModel.getCustomerTempGroupId())) {
            cfgCustomerGroupDataSource.updateGroupWithSyncStatusAsInserted(customerDataModel, phaseDataModel);
        } else {
            cfgCustomerGroupDataSource.updateGroupWithSyncStatusAsUpdated(customerDataModel, phaseDataModel);
        }
        Toast.makeText(context, "Proposal Added Successfully", Toast.LENGTH_SHORT).show();
        setResult(GlobalConstant.FEEDBACK_ACTIVITY_RESULT_CODE);
        finish();
    }

    private String addProposalHeader() {
        Log.e(TAG, "addNewProposal: ");
        MstProposalHeaderDataSource mstProposalHeaderDataSource = new MstProposalHeaderDataSource(context);
        ProposalHeaderDataModel proposalHeaderDataModel = new ProposalHeaderDataModel();
        proposalHeaderDataModel.setProp_spid(SharedPreference.getInstance(context).getIntSharedPreference(GlobalConstant.UserId));
        proposalHeaderDataModel.setProp_contact_id(customerDataModel.getCustomerTempGroupId());
        proposalHeaderDataModel.setProp_date(DateUtil.getCurrentFormatDateAndTime());
        proposalHeaderDataModel.setProp_stage("draft");
        proposalHeaderDataModel.setProp_devl_id(SharedPreference.getInstance(context).getIntSharedPreference(GlobalConstant.DeveloperId));
        proposalHeaderDataModel.setProp_status("");
        proposalHeaderDataModel.setProp_proj_id(phaseDataModel.getPhas_proj_id());
        proposalHeaderDataModel.setProp_phas_id(phaseDataModel.getPhas_id());
        proposalHeaderDataModel.setProp_prod_total("");
        proposalHeaderDataModel.setProp_charge_total("");
        proposalHeaderDataModel.setProp_disc_total("");
        proposalHeaderDataModel.setProp_net("");
        proposalHeaderDataModel.setProp_validity(0);
        proposalHeaderDataModel.setProp_notes("");
        proposalHeaderDataModel.setProp_template("");
        proposalHeaderDataModel.setProp_attachments("");
        proposalHeaderDataModel.setCreated_at(DateUtil.getCurrentFormatDateAndTime());
        proposalHeaderDataModel.setDeleted_at("");
        return String.valueOf(mstProposalHeaderDataSource.insertProposal(proposalHeaderDataModel));
    }

    private void saveVideoAndJumpToTextFeedbackScreen() {
        Log.e(TAG, "saveVideoAndJumpToTextFeedbackScreen: ");
        openTextFeedbackBottomSheet();
    }

    private void saveAndCloseTextFeedback() {
        Log.e(TAG, "saveAndCloseTextFeedback: ");
        if (adapterForFeedback != null) {
            AnswerDataModel answerDataModel = new AnswerDataModel();
            answerDataModel.setAns_spid(SharedPreference.getInstance(context).getIntSharedPreference(GlobalConstant.UserId));
            answerDataModel.setAns_cust_id(String.valueOf(customerDataModel.getCust_unique_id()));
            answerDataModel.setAns_group_id(customerDataModel.getCustomerTempGroupId());
            TrnFeedbackDataSource trnFeedbackDataSource = new TrnFeedbackDataSource(context);
            trnFeedbackDataSource.insertFeedbackDetails(adapterForFeedback.getList(), answerDataModel);

            insertMediaFeedbackIntoContent();

            isTextFeedbackTaken = true;
            bottomSheetBehaviorForTextFeedback.setState(BottomSheetBehavior.STATE_COLLAPSED);
            if (likePlotByCustomerAdapter.getItemCount() > 0) {
                alertManager.confirmationDialog(GlobalConstant.ADD_PROPOSAL_CONFIRMATION, "Do you want to add plot in the proposal");
            } else {
                setResult(GlobalConstant.FEEDBACK_ACTIVITY_RESULT_CODE);
                finish();
            }
        }
    }

    private void insertMediaFeedbackIntoContent() {
        Log.e(TAG, "insertMediaFeedbackIntoContent: ");
        File audioFile = new File(audioSavePathInDevice);
        if (audioFile.exists()) {
            saveIntoContent(audioSavePathInDevice, "mp3", audioFileName);
        }

        File videoFile = new File(videoSavePathInDevice);
        if (videoFile.exists()) {
            saveIntoContent(videoSavePathInDevice, "mp4", videoFileName);
        }
    }

    private void saveIntoContent(String filePath, String ContentType, String fileName) {
        Log.e(TAG, "saveIntoContent: ");
        ContentDataModel contentDataModel = new ContentDataModel();
        contentDataModel.setCont_devc_id(Utility.getDeviceId(context));
        contentDataModel.setCont_devl_id(SharedPreference.getInstance(context).getIntSharedPreference(GlobalConstant.DeveloperId));
        contentDataModel.setCont_project_id(phaseDataModel.getPhas_proj_id());
        contentDataModel.setCont_phase_id(phaseDataModel.getPhas_id());
        contentDataModel.setCont_file_type(ContentType);
        contentDataModel.setCont_file_location(filePath);
        contentDataModel.setCont_cust_id(customerDataModel.getCust_unique_id());
        contentDataModel.setCont_description("This is a feedback media.");
        if (MainActivity.mLocation != null) {
            contentDataModel.setCont_latitude(String.valueOf(MainActivity.mLocation.getLatitude()));
            contentDataModel.setCont_longitude(String.valueOf(MainActivity.mLocation.getLongitude()));
        }
        contentDataModel.setCont_status(0);
        contentDataModel.setCont_type("feedback");
        contentDataModel.setCreated_at(DateUtil.getCurrentFormatDateAndTime());
        contentDataModel.setUpdated_at(DateUtil.getCurrentFormatDateAndTime());
        contentDataModel.setCont_sync_status(GlobalConstant.INSERTED);
        contentDataModel.setCont_file_name(fileName);

        CfgContentDataSource contentDataSource = new CfgContentDataSource(context);
        contentDataSource.insertProjectPhotoPath(contentDataModel);
    }

    private void openTextFeedbackBottomSheet() {
        Log.e(TAG, "openTextFeedbackBottomSheet: ");
        bottomSheetBehaviorForAudioFeedback.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bottomSheetBehaviorForVideoFeedback.setState(BottomSheetBehavior.STATE_COLLAPSED);
        if (bottomSheetBehaviorForTextFeedback.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
            bottomSheetBehaviorForTextFeedback.setState(BottomSheetBehavior.STATE_EXPANDED);
        } else {
            bottomSheetBehaviorForTextFeedback.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }


        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewForFeedbackQuestions.setLayoutManager(llm);

        ArrayList<QuestionsDataModel> questionDataModelArrayList;
        MstFeedbackMasterDataSource mstFeedbackMasterDataSource = new MstFeedbackMasterDataSource(context);
        questionDataModelArrayList = mstFeedbackMasterDataSource.getAllQuestions(phaseDataModel.getPhas_id());

        adapterForFeedback = new AdapterForFeedback(questionDataModelArrayList);
        recyclerViewForFeedbackQuestions.setAdapter(adapterForFeedback);
    }

//    private void saveAudioAndJumpToTextFeedbackScreen() {
//        Log.e(TAG, "saveAudioAndJumpToTextFeedbackScreen: " + audioSavePathInDevice);
//        File file = new File();
//        if (file.exists()) {
//            openTextFeedbackBottomSheet();
//        } else {
//            Log.e(TAG, "saveAudioAndJumpToTextFeedbackScreen: Please take audio feedback first");
////            openTextFeedbackBottomSheet();
//        }
//    }

    private void stopPlayRecord() {
        Log.e(TAG, "stopPlayRecord: ");
        imageButtonStopAudioRecording.setBackgroundColor(Color.LTGRAY);
        imageButtonStopAudioRecording.setEnabled(false);

        imageButtonStartAudioRecording.setBackground(ContextCompat.getDrawable(context, R.drawable.ripple_effect));
        imageButtonStartAudioRecording.setEnabled(true);

        imageButtonStopPlayRecord.setVisibility(View.GONE);
        imageButtonStopAudioRecording.setVisibility(View.VISIBLE);
        imageButtonStopAudioRecording.setBackgroundColor(Color.LTGRAY);
        imageButtonStopAudioRecording.setEnabled(false);

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaRecorderReady();
        }
    }

    private void playRecording() {
        Log.e(TAG, "playRecording: ");
        imageButtonStopAudioRecording.setBackgroundColor(Color.LTGRAY);
        imageButtonStopAudioRecording.setEnabled(false);

        imageButtonStartAudioRecording.setBackgroundColor(Color.LTGRAY);
        imageButtonStartAudioRecording.setEnabled(false);

        imageButtonStopPlayRecord.setVisibility(View.VISIBLE);
        imageButtonStopPlayRecord.setBackground(ContextCompat.getDrawable(context, R.drawable.ripple_effect));

        imageButtonStopAudioRecording.setVisibility(View.GONE);

        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(audioSavePathInDevice);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();
        Toast.makeText(context, "Recording Playing", Toast.LENGTH_LONG).show();
    }


    private void startRecording() {
        Log.e(TAG, "startRecord: ");
        mediaRecorderReady();
        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        imageButtonStartAudioRecording.setBackgroundColor(Color.LTGRAY);
        imageButtonStartAudioRecording.setEnabled(false);

        imageButtonPlayAudioRecording.setBackgroundColor(Color.LTGRAY);
        imageButtonPlayAudioRecording.setEnabled(false);

        imageButtonStopAudioRecording.setBackground(ContextCompat.getDrawable(context, R.drawable.ripple_effect));
        imageButtonStopAudioRecording.setEnabled(true);

        Toast.makeText(context, "Recording started", Toast.LENGTH_LONG).show();
        showProgressBar();
    }

    private void showProgressBar() {
        Log.e(TAG, "showProgressBar: ");
        progressBarForAudio.setMax(60);
        progressBarForAudio.setProgress(progressStatus);
        myCountDownTimer = new MyCountDownTimer(62000, 1000);
        myCountDownTimer.start();
    }

    public void mediaRecorderReady() {
        Log.e(TAG, "mediaRecorderReady: ");
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(audioSavePathInDevice);
    }

    private void stopRecording() {
        Log.e(TAG, "stopRecording: ");
        mediaRecorder.stop();
        imageButtonPlayAudioRecording.setBackground(ContextCompat.getDrawable(context, R.drawable.ripple_effect));
        imageButtonPlayAudioRecording.setEnabled(true);

        imageButtonStopAudioRecording.setBackgroundColor(Color.LTGRAY);
        imageButtonStopAudioRecording.setEnabled(false);

        imageButtonStartAudioRecording.setBackground(ContextCompat.getDrawable(context, R.drawable.ripple_effect));
        imageButtonStartAudioRecording.setEnabled(true);

        myCountDownTimer.cancel();
        progressBarForAudio.setProgress(progressStatus);
        textViewCounter.setText(String.valueOf(progressStatus));

        Toast.makeText(context, "Recording Completed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onYesResponse(String title) {
        Log.e(TAG, "onYesResponse: ");
        switch (title) {
            case GlobalConstant.ADD_PROPOSAL_CONFIRMATION:


                break;
        }
    }

    @Override
    public void onNoResponse(String title) {
        Log.e(TAG, "onNoResponse: ");
        switch (title) {
            case GlobalConstant.ADD_PROPOSAL_CONFIRMATION:
                setResult(GlobalConstant.FEEDBACK_ACTIVITY_RESULT_CODE);
                finish();
                break;
        }

    }

    @Override
    public void onOkResponse(String title) {
        Log.e(TAG, "onOkResponse: ");
    }

    public class MyCountDownTimer extends CountDownTimer {

        MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            progressStatus = (int) (62000 - millisUntilFinished) / 1000;
            progressBarForAudio.setProgress(progressStatus);
            textViewCounter.setText(String.valueOf(progressStatus) + "/60");
        }

        @Override
        public void onFinish() {
            Log.e(TAG, "onFinish: ");
            progressBarForAudio.setProgress(progressStatus);
            stopRecording();
        }
    }
}