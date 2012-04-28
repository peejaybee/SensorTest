package com.pjbsoftware.android.test;

import android.app.Activity;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
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
    
    private Sensor mAccelerometer;
    private Sensor mMagnetometer;
    private SensorManager mSensorManager;

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

        
        mSensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mMagnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        
    }
    
    protected void onResume()
    {
    	super.onResume();
    	mSensorManager.registerListener(new SensorEventListener() { 
            
            public void onSensorChanged(SensorEvent e)
            { 
                updateAccelValues(e.values);
            }

    	public void onAccuracyChanged(Sensor sensor, int accuracy)
    	{
    	    // TODO Auto-generated method stub
    	    
    	}}, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL );
 
    	mSensorManager.registerListener(new SensorEventListener() 
        { 
            
            public void onSensorChanged(SensorEvent e)
            { 
                updateMagValues(e.values);
            }

    	public void onAccuracyChanged(Sensor sensor, int accuracy)
    	{
    	    // TODO Auto-generated method stub
    	    
    	}}, mMagnetometer, SensorManager.SENSOR_DELAY_NORMAL );
	
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