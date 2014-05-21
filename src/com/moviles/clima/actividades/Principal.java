package com.moviles.clima.actividades;

import com.moviles.clima.R;
import com.moviles.clima.utilidades.Geolocalizacion;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

public class Principal extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);
		LocationManager milocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		LocationListener milocListener = new Geolocalizacion();
		milocManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 5000, 10, milocListener);
	}
	
	public void setLocation(Location loc){
		try {
			Toast.makeText( getApplicationContext(),"Gps Desactivado",Toast.LENGTH_SHORT ).show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
