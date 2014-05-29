package com.moviles.clima.tareas;

import java.lang.ref.WeakReference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.moviles.clima.R;
import com.moviles.clima.actividades.Principal;
import com.moviles.clima.utilidades.Conversion;
import com.moviles.clima.utilidades.Dia;
import com.moviles.clima.utilidades.OpenWeather;

public class OpenWeatherTask extends AsyncTask<String, JSONObject, String[]> {

	WeakReference<Principal> context;
	ProgressDialog pDialog;
	
	public OpenWeatherTask(Principal principal){
		context = new WeakReference<Principal>(principal);
	}
	
	@Override
    protected void onPreExecute() {//Primero en ejecucion
		 super.onPreExecute();
		 
	        pDialog = new ProgressDialog(context.get());
	        pDialog.setMessage("Cargando...");
	        pDialog.setCancelable(true);
	        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	        pDialog.show();
    }
	
	@Override
	protected String[] doInBackground(String... params) { //Segundo en ejecucuion
		JSONObject respuesta = null;
		String resultados[] = new String[7];
		try {
			

			OpenWeather openWeather = new OpenWeather(R.drawable.despejado, R.drawable.electrica, R.drawable.granizo, R.drawable.llovizna, R.drawable.lluvia, R.drawable.niebla, R.drawable.nublado);//Instanciamos OpenWeather
			Conversion conversion = new Conversion();//Convertimos de K a C
			Dia dia = new Dia();
			
			 if(params.length > 1){
				Double lati=Double.valueOf(params[0]);
				Double longi=Double.valueOf(params[1]);
				respuesta = openWeather.getData(lati,longi);//Mandamos las cordenadas para que nos retorne el clima
				}else{
					String ciudad = params[0].toString();
					respuesta = openWeather.getData(ciudad);//Mandamos la ciudad para que nos retorne el clima
				}
			Log.i("Respuesta",respuesta.toString());
			JSONObject datosPrincipales = null;
			String temperatura = null;
			String c = null;
			String desc = null;
			String tempMax = null;
			String tempMin = null;
			Integer t = null;
			Integer tMax = null;
			Integer tMin = null;
			 try {
				 c = respuesta.getString("name");
				 datosPrincipales = (JSONObject) respuesta.get("main");
				 JSONArray weather = respuesta.getJSONArray("weather");
				 JSONObject w = weather.getJSONObject(0);
				 temperatura = datosPrincipales.getString("temp");
				 desc = w.getString("description");
				 tempMax = datosPrincipales.getString("temp_max");
				 tempMin = datosPrincipales.getString("temp_min");
				 t = conversion.kelvinCelsius(Double.parseDouble(temperatura.toString()));
				 tMax = conversion.kelvinCelsius(Double.parseDouble(tempMax.toString()));
				 tMin = conversion.kelvinCelsius(Double.parseDouble(tempMin.toString()));
				 resultados[0] = t.toString() ;
				 resultados[1] = c;
				 resultados[2] = desc;
				 resultados[3] = tMax.toString();
				 resultados[4] = tMin.toString();
				 resultados[5] = dia.getDia();
				 resultados[6] = openWeather.getImagen(desc);
			} catch (JSONException e) {
				e.printStackTrace();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultados;
	}
	
	@Override
	protected void onPostExecute(String[] result) {//Tercero en ejecucuion
		super.onPostExecute(result);
		Principal principal = context.get();
		String temperatura = result[0];
		String ciudad = result[1];
		String descripcion = result[2];
		String tempMax = result[3];
		String tempMin = result[4];
		String dia = result[5];
		Integer imagen = Integer.parseInt(result[6]);
		 if (principal != null && !principal.isFinishing()) {
			 TextView temperaturaVw = (TextView) principal.findViewById(R.id.temperatura);
			 TextView ciudadVw = (TextView) principal.findViewById(R.id.ciudad);
			 TextView descripcionVw = (TextView) principal.findViewById(R.id.descripcion);
			 TextView temperaturasVw = (TextView) principal.findViewById(R.id.temperaturas);
			 TextView fechaVw = (TextView) principal.findViewById(R.id.fecha);
			 ImageView imagenVw = (ImageView) principal.findViewById(R.id.imagenClima);
			 try {
				 temperaturaVw.setText(temperatura.toString() + " ᵒC");
				 ciudadVw.setText(ciudad);
				 descripcionVw.setText(descripcion);
				 temperaturasVw.setText(tempMax + " ᵒC/" + tempMin + " ᵒC" );
				 fechaVw.setText(dia);
				 imagenVw.setImageResource(imagen);
			} catch (Exception e) {
				e.printStackTrace();
			}

         }
		 pDialog.dismiss();
	}
}
