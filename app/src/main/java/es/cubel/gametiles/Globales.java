package es.cubel.gametiles;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by cubel on 29/07/16.
 */
public class Globales extends Application {

    private boolean aviso_cookies;

    private Boolean publicidad;


    //Variables estaticas


    //Preferencias
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    //Constructor
    public void onCreate() {
        super.onCreate();

        //Cargamos las preferencias
        prefs = getSharedPreferences(getApplicationContext().getPackageName(), Context.MODE_PRIVATE); //Para poder leer
        editor = prefs.edit(); //Para poder guardar

        //Cargamos el contenido de las preferencias
        this.aviso_cookies = prefs.getBoolean("aviso_cookies", false);

        //Iniciamos la publicidad a no
        this.publicidad = false;
    }



    //Getters
    public boolean getPublicidad() {
        return this.publicidad;
    }
    public boolean getAviso_cookies() {
        return this.aviso_cookies;
    }



    //Setters
    public void setPublicidad(boolean publicidad) {
        this.publicidad = publicidad;
    }
    public void setAviso_cookies(boolean aviso_cookies) {
        //Editamos las preferencias
        editor.putBoolean("aviso_cookies", aviso_cookies);
        editor.commit();

        this.aviso_cookies = aviso_cookies;
    }
}