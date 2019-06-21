package com.plotalong.android.dialogManager;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

import com.plotalong.android.R;
import com.plotalong.android.listener.DialogBackListener;
import com.plotalong.android.util.GlobalConstant;

/**
 * Created by shantanu on 24/5/17.
 */

public class DialogSettings extends Dialog implements DialogInterface.OnKeyListener {
    private Context context;
    private String TAG = DialogSettings.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    private DialogBackListener dialogBackListener;

    public DialogSettings(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public Dialog getTransparentDialogSettings(int layout, DialogBackListener dialogBackListener) {
        Log.e(TAG, "getTransparentDialogSettings: ");
        this.dialogBackListener = dialogBackListener;
        Dialog dialog = new Dialog(context, R.style.AppTheme);
        dialog.setContentView(layout);
        dialog.setCancelable(false);
        Window window = dialog.getWindow();
        if (window != null) {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            window.setBackgroundDrawableResource(android.R.color.transparent);
            WindowManager.LayoutParams param = window.getAttributes();
            param.gravity = Gravity.LEFT;
            window.setAttributes(param);
        }
        dialog.setOnKeyListener(this);
        return dialog;
    }


    public Dialog getSimpleDialog(int layout, DialogBackListener dialogBackListener) {
        Log.e(TAG, "getTransparentDialogSettings: ");
        this.dialogBackListener = dialogBackListener;
        Dialog dialog = new Dialog(context, R.style.Theme_AppCompat_Dialog_Alert);
        dialog.setContentView(layout);
        dialog.setCancelable(false);
        Window window = dialog.getWindow();
        if (window != null) {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            WindowManager.LayoutParams param = window.getAttributes();
            param.gravity = Gravity.CENTER;
            window.setAttributes(param);
        }
        dialog.setOnKeyListener(this);
        return dialog;
    }

    public Dialog getSimpleDialogCancalable(int layout) {
        Log.e(TAG, "getTransparentDialogSettings: ");
        Dialog dialog = new Dialog(context);
        dialog.setContentView(layout);
        Window window = dialog.getWindow();
        if (window != null) {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            WindowManager.LayoutParams param = window.getAttributes();
            window.setDimAmount(0);
            param.gravity = Gravity.CENTER;
            param.height = WindowManager.LayoutParams.MATCH_PARENT;
            param.width = WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(param);
        }
        dialog.setOnKeyListener(this);
        return dialog;
    }



    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        Log.e(TAG, "onKey: ");
        if (keyCode == KeyEvent.KEYCODE_BACK &&
                event.getAction() == KeyEvent.ACTION_UP &&
                !event.isCanceled() &&
                dialogBackListener != null) {
            dialogBackListener.onDialogBackClick(dialog);
            return true;
        }
        return false;
    }

    public Dialog getFullscreenDialog(Dialog galleryDialog) {
        Window window = galleryDialog.getWindow();

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        window.setGravity(Gravity.CENTER);

        return galleryDialog;
    }

    public Dialog getDialog(Dialog galleryDialog) {
        Window window = galleryDialog.getWindow();
        if (window != null) {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            WindowManager.LayoutParams param = window.getAttributes();
            window.setDimAmount(0);
            param.height = WindowManager.LayoutParams.WRAP_CONTENT;
            param.width = WindowManager.LayoutParams.WRAP_CONTENT;
            param.gravity = Gravity.CENTER;
            window.setAttributes(param);
        }

        return galleryDialog;
    }
}