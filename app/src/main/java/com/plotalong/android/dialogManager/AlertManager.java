package com.plotalong.android.dialogManager;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.plotalong.android.listener.YesNoAlertListener;
import com.plotalong.android.util.GlobalConstant;

/**
 * Created by kbhakade on 29/6/17.
 */

public class AlertManager {
    private final String TAG = AlertManager.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    private YesNoAlertListener yesNoAlertListener;
    private Context context;

    public AlertManager(Context context, YesNoAlertListener yesNoAlertListener) {
        Log.e(TAG, "AlertManager: ");
        this.context = context;
        this.yesNoAlertListener = yesNoAlertListener;
    }


    public void confirmationDialog(final String title, String message) {
        Log.e(TAG, "confirmationDialog: ");
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setCancelable(false);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setPositiveButton(GlobalConstant.STRING_YES, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                yesNoAlertListener.onYesResponse(title);
            }
        })
                .setNegativeButton(GlobalConstant.STRING_NO, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        yesNoAlertListener.onNoResponse(title);
                    }
                });

        final AlertDialog alert = dialog.create();
        alert.show();
    }


    public void informationDialog(final String title, String message) {
        Log.e(TAG, "informationDialog: ");
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setCancelable(false);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setPositiveButton(GlobalConstant.STRING_OK, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                yesNoAlertListener.onOkResponse(title);
            }
        });
        final AlertDialog alert = dialog.create();
        alert.show();
    }

    public void customerRemoveDialog(final String title, String message) {
        Log.e(TAG, "informationDialog: ");
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setCancelable(false);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setPositiveButton(GlobalConstant.STRING_OK, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                yesNoAlertListener.onOkResponse(title);
            }
        });
        final AlertDialog alert = dialog.create();
        alert.show();
    }
}