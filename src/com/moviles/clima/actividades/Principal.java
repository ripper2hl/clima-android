package com.moviles.clima.actividades;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ViewFlipper;
import android.os.Build;

import com.moviles.clima.R;
import com.moviles.clima.R;
//import com.moviles.clima.actividades.ViewflipperBlogActivity.ListenerTouchViewFlipper;
import com.moviles.clima.tareas.OpenWeatherTask;
import com.moviles.clima.utilidades.Geolocalizacion;

public class Principal extends Activity {
	private TextView lati;
	private TextView longi;
	private TextView fecha;
	public float init_x;
    private ViewFlipper vf;
    private EditText editText;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		
		try{
	        vf = (ViewFlipper) findViewById(R.id.viewFlipper);
	        
	        vf.setOnTouchListener(new ListenerTouchViewFlipper());
		    fecha = (TextView)findViewById(R.id.fecha);
			lati = (TextView)findViewById(R.id.textLati);
			longi = (TextView)findViewById(R.id.textLongi);
			fecha.setText("Viernes");
			//Toast.makeText(getApplicationContext(),"Toast por defecto", Toast.LENGTH_SHORT).show();
		    editText = (EditText)findViewById(R.id.ingresaCiudad);
			//OpenWeatherTask opWeatherTask = new OpenWeatherTask(this);
			//opWeatherTask.execute("toronto");//Ejecutamos toda la clase
			ImageView imageView = (ImageView)findViewById(R.id.imagenClima);
			imageView.setImageResource(R.drawable.despejado);
			
		    if(editText.getText().toString().matches("")){
		    	Double ubic[]=getGPS();//Sacomos las coordenadas
				lati.setText(String.valueOf(ubic[0]));
			    longi.setText(String.valueOf(ubic[1]));
			    OpenWeatherTask opWeatherTask = new OpenWeatherTask(this);
				opWeatherTask.execute(String.valueOf(ubic[0]),String.valueOf(ubic[1]));//Ejecutamos toda la clase
			    
		    }
		}catch(Exception ex){
			Toast.makeText(getApplicationContext(),"Algo malo paso", Toast.LENGTH_SHORT).show();
		}	
	}
	
	public void getClimaUbic(View view){
		OpenWeatherTask opWeatherTask = new OpenWeatherTask(this);
		opWeatherTask.execute(editText.getText().toString());//Ejecutamos toda la clase
		vf.showNext();
	}
	
	public Double[] getGPS(){
		LocationManager milocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
	       Criteria crit = new Criteria();
	       String provider = milocManager.getBestProvider(crit, true);
	       Location location = milocManager.getLastKnownLocation(provider);
	       LocationListener milocListener = new Geolocalizacion();
	       milocManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 5000, 10, milocListener);
	       //lati.setText(String.valueOf(location.getLatitude()));
	       //longi.setText(String.valueOf(location.getLongitude()));
	       location.getLongitude();
	       return  new  Double []{location.getLatitude(),location.getLongitude()};

	}
	
	
	private class ListenerTouchViewFlipper implements View.OnTouchListener{
		 
        @Override
        public boolean onTouch(View v, MotionEvent event) {
 
            switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: //Cuando el usuario toca la pantalla por primera vez
                init_x=event.getX();
                return true;
            case MotionEvent.ACTION_UP: //Cuando el usuario deja de presionar
                float distance =init_x-event.getX();
 
                if(distance>0)
                {
                     vf.setInAnimation(inFromRightAnimation());
                     vf.setOutAnimation(outToLeftAnimation());
                     vf.showPrevious();
                }
 
                if(distance<0)
                {
                     vf.setInAnimation(inFromLeftAnimation());
                     vf.setOutAnimation(outToRightAnimation());
                     vf.showNext();
                }
 
            default:
                break;
            }
 
            return false;
        }
 
    }
 
    private Animation inFromRightAnimation() {
 
        Animation inFromRight = new TranslateAnimation(
        Animation.RELATIVE_TO_PARENT,  +1.0f, Animation.RELATIVE_TO_PARENT,  0.0f,
        Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,   0.0f );
 
        inFromRight.setDuration(500);
        inFromRight.setInterpolator(new AccelerateInterpolator());
 
        return inFromRight;
 
    }
 
    private Animation outToLeftAnimation() {
        Animation outtoLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        outtoLeft.setDuration(500);
        outtoLeft.setInterpolator(new AccelerateInterpolator());
        return outtoLeft;
    }
 
    private Animation inFromLeftAnimation() {
        Animation inFromLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromLeft.setDuration(500);
        inFromLeft.setInterpolator(new AccelerateInterpolator());
        return inFromLeft;
    }
 
    private Animation outToRightAnimation() {
        Animation outtoRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        outtoRight.setDuration(500);
        outtoRight.setInterpolator(new AccelerateInterpolator());
        return outtoRight;
    }

	
		
}
