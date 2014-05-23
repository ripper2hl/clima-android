package com.moviles.clima.utilidades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public class OpenWeather{

	public JSONObject getData(Double lat, Double lon) {
		int status = 0;
		String result = null;
		InputStream inputStream = null;
		HttpResponse response = null;
		JSONObject jsonObject = null;
		try {
			// Instanciamos el cliente
			DefaultHttpClient httpClient = new DefaultHttpClient();
			// Vamos por el json
			String url = "http://api.openweathermap.org/data/2.5/forecast?lat=" + lat + "&lon=" + lon+"&lang=sp";
			HttpGet httpGet = new HttpGet(url);
			response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			inputStream = entity.getContent();
		} catch (IOException e) {
			e.printStackTrace();
			status = -1;
		}
		
		try{
	         BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"),8);
	         StringBuilder sb = new StringBuilder();
	         String line = null;
	         while ((line = reader.readLine()) != null) {
	             sb.append(line + "\n");
	         }
	         inputStream.close();
	         result = sb.toString();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try{
			jsonObject = new JSONObject(result);
	     }catch(JSONException e){
	    	 e.printStackTrace();
	     }
		return jsonObject;
	}
	
	public JSONObject getData(String ciudad) {
		int status = 0;
		String result = null;
		InputStream inputStream = null;
		HttpResponse response = null;
		JSONObject jsonObject = null;
		try {
			// Instanciamos el cliente
			DefaultHttpClient httpClient = new DefaultHttpClient();
			// Vamos por el json
			String url = "http://api.openweathermap.org/data/2.5/weather?q="+ ciudad + "&lang=sp";
			HttpGet httpGet = new HttpGet(url);
			response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			inputStream = entity.getContent();
		} catch (IOException e) {
			e.printStackTrace();
			status = -1;
		}
		
		try{
	         BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"),8);
	         StringBuilder sb = new StringBuilder();
	         String line = null;
	         while ((line = reader.readLine()) != null) {
	             sb.append(line + "\n");
	         }
	         inputStream.close();
	         result = sb.toString();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try{
			jsonObject = new JSONObject(result);
	     }catch(JSONException e){
	    	 e.printStackTrace();
	     }
		return jsonObject;
	}
}