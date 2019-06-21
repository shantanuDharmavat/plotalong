package com.plotalong.android.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.plotalong.android.R;
import com.plotalong.android.adapter.PagerAdapter;
import com.plotalong.android.model.quickSyncModel.CustomerDataModel;
import com.plotalong.android.util.GlobalConstant;
import com.plotalong.android.util.Singleton;
import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Created by shantanu on 13/10/17.
 */

public class CustomerActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {
    private static final String TAG = CustomerActivity.class.getSimpleName();
    private TextView custName;
    private TextView custEmail;
    private TextView custPhone;
    private ImageView imageView;
    private CustomerDataModel customerDataModel;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        initControls();
        getIntentDataAndSetToControls();
        initViewPagerAndTabLayout();
    }

    private void initViewPagerAndTabLayout() {
        Log.e(TAG, "initViewPagerAndTabLayout: ");
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Details"));
        tabLayout.addTab(tabLayout.newTab().setText("Activity"));
        tabLayout.addTab(tabLayout.newTab().setText("Visits"));

        viewPager = findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), customerDataModel);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(this);
    }

    private void getIntentDataAndSetToControls() {
        Log.e(TAG, "getIntentDataAndSetToControls: ");
        String customerModelString = getIntent().getStringExtra("customer_model");
        Gson gson = new Gson();
        customerDataModel = gson.fromJson(customerModelString, CustomerDataModel.class);

        custName.setText(customerDataModel.getCust_first_name().concat(" ").concat(customerDataModel.getCust_last_name()));
        custEmail.setText(customerDataModel.getCust_email());
        custPhone.setText(customerDataModel.getCust_mobile());
        int DeveloperId = Singleton.getInstance().getDeveloperId();
        File file = new File(GlobalConstant.PROJECT_FOLDER_PATH + DeveloperId + "/customers/" + customerDataModel.getCust_email() + ".jpg");

        Picasso.with(this)
                .load(file)
                .placeholder(getResources().getDrawable(R.drawable.ic_user_image))
                .into(imageView);
    }

    private void initControls() {
        Log.e(TAG, "initControls: ");
        custName = findViewById(R.id.customerName);
        custEmail = findViewById(R.id.customerEmail);
        custPhone = findViewById(R.id.customerPhone);
        imageView = findViewById(R.id.customerProfilePlaceholder);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Log.e(TAG, "onTabSelected: ");
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        Log.e(TAG, "onTabUnselected: ");
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        Log.e(TAG, "onTabReselected: ");
    }
}