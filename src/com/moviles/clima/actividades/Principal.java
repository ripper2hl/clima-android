package com.moviles.clima.actividades;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.moviles.clima.R;
import com.moviles.clima.tareas.OpenWeatherTask;

public class Principal extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_principal);
		TextView fecha = (TextView)findViewById(R.id.fecha);
		fecha.setText("Viernes");
		//Toast.makeText(getApplicationContext(),"Toast por defecto", Toast.LENGTH_SHORT).show();
		OpenWeatherTask opWeatherTask = new OpenWeatherTask(this);
		opWeatherTask.execute("guadalupe");
//		LocationManager milocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
//		LocationListener milocListener = new Geolocalizacion();
//		milocManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 5000, 10, milocListener);
		
		ImageView imageView = (ImageView)findViewById(R.id.imagenClima);
		imageView.setImageResource(R.drawable.despejado);
	}
	
	public void setLocation(Location loc){
		try {
			System.out.print(loc.getLatitude());
			System.out.print(loc.getLongitude());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
