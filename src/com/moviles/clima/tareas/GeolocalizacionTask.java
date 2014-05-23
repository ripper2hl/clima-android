package com.moviles.clima.tareas;

import com.moviles.clima.utilidades.Geolocalizacion;

import android.os.AsyncTask;

public class GeolocalizacionTask extends AsyncTask<String, String, String> {

	@Override
	protected String doInBackground(String... params) {
		Geolocalizacion geoloc = new Geolocalizacion();
		//geoloc.onLocationChanged(loc);
		return null;
	}
	
	

}
