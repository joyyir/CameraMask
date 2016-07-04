package com.cameramask;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.Camera;
import android.os.Build;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;


public class Preview extends SurfaceView implements SurfaceHolder.Callback {

    SurfaceHolder mHolder;
    Camera mCamera = null;

    public Preview(Context context, AttributeSet attrs) {
        super(context, attrs);

        try {
            if (mCamera == null)
                mCamera = Camera.open();

            mHolder = getHolder();
            mHolder.addCallback(this);
            mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @TargetApi(Build.VERSION_CODES.FROYO)
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            if (mCamera == null) {
                mCamera = Camera.open();
            }

            if (mCamera != null) {
                try {
                    mCamera.setPreviewDisplay(holder);
                    Camera.Parameters parameters = mCamera.getParameters();
                    mCamera.setDisplayOrientation(90);
                    parameters.setRotation(90);
                    mCamera.setParameters(parameters);
                    mCamera.startPreview();
                } catch (IOException e) {
                    e.printStackTrace();
                    mCamera.release();
                    mCamera = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        // 표시할 영역의 크기를 알았으므로 해당 크기로 Preview를 시작합니다.
        //Log.d("surfaceChanged", "w: " + w + ", h: " + h);
        Camera.Parameters parameters = mCamera.getParameters();
        parameters.setPreviewSize(w, h);
        mCamera.startPreview();
    }
}

