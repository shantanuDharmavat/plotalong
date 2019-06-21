package com.plotalong.android.util;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * Created by kbhakade on 28/7/17.
 */

public class DatabaseBackupRestoreManager {
    private static final String TAG = DatabaseBackupRestoreManager.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    private final Context context;

    public DatabaseBackupRestoreManager(Context context) {
        this.context = context;
    }

    public void restoredDatabase() {
        Log.e(TAG, "restoredDatabase: ");
        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();
            if (sd.canWrite()) {
                String currentDBPath = "//data//" + "com.plotalong.android" + "//databases//" + "PlotAlong.db";
                String backupDBPath = "PlotAlong.db";
                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(sd, backupDBPath);

                FileChannel src = new FileInputStream(currentDB).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
                Toast.makeText(context, "Restored Successful!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(context, "Restored Failed!", Toast.LENGTH_SHORT).show();
        }
    }

    public void backupDatabase() {
        Log.e(TAG, "backupDatabase: ");
        try {
            File sd = FileHandlingUtil.createDirectory("New Plot Along Database");
            File data = Environment.getDataDirectory();
            if (sd.canWrite()) {
                String currentDBPath = "//data//" + "com.plotalong.android"
                        + "//databases//" + "PlotAlong.db";
                String backupDBPath = "PlotAlong.db";
                File backupDB = new File(data, currentDBPath);
                File currentDB = new File(sd, backupDBPath);

                FileChannel src = new FileInputStream(backupDB).getChannel();
                FileChannel dst = new FileOutputStream(currentDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
                Toast.makeText(context, "Backup Successful!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(context, "Backup Failed!", Toast.LENGTH_SHORT).show();
        }
    }
}
