package com.moviles.clima.actividades;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.moviles.clima.R;
import com.moviles.clima.tareas.OpenWeatherTask;

public class Principal extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);
		TextView fecha = (TextView)findViewById(R.id.fecha);
		TextView temperatura = (TextView)findViewById(R.id.temperatura);
		TextView ciudad = (TextView)findViewById(R.id.ciudad);
		TextView descripcion = (TextView)findViewById(R.id.descripcion);
		TextView temperaturas = (TextView)findViewById(R.id.temperaturas);
		fecha.setText("Viernes");
		Toast.makeText(getApplicationContext(),"Toast por defecto", Toast.LENGTH_SHORT).show();
		OpenWeatherTask opWeatherTask = new OpenWeatherTask(this);
		opWeatherTask.execute("guadalupe");
//		LocationManager milocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
//		LocationListener milocListener = new Geolocalizacion();
//		milocManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 5000, 10, milocListener);
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
