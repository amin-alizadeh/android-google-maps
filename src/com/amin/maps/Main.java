package com.amin.maps;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;

public class Main extends MapActivity {

	MapView map;
	long start, stop;
	MyLocationOverlay compass;
	MapController controller;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		map = (MapView) findViewById(R.id.mvMain);
		map.setBuiltInZoomControls(true);

		Touchy t = new Touchy();
		List<Overlay> overlayList = map.getOverlays();
		overlayList.add(t);
		
		compass = new MyLocationOverlay(Main.this, map);
		overlayList.add(compass);
		
		controller = map.getController();
		GeoPoint point = new GeoPoint(51643234, 7848593);
		controller.animateTo(point);
		controller.setZoom(6);
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		compass.disableCompass();
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		compass.enableCompass();
		super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	class Touchy extends Overlay {

		@Override
		public boolean onTouchEvent(MotionEvent e, MapView m) {
			if (e.getAction() == MotionEvent.ACTION_DOWN) {
				start = e.getEventTime();
			} else if (e.getAction() == MotionEvent.ACTION_UP) {
				stop = e.getEventTime();
			}

			if ((stop - start) > 500) {
				AlertDialog alert = new AlertDialog.Builder(Main.this).create();
				alert.setTitle("Pick an option");
				alert.setMessage("What option are you willing to pick?");
				alert.setButton("Place a pinpoint", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
					}
				});
				
				alert.setButton2("Get the address", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
					}
				});
				
				alert.setButton3("Get the address", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
					}
				});

				alert.show();
				return true;
			}

			return false;
		}

	}
}
