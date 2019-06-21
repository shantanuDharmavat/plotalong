package com.plotalong.android.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.plotalong.android.R;
import com.plotalong.android.model.geoGalleryModel.ContentDataModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import id.zelory.compressor.Compressor;

/**
 * Created by kbhakade on 24/5/17.
 */

public class Utility {
    private static String TAG = Utility.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);

    public static String getDeviceId(Context context) {
        Log.e(TAG, "getDeviceId: ");
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    public static boolean isValidEmail(String email) {
        Log.e(TAG, "isValidEmail: ");
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static String createCustomerGroup(long customerInsertedId) {
        Log.e(TAG, "createCustomerGroup: ");
        StringBuilder stringBuilder = new StringBuilder();
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hrs = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        stringBuilder.append(year).append(month).append(day).append(hrs).append(minute).append(second).append(customerInsertedId);
        return String.valueOf(stringBuilder);
    }

    public static String createSessionKey(Context context, int cust_unique_id, String cust_first_name) {
        Log.e(TAG, "createSessionKey: ");
        StringBuilder stringBuilder = new StringBuilder();
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hrs = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        int milliSecond = c.get(Calendar.MILLISECOND);
        stringBuilder.append(year).append(month).append(day).append(hrs).append(minute).append(second).append(milliSecond)
                .append(cust_first_name).append(cust_unique_id).append(getDeviceId(context));
        return String.valueOf(stringBuilder);
    }

    public static int getResourceIdeFromString(String resourceName, Context context) {
        try {
            int resId = context.getResources().getIdentifier(resourceName, "drawable", context.getPackageName());
            Log.e(TAG, "getResourceIdeFromString: Category type: " + resourceName + " , Id: " + resId);
            return resId;
        } catch (Exception e) {
            throw new RuntimeException("No resource ID found for: "
                    + resourceName + " / ", e);
        }
    }

    public static String convertFirstLetterToUpperCase(String str) {
        String[] words = str.split(" ");
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            ret.append(Character.toUpperCase(words[i].charAt(0)));
            ret.append(words[i].substring(1));
            if (i < words.length - 1) {
                ret.append(' ');
            }
        }
        return ret.toString();
    }

    public static String removeCharacterFromString(String str, String _char) {
        return str.replace(_char, " ");
    }

    public static String validateURIForGallery(String str) {
        return str.replace("/assets/user/", GlobalConstant.PROJECT_FOLDER_PATH);
    }

    public void hideKeyboard(View view) {
        Log.e(TAG, "hideKeyboard: ");
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    public byte[] getBytes(InputStream is) throws IOException {
        ByteArrayOutputStream byteBuff = new ByteArrayOutputStream();

        int buffSize = 1024;
        byte[] buff = new byte[buffSize];

        int len = 0;
        while ((len = is.read(buff)) != -1) {
            byteBuff.write(buff, 0, len);
        }

        return byteBuff.toByteArray();
    }

    public static Bitmap overLayBitmapWithPlaySymbol(Bitmap bMap,Context context ){

        Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_play);
        Bitmap bitmapOverLay = Bitmap.createBitmap(bMap.getWidth(),bMap.getHeight(),Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmapOverLay);
        Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG);

        int centerX = (bMap.getWidth() - icon.getWidth()) / 2;
        int centerY = (bMap.getHeight() - icon.getHeight()) / 2;

        canvas.drawBitmap(bMap,0,0,null);
        canvas.drawBitmap(icon,centerX,centerY,null);

        return bitmapOverLay;
    }

    public static LatLng getLatLng(String strLat,String strLng){
        Double lat = Double.parseDouble(strLat);
        Double lng = Double.parseDouble(strLng);

        return new LatLng(lat,lng);
    }

    public static LatLng getLatLng(double latitude, double longitude) {
        return new LatLng(latitude,longitude);
    }

    public static ContentDataModel getKeyByValue(Map<ContentDataModel, Marker> map, Marker value) {
        for (Map.Entry entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                return (ContentDataModel) entry.getKey();
            }
        }
        return null;
    }

    public static void removeMarkersFromMap(Map<Marker,ArrayList<ContentDataModel>> map){
        Set<Marker> markers = map.keySet();
        Iterator<Marker> iterator = markers.iterator();
        while (iterator.hasNext())
            iterator.next().remove();
    }

    public static BitmapDrawable scaleBitmapToDrawable(Context context, ContentDataModel contentDataModel){
        BitmapDrawable drawable = null;
        DisplayMetrics display = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(display);
        int screenWidth = display.widthPixels;
        int  screenHeight = display.heightPixels;
        try {
            File file = new File(Utility.validateURIForGallery(contentDataModel.getCont_file_location()));
            Log.e(TAG, "imageGalleryToggle: " );
            Bitmap bitmap = new Compressor(context)
                    .setMaxWidth(screenWidth)
                    .setMaxHeight(screenHeight)
                    .setQuality(70)
                    .compressToBitmap(file);
            drawable = new BitmapDrawable(bitmap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return drawable;
    }

    public static Bitmap scaleBitmapToBitmap(Context context, ContentDataModel contentDataModel){
        BitmapDrawable drawable = null;
        DisplayMetrics display = new DisplayMetrics();
        Bitmap bitmap = null;
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(display);
        int screenWidth = display.widthPixels;
        int  screenHeight = display.heightPixels;
        try {
            File file = new File(Utility.validateURIForGallery(contentDataModel.getCont_file_location()));
            Log.e(TAG, "imageGalleryToggle: " );
            bitmap = new Compressor(context)
                    .setMaxWidth(screenWidth)
                    .setMaxHeight(screenHeight)
                    .setQuality(70)
                    .compressToBitmap(file);
        }catch (Exception e){
            e.printStackTrace();
        }
        return bitmap;
    }
}