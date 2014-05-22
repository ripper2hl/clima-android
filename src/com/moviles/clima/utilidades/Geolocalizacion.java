package com.moviles.clima.utilidades;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import com.moviles.clima.actividades.Principal;

public class Geolocalizacion implements LocationListener{

	Principal principal;
	Double latitud;
	Double longitud;
	
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
}