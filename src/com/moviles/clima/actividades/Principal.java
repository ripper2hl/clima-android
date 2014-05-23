package com.moviles.clima.actividades;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.moviles.clima.R;
import com.moviles.clima.tareas.OpenWeatherTask;
import com.moviles.clima.utilidades.Geolocalizacion;

public class Principal extends Activity {
	private TextView lati;
	private TextView longi;
	private TextView fecha;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_principal);
	    fecha = (TextView)findViewById(R.id.fecha);
		lati = (TextView)findViewById(R.id.textLati);
		longi = (TextView)findViewById(R.id.textLongi);
		fecha.setText("Viernes");
		//Toast.makeText(getApplicationContext(),"Toast por defecto", Toast.LENGTH_SHORT).show();
		OpenWeatherTask opWeatherTask = new OpenWeatherTask(this);
		opWeatherTask.execute("guadalupe");//Ejecutamos toda la clase
		ImageView imageView = (ImageView)findViewById(R.id.imagenClima);
		imageView.setImageResource(R.drawable.despejado);
		double ubic[]=getGPS();//Sacomos las coordenadas
		lati.setText(String.valueOf(ubic[0]));
	    longi.setText(String.valueOf(ubic[1]));
	
	
		
	
	}
	public double[] getGPS(){
		LocationManager milocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
	       Criteria crit = new Criteria();
	       String provider = milocManager.getBestProvider(crit, true);
	       Location location = milocManager.getLastKnownLocation(provider);
	       LocationListener milocListener = new Geolocalizacion();
	       milocManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 5000, 10, milocListener);
	       //lati.setText(String.valueOf(location.getLatitude()));
	       //longi.setText(String.valueOf(location.getLongitude()));
	       location.getLongitude();
	       return  new  double []{location.getLatitude(),location.getLongitude()};

	}
	
	
		
}
