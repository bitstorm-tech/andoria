package net.joesoft.andoria.listener;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class TouchListener implements OnTouchListener {

	public boolean onTouch(View v, MotionEvent event) {
		System.out.println("Got event: " + event.getAction());
		return true;
	}

}
