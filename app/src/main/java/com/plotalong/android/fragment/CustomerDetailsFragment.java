package com.plotalong.android.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.plotalong.android.R;
import com.plotalong.android.dialogManager.AlertManager;
import com.plotalong.android.model.quickSyncModel.CustomerDataModel;
import com.plotalong.android.util.DateUtil;
import com.plotalong.android.util.GlobalConstant;
import com.plotalong.android.util.SharedPreference;
import com.plotalong.android.util.Utility;

/**
 * Created by shantanu on 14/10/17.
 */

public class CustomerDetailsFragment extends Fragment {
    View view;
    private String TAG = CustomerDetailsFragment.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    private EditText custFirstNameEditText, custLastNameEditText, custMobileEditText, custLandlineEditText, custEmailEditText, custBudgetEditText, custAreaEditText, custPreferLocationEditText, custOccupationEditText, custSonOfEditText, custAdharEditText, custPanEditText;
    private RadioGroup custSexRadioGroup, custMarriedStatusRadioGroup;
    private AlertManager alertManager;
    private CustomerDataModel customerDataModel;
    private static boolean fragmentInitiate = false;
    private Button buttonSubmit;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: ");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView: ");
        if (!fragmentInitiate) {
            view = inflater.inflate(R.layout.fragment_customer_details_and_edit, container, false);

            init();
            initiateEditTexts();
            fragmentInitiate = true;
        }
        return view;
    }

    private void init() {
        Log.e(TAG, "initAllControls: ");
        custFirstNameEditText = view.findViewById(R.id.fragCustFirstNameEditText);
        custLastNameEditText = view.findViewById(R.id.fragCustLastNameEditText);
        custMobileEditText = view.findViewById(R.id.fragCustMobileEditText);
        custLandlineEditText = view.findViewById(R.id.fragCustLandlineEditText);
        custEmailEditText = view.findViewById(R.id.fragCustEmailEditText);
        custBudgetEditText = view.findViewById(R.id.fragCustBudgetEditText);
        custAreaEditText = view.findViewById(R.id.fragCustAreaEditText);
        custPreferLocationEditText = view.findViewById(R.id.fragCustPreferLocationEditText);
        custOccupationEditText = view.findViewById(R.id.fragCustOccupationEditText);
        custSonOfEditText = view.findViewById(R.id.fragCustSonOfEditText);
        custAdharEditText = view.findViewById(R.id.fragCustAdharEditText);
        custPanEditText = view.findViewById(R.id.fragCustPanEditText);
        custSexRadioGroup = view.findViewById(R.id.fragCustSexRadioGroup);
        custMarriedStatusRadioGroup = view.findViewById(R.id.fragCustMarriedStatusRadioGroup);

        buttonSubmit = view.findViewById(R.id.fragCustFormUpdateButton);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitAllCustomerDetailsInLocalDatabase();
            }
        });
    }

    private void initiateEditTexts(){
        Log.e(TAG, "initiateEditTexts: " + customerDataModel.getCust_first_name() );
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

    public void setCustomerDataModel(CustomerDataModel customerDataModel) {
        Log.e(TAG, "setCustomerDetailsToControls: ");
        this.customerDataModel = customerDataModel;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: " );
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: " );
        fragmentInitiate = false;
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
            customerDataModel.setCust_devl_id(SharedPreference.getInstance(getContext()).getIntSharedPreference(GlobalConstant.DeveloperId));
            customerDataModel.setCust_spid(SharedPreference.getInstance(getContext()).getIntSharedPreference(GlobalConstant.UserId));
            customerDataModel.setCreated_at(DateUtil.getCurrentFormatDateAndTime());
            customerDataModel.setUpdated_at(DateUtil.getCurrentFormatDateAndTime());
//            customerDataModel.setCust_marital_status(((RadioButton) findViewById(custMarriedStatusRadioGroup.getCheckedRadioButtonId())).getText().toString());

//            MstCustomerDataSource customerDataSource = new MstCustomerDataSource(context);
//            long customerInsertedId = customerDataSource.insertCustomerAndGetInsertedId(customerDataModel);
//            createGroupIdForNewCustomerAndInsert(customerInsertedId);

            Toast.makeText(getContext(), "Customer Added Successfully !", Toast.LENGTH_SHORT).show();
        }
    }
}
