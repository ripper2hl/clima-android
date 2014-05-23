package com.moviles.clima.utilidades;

import java.util.Calendar;
import java.util.*;

public class Dia{
	public String getDia(){
		String[] dias={"Domingo","Lunes","Martes", "Miércoles","Jueves","Viernes","Sábado"};
		Calendar date = Calendar.getInstance();
		return dias[date.get(Calendar.DAY_OF_WEEK) - 1];
	}

}