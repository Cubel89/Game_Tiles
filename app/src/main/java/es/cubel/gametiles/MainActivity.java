package es.cubel.gametiles;

import android.content.DialogInterface;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity {

    /**
     * Log
     */
    private String logname = "MainActivity";

    //Variables
    private Globales globales;
    private InterstitialAd mInterstitialAd;
    private View view;


    /**
     * Instancia del drawer
     */
    private DrawerLayout drawerLayout;

    /**
     * Titulo inicial del drawer
     */
    private String drawerTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Variables globales
        globales = (Globales) getApplication();
        view = findViewById(android.R.id.content);
        setToolbar(); // Setear Toolbar como action bar
    }


    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.mipmap.ic_menu);
            ab.setDisplayHomeAsUpEnabled(true);
        }

    }


    /**
     * Aviso Cookies
     */
    private void aviso_cookies() {
        boolean pregunta = true;

        //Creamos el cartel
        AlertDialog.Builder cartel_mostrar = new AlertDialog.Builder(this);

        //Añadimos su icono
        cartel_mostrar.setIcon(R.mipmap.ic_information_outline_grey600_48dp);

        //Añadimos titulo
        cartel_mostrar.setTitle(this.getString(R.string.titulo_politica_cookies));

        //Añadimos mensaje
        cartel_mostrar.setMessage(this.getString(R.string.texto_politica_cookies));

        //Comprobamos si es informacion o pregunta
        if (pregunta) { //Si es verdadero añadiremos los dos botones
            cartel_mostrar.setPositiveButton(this.getString(R.string.boton_aceptar), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    globales.setAviso_cookies(true);
                }
            });
            cartel_mostrar.setNegativeButton(this.getString(R.string.boton_cancelar), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    System.exit(0);
                }
            });
        } else { //Si es falso, solo uno
            cartel_mostrar.setPositiveButton(this.getString(R.string.boton_leido), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // Do something else
                    //Log.d("Dialogo", getActivity().getString(R.string.boton_leido));
                    //solicitar_permisos();
                    //return true;
                }
            });
        }

        //Mostramos el cartel
        cartel_mostrar.show();
    }

    /**
     * Publicidad pantalla completa
     */
    private void showInterstitial() {
         Log.d(logname, "DEBERIA SALIR PUBLICIDAD");

        Log.d(logname, "PUBLICIDAD");
        //Cargamos la publicidad
        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
        // Show the ad if it's ready. Otherwise toast and restart the game.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }


    }
}
