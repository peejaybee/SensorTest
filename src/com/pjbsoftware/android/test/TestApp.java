package com.pjbsoftware.android.test;

import android.app.Activity;
import android.os.Bundle;
import android.hardware.SensorManager;
import android.hardware.SensorListener;
import android.widget.TextView;




public class TestApp extends Activity  {
    TextView mFieldAccelX;
    TextView mFieldAccelY;
    TextView mFieldAccelZ;
    TextView mFieldAccelMag;

    TextView mFieldMagX;
    TextView mFieldMagY;
    TextView mFieldMagZ;
    TextView mFieldMagMag;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_main);
 
        mFieldAccelX = (TextView)findViewById(R.id.value_x);
        mFieldAccelY = (TextView)findViewById(R.id.value_y);
        mFieldAccelZ = (TextView)findViewById(R.id.value_z);
        mFieldAccelMag = (TextView)findViewById(R.id.value_total);
  
        mFieldMagX = (TextView)findViewById(R.id.mag_x);
        mFieldMagY = (TextView)findViewById(R.id.mag_y);
        mFieldMagZ = (TextView)findViewById(R.id.mag_z);
        mFieldMagMag = (TextView)findViewById(R.id.mag_total);

        
        SensorManager sm = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        
        sm.registerListener(new SensorListener() { 
            
            public void onSensorChanged(int whichOne, float[] values)
            { 
                updateAccelValues(values);
            }

    	public void onAccuracyChanged(int sensor, int accuracy)
    	{
    	    // TODO Auto-generated method stub
    	    
    	}}, SensorManager.SENSOR_ACCELEROMETER );
 
        sm.registerListener(new SensorListener() 
        { 
            
            public void onSensorChanged(int whichOne, float[] values)
            { 
                updateMagValues(values);
            }

    	public void onAccuracyChanged(int sensor, int accuracy)
    	{
    	    // TODO Auto-generated method stub
    	    
    	}}, SensorManager.SENSOR_MAGNETIC_FIELD );
    }
    
    void updateAccelValues(float[] values)
    {
	Double magnitude = Math.sqrt(Math.pow(values[SensorManager.DATA_X],2) +
		Math.pow(values[SensorManager.DATA_Y],2) +
		Math.pow(values[SensorManager.DATA_Z], 2));
	
	mFieldAccelX.setText(new Float(values[SensorManager.DATA_X]).toString());
	mFieldAccelY.setText(new Float(values[SensorManager.DATA_Y]).toString());
	mFieldAccelZ.setText(new Float(values[SensorManager.DATA_Z]).toString());
	mFieldAccelMag.setText(magnitude.toString());
    }
    
    void updateMagValues(float[] values)
    {
	Double magnitude = Math.sqrt(Math.pow(values[SensorManager.DATA_X],2) +
		Math.pow(values[SensorManager.DATA_Y],2) +
		Math.pow(values[SensorManager.DATA_Z], 2));

	mFieldMagX.setText(new Float(values[SensorManager.DATA_X]).toString());
	mFieldMagY.setText(new Float(values[SensorManager.DATA_Y]).toString());
	mFieldMagZ.setText(new Float(values[SensorManager.DATA_Z]).toString());
	mFieldMagMag.setText(magnitude.toString());
    }
}