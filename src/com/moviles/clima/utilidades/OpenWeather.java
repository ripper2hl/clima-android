package com.moviles.clima.utilidades;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Consulta la api de openWeather para obtener
 * los datos del clima
 * @author Jesus Perales
 *
 */
public class OpenWeather{

	/**
	 * @author Jesus Perales
	 * @param lat Latitud de la ubicacion
	 * @param lon Longitud de la ubicacion
	 * @return jsonObject Datos del clima
	 */
	public JSONObject getData(Double lat, Double lon) {//Traemos datos por latitud y longitud
		
		String result = null;
		HttpResponse response = null;
		JSONObject jsonObject = null;
		try {
			// Instanciamos el cliente
			DefaultHttpClient httpClient = new DefaultHttpClient();
			// Vamos por el json
			String url = "http://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon+"&lang=sp";
			HttpGet httpGet = new HttpGet(url);
			response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity);
		} catch (Exception ex) {
			ex.printStackTrace();
		}	
		try{
			jsonObject = new JSONObject(result);
	     }catch(JSONException e){
	    	 e.printStackTrace();
	     }
		return jsonObject;
	}
	
	/**
	 * Obtiene los datos del clima de la ciudad indicada
	 * @param ciudad nombre de la ciudad
	 * @return jsonObject datos del clima
	 */
	public JSONObject getData(String ciudad) {//Traemos datos por ciudad
		String result = null;
		HttpResponse response = null;
		JSONObject jsonObject = null;
		try {
			// Instanciamos el cliente
			DefaultHttpClient httpClient = new DefaultHttpClient();
			// Vamos por el json
			String url = "http://api.openweathermap.org/data/2.5/weather?q="+ ciudad + "&lang=sp";
			HttpGet httpGet = new HttpGet(url);
			response = httpClient.execute(httpGet);
			result = EntityUtils.toString(response.getEntity());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try{
			jsonObject = new JSONObject(result);
	     }catch(JSONException e){
	    	 e.printStackTrace();
	     }
		return jsonObject;
	}
}