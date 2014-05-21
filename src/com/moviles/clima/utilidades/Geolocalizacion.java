package com.moviles.clima.utilidades;

import com.moviles.clima.actividades.Principal;

import android.location.Location;
import android.location.LocationListener;
import android.net.MailTo;
import android.os.Bundle;
import android.widget.Toast;

public class Geolocalizacion implements LocationListener{

	private Principal principal;
	private Double latitud;
	private Double longitud;
	
	@Override
	public void onLocationChanged(Location loc) {
		loc.getLatitude();
	    loc.getLongitude();
	    latitud = loc.getLatitude();
	    longitud = loc.getLongitude();
	    this.principal.setLocation(loc);
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO 
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}
	
	public Principal getPrincipal() {
		return principal;
	}

	public void setPrincipal(Principal principal) {
		this.principal = principal;
	}
}