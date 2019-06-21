package com.plotalong.android.listener;

/**
 * Created by kbhakade on 6/7/17.
 */

public interface UserLoginListener {
    void onLoginSuccess();

    void onLoginFail(String message);

    void onLoginSuccessFromLocal();
}
