package com.plotalong.android.fragment;

/**
 * Created by shantanu on 5/6/17.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.content.res.ColorStateList;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.gson.Gson;
import com.plotalong.android.R;
import com.plotalong.android.activity.AddCustomerActivity;
import com.plotalong.android.activity.CustomerActivity;
import com.plotalong.android.activity.FeedbackActivity;
import com.plotalong.android.activity.MainActivity;
import com.plotalong.android.adapter.CustomerAdapter;
import com.plotalong.android.adapter.ProposalAdapter;
import com.plotalong.android.adapter.SelectedCustomerAdapter;
import com.plotalong.android.dataSource.CfgContentDataSource;
import com.plotalong.android.dataSource.CfgCustomerGroupDataSource;
import com.plotalong.android.dataSource.MstCustomerDataSource;
import com.plotalong.android.dataSource.MstPlotDataSource;
import com.plotalong.android.dataSource.MstProposalHeaderDataSource;
import com.plotalong.android.dataSource.TrnSessionDataSource;
import com.plotalong.android.dataSource.TrnTraceDetailsDataSource;
import com.plotalong.android.databinding.FragmentMapBinding;
import com.plotalong.android.dialogManager.AlertManager;
import com.plotalong.android.helper.ConnectivityReceiver;
import com.plotalong.android.listener.CustomerRecyclerListener;
import com.plotalong.android.listener.DirectionsListener;
import com.plotalong.android.listener.ProposalRecyclerListener;
import com.plotalong.android.listener.SelectedCustomerExpandOptionListener;
import com.plotalong.android.listener.YesNoAlertListener;
import com.plotalong.android.model.commonModel.DirectionFromDatabaseModel;
import com.plotalong.android.model.commonModel.LocationModel;
import com.plotalong.android.model.direction.DirectionResults;
import com.plotalong.android.model.geoGalleryModel.ContentDataModel;
import com.plotalong.android.model.nearbyModel.CategoryResult;
import com.plotalong.android.model.nearbyModel.Nearby;
import com.plotalong.android.model.plotJsonModel.PlotJsonModel;
import com.plotalong.android.model.quickSyncModel.CustomerDataModel;
import com.plotalong.android.model.quickSyncModel.CustomerGroupDataModel;
import com.plotalong.android.model.quickSyncModel.PhaseDataModel;
import com.plotalong.android.model.quickSyncModel.PlotModel;
import com.plotalong.android.model.quickSyncModel.ProposalHeaderDataModel;
import com.plotalong.android.model.quickSyncModel.SessionDataModel;
import com.plotalong.android.requestResponseManager.Directions;
import com.plotalong.android.util.BubbleTransformation;
import com.plotalong.android.util.DateUtil;
import com.plotalong.android.util.DirectionDecode;
import com.plotalong.android.util.GetLocationDistance;
import com.plotalong.android.util.GlobalConstant;
import com.plotalong.android.util.MapSettings;
import com.plotalong.android.util.SharedPreference;
import com.plotalong.android.util.TileProviderUtil;
import com.plotalong.android.util.Utility;
import com.sa90.materialarcmenu.ArcMenu;

import net.cachapa.expandablelayout.ExpandableLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapFragment extends Fragment implements OnMapReadyCallback,
        View.OnClickListener,
        DirectionsListener,
        CustomerRecyclerListener,
        SelectedCustomerExpandOptionListener,
        YesNoAlertListener,
        GoogleMap.OnPolygonClickListener,
        ProposalRecyclerListener, GoogleMap.OnMarkerClickListener {
    public SupportMapFragment mapFragment;
    public GoogleMap mMap;
    public ArcMenu arcMenu;
    public RecyclerView selectedCustomerRecyclerView;
    public Button navigationButton;
    public Button searchCustomerButton;
    MapSettings mapSettings;
    FragmentMapBinding fragmentMapBinding;
    BottomSheetBehavior mBottomSheetBehaviorCustomerSearch;
    LatLngBounds.Builder boundriesBuilder;
    Location mLocation;
    TextView tv1, tv2, tv3, tv4;
    double siteDistanceBetweenEdgeAndCenter = 0;
    Polygon phasePolygon;
    boolean autoRecordToggle = true;
    private String TAG = MapFragment.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    private View rootView;
    private CustomerAdapter customerAdapter;
    private CoordinatorLayout customerSearchCoordinatorLayout;
    private PhaseDataModel phaseDataModel;
    private Polyline routePolyline;
    private SelectedCustomerAdapter selectedCustomerAdapter;
    private TileOverlay tileOverlay = null;
    private Marker endMarker;
    private FloatingActionButton fab3, fab1, fab2, fab4;
    private LatLng siteCenterLatLng;
    private List<LatLng> latLongList;
    private List<Marker> categoryMarkerList = new ArrayList<>();
    private ArrayList<CustomerDataModel> selectedCustomerItemList = new ArrayList<>();
    private ArrayList<CustomerDataModel> customerDataModelArrayList;
    private Map<Polygon, PlotModel> plotModelMap;
    private Map<ContentDataModel, Marker> contentDataModelMarkerHashMap;
    private Map<Marker, ArrayList<ContentDataModel>> markerContentDataModelHashMap;
    private RecyclerView customerRecyclerView;
    private String traceId;
    private int selectedCustomerPosition;
    private int mapType = 1;
    private int getSessionLocationFromDatabaseCount = 0;
    private int arcMenuToggle = 0;
    private int contentRange = 100000;
    private int distanceVariable = 100;
    private boolean siteVisitRecordToggle = false;
    private boolean siteEnterBol = false;
    private boolean tileVisibilityBoolean = false;
    private boolean focusBol = false;
    private boolean galleryBool = false;
    private AlertManager alertManager;
    private RelativeLayout rootRL;
    private ProposalAdapter proposalAdapter;
    private ArrayList<CustomerDataModel> tempSelectedCustomer = new ArrayList<>();
    private ExpandableLayout tempExpandableLayoutForSelectedCustomer;
    private ArrayList<ContentDataModel> locationContentList;
    private ArrayList<ContentDataModel> contentWithinRangeList = new ArrayList<>();
    private ArrayList<Marker> contentMarkers = new ArrayList<>();
    private TextView selectedCustomerListCustomerName;
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            mLocation = intent.getParcelableExtra("location");
            Log.e(TAG, "onReceive: " + mLocation.getLongitude());
            Log.e(TAG, "onReceive: ACCURACY " + mLocation.getAccuracy());
            if (mLocation.getAccuracy() < 15 && focusBol) {
                if (siteCenterLatLng != null && siteDistanceBetweenEdgeAndCenter != 0) {
                    Log.e(TAG, "onReceive: AUTO RECORD TEST");
                    LatLng latLng = new LatLng(mLocation.getLatitude(), mLocation.getLongitude());
                    double distance = new GetLocationDistance().getDistanceBetweenPoints(latLng, siteCenterLatLng);
                    Log.e(TAG, "onReceive: distance" + distance);
                    if (distance <= distanceVariable) {
                        Log.e(TAG, "onReceive: inside");
                        Toast.makeText(getActivity(), "RECORD ON, DISTANCE: " + distance, Toast.LENGTH_SHORT).show();
                        siteEnterBol = true;
                        addLocationDataIntoDatabase(mLocation);
                        if (getSessionLocationFromDatabaseCount == 1) {
                            drawSessionPolygonFromDatabase();
                            getSessionLocationFromDatabaseCount = 0;
                        } else
                            getSessionLocationFromDatabaseCount++;

                        showGeoContent();
                    } else if (distance > distanceVariable) {

                        Log.e(TAG, "onReceive: outside");
                        if (siteEnterBol) {
                            mapScreenShot();
                            siteEnterBol = false;
                            exitSession();
                        }
                    }
                }
            }
        }
    };

    @Override
    public void onResume() {
        Log.e(TAG, "onResume: ");
        super.onResume();
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mMessageReceiver, new IntentFilter("GPSLocationUpdates"));
    }

    public void setMapEnableFalse() {
        Log.e(TAG, "setMapEnableFalse: ");
        rootRL = rootView.findViewById(R.id.rootRL);
        rootRL.setAlpha(0.4f);
    }

    public void onFocus() {
        Log.e("TAG", "onFocus: ");
        if (mMap != null) {
            focusBol = true;
            rootRL.setAlpha(1);
            navigationButton = rootView.findViewById(R.id.button_menu);
            navigationButton.setVisibility(View.VISIBLE);
            mapSettings = new MapSettings(mMap);
            mMap = mapSettings.onFocusMapOptions();
            arcMenu = fragmentMapBinding.arcmenuAndroidExampleLayout;
            arcMenu.setVisibility(View.VISIBLE);
            arcMenu.setRadius((float) 300.0);
            searchCustomerButton = rootView.findViewById(R.id.searchCustomerButton);
            searchCustomerButton.setVisibility(View.GONE);
            initiateCustomerBottomSheet();
            traceId = String.valueOf(System.currentTimeMillis());

            plotModelMap = new HashMap<>();
            contentDataModelMarkerHashMap = new HashMap<>();

            for (int i = 0; i < selectedCustomerItemList.size() - 1; i++) {
                String id = selectedCustomerItemList.get(i).getCustomerTempGroupId();
                TrnSessionDataSource trnSessionDataSource = new TrnSessionDataSource(getActivity());
                trnSessionDataSource.updateSession(id, traceId);
            }

            getLocationBasedContent();

            mMap.setOnPolygonClickListener(this);
        } else {
            Log.e(TAG, "onFocus: mMap is null");
        }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.e(TAG, "setUserVisibleHint: ");
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG, ": onCreateView: ");
        fragmentMapBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_map, container, false);
        rootView = fragmentMapBinding.getRoot();
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        fragmentMapBinding.setMaphandler(this);
        customerSearchCoordinatorLayout = fragmentMapBinding.customerSearchCoordinatorLayout;
        tv1 = fragmentMapBinding.tv1;
        tv2 = fragmentMapBinding.tv2;
//        tv3 = fragmentMapBinding.tv3;
        tv4 = fragmentMapBinding.tv4;

        fab1 = fragmentMapBinding.fabArcMenu1;
        fab2 = fragmentMapBinding.fabArcMenu2;
//        fab3 = fragmentMapBinding.fabArcMenu3;
        fab4 = fragmentMapBinding.fabArcMenu4;

        alertManager = new AlertManager(getActivity(), this);
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.e(TAG, "onStart: ");
        super.onStart();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onActivityCreated: ");
        super.onActivityCreated(savedInstanceState);
        setMapEnableFalse();
    }

    private void initiateCustomerBottomSheet() {
        Log.e(TAG, "initiateCustomerBottomSheet: ");
        NestedScrollView bottomSheetCustomerSearch = fragmentMapBinding.bottomSheetCustomerSearch;
        mBottomSheetBehaviorCustomerSearch = BottomSheetBehavior.from(bottomSheetCustomerSearch);
        mBottomSheetBehaviorCustomerSearch.setPeekHeight(200);
        mBottomSheetBehaviorCustomerSearch.setHideable(true);

        mBottomSheetBehaviorCustomerSearch.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                Log.e(TAG, "onStateChanged: " + newState);
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    closeCustomerBottomSheet();
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.e(TAG, "onSlide: ");
            }
        });
    }

    public void closeCustomerBottomSheet() {
        Log.e(TAG, "closeCustomerBottomSheet: ");
        if (mBottomSheetBehaviorCustomerSearch != null) {
            customerSearchCoordinatorLayout.setVisibility(View.GONE);
            mBottomSheetBehaviorCustomerSearch.setHideable(true);
            mBottomSheetBehaviorCustomerSearch.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
    }

    @Override
    public void onStop() {
        Log.e(TAG, "onStop: ");
        super.onStop();
        siteEnterBol = false;
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(mMessageReceiver);
    }

    public void openBottomSheetCustomerList() {
        Log.e(TAG, "openBottomSheetCustomerList: ");
        if (mBottomSheetBehaviorCustomerSearch.isHideable()) {
            openCustomerBottomSheet();
            initCustomerList();
        } else {
            closeCustomerBottomSheet();
        }
    }

    private void openCustomerBottomSheet() {
        Log.e(TAG, "openCustomerBottomSheet: ");
        customerSearchCoordinatorLayout.setVisibility(View.VISIBLE);
        mBottomSheetBehaviorCustomerSearch.setState(BottomSheetBehavior.STATE_EXPANDED);
        mBottomSheetBehaviorCustomerSearch.setHideable(false);
    }

    private void initCustomerList() {
        Log.e(TAG, "initCustomerList: ");
        MstCustomerDataSource customerDataSource = new MstCustomerDataSource(getActivity());
        customerDataModelArrayList = customerDataSource.getAllCustomer(SharedPreference.getInstance(getActivity()).getIntSharedPreference(GlobalConstant.DeveloperId));
        if (selectedCustomerAdapter != null) {
            removeSelectedCustomerFromList();
        }

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        customerRecyclerView = rootView.findViewById(R.id.customerRecyclerView);

        customerRecyclerView.setHasFixedSize(true);
        customerRecyclerView.setItemViewCacheSize(20);
        customerRecyclerView.setDrawingCacheEnabled(true);


        customerRecyclerView.setLayoutManager(llm);
        customerAdapter = new CustomerAdapter(customerDataModelArrayList, getActivity(), this);
        customerRecyclerView.setAdapter(customerAdapter);

        ((EditText) rootView.findViewById(R.id.searchCustomerEditText)).addTextChangedListener(new TextWatcher() {
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
    }

    private void removeSelectedCustomerFromList() {
        Log.e(TAG, "removeSelectedCustomerFromList: ");
        for (int i = 0; i < selectedCustomerAdapter.getCustomerList().size(); i++) {
            for (int j = 0; j < customerDataModelArrayList.size(); j++) {
                if (customerDataModelArrayList.get(j).getCust_unique_id() == selectedCustomerItemList.get(i).getCust_unique_id()) {
                    customerDataModelArrayList.remove(j);
                }
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

    private void removeCustomerFromList(ArrayList<CustomerDataModel> customerDataModelArrayList, CustomerDataModel customerDataModel) {
        Log.e(TAG, "removeCustomerFromList: ");
        for (int i = 0; i < customerDataModelArrayList.size(); i++) {
            if (customerDataModelArrayList.get(i).getCust_unique_id() == customerDataModel.getCust_unique_id()) {
                customerDataModelArrayList.remove(i);
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.e(TAG, ": onMapReady: ");
        mMap = googleMap;
        mapSettings = new MapSettings(mMap);
        mMap = mapSettings.preFocusMapOptions();
    }

    @Override
    public void onClick(View v) {
        Log.e(TAG, "onClick: ");
    }

    public void menuButtonClick() {
        ((MainActivity) getActivity()).openLeftToolbar();
        Log.e(TAG, "menuButtonClick: ");
    }


    public void onAddCustomerClick() {
        Log.e(TAG, "onAddCustomerClick: ");
        Intent intent = new Intent(getActivity(), AddCustomerActivity.class);
        startActivity(intent);
    }

    public void onStartSessionClick() {
        Log.e(TAG, "onStartSessionClick: ");
        startCustomerSessions();
    }


    private void startCustomerSessions() {
        Log.e(TAG, "startCustomerSessions: ");
        if (selectedCustomerItemList != null && selectedCustomerItemList.size() > 0) {
            ArrayList<SessionDataModel> sessionDataModelArrayList = new ArrayList<>();
            for (int i = 0; i < selectedCustomerItemList.size(); i++) {
                selectedCustomerItemList.get(i).setCustSessKey(Utility.createSessionKey(getActivity(), selectedCustomerItemList.get(i).getCust_unique_id(), selectedCustomerItemList.get(i).getCust_first_name()));
                SessionDataModel sessionDataModel = new SessionDataModel();
                sessionDataModel.setSess_key(selectedCustomerItemList.get(i).getCustSessKey());
                sessionDataModel.setSess_start_timestamp(DateUtil.getCurrentTimeStamp());
                if (mLocation != null) {
                    sessionDataModel.setSess_start_lat(String.valueOf(mLocation.getLatitude()));
                    sessionDataModel.setSess_start_long(String.valueOf(mLocation.getLongitude()));
                }
                sessionDataModel.setSess_type("Customer Session");
                sessionDataModel.setSess_group_id(selectedCustomerItemList.get(i).getCustomerTempGroupId());
                sessionDataModel.setSess_cust_unique_id(selectedCustomerItemList.get(i).getCust_unique_id());
                sessionDataModel.setSess_spid(SharedPreference.getInstance(getActivity()).getIntSharedPreference(GlobalConstant.UserId));
                sessionDataModel.setSess_project_id(phaseDataModel.getPhas_proj_id());
                sessionDataModel.setSess_phase_id(phaseDataModel.getPhas_id());
                sessionDataModel.setSess_customer_feedback("Nice Plot, Ready to buy");
                sessionDataModel.setSess_user_feedback("Customer willing to buy plot");
                sessionDataModel.setSess_image_location("");
                sessionDataModel.setCreated_by(String.valueOf(SharedPreference.getInstance(getActivity()).getIntSharedPreference(GlobalConstant.UserId)));
                sessionDataModel.setUpdated_by(String.valueOf(SharedPreference.getInstance(getActivity()).getIntSharedPreference(GlobalConstant.UserId)));
                sessionDataModel.setCreated_at(DateUtil.getCurrentFormatDateAndTime());
                sessionDataModel.setUpdated_at(DateUtil.getCurrentFormatDateAndTime());
                sessionDataModel.setSess_end_flag("a");
                sessionDataModelArrayList.add(sessionDataModel);
            }
            TrnSessionDataSource trnSessionDataSource = new TrnSessionDataSource(getActivity());
            trnSessionDataSource.insertSession(sessionDataModelArrayList);
            modifySelectedCustomerListWithNewCustomer(tempSelectedCustomer);
        } else {
            alertManager.informationDialog(getResources().getString(R.string.search_customer), getResources().getString(R.string.please_select_customer));
        }
    }

    private void modifySelectedCustomerListWithNewCustomer(ArrayList<CustomerDataModel> newSelectedCustomerArrayList) {
        Log.e(TAG, "modifySelectedCustomerListWithNewCustomer: ");
        CustomerDataModel customerDataModel = selectedCustomerItemList.get(selectedCustomerItemList.size() - 1);
        selectedCustomerItemList.remove(selectedCustomerItemList.size() - 1);
        for (int i = 0; i < newSelectedCustomerArrayList.size(); i++) {
            selectedCustomerItemList.add(newSelectedCustomerArrayList.get(i));
        }
        selectedCustomerItemList.add(selectedCustomerItemList.size(), customerDataModel);
        selectedCustomerAdapter = new SelectedCustomerAdapter(selectedCustomerItemList, this, getActivity());
        selectedCustomerRecyclerView.setAdapter(selectedCustomerAdapter);

        tempSelectedCustomer.clear();
        closeCustomerBottomSheet();
    }

    public void onNearbyClick() {
        Log.e(TAG, "onNearbyClick: arcMenuToggle: " + arcMenuToggle);
        if (arcMenuToggle == 2) {
            Nearby[] nearbyModel = new Gson().fromJson(phaseDataModel.getPhas_nearby(), Nearby[].class);
            ((MainActivity) getActivity()).openRightToolbar();
            if (nearbyModel != null && nearbyModel.length >= 0) {
                ((MainActivity) getActivity()).initiateRightDrawer(nearbyModel);
            }
        } else if (arcMenuToggle == 1) {
            /*CfgContentDataSource cfgContentDataSource = new CfgContentDataSource(getActivity());
            ArrayList<ContentDataModel> list = cfgContentDataSource.getAllGeoGalleryModelsPhasId(phaseDataModel.getPhas_id());
            ((MainActivity) getActivity()).initiateGalleryDialog(list);*/

            if (!galleryBool) {
                drawGeoGalleryOnButtonClick();
                galleryBool = true;
            } else {
                galleryBool = false;
                Iterator<Marker> iterator = markerContentDataModelHashMap.keySet().iterator();
                while (iterator.hasNext()) {
                    iterator.next().remove();
                }
                markerContentDataModelHashMap.clear();
            }

            if (endMarker != null && endMarker.isVisible()) endMarker.remove();
            if (routePolyline != null && routePolyline.isVisible()) routePolyline.remove();
        }
        arcMenu.hideMenu();
    }

    public void onDirectionsClick() {
        Log.e(TAG, "onDirectionsClick: arcMenuToggle: " + arcMenuToggle);
        if (arcMenuToggle == 2) {
            ((MainActivity) getActivity()).openRightToolbar();
            DirectionFromDatabaseModel[] directions = new Gson().fromJson(phaseDataModel.getPhas_routes(), DirectionFromDatabaseModel[].class);
            if (directions != null && directions.length >= 0) {
                ((MainActivity) getActivity()).initiateRightDrawer(directions);
                arcMenu.toggleMenu();
            }
        } else if (arcMenuToggle == 1) {
            mMap.setMapType(++mapType);
            Log.e(TAG, "MAP TYPE TOGGLE: " + mapType);
            if (mapType >= 5)
                mapType = 1;
        }
    }

    public void onProjectListSingleClick(PhaseDataModel phaseDataModel) throws JSONException {
        Log.e(TAG, "onProjectListSingleClick: ");
        this.phaseDataModel = phaseDataModel;
        JSONObject obj = new JSONObject(phaseDataModel.getPhas_boundaries().replace("'", ""));
        JSONArray array = obj.getJSONArray("geodata").getJSONObject(0).getJSONArray("coordinates");

        JSONObject objCoordinates = new JSONObject(phaseDataModel.getPhas_coordinates());
        String strLat = objCoordinates.getString("lat");
        String strLng = objCoordinates.getString("lng");

        latLongList = new ArrayList<>();
        JSONObject latLongObj;
        for (int i = 0; i < array.length(); i++) {
            latLongObj = array.getJSONObject(i);
            if (!(latLongObj.isNull("lat")) && !(latLongObj.isNull("long"))) {
                Double lat = latLongObj.getDouble("lat");
                Double lon = latLongObj.getDouble("long");
                LatLng latlong = new LatLng(lat, lon);
                latLongList.add(latlong);

            }
        }
        if (phasePolygon != null && phasePolygon.isVisible()) {
//            phasePolygon.remove();
        }
        phasePolygon = drawPolygons(latLongList, Color.BLUE, 0, false, false);
        if (mLocation != null && !ConnectivityReceiver.isConnected()) {
            Log.e(TAG, "onProjectListClick: location not null");
            String start = mLocation.getLatitude() + "," + mLocation.getLongitude();
            String end = strLat + "," + strLng;
            Directions directions = new Directions(getContext(), this);
            directions.callDirections(start, end);
        } else {
            Log.e(TAG, "onProjectListClick: location null");
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            List<LatLng> list = phasePolygon.getPoints();
            for (int i = 0; i < list.size(); i++) {
                builder.include(list.get(i));
            }
            onLayerClick();
//            mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 150));
        }
    }


    public void onProjectListDoubleClick(PhaseDataModel phaseDataModel, final ArrayList<CustomerDataModel> selectedCustomerItemList) throws JSONException {
        Log.e(TAG, "onProjectListDoubleClick: ");
        this.phaseDataModel = phaseDataModel;
        selectedCustomerItemList.add(selectedCustomerItemList.size(), new CustomerDataModel(0, -1, "", 0, 0, 0, "", "", "", "", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "", "", "", 0, "", 0, 0, "", "", "", ""));
        this.selectedCustomerItemList = selectedCustomerItemList;

        if (selectedCustomerItemList.size() > 0) {
            selectedCustomerRecyclerView = rootView.findViewById(R.id.selectedCustomerRecyclerView);
            selectedCustomerRecyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
            llm.setOrientation(LinearLayoutManager.VERTICAL);

            selectedCustomerRecyclerView.setLayoutManager(llm);
            selectedCustomerAdapter = new SelectedCustomerAdapter(selectedCustomerItemList, this, getActivity());
            selectedCustomerRecyclerView.setAdapter(selectedCustomerAdapter);
        }

        JSONObject obj = new JSONObject(phaseDataModel.getPhas_boundaries().replace("'", ""));
        JSONArray array = obj.getJSONArray("geodata").getJSONObject(0).getJSONArray("coordinates");

        JSONObject objCoordinates = new JSONObject(phaseDataModel.getPhas_coordinates());
        String strLat = objCoordinates.getString("lat");
        String strLng = objCoordinates.getString("lng");

        Nearby[] nearbyModel = new Gson().fromJson(phaseDataModel.getPhas_nearby(), Nearby[].class);
        latLongList = new ArrayList<>();
        JSONObject latLongObj;

        for (int i = 0; i < array.length(); i++) {
            latLongObj = array.getJSONObject(i);
            if (!(latLongObj.isNull("lat")) && !(latLongObj.isNull("long"))) {
                Double lat = latLongObj.getDouble("lat");
                Double lon = latLongObj.getDouble("long");
                LatLng latlong = new LatLng(lat, lon);
                latLongList.add(latlong);
            }
        }

        phasePolygon = drawPolygons(latLongList, Color.BLUE, 0, false, false);

        if (mLocation != null && !ConnectivityReceiver.isConnected()) {
            String start = mLocation.getLatitude() + "," + mLocation.getLongitude();
            String end = strLat + "," + strLng;
            Directions directions = new Directions(getContext(), this);
            directions.callDirections(start, end);
        } else {
            Log.e(TAG, "onProjectListClick: location null");
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            List<LatLng> list = phasePolygon.getPoints();
            for (int i = 0; i < list.size(); i++) {
                builder.include(list.get(i));
            }
            onLayerClick();
//            mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 150));
        }

        LatLng test = new LatLng(18.559044, 73.803571);
        siteCenterLatLng = test;

        for (int i = 0; i < phasePolygon.getPoints().size(); i++) {
            LatLng siteEdgeLatLng = phasePolygon.getPoints().get(i);
            siteDistanceBetweenEdgeAndCenter = new GetLocationDistance().getDistanceBetweenPoints(siteCenterLatLng, siteEdgeLatLng);
        }
    }

    private void endSessionForCustomer(final int position) {
        Log.e(TAG, "endSessionForCustomer: ");
        CustomerDataModel customerDataModel = selectedCustomerAdapter.getCustomerModel(position);
        mapScreenShot(customerDataModel);

        Log.e(TAG, "endSessionForCustomer: " + customerDataModel.getCustSessKey());
        TrnSessionDataSource trnSessionDataSource = new TrnSessionDataSource(getActivity());
        SessionDataModel sessionDataModel = new SessionDataModel();
        if (mLocation != null) {
            sessionDataModel.setSess_end_lat(String.valueOf(mLocation.getLatitude()));
            sessionDataModel.setSess_end_long(String.valueOf(mLocation.getLongitude()));
        }
        sessionDataModel.setSess_end_flag("end");
        sessionDataModel.setSess_key(customerDataModel.getCustSessKey());
        trnSessionDataSource.closeSession(sessionDataModel);
        customerDataModel.setCustomerSelectedFlag(0);
        selectedCustomerAdapter.onItemDismiss(position);
        refreshFragmentAfterEndSessions(customerDataModel);
//        onLoseFocus();
    }

    public void refreshFragmentAfterEndSessions(CustomerDataModel customerDataModel) {
        Log.e(TAG, "refreshFragmentAfterEndSessions: ");
        removeCustomerFromList(selectedCustomerItemList, customerDataModel);
        refreshCustomerListByAddingNewCustomer(customerDataModel);
        if (selectedCustomerItemList.size() == 1) {
            searchCustomerButton.setVisibility(View.GONE);
            navigationButton.setVisibility(View.GONE);
            ((MainActivity) getActivity()).initiateProjectListDialog();
            ((MainActivity) getActivity()).mapForPlotLikeByCustomerList.clear();
            arcMenu.setVisibility(View.GONE);
            selectedCustomerRecyclerView.setVisibility(View.GONE);
            onLoseFocus();
        } else {
            onFocus();
        }
    }

    private void refreshCustomerListByAddingNewCustomer(CustomerDataModel customerDataModel) {
        Log.e(TAG, "refreshCustomerListByAddingNewCustomer: ");
        if (customerDataModelArrayList != null) {
            customerDataModelArrayList.add(customerDataModel);
            if (customerDataModelArrayList.size() > 1) {
                customerRecyclerView = rootView.findViewById(R.id.customerRecyclerView);

                customerRecyclerView.setHasFixedSize(true);
                customerRecyclerView.setItemViewCacheSize(20);
                customerRecyclerView.setDrawingCacheEnabled(true);

                LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                customerRecyclerView.setLayoutManager(llm);
                customerAdapter = new CustomerAdapter(customerDataModelArrayList, getActivity(), this);
                customerRecyclerView.setAdapter(customerAdapter);
            }
        }
    }

    private Polygon drawPolygons(List<LatLng> list, int strokeColour, int fillColour, boolean clickable, boolean fillBol) {
        Log.e(TAG, "drawPolygons: " + list.size());
        Log.e(TAG, "drawPolygons: clickable: " + clickable);

        PolygonOptions polygonOptions = new PolygonOptions();

        if (fillBol)
            polygonOptions.fillColor(fillColour);

        polygonOptions.strokeColor(strokeColour);
        polygonOptions.addAll(list);
        polygonOptions.clickable(clickable);
        polygonOptions.zIndex(2);
//        polygonOptions.zIndex(2.0f);
        if (strokeColour == Color.YELLOW)
            polygonOptions.strokeWidth(3.0f);
        else
            polygonOptions.strokeWidth(2.0f);
        Polygon polygon = mMap.addPolygon(polygonOptions);

//        polygon.setZIndex(2.0f);
        return polygon;
    }

    @Override
    public void OnDirectionsSuccessful(DirectionResults directionResults) {
        Log.e(TAG, "OnDirectionsSuccessful: ");
        DirectionDecode directionDecode = new DirectionDecode(directionResults);
        ArrayList<LatLng> latLngList = directionDecode.decodeDirections(mMap);
        String phaseLogoPath = phaseDataModel.getPhas_logo().replace("assets/user/", "");
        boundriesBuilder = new LatLngBounds.Builder();
        for (int i = 0; i < latLngList.size(); i++) {
            boundriesBuilder.include(latLngList.get(i));
        }
        drawPolyline(latLngList);

        addMarker(latLngList.get(latLngList.size() - 1), phaseLogoPath);
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(boundriesBuilder.build(), 150));
    }

    @Override
    public void OnDirectionsFail() {
        Log.e(TAG, "OnDirectionsFail: ");
        boundriesBuilder = new LatLngBounds.Builder();
        List<LatLng> latLngs = phasePolygon.getPoints();
        for (int i = 0; i < latLngs.size(); i++)
            boundriesBuilder.include(latLngs.get(i));
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(boundriesBuilder.build(), 150));
    }

    private void addMarker(LatLng latLng, String logoPath) {
        try {
            Log.e(TAG, "addMarker: ");

            if (endMarker != null && endMarker.isVisible()) endMarker.remove();
//            if (routePolyline != null && routePolyline.isVisible()) routePolyline.remove();

            String absolutePath = GlobalConstant.PROJECT_FOLDER_PATH + logoPath;
            Uri uri = Uri.fromFile(new File(absolutePath));

            Bitmap bitmap1 = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
            Bitmap bitmap = new BubbleTransformation(8).drawMarkerBorder(bitmap1);

            bitmap.setHeight(100);
            bitmap.setWidth(100);
            endMarker = mMap.addMarker(new MarkerOptions()
                            .position(latLng)
//                    .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                            .title("test")
                            .snippet("test address")
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawPolyline(ArrayList<LatLng> latLngList) {
        Log.e(TAG, "drawPolyline: ");
        boundriesBuilder = new LatLngBounds.Builder();
        if (latLngList.size() > 0) {
            PolylineOptions rectLine = new PolylineOptions().width(10).color(Color.BLUE);
            for (int i = 0; i < latLngList.size(); i++) {
                boundriesBuilder.include(latLngList.get(i));
                rectLine.add(latLngList.get(i));
            }

            if (routePolyline != null)
                routePolyline.remove();

            routePolyline = mMap.addPolyline(rectLine);
        }

        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(boundriesBuilder.build(), 250));
    }

    public void addCategoryMarker(List<CategoryResult> categoryResultList, String category) {
        Log.e(TAG, "addCategoryMarker: ");
        if (!categoryMarkerList.isEmpty())
            for (int i = 0; i < categoryMarkerList.size(); i++)
                categoryMarkerList.get(i).remove();

        boundriesBuilder = new LatLngBounds.Builder();

        for (int i = 0; i < categoryResultList.size(); i++) {
            Double lat = categoryResultList.get(i).getGeometry().getLocation().getLat();
            Double lng = categoryResultList.get(i).getGeometry().getLocation().getLng();

            LatLng latLng = new LatLng(lat, lng);

            String title = categoryResultList.get(i).getName();
            String vicinity = categoryResultList.get(i).getVicinity();
            String resourceName = "icon_" + category;

            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), Utility.getResourceIdeFromString(resourceName, getContext()));

            if (bitmap != null && bitmap.getByteCount() > 0)
                categoryMarkerList.add(mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title(title)
                        .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                        .snippet(vicinity)
                        .flat(false)));
            else
                categoryMarkerList.add(mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title(title)
                        .snippet(vicinity)
                        .flat(false)));
            boundriesBuilder.include(latLng);
        }

        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(boundriesBuilder.build(), 250));
    }

    public void addDatabaseDirection(DirectionFromDatabaseModel object) {
        Log.e(TAG, "addDatabaseDirection: ");
        DirectionDecode decode = new DirectionDecode();
        ArrayList<LatLng> latLngList = decode.decodeString(object.getOverview_polyline());
        drawPolyline(latLngList);
    }

    @Override
    public void onCustomerClicked(int position, RecyclerView recyclerViewForProposals, ExpandableLayout expandableLayout) {
        Log.e(TAG, "onCustomerClicked: " + position);
        CustomerDataModel customerDataModel = customerAdapter.getCustomerModel(position);
        if (customerDataModel.getCustomerSelectedFlag() == 1) {
            alertManager.informationDialog("Customer Selection", "Customer Already Selected");
        } else if (expandableLayout.isExpanded()) {
            expandableLayout.collapse();
        } else {
            ArrayList<CustomerGroupDataModel> customerGroupDataModelArrayList;
            CfgCustomerGroupDataSource cfgCustomerGroupDataSource = new CfgCustomerGroupDataSource(getContext());
            customerGroupDataModelArrayList = cfgCustomerGroupDataSource.getAllCustGroupHavingOpps(customerDataModel.getCust_unique_id());
            ArrayList<ProposalHeaderDataModel> proposalHeaderDataModelArrayList = new ArrayList<>();
            MstProposalHeaderDataSource mstProposalHeaderDataSource = new MstProposalHeaderDataSource(getContext());
            if (customerGroupDataModelArrayList.size() > 0) {
                for (int i = 0; i < customerGroupDataModelArrayList.size(); i++) {
                    ProposalHeaderDataModel proposalHeaderDataModel;
                    proposalHeaderDataModel = mstProposalHeaderDataSource.getProposalDataModel(customerGroupDataModelArrayList.get(i).getCgc_group_id(), phaseDataModel.getPhas_id());
                    if (proposalHeaderDataModel != null)
                        proposalHeaderDataModelArrayList.add(proposalHeaderDataModel);
                }
                expandableLayout.expand();
                LinearLayoutManager llm = new LinearLayoutManager(getContext());
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewForProposals.setLayoutManager(llm);
                proposalHeaderDataModelArrayList.add(new ProposalHeaderDataModel(0, 0, 0, "", "", "", "", 0, 0, 0, "", "", "", "", 0, "", "", "", "", "", "", 0, ""));
                proposalAdapter = new ProposalAdapter(proposalHeaderDataModelArrayList, getContext(), this, customerDataModel, position);
                recyclerViewForProposals.setAdapter(proposalAdapter);
            } else {
                useOldGroupOrCreateNewGroup(customerDataModel, position);
            }
        }
    }

    @Override
    public void onRemoveCustomer(int position) {
        Log.e(TAG, "onRemoveCustomer: ");
        final CustomerDataModel customerDataModel = customerAdapter.getCustomerModel(position);
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
        final CustomerDataModel customerDataModel = customerAdapter.getCustomerModel(position);
        Gson gson = new Gson();
        String customerObjectJson = gson.toJson(customerDataModel);
        Intent intent = new Intent(getActivity(), CustomerActivity.class);
        intent.putExtra("customer_model", customerObjectJson);
        startActivity(intent);
    }


    private void useOldGroupOrCreateNewGroup(CustomerDataModel customerDataModel, int position) {
        Log.e(TAG, "useOldGroupOrCreateNewGroup: ");
        String customerGroupId;
        CfgCustomerGroupDataSource cfgCustomerGroupDataSource = new CfgCustomerGroupDataSource(getActivity());
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
            CfgCustomerGroupDataSource tempCfgCustomerGroupDataSource = new CfgCustomerGroupDataSource(getActivity());
            tempCfgCustomerGroupDataSource.insertCustomerGroup(customerGroupDataModel);
            addCustomerIntoSelectedCustomerList(customerGroupId, customerDataModel, position);
        }
    }

    private void addCustomerIntoSelectedCustomerList(String customerGroupId, CustomerDataModel customerDataModel, int position) {
        Log.e(TAG, "addCustomerIntoSelectedCustomerList: ");
        MstProposalHeaderDataSource mstProposalHeaderDataSource = new MstProposalHeaderDataSource(getActivity());
        ProposalHeaderDataModel proposalHeaderDataModel = mstProposalHeaderDataSource.getProposalDataModel(customerGroupId);
        if (proposalHeaderDataModel != null) {
            customerDataModel.setTempProposalId(String.valueOf(proposalHeaderDataModel.getProp_id()));
        }
        customerDataModel.setCustomerTempGroupId(customerGroupId);
        customerDataModel.setCustomerSelectedFlag(1);
        tempSelectedCustomer.add(customerDataModel);
        customerAdapter.onItemDismiss(position);
        removeCustomerFromList(customerDataModelArrayList, customerDataModel);

        customerDataModelArrayList.add(0, customerDataModel);
        customerAdapter = new CustomerAdapter(customerDataModelArrayList, getActivity(), this);
        customerRecyclerView.setAdapter(customerAdapter);
        ((EditText) rootView.findViewById(R.id.searchCustomerEditText)).setText("");

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(rootView.findViewById(R.id.searchCustomerEditText).getWindowToken(), 0);
    }


    public void onLayerClick() {
        MstPlotDataSource dataSource = new MstPlotDataSource(getContext());
        List<PlotModel> modelList = dataSource.getAllPlots(phaseDataModel.getPhas_id());
        LatLngBounds.Builder builder = LatLngBounds.builder();
        if (endMarker != null && endMarker.isVisible()) endMarker.remove();
        if (routePolyline != null && routePolyline.isVisible()) routePolyline.remove();

        for (int i = 0; i < modelList.size(); i++) {
            if (modelList.get(i) != null && modelList.get(i) != null && !modelList.isEmpty())
                drawPlotPolygon(modelList.get(i));
        }

        if (!tileVisibilityBoolean) {
            tileOverlay = mMap.addTileOverlay(new TileOverlayOptions().tileProvider(new TileProviderUtil(getResources().getAssets(), 100, getActivity())));
            if (routePolyline != null) routePolyline.remove();
//            if (phasePolygon != null) phasePolygon.remove();
            for (int i = 0; i < latLongList.size(); i++) {
                builder.include(latLongList.get(i));
            }

            mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 250));
            tileVisibilityBoolean = true;
        } else {
            tileVisibilityBoolean = false;
            tileOverlay.remove();
            removePlotPolygons();
            phasePolygon = drawPolygons(latLongList, Color.BLUE, 0, false, false);

        }
    }

    private void drawPlotPolygon(PlotModel plotModel) {
        Gson gson = new Gson();
        PlotJsonModel plotJsonModels = gson.fromJson(plotModel.getPlot_coordinates(), PlotJsonModel.class);
        List<LatLng> latLngs = new ArrayList<>();

        if (plotJsonModels != null) {
            Log.e(TAG, "drawPlotPolygon: " + plotJsonModels.getCoordinates().size());
            for (int i = 0; i < plotJsonModels.getCoordinates().size(); i++) {
                latLngs.add(plotJsonModels.getCoordinates().get(i).getLatLng());
//                Log.e(TAG, "drawPlotPolygon: list at " + i + ": " + latLngs.get(i).toString());
            }
            int fillColour = 0;
            int borderColor = Color.WHITE;

            if (plotModel.getPlot_status().equals(getString(R.string.AVAILABLE))) {
                fillColour = getResources().getColor(R.color.AVAILABLE);
            } else if (plotModel.getPlot_status().equals(getString(R.string.LIKED))) {
                fillColour = getResources().getColor(R.color.LIKED);
            } else if (plotModel.getPlot_status().equals(getString(R.string.BOOKED))) {
                fillColour = getResources().getColor(R.color.BOOK);
            } else if (plotModel.getPlot_status().equals(getString(R.string.RESERVED))) {
                fillColour = getResources().getColor(R.color.RESERVE);
            } else if (plotModel.getPlot_status().equals(getString(R.string.SOLD))) {
                fillColour = getResources().getColor(R.color.SOLD);
            }

            if (plotModel.getCat_name().contains("Premium Plots")) {
                borderColor = Color.YELLOW;
            }

            if (plotModelMap == null)
                plotModelMap = new HashMap<>();

            plotModelMap.put(drawPolygons(latLngs, borderColor, fillColour, true, true), plotModel);
        }
    }

    private void setGeoFence(double lat, double lng, float radius, String id) {
        Geofence geofence = new Geofence.Builder()
                .setCircularRegion(lat, lng, radius)
                .setRequestId(id)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER
                        | Geofence.GEOFENCE_TRANSITION_EXIT)
                .build();
    }

    @Override
    public void onSelectedCustomerOptionClick(int optionPosition, int layoutPosition) {
        Log.e(TAG, "onSelectedCustomerOptionClick: ");
        this.selectedCustomerPosition = layoutPosition;
        switch (optionPosition) {
            case 0:
                tempExpandableLayoutForSelectedCustomer.collapse();
                selectedCustomerListCustomerName.setVisibility(View.VISIBLE);
                viewCustomerDetails(layoutPosition);
                break;
            case 1:
                tempExpandableLayoutForSelectedCustomer.collapse();
                selectedCustomerListCustomerName.setVisibility(View.VISIBLE);
                startFeedbackForm();
                break;
        }
    }

    private boolean isReadForFeedback() {
        boolean isReadyForTakeFeedbackFlag = false;
        if (((MainActivity) getActivity()).mapForPlotLikeByCustomerList == null || ((MainActivity) getActivity()).mapForPlotLikeByCustomerList.size() == 0) {
            return false;
        } else {
            CustomerDataModel customerDataModel = selectedCustomerAdapter.getCustomerModel(selectedCustomerPosition);
            for (int key : ((MainActivity) getActivity()).mapForPlotLikeByCustomerList.keySet()) {
                Set<CustomerDataModel> mapForPlotLikeByCustomerList = ((MainActivity) getActivity()).mapForPlotLikeByCustomerList.get(key);
                for (CustomerDataModel tempCustomerDataModel : mapForPlotLikeByCustomerList) {
                    if (tempCustomerDataModel.getCust_unique_id() == customerDataModel.getCust_unique_id()) {
                        isReadyForTakeFeedbackFlag = true;
                        break;
                    }
                }
            }
        }
        return isReadyForTakeFeedbackFlag;
    }

    private void startFeedbackForm() {
        Log.e(TAG, "startFeedbackForm: ");
        final CustomerDataModel customerDataModel = selectedCustomerAdapter.getCustomerModel(selectedCustomerPosition);
        TrnSessionDataSource trnSessionDataSource = new TrnSessionDataSource(getActivity());
        SessionDataModel sessionDataModel = trnSessionDataSource.getSessionOfCustomer(customerDataModel.getCustomerTempGroupId());

        Map<Integer, Set<CustomerDataModel>> customerMap = ((MainActivity) getActivity()).mapForPlotLikeByCustomerList;
        Iterator<Integer> iterator = customerMap.keySet().iterator();
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        while (iterator.hasNext()) {
            int plotId = iterator.next();
            Set<CustomerDataModel> tempList = customerMap.get(plotId);
            for (CustomerDataModel aTempList : tempList) {
                if (aTempList.getCust_unique_id() == customerDataModel.getCust_unique_id()) {
                    integerArrayList.add(plotId);
                }
            }
        }

        Gson gson = new Gson();

        String customerObjectJson = gson.toJson(customerDataModel);
        String projectObjectJson = gson.toJson(phaseDataModel);
        String sessionObjectJson = gson.toJson(sessionDataModel);

        Intent intent = new Intent(getContext(), FeedbackActivity.class);
        intent.putExtra(GlobalConstant.SelectedCustomerModel, customerObjectJson);
        intent.putExtra(GlobalConstant.SelectedProjectModel, projectObjectJson);
        intent.putExtra(GlobalConstant.SelectedCustomerSessionModel, sessionObjectJson);
        intent.putIntegerArrayListExtra(GlobalConstant.ArrayListOfPlotNo, integerArrayList);

        startActivityForResult(intent, GlobalConstant.FEEDBACK_ACTIVITY_REQUEST_CODE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GlobalConstant.FEEDBACK_ACTIVITY_REQUEST_CODE && resultCode == GlobalConstant.FEEDBACK_ACTIVITY_RESULT_CODE) {
            endSessionForCustomer(selectedCustomerPosition);
        }
    }

    @Override
    public void onAddNewCustomerInSelectedList() {
        Log.e(TAG, "onAddNewCustomerInSelectedList: ");
        if (tempExpandableLayoutForSelectedCustomer != null && tempExpandableLayoutForSelectedCustomer.isExpanded()) {
            tempExpandableLayoutForSelectedCustomer.collapse();
            if (selectedCustomerListCustomerName != null)
                selectedCustomerListCustomerName.setVisibility(View.VISIBLE);
        }
        if (mBottomSheetBehaviorCustomerSearch.isHideable()) {
            openCustomerBottomSheet();
            initCustomerList();
        } else {
            closeCustomerBottomSheet();
        }
    }

    @Override
    public void onSelectedCustomerClick(ExpandableLayout expandableLayout, TextView selectedCustomerListCustomerName) {
        Log.e(TAG, "onSelectedCustomerClick: ");
        this.selectedCustomerListCustomerName = selectedCustomerListCustomerName;
        closeCustomerBottomSheet();
        if (tempExpandableLayoutForSelectedCustomer != null) {
            if (tempExpandableLayoutForSelectedCustomer == expandableLayout && expandableLayout.isExpanded()) {
                tempExpandableLayoutForSelectedCustomer.collapse();
                selectedCustomerListCustomerName.setVisibility(View.VISIBLE);
            } else {
                tempExpandableLayoutForSelectedCustomer.collapse();
                selectedCustomerListCustomerName.setVisibility(View.GONE);
                expandableLayout.expand();
                this.tempExpandableLayoutForSelectedCustomer = expandableLayout;
            }
        } else {
            selectedCustomerListCustomerName.setVisibility(View.GONE);
            expandableLayout.expand();
            this.tempExpandableLayoutForSelectedCustomer = expandableLayout;
        }

//        if (expandableLayoutForSyncOption != null && expandableLayoutForSyncOption.isExpanded()) {
//            expandableLayoutForSyncOption.collapse();
//            textViewProjectName.setVisibility(View.VISIBLE);
//        }
    }

    private void viewCustomerDetails(int position) {
        Log.e(TAG, "viewCustomerDetails: ");
        final CustomerDataModel customerDataModel = selectedCustomerAdapter.getCustomerModel(position);
        Gson gson = new Gson();
        String customerObjectJson = gson.toJson(customerDataModel);
//        Intent intent = new Intent(getActivity(), AddCustomerActivity.class);
        Intent intent = new Intent(getActivity(), CustomerActivity.class);
        intent.putExtra("customer_model", customerObjectJson);
        startActivity(intent);
    }

    @Override
    public void onYesResponse(String title) {
        Log.e(TAG, "onYesResponse: ");
        switch (title) {
            case GlobalConstant.END_CUSTOMER_SESSION:


                break;
        }
    }

    @Override
    public void onNoResponse(String title) {
        Log.e(TAG, "onNoResponse: ");
        switch (title) {
            case GlobalConstant.END_CUSTOMER_SESSION:
                break;
        }
    }

    @Override
    public void onOkResponse(String title) {
        Log.e(TAG, "onOkResponse: ");
        switch (title) {
            case GlobalConstant.PLOT_LIKE_STATUS:
                onLayerClick();
                break;
        }
    }

    public void startSiteVisit() {
        Log.e(TAG, "startSiteVisit: ");
        if (!siteVisitRecordToggle) {
            /*SITE VISIT CAN BE TURNED ON USING THIS BUTTON*/
            siteVisitRecordToggle = true;
            fab3.setImageResource(R.drawable.ic_stop_black);
            fab3.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.primary)));
            tv3.setText("Stop Visit");
        } else if (!siteEnterBol) {
            /*SITE VISIT CAN BE STOPPED VIA BUTTON IF THE DEVICE HAS ENTERED THE PROJECT PREMISE*/
            siteVisitRecordToggle = false;
            fab3.setImageResource(R.drawable.ic_meeting);
            tv3.setText("Start Visit");
            exitSession();
            mapScreenShot();
        } else {
            /*SITE VISIT CANNOT BE STOPPED VIA BUTTON IF THE DEVICE HAS ENTERED THE PROJECT PREMISE*/
            Toast.makeText(getContext(), "CANNOT STOP VISIT ONCE INSIDE PREMISE", Toast.LENGTH_LONG).show();
        }
    }

    private void exitSession() {
        Log.e(TAG, "exitSession: ");
    }

    private LocationModel locationToModelParse(Location location) {
        LocationModel model = new LocationModel();
        model.setTraceId(traceId);
        model.setAccuracy(location.getAccuracy());
        model.setLatitude(location.getLatitude());
        model.setLongitude(location.getLongitude());
        model.setAltitude(location.getAltitude());
        model.setTime(location.getTime());
        Log.e(TAG, "locationToModelParse: LOCATION TIME: " + location.getTime());
        return model;
    }

    private void addLocationDataIntoDatabase(Location mLocation) {
        TrnTraceDetailsDataSource locationDatabase = new TrnTraceDetailsDataSource(getActivity());
        LocationModel mLocationModel = locationToModelParse(mLocation);
        locationDatabase.insertLocationDetails(mLocationModel);
    }

    public void onLoseFocus() {
        Log.e(TAG, "onLoseFocus: ");
        siteEnterBol = false;
        siteVisitRecordToggle = false;
        focusBol = false;
//        fab3.setImageResource(R.drawable.ic_meeting);
        mMap.clear();
        mMap.animateCamera(CameraUpdateFactory.zoomTo(1));
        if (arcMenu != null && arcMenu.isMenuOpened())
            arcMenu.hideMenu();
        mMap = mapSettings.onLoseFocusMapOptions();
    }

    @Override
    public void onPolygonClick(Polygon polygon) {
        Log.e(TAG, "onPolygonClick: ");
        PlotModel plotModel = plotModelMap.get(polygon);
        ((MainActivity) getActivity()).openRightToolbar();
        ((MainActivity) getActivity()).initiateRightDrawer(removeLastElement(selectedCustomerItemList), plotModel);
        arcMenu.hideMenu();
    }

    public ArrayList<CustomerDataModel> removeLastElement(ArrayList<CustomerDataModel> selectedCustomerItemList) {
        Log.e(TAG, "removeLastElement: ");
        ArrayList<CustomerDataModel> tempCustomerListForPlotLike = new ArrayList<>();
        for (int i = 0; i < selectedCustomerItemList.size() - 1; i++) {
            tempCustomerListForPlotLike.add(selectedCustomerItemList.get(i));
        }
        return tempCustomerListForPlotLike;
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

    private void drawSessionPolygonFromDatabase() {
        List<LocationModel> list = new TrnTraceDetailsDataSource(getActivity()).getSiteVisitRecordList(traceId);
        ArrayList<LatLng> latLngList = new ArrayList<>();
        for (int j = 0; j < list.size(); j++) {
            latLngList.add(list.get(j).getLatLng());
        }
        drawPolyline(latLngList);
    }

    private void mapScreenShot() {
        String rootPath = GlobalConstant.PROJECT_FOLDER_PATH;
        String path = phaseDataModel.getPhas_devl_id() + "/" + phaseDataModel.getPhas_proj_id() + "/" + phaseDataModel.getPhas_id() + "/trace";
        File traceFile = new File(rootPath + path);

        if (!traceFile.exists())
            traceFile.mkdir();
        for (int i = 0; i < selectedCustomerItemList.size(); i++) {
            if (selectedCustomerItemList.get(i).getCustomerTempGroupId() != null && !selectedCustomerItemList.get(i).getCustomerTempGroupId().isEmpty()) {
                mapScreenShot(selectedCustomerItemList.get(i));
            }
        }
    }

    private void mapScreenShot(CustomerDataModel model) {
        String rootPath = GlobalConstant.PROJECT_FOLDER_PATH;
        String path = phaseDataModel.getPhas_devl_id() + "/" + phaseDataModel.getPhas_proj_id() + "/" + phaseDataModel.getPhas_id() + "/trace";
        File traceFile = new File(rootPath + path);
        String sessionKey = model.getCustSessKey();

        if (!traceFile.exists())
            traceFile.mkdir();


        if (model != null && !model.getCustomerTempGroupId().isEmpty()) {
            File traceGroupFile = new File(traceFile.getAbsolutePath() + "/" + model.getCustomerTempGroupId());

            Log.e(TAG, "END SITE VISIT FOR INDIVIDUAL " + model.getCustomerTempGroupId());

            if (!traceGroupFile.exists())
                traceGroupFile.mkdir();

            mapSettings.mapSnapShot(mMap, traceId, traceGroupFile, model.getCustomerTempGroupId(), getContext(), sessionKey);
        }
    }

    public void removePlotPolygons() {
        Set<Polygon> polygonSet = plotModelMap.keySet();
        Iterator<Polygon> iterator = polygonSet.iterator();

        while (iterator.hasNext())
            iterator.next().remove();
    }

    public void drawPlotPolygons() {
        MstPlotDataSource dataSource = new MstPlotDataSource(getContext());
        List<PlotModel> modelList = dataSource.getAllPlots(phaseDataModel.getPhas_id());
        for (int i = 0; i < modelList.size(); i++) {
            if (modelList.get(i) != null && modelList.get(i) != null && !modelList.isEmpty())
                drawPlotPolygon(modelList.get(i));
        }
    }

    public void updatePlot(PlotModel model) {
        Log.e(TAG, "updatePlot: 0");
        Polygon polygon;

        for (Map.Entry entry : plotModelMap.entrySet()) {
            if (model.equals(entry.getValue())) {
                polygon = (Polygon) entry.getKey();
                polygon.remove();
                break;
            }
        }
        plotModelMap.values().remove(model);
        MstPlotDataSource mstPlotDataSource = new MstPlotDataSource(getContext());
        PlotModel plotModel = mstPlotDataSource.getPlotFromId(String.valueOf(model.getPlot_id()));
        drawPlotPolygon(plotModel);
    }

    public void arcMenuClicked() {
        Log.e(TAG, "arcMenuClicked: " + arcMenuToggle);

        if (!arcMenu.isMenuOpened())
            arcMenuToggle = 0;

        if (arcMenuToggle == 0) {
            Log.e(TAG, "arcMenuClicked: arcMenuToggle: " + arcMenuToggle);
            arcMenu.openMenu();
            arcMenuToggle = 1;

            tv1.setText("Gallery");
            tv2.setText("Map Type");

            fab1.setImageResource(R.drawable.ic_menu_gallery);
            fab2.setImageResource(R.drawable.ic_menu_maptype);
        } else if (arcMenuToggle == 1) {
            Log.e(TAG, "arcMenuClicked: arcMenuToggle: " + arcMenuToggle);
            arcMenu.hideMenu();
            arcMenuToggle = 2;

            tv1.setText("Nearby");
            tv2.setText("Routes");

            fab1.setImageResource(R.drawable.ic_menu_nearby);
            fab2.setImageResource(R.drawable.ic_menu_route);

            arcMenu.openMenu();
        } else if (arcMenuToggle == 2) {
            Log.e(TAG, "arcMenuClicked: arcMenuToggle: " + arcMenuToggle);
            arcMenuToggle = 0;
            arcMenu.hideMenu();
        }
    }

    private void getLocationBasedContent() {
        CfgContentDataSource cfgContentDataSource = new CfgContentDataSource(getActivity());
        locationContentList = cfgContentDataSource.getGeoContent();
        Log.e(TAG, "getLocationBasedContent: list size: " + locationContentList.size());
    }

    private void showGeoContent() {
        GetLocationDistance locationDistance = new GetLocationDistance();
        if (locationContentList.size() > 0) {
            for (int i = 0; i < locationContentList.size(); i++) {
                ContentDataModel model = locationContentList.get(i);

                LatLng latLng2 = Utility.getLatLng(model.getCont_latitude(), model.getCont_longitude());
                LatLng latLng1 = Utility.getLatLng(mLocation.getLatitude(), mLocation.getLongitude());

                if (locationDistance.getDistanceBetweenPoints(latLng1, latLng2) < contentRange)
                    contentWithinRangeList.add(model);
            }
        }
        if (contentDataModelMarkerHashMap.size() > 0) {
            Iterator<ContentDataModel> iterator = contentDataModelMarkerHashMap.keySet().iterator();
            while (iterator.hasNext()) {
                ContentDataModel model = iterator.next();
                LatLng latLng1 = Utility.getLatLng(model.getCont_latitude(), model.getCont_longitude());
                LatLng latLng2 = Utility.getLatLng(mLocation.getLatitude(), mLocation.getLongitude());

                if (locationDistance.getDistanceBetweenPoints(latLng1, latLng2) > contentRange) {
                    contentWithinRangeList.remove(model);
                    contentDataModelMarkerHashMap.remove(model);
                }
            }

        }
        drawMarker(contentWithinRangeList);
    }

    private void drawMarker(ArrayList<ContentDataModel> contentWithinRangeList) {
        try {
            for (int i = 0; i < contentWithinRangeList.size(); i++) {
                ContentDataModel model = contentWithinRangeList.get(i);
                if (!contentDataModelMarkerHashMap.containsKey(model)) {
                    LatLng latLng = Utility.getLatLng(model.getCont_latitude(), model.getCont_longitude());

                    String absolutePath = Utility.validateURIForGallery(model.getCont_file_location());
                    Uri uri = Uri.fromFile(new File(absolutePath));
                    Log.e(TAG, "drawMarker: URI: " + uri);

                    BitmapFactory.Options options = new BitmapFactory.Options();
                    AssetFileDescriptor fileDescriptor;
                    fileDescriptor = getActivity().getContentResolver().openAssetFileDescriptor(uri, "r");

                    Bitmap b = BitmapFactory.decodeFileDescriptor(fileDescriptor.getFileDescriptor(), null, options);
                    Bitmap bitmap1 = Bitmap.createScaledBitmap(b, 50, 50, true);
                    Bitmap bitmap = new BubbleTransformation(3).drawMarkerBorder(bitmap1);

                    Marker marker = (mMap.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title(model.getCont_description())
                            .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                            .flat(false))
                    );

                    mMap.setOnMarkerClickListener(this);
                    contentDataModelMarkerHashMap.put(model, marker);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (contentDataModelMarkerHashMap.containsValue(marker)) {
            ContentDataModel model = Utility.getKeyByValue(contentDataModelMarkerHashMap, marker);
            Log.e(TAG, "onMarkerClick: MODEL: " + model.getCont_id());
            ((MainActivity) getActivity()).initiateGalleryDialog(model);
        } else if (markerContentDataModelHashMap.containsKey(marker)) {
            ArrayList<ContentDataModel> list = markerContentDataModelHashMap.get(marker);
            ((MainActivity) getActivity()).initiateGalleryDialog(list);

        }
        return false;
    }

    public void drawGeoGalleryOnButtonClick() {
        List<LatLng> latLngList = new ArrayList<>();
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        if (locationContentList == null || locationContentList.isEmpty())
            getLocationBasedContent();

        if (markerContentDataModelHashMap != null && !markerContentDataModelHashMap.isEmpty())
            Utility.removeMarkersFromMap(markerContentDataModelHashMap);

        markerContentDataModelHashMap = new HashMap<>();

        Map<LatLng, ArrayList<ContentDataModel>> latLngListMap = new HashMap<>();
        for (int i = 0; i < locationContentList.size(); i++) {
            LatLng latLng = locationContentList.get(i).getLatLng();
            ContentDataModel model = locationContentList.get(i);
            if (!latLngListMap.isEmpty()) {
                if (latLngListMap.containsKey(latLng)) {
                    ArrayList<ContentDataModel> list = latLngListMap.get(latLng);
                    list.add(model);
                } else {
                    ArrayList<ContentDataModel> list = new ArrayList<>();
                    list.add(model);
                    latLngListMap.put(model.getLatLng(), list);
                }
            } else {
                ArrayList<ContentDataModel> list = new ArrayList<>();
                list.add(model);
                latLngListMap.put(model.getLatLng(), list);
            }
        }

        Set<LatLng> latLngSet = latLngListMap.keySet();
        Iterator<LatLng> iterator = latLngSet.iterator();
        while (iterator.hasNext()) {
            LatLng latLng = iterator.next();
            Marker marker = (mMap.addMarker(new MarkerOptions().position(latLng).draggable(false)));
            mMap.setOnMarkerClickListener(this);
            ArrayList<ContentDataModel> list = latLngListMap.get(latLng);
            markerContentDataModelHashMap.put(marker, list);
            builder.include(latLng);
        }

        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 25));
    }
}