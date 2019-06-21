package com.plotalong.android.event;

/**
 * Created by kbhakade on 28/8/17.
 */
public class LoginEvent {
    public String loginMessage;
    public int loginStatus;

    public LoginEvent() {
    }

    public LoginEvent(String loginMessage, int loginStatus) {
        this.loginMessage = loginMessage;
        this.loginStatus = loginStatus;
    }

    public String getLoginMessage() {
        return loginMessage;
    }

    public void setLoginMessage(String loginMessage) {
        this.loginMessage = loginMessage;
    }

    public int getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(int loginStatus) {
        this.loginStatus = loginStatus;
    }

    @Override
    public String toString() {
        return "LoginEvent{" +
                "loginMessage='" + loginMessage + '\'' +
                ", loginStatus=" + loginStatus +
                '}';
    }
}
