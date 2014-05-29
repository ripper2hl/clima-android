package com.moviles.clima.utilidades;

import java.util.regex.Pattern;

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
	
	private Integer despejadoId;
	private Integer electricaId;
	private Integer granizoId;
	private Integer lloviznaId;
	private Integer lluviaId;
	private Integer nieblaId;
	private Integer nubladoId;

	
	public OpenWeather(Integer despejadoId, Integer electricaId,
			Integer granizoId, Integer lloviznaId, Integer lluviaId,
			Integer nieblaId, Integer nubladoId) {
		super();
		this.despejadoId = despejadoId;
		this.electricaId = electricaId;
		this.granizoId = granizoId;
		this.lloviznaId = lloviznaId;
		this.lluviaId = lluviaId;
		this.nieblaId = nieblaId;
		this.nubladoId = nubladoId;
	}

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
/*			String url = "http://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon+"&lang=sp";*/
			String url = "http://clima-jesusisrael.rhcloud.com/clima/openweather/geo/?lat=" + lat + "&lon=" + lon+"&lang=sp&so=android";
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
			String url = "http://clima-jesusisrael.rhcloud.com/clima/openweather/ciudad/?ciudad="+ ciudad + "&lang=sp&so=android";
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
	
	/**
	 * Regresa el nombre de la imagen segun sea la descripcion del 
	 * clima que se obtuvo de la consulta
	 * @param descripcion estado del clima
	 * @return imagenId id de la imagen en forma de cadena;
	 */
	public String getImagen(String descripcion){
		String exRegDespejado = "sol";
		String exRegElectrica = "elec";
		String exRegGranizo = "nie";
		String exRegLlovizna = "llov";
		String exRegLluvia = "lluv";
		String exRegNiebla = "nieb";
		String exRegNublado = "nubes";
		
		if(Pattern.matches(exRegDespejado, descripcion)){
			return getDespejadoId().toString();
		}
		
		if(Pattern.matches(exRegElectrica, descripcion)){
			return getElectricaId().toString();
		}
		
		if(Pattern.matches(exRegGranizo, descripcion)){
			return getGranizoId().toString();
		}
		
		if(Pattern.matches(exRegLlovizna, descripcion)){
			return getLloviznaId().toString();
		}
		
		if(Pattern.matches(exRegLluvia, descripcion)){
			return getLluviaId().toString();
		}
		
		if(Pattern.matches(exRegNiebla, descripcion)){
			return getNieblaId().toString();
		}
		
		if(Pattern.matches(exRegNublado, descripcion)){
			return getNubladoId().toString();
		}
		
		return getDespejadoId().toString();
	}

	public Integer getDespejadoId() {
		return despejadoId;
	}

	public void setDespejadoId(Integer despejadoId) {
		this.despejadoId = despejadoId;
	}

	public Integer getElectricaId() {
		return electricaId;
	}

	public void setElectricaId(Integer electricaId) {
		this.electricaId = electricaId;
	}

	public Integer getGranizoId() {
		return granizoId;
	}

	public void setGranizoId(Integer granizoId) {
		this.granizoId = granizoId;
	}

	public Integer getLloviznaId() {
		return lloviznaId;
	}

	public void setLloviznaId(Integer lloviznaId) {
		this.lloviznaId = lloviznaId;
	}

	public Integer getLluviaId() {
		return lluviaId;
	}

	public void setLluviaId(Integer lluviaId) {
		this.lluviaId = lluviaId;
	}

	public Integer getNieblaId() {
		return nieblaId;
	}

	public void setNieblaId(Integer nieblaId) {
		this.nieblaId = nieblaId;
	}

	public Integer getNubladoId() {
		return nubladoId;
	}

	public void setNubladoId(Integer nubladoId) {
		this.nubladoId = nubladoId;
	}
}