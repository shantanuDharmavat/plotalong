package com.plotalong.android.activity;

import android.content.pm.ActivityInfo;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.plotalong.android.R;
import com.plotalong.android.util.GlobalConstant;

import java.io.IOException;

public class VideoCapture extends AppCompatActivity implements View.OnClickListener, SurfaceHolder.Callback {
    MediaRecorder recorder;
    SurfaceHolder holder;
    boolean recording = false;
    private String TAG = VideoCapture.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.e(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        recorder = new MediaRecorder();
        initRecorder();
        setContentView(R.layout.activity_video_capture);

        SurfaceView cameraView = findViewById(R.id.CameraView);
        holder = cameraView.getHolder();
        holder.addCallback(this);
        cameraView.setClickable(true);
        cameraView.setOnClickListener(this);
    }

    private void initRecorder() {
        Log.e(TAG, "initRecorder: ");
        recorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
        recorder.setVideoSource(MediaRecorder.VideoSource.DEFAULT);
        CamcorderProfile cpHigh = CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH);
        recorder.setProfile(cpHigh);
        recorder.setOutputFile("/sdcard/videocapture_example.mp4");
//        recorder.setMaxDuration(50000); // 50 seconds
//        recorder.setMaxFileSize(5000000); // Approximately 5 megabytes
    }

    private void prepareRecorder() {
        Log.e(TAG, "prepareRecorder: ");
        recorder.setPreviewDisplay(holder.getSurface());
        try {
            recorder.prepare();
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
            finish();
        }
    }

    public void onClick(View v) {
        Log.e(TAG, "onClick: ");
        if (recording) {
            recorder.stop();
            recording = false;
            initRecorder();
            prepareRecorder();
        } else {
            recording = true;
            recorder.start();
        }
    }

    public void surfaceCreated(SurfaceHolder holder) {
        Log.e(TAG, "surfaceCreated: ");
        prepareRecorder();
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.e(TAG, "surfaceChanged: ");
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.e(TAG, "surfaceDestroyed: ");
        if (recording) {
            recorder.stop();
            recording = false;
        }
        recorder.release();
        finish();
    }
}