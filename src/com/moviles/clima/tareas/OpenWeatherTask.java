package com.moviles.clima.tareas;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.moviles.clima.utilidades.OpenWeather;

public class OpenWeatherTask extends AsyncTask<String, String, String> {

	@Override
	protected String doInBackground(String... params) {
		String ciudad = params[0].toString();
		OpenWeather openWeather = new OpenWeather();
		String respuesta = openWeather.getData(ciudad).toString();
		Log.i("Respuesta",respuesta.toString());
		return respuesta;
	}
	
	@Override
	protected void onPostExecute(String result) {
	}
}
