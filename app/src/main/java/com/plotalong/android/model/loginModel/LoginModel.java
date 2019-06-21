package com.plotalong.android.model.loginModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by shantanu on 29/5/17.
 */

public class LoginModel extends BaseObservable {
    private String loginId, loginPassword;

    public LoginModel(String loginId, String loginPassword) {
        this.loginId = loginId;
        this.loginPassword = loginPassword;
    }

    @Bindable
    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
        notifyPropertyChanged(com.plotalong.android.BR.loginId);
    }

    @Bindable
    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String password) {
        this.loginPassword = password;
        notifyPropertyChanged(com.plotalong.android.BR.loginPassword);
    }
}
