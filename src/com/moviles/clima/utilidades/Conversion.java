package com.moviles.clima.utilidades;
/**
 * Conversiones de temperaturas
 * @author Aaron Ramirez
 */
public class Conversion {
	
	/**
	 * Obtiene la temperatura en grados kelvin
	 * y la convierte a grados celcius sin decimales
	 * @param k temperatura en grados kelvin
	 * @return Temperatura en grados celsius
	 */
	public Integer kelvinCelsius(Double k){
		Double res;
		res=k-272.15;
		Integer c = res.intValue();
		return c;
	}
}
