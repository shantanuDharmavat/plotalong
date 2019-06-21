package com.plotalong.android.util;

import android.util.Log;

/**
 * Created by kbhakade on 11/7/17.
 */


// TODO: 6/9/17 do not delete
public class SecurityUtil {
    private static final String TAG = SecurityUtil.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);

    public static String encryption(String strNormalText, String email) {
        Log.e(TAG, "encryption: ");
        String normalTextEnc = "";
        try {
            normalTextEnc = AESHelper.encrypt(email, strNormalText);
            Log.e(TAG, "encryption: " + normalTextEnc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return normalTextEnc;
    }

    public static String decryption(String strEncryptedText, String email) {
        Log.e(TAG, "decryption: ");
        String strDecryptedText = "";
        try {
            strDecryptedText = AESHelper.decrypt(email, strEncryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDecryptedText;
    }
}