package com.moviles.clima.tareas;

import java.lang.ref.WeakReference;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.moviles.clima.R;
import com.moviles.clima.actividades.Principal;
import com.moviles.clima.utilidades.OpenWeather;

public class OpenWeatherTask extends AsyncTask<String, JSONObject, JSONObject> {

	WeakReference<Principal> context;
	ProgressDialog pDialog;
	
	public OpenWeatherTask(Principal principal){
		context = new WeakReference<Principal>(principal);
	}
	
	@Override
    protected void onPreExecute() {
		 super.onPreExecute();
		 
	        pDialog = new ProgressDialog(context.get());
	        pDialog.setMessage("Cargando...");
	        pDialog.setCancelable(true);
	        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	        pDialog.show();
    }
	
	@Override
	protected JSONObject doInBackground(String... params) {
		JSONObject respuesta = null;

			String ciudad = params[0].toString();
			OpenWeather openWeather = new OpenWeather();
			respuesta = openWeather.getData(ciudad);
			Log.i("Respuesta",respuesta.toString());
		return respuesta;
	}
	
	@Override
	protected void onPostExecute(JSONObject result) {
		super.onPostExecute(result);
		Principal principal = context.get();
		JSONObject datosPrincipales = null;
		String temperatura = null;
		String ciudad = null;
		String descripcion = null;
		String tempMax = null;
		String tempMin = null;
		 if (principal != null && !principal.isFinishing()) {
			 TextView temperaturaVw = (TextView) principal.findViewById(R.id.temperatura);
			 TextView ciudadVw = (TextView) principal.findViewById(R.id.ciudad);
			 try {
				 ciudad = result.getString("name");
				 datosPrincipales = (JSONObject) result.get("main");
				 temperatura = datosPrincipales.getString("temp");
				 
			} catch (JSONException e) {
				e.printStackTrace();
			}
			 temperaturaVw.setText(temperatura.toString() + "ᵒC");
			 ciudadVw.setText(ciudad);
         }
		 pDialog.dismiss();
	}
}
