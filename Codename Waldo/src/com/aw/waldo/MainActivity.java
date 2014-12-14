package com.aw.waldo;

import com.aw.waldo.R;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.DragEvent;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnGestureListener, OnDoubleTapListener, View.OnDragListener{
	
	private static final String SERVER_URL = "http://10.122.201.217:8080/waldo_server/restImageTest/save/";
	private static final String DEBUG_TAG = "Gestures";
    private GestureDetectorCompat mDetector; 
    private int mActivePointerId;
 // The ‘active pointer’ is the one currently moving our object.
//    private int mActivePointerId = INVALID_POINTER_ID;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		// Instantiate the gesture detector with the
        // application context and an implementation of
        // GestureDetector.OnGestureListener
        mDetector = new GestureDetectorCompat(this,this);
        // Set the gesture detector as the double tap
        // listener.
        mDetector.setOnDoubleTapListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override 
    public boolean onTouchEvent(MotionEvent event){ 
        this.mDetector.onTouchEvent(event);
        // Be sure to call the superclass implementation
        
        //TODO 
        
        // Get the pointer ID
        mActivePointerId = event.getPointerId(0);

        // ... Many touch events later...

        // Use the pointer ID to find the index of the active pointer 
        // and fetch its position
        int pointerIndex = event.findPointerIndex(mActivePointerId);
        // Get the pointer's current position
        float x = event.getX(pointerIndex);
        float y = event.getY(pointerIndex);
        
        return super.onTouchEvent(event);
        
	}

    @Override
    public boolean onDown(MotionEvent event) { 
//        Log.d(DEBUG_TAG,"onDown: " + event.toString()); 
//        Toast.makeText(getApplicationContext(), "Down", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2, 
            float velocityX, float velocityY) {
//        Log.d(DEBUG_TAG, "onFling: " + event1.toString()+event2.toString());
        Toast.makeText(getApplicationContext(), "Fling!!!", Toast.LENGTH_SHORT).show();
        //TODO check if the fling is a swipe then change the imageview 
        Log.d(DEBUG_TAG, "FLING!!!");
        ImageView image = (ImageView) findViewById(R.id.imageView1);
        image.setImageResource(R.drawable.qe2);
        return true;
    }

    @Override
    public void onLongPress(MotionEvent event) {
//        Log.d(DEBUG_TAG, "onLongPress: " + event.toString()); 
//        Toast.makeText(getApplicationContext(), "Long Press", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
            float distanceY) {
//        Log.d(DEBUG_TAG, "onScroll: " + e1.toString()+e2.toString());
//        Toast.makeText(getApplicationContext(), "On Scroll", Toast.LENGTH_SHORT).show();
        
        
        //TODO change image
        
        return true;
    }

    @Override
    public void onShowPress(MotionEvent event) {
//        Log.d(DEBUG_TAG, "onShowPress: " + event.toString());
//        Toast.makeText(getApplicationContext(), "Show Press", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
//        Log.d(DEBUG_TAG, "onSingleTapUp: " + event.toString());
//        Toast.makeText(getApplicationContext(), "Single Tap Up", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {
//        Log.d(DEBUG_TAG, "onDoubleTap: " + event.toString());
//        Toast.makeText(getApplicationContext(), "Double Tap..", Toast.LENGTH_SHORT).show();
        
        //SPLAT set visible and location
        ImageView imageView = (ImageView) findViewById(R.id.imageView2);
        imageView.setVisibility(ImageView.VISIBLE);
        float cx = event.getX() - imageView.getWidth()/2;
        float cy = event.getY() - 3*imageView.getHeight()/4;
        imageView.setX(cx);
        imageView.setY(cy);
        
        sendTapLocationToServer(cx, cy);
        return true;
    }
    
    private void sendTapLocationToServer(float x, float y){
    	//TODO convert the x & y location into 28x28 in another method
    	
    	
    	new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    RestClient client = new RestClient(SERVER_URL);
                    
                    //TODO add parameters here about 
//                        client.AddParam("accountType", "GOOGLE");
//                        client.AddParam("source", "tboda-widgalytics-0.1");
//                        client.AddParam("Email", _username);
//                        client.AddParam("Passwd", _password);
//                        client.AddParam("service", "analytics");
//                        client.AddHeader("GData-Version", "2");
                    client.AddParam("label", "8");
                    client.AddParam("serial", "thisisaserializedarrayrepresentinganimage.");
                    client.AddHeader("contentType", "application/json;charset=utf-8");
                    client.AddHeader("dataType", "json");
               
                    
                    client.Execute(RestClient.RequestMethod.POST);
                    String response = client.getResponse();
                    Log.d("Response", response);
                } catch (Exception e) {
                    Log.e("REST Error", e.getMessage(), e.getCause());
                }
            }
        }).start();
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
//        Log.d(DEBUG_TAG, "onDoubleTapEvent: " + event.toString());
//        Toast.makeText(getApplicationContext(), "Double Tap Event", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
    	
//        Log.d(DEBUG_TAG, "onSingleTapConfirmed: " + event.toString());
//        Toast.makeText(getApplicationContext(), "Single Tap Confirmed", Toast.LENGTH_SHORT).show();
        return true;
    }

	@Override
	public boolean onDrag(View v, DragEvent event) {
		// TODO Draw Box using Bitmap drawable or OpenGL
		return false;
	}

}
