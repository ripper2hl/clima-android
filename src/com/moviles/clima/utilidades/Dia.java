package com.moviles.clima.utilidades;

import java.util.Calendar;
import java.util.*;

/**
 * Contiene metodos utiles para 
 * manejar las fechas
 * @author Jesus Perales
 *
 */
public class Dia{
	
	/**
	 * 
	 * @return nombre del dia de la semana
	 */
	public String getDia(){
		String[] dias={"Domingo","Lunes","Martes", "Miércoles","Jueves","Viernes","Sábado"};
		Calendar date = Calendar.getInstance();
		return dias[date.get(Calendar.DAY_OF_WEEK) - 1];
	}

}