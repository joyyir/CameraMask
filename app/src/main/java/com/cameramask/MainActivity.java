package com.cameramask;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Window;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	private Preview mPreview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Window win = getWindow();
		win.setContentView(R.layout.bottom); // 첫번째에 bottom을 깔고
		// 그 다음 인플레이션으로 겹치는 레이아웃을 깐다.
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LinearLayout linear = (LinearLayout) inflater.inflate(R.layout.top, null);
		LinearLayout.LayoutParams paramlinear = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
		win.addContentView(linear, paramlinear); // 이 부분이 레이아웃을 겹치는 부분. add는 기존의 레이아웃에 겹쳐서 배치하라는 뜻이다.
		mPreview = (Preview) win.findViewById(R.id.camera_preview);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		if (mPreview != null) {
			if (mPreview.mCamera != null) {
				mPreview.mCamera.stopPreview();
				mPreview.mCamera.release();
				mPreview.mCamera = null;
			}
		}
		super.onDestroy();
	}
}




