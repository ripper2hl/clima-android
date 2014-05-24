package com.moviles.clima.utilidades;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ViewFlipper;


public class ListenerTouchViewFlipper implements View.OnTouchListener {
	public float init_x;
    private ViewFlipper vf;
	@Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN: //Cuando el usuario toca la pantalla por primera vez
            init_x=event.getX();
            return true;
        case MotionEvent.ACTION_UP: //Cuando el usuario deja de presionar
            float distance =init_x-event.getX();

            if(distance>0)
            {
                 vf.showPrevious();
            }

            if(distance<0)
            {
                 vf.showNext();
            }

        default:
            break;
        }
        return false;
    }
}
