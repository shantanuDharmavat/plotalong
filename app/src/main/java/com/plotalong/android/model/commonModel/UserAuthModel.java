package com.plotalong.android.model.commonModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

import com.plotalong.android.BR;

/**
 * Created by shantanu on 24/5/17.
 */

public class UserAuthModel extends BaseObservable {
    @NonNull
    private String userName;
    private String userPassword;
    private String userPassphrase;


    public UserAuthModel(@NonNull String userName, String userPassword, String userPassphrase) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userPassphrase = userPassphrase;
    }

    @NonNull
    @Bindable
    public String getUserName() {
        return userName;
    }

    public void setUserName(@NonNull String userName) {
        this.userName = userName;
        notifyPropertyChanged(BR.userName);
    }

    @NonNull
    @Bindable
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(@NonNull String userPassword) {
        this.userPassword = userPassword;
        notifyPropertyChanged(BR.userPassword);
    }

    @NonNull
    @Bindable
    public String getUserPassphrase() {
        return userPassphrase;
    }

    public void setUserPassphrase(@NonNull String userPassphrase) {
        this.userPassphrase = userPassphrase;
        notifyPropertyChanged(BR.userPassphrase);
    }
}
