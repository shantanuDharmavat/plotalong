package com.plotalong.android.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.plotalong.android.R;
import com.plotalong.android.dataSource.CfgCustomerGroupDataSource;
import com.plotalong.android.dataSource.MstCustomerDataSource;
import com.plotalong.android.dialogManager.AlertManager;
import com.plotalong.android.listener.YesNoAlertListener;
import com.plotalong.android.model.quickSyncModel.CustomerDataModel;
import com.plotalong.android.model.quickSyncModel.CustomerGroupDataModel;
import com.plotalong.android.util.AndroidBmpUtil;
import com.plotalong.android.util.DateUtil;
import com.plotalong.android.util.GlobalConstant;
import com.plotalong.android.util.MarshmallowIntentId;
import com.plotalong.android.util.SharedPreference;
import com.plotalong.android.util.Utility;

import java.io.File;
import java.io.IOException;

public class AddCustomerActivity extends AppCompatActivity implements YesNoAlertListener {
    private static final int REQUEST_CAMERA = 101;
    private static final int SELECT_FILE = 102;
    private String TAG = AddCustomerActivity.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    private EditText custFirstNameEditText, custLastNameEditText, custMobileEditText, custLandlineEditText, custEmailEditText, custBudgetEditText, custAreaEditText, custPreferLocationEditText, custOccupationEditText, custSonOfEditText, custAdharEditText, custPanEditText;
    private RadioGroup custSexRadioGroup, custMarriedStatusRadioGroup;
    private Context context;
    private AlertManager alertManager;
    private ImageView custProfileImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        context = AddCustomerActivity.this;
//        initToolBar();
        initAllControls();
        initRequiredClasses();
        getCustomerModel();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    private void initToolBar() {
        Log.e(TAG, "initToolBar: ");
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setCustomerDetailsToControls(CustomerDataModel customerDataModel) {
        Log.e(TAG, "setCustomerDetailsToControls: ");
        custFirstNameEditText.setText(customerDataModel.getCust_first_name());
        custLastNameEditText.setText(customerDataModel.getCust_last_name());
        custMobileEditText.setText(customerDataModel.getCust_mobile());
        custLandlineEditText.setText(customerDataModel.getCust_landline());
        custEmailEditText.setText(customerDataModel.getCust_email());
        custBudgetEditText.setText(customerDataModel.getCust_budget());
        custAreaEditText.setText(customerDataModel.getCust_area());
        custPreferLocationEditText.setText(customerDataModel.getCust_preferred_location());
        custOccupationEditText.setText(customerDataModel.getCust_occupation());
        custSonOfEditText.setText(customerDataModel.getCust_son_of());
        custAdharEditText.setText(customerDataModel.getCust_address());
        custPanEditText.setText(customerDataModel.getCust_pan_no());
        custFirstNameEditText.requestFocus();
    }

    private void getCustomerModel() {
        Log.e(TAG, "getCustomerDataModel: ");
        Gson gson = new Gson();
        CustomerDataModel customerDataModel = gson.fromJson(getIntent().getStringExtra("CustomerObject"), CustomerDataModel.class);
        if (customerDataModel != null && customerDataModel.getCust_unique_id() > 0) {
            setCustomerDetailsToControls(customerDataModel);
        }
    }

    private void initRequiredClasses() {
        Log.e(TAG, "initRequiredClasses: ");
        alertManager = new AlertManager(context, this);
    }

    private void initAllControls() {
        Log.e(TAG, "initAllControls: ");
        custFirstNameEditText = findViewById(R.id.custFirstNameEditText);
        custLastNameEditText = findViewById(R.id.custLastNameEditText);
        custMobileEditText = findViewById(R.id.custMobileEditText);
        custLandlineEditText = findViewById(R.id.custLandlineEditText);
        custEmailEditText = findViewById(R.id.custEmailEditText);
        custBudgetEditText = findViewById(R.id.custBudgetEditText);
        custAreaEditText = findViewById(R.id.custAreaEditText);
        custPreferLocationEditText = findViewById(R.id.custPreferLocationEditText);
        custOccupationEditText = findViewById(R.id.custOccupationEditText);
        custSonOfEditText = findViewById(R.id.custSonOfEditText);
        custAdharEditText = findViewById(R.id.custAdharEditText);
        custPanEditText = findViewById(R.id.custPanEditText);
        custSexRadioGroup = findViewById(R.id.custSexRadioGroup);
        custMarriedStatusRadioGroup = findViewById(R.id.custMarriedStatusRadioGroup);

        /*custProfileImageView = (ImageView) findViewById(R.id.custProfileImageView);
        custProfileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FileHandlingUtil.isExternalStorageWritable()) {
                    if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        MarshMallowPermission.requestStoragePermission(context);
                    } else {
                        getImageFromUser();
                    }
                } else {
                    Toast.makeText(context, getResources().getString(R.string.storage_not_available), Toast.LENGTH_SHORT).show();
                }
            }
        });*/


        findViewById(R.id.custFormClearButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllControls();
            }
        });

        findViewById(R.id.custFormSubmitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitAllCustomerDetailsInLocalDatabase();
            }
        });
    }

    private void createGroupIdForNewCustomerAndInsert(String customerInsertedId) {
        Log.e(TAG, "createGroupIdForNewCustomer: ");
        CustomerGroupDataModel customerGroupDataModel = new CustomerGroupDataModel();
        customerGroupDataModel.setCreated_at(DateUtil.getCurrentFormatDateAndTime());
        customerGroupDataModel.setUpdated_at(DateUtil.getCurrentFormatDateAndTime());
        customerGroupDataModel.setCgc_member_type(GlobalConstant.po);
        customerGroupDataModel.setCgc_status(GlobalConstant.STRING_warmLead);
        customerGroupDataModel.setCgc_phas_id(0);
        customerGroupDataModel.setCgc_group_id(Utility.createCustomerGroup(Integer.parseInt(customerInsertedId)));
        customerGroupDataModel.setCgc_cust_id(Integer.parseInt(customerInsertedId));
        CfgCustomerGroupDataSource cfgCustomerGroupDataSource = new CfgCustomerGroupDataSource(context);
        cfgCustomerGroupDataSource.insertCustomerGroup(customerGroupDataModel);
    }

    private void submitAllCustomerDetailsInLocalDatabase() {
        Log.e(TAG, "submitAllCustomerDetailsInLocalDatabase: ");
        if (isAllFieldValid()) {
            CustomerDataModel customerDataModel = new CustomerDataModel();
            customerDataModel.setCust_first_name(custFirstNameEditText.getText().toString());
            customerDataModel.setCust_last_name(custLastNameEditText.getText().toString());
            customerDataModel.setCust_mobile(custMobileEditText.getText().toString());
            customerDataModel.setCust_landline(custLandlineEditText.getText().toString());
            customerDataModel.setCust_email(custEmailEditText.getText().toString());
            customerDataModel.setCust_area(custAreaEditText.getText().toString());
            customerDataModel.setCust_budget(custBudgetEditText.getText().toString());
            customerDataModel.setCust_occupation(custOccupationEditText.getText().toString());
            customerDataModel.setCust_pan_no(custPanEditText.getText().toString());
            customerDataModel.setCust_preferred_location(custPreferLocationEditText.getText().toString());
            customerDataModel.setCust_son_of(custSonOfEditText.getText().toString());
            customerDataModel.setCust_devl_id(SharedPreference.getInstance(context).getIntSharedPreference(GlobalConstant.DeveloperId));
            customerDataModel.setCust_spid(SharedPreference.getInstance(context).getIntSharedPreference(GlobalConstant.UserId));
            customerDataModel.setCreated_at(DateUtil.getCurrentFormatDateAndTime());
            customerDataModel.setUpdated_at(DateUtil.getCurrentFormatDateAndTime());
            customerDataModel.setCust_requirement("KAILAS");

            MstCustomerDataSource customerDataSource = new MstCustomerDataSource(context);
            String customerInsertedId = customerDataSource.insertCustomerAndReturnInsertedId(customerDataModel);
            customerDataSource.updateCustUniqueId(customerInsertedId);
            createGroupIdForNewCustomerAndInsert(customerInsertedId);
            clearAllControls();
            Toast.makeText(context, "Customer Added Successfully !", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private boolean isAllFieldValid() {
        Log.e(TAG, "isAllFieldValid: ");
        if (custFirstNameEditText.getText().toString().isEmpty()) {
            alertManager.informationDialog("Error", "Please enter valid First Name !");
            custFirstNameEditText.requestFocus();
            return false;
        } else if (custLastNameEditText.getText().toString().isEmpty()) {
            alertManager.informationDialog("Error", "Please enter valid Last Name !");
            custLastNameEditText.requestFocus();
            return false;
        } else if (custMobileEditText.getText().toString().isEmpty()) {
            alertManager.informationDialog("Error", "Please enter Mobile Number !");
            custMobileEditText.requestFocus();
            return false;
        } else if ((custMobileEditText.getText().toString().length() != 10)) {
            alertManager.informationDialog("Error", "Please enter valid Mobile Number !");
            custMobileEditText.requestFocus();
            return false;
        } else if (custEmailEditText.getText().toString().isEmpty()) {
            alertManager.informationDialog("Error", "Please enter Email !");
            custEmailEditText.requestFocus();
            return false;
        } else if (!Utility.isValidEmail(custEmailEditText.getText().toString())) {
            alertManager.informationDialog("Error", "Please enter valid Email !");
            custEmailEditText.requestFocus();
            return false;
        } else if (custAreaEditText.getText().toString().isEmpty()) {
            alertManager.informationDialog("Error", "Please enter valid Customer Area !");
            custAreaEditText.requestFocus();
            return false;
        } else if (custPreferLocationEditText.getText().toString().isEmpty()) {
            alertManager.informationDialog("Error", "Please enter valid Prefer Location !");
            custPreferLocationEditText.requestFocus();
            return false;
        } else if (custOccupationEditText.getText().toString().isEmpty()) {
            alertManager.informationDialog("Error", "Please enter valid Occupation !");
            custOccupationEditText.requestFocus();
            return false;
        } else if (custSonOfEditText.getText().toString().isEmpty()) {
            alertManager.informationDialog("Error", "Please enter valid Son of !");
            custSonOfEditText.requestFocus();
            return false;
        } else if (custAdharEditText.getText().toString().isEmpty()) {
            alertManager.informationDialog("Error", "Please enter valid Adhar Number !");
            custAdharEditText.requestFocus();
            return false;
        } else if (custPanEditText.getText().toString().isEmpty()) {
            alertManager.informationDialog("Error", "Please enter valid Pan Number !");
            custPanEditText.requestFocus();
            return false;
        }
        return true;
    }

    private void clearAllControls() {
        Log.e(TAG, "clearAllControls: ");
        custFirstNameEditText.setText("");
        custLastNameEditText.setText("");
        custMobileEditText.setText("");
        custLandlineEditText.setText("");
        custEmailEditText.setText("");
        custBudgetEditText.setText("");
        custAreaEditText.setText("");
        custPreferLocationEditText.setText("");
        custOccupationEditText.setText("");
        custSonOfEditText.setText("");
        custAdharEditText.setText("");
        custPanEditText.setText("");
        custFirstNameEditText.requestFocus();
    }

    private void getImageFromUser() {
        Log.e(TAG, "getImageFromUser: ");
        final CharSequence[] items = {"From Camera", "From Gallery"};
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Select Photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("From Camera")) {
                    cameraIntent();
                } else if (items[item].equals("From Gallery")) {
                    galleryIntent();
                }
            }
        });
        builder.show();
    }

    private void cameraIntent() {
        Log.e(TAG, "cameraIntent: ");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    private void galleryIntent() {
        Log.e(TAG, "galleryIntent: ");
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Photo"), SELECT_FILE);
    }

    @Override
    public void onYesResponse(String title) {
        Log.e(TAG, "onYesResponse: ");
    }

    @Override
    public void onNoResponse(String title) {
        Log.e(TAG, "onNoResponse: ");
    }

    @Override
    public void onOkResponse(String title) {
        Log.e(TAG, "onOkResponse: ");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e(TAG, "onActivityResult: ");
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void onSelectFromGalleryResult(Intent data) {
        Log.e(TAG, "onSelectFromGalleryResult: ");
        Bitmap bitmap = null;
        if (data != null) {
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        custProfileImageView.setImageBitmap(bitmap);
    }

    private void onCaptureImageResult(Intent data) {
        Log.e(TAG, "onCaptureImageResult: ");
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        File destination = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".png");
        new AndroidBmpUtil().save(bitmap, String.valueOf(destination));
        custProfileImageView.setImageBitmap(bitmap);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.e(TAG, "onRequestPermissionsResult: ");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MarshmallowIntentId.WRITE_EXTERNAL_STORAGE_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getImageFromUser();
                } else {
                    Toast.makeText(context, getResources().getString(R.string.storage_permission_denied), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Log.e(TAG, "onBackPressed: ");
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}