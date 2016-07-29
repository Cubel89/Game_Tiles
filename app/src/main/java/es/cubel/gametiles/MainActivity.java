package es.cubel.gametiles;


import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;

import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;


import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


import es.cubel.gametiles.fragments.Home;
import es.cubel.gametiles.fragments.Home2;


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
    Toolbar toolbar;
    ActionBar ab;
    NavigationView navigationView;
    int mNavItemId;//Id del item seleccionado

    /**
     * Titulo inicial del drawer
     */
    private String drawerTitle;


    /**
     * Sensor de luz
     *
     */
    Double limite_lum = 70.0;
    //Preferencias
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Boolean thema_claro;
    Activity act;
    //Temas
    Themes tema = new Themes();

    private SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        act = (Activity) this;

        //Cargamos las preferencias
        prefs = getSharedPreferences(getApplicationContext().getPackageName(), act.MODE_PRIVATE); //Para poder leer
        editor = prefs.edit(); //Para poder guardar
        //Cargamos el contenido de las preferencias
        this.thema_claro = prefs.getBoolean("thema_claro", true);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);


        //Variables globales
        globales = (Globales) getApplication();
        view = findViewById(android.R.id.content);
        setToolbar(); // Setear Toolbar como action bar

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        drawerTitle = getResources().getString(R.string.home_item);
        if (savedInstanceState == null) {
            selectItem(drawerTitle);
        }


        //Inicio
        //mostrar aviso de cookies
        if (!globales.getAviso_cookies()) {
            aviso_cookies();
        }


        //Publicidad
        // Create the InterstitialAd and set the adUnitId.
        mInterstitialAd = new InterstitialAd(this);
        // Defined in res/values/strings.xml
        mInterstitialAd.setAdUnitId(getString(R.string.publicidad_Intersticial_id));


        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                Log.d(logname, "Cerrada publicidad");
            }
        });
    }


    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.mipmap.ic_menu);
            ab.setDisplayHomeAsUpEnabled(true);
        }

    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // Marcar item presionado
                        menuItem.setChecked(true);

                        mNavItemId = menuItem.getOrder();
                        Log.d("ITEM Selected", String.valueOf(mNavItemId));
                        // Crear nuevo fragmento
                        String title = menuItem.getTitle().toString();
                        selectItem(title);
                        return true;
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void selectItem(String title) {
        Log.d(logname,title);
        //Cargamos el fragment
        cargar_fragment(title);

        drawerLayout.closeDrawers(); // Cerrar drawer
        setTitle(title); // Setear título actual*/

    }

    public void cargar_fragment(String title){
        //Creamos la variable fragment
        Fragment fragment = null;


        //Comprobamos el fragment
        if (title == getApplication().getString(R.string.home_item)) fragment = new Home();
        else if (title == getApplication().getString(R.string.arduino_item))
            fragment = new Home2();
        else if (title == getApplication().getString(R.string.log_out_item))
            System.exit(0);
        else {
            fragment = new Home();
            Snackbar.make(findViewById(android.R.id.content), "Activity no existente", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }

        //Cargamos el fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_content, fragment)
                .commit();
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


    private final SensorEventListener LightSensorListener
            = new SensorEventListener(){

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if(event.sensor.getType() == Sensor.TYPE_LIGHT){
                if (limite_lum > event.values[0]){
                    cambiar_tema(false);
                } else {
                    cambiar_tema(true);
                }
                //sensor_txt.setText("LIGHT: " + event.values[0]);
                Log.d("Luz", String.valueOf(event.values[0]));
            }
        }

    };

    public void cambiar_tema(Boolean nuevo_tema){
        if (nuevo_tema != thema_claro){
            editor.putBoolean("thema_claro", nuevo_tema);
            editor.commit();
            thema_claro = nuevo_tema;
            if (thema_claro){
                //Utils.changeToTheme(act, Utils.THEME_CLARO);
                //getWindow().getDecorView().setBackgroundColor(Color.WHITE);
                colores(true);
                Log.d("Colores", "DIA");
            } else {
                Log.d("Colores", "NOCHE");
                colores(false);
                //Utils.changeToTheme(act, Utils.THEME_OSCURO);
                //getWindow().getDecorView().setBackgroundColor(Color.BLACK);
                //getWindow().getDecorView().setBackgroundColor(Color.BLACK);
            }
        }
    }

    public void colores(Boolean tema_claro){

        //Fondo
        getWindow().getDecorView().setBackgroundColor(Color.parseColor(tema.get_color_fondo(tema_claro)));


        //Textos
        //lista_sensores.setTextColor(Color.parseColor(tema.get_color_texto(tema_claro)));
        //sensor_txt.setTextColor(Color.parseColor(tema.get_color_texto(tema_claro)));
        // title.setTextColor(Color.parseColor(tema.get_color_texto(tema_claro)));

        //Barra superior
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor(tema.get_color_barra(tema_claro)));
        }

        //Toobar
        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor(tema.get_color_barra_opciones(tema_claro))));

        //Menu
        navigationView.setBackgroundColor(Color.parseColor(tema.get_color_fondo(tema_claro)));
        navigationView.setItemTextColor(ColorStateList.valueOf(Color.parseColor(tema.get_color_texto(tema_claro))));


        MenuItem itemSelected = navigationView.getMenu().getItem(mNavItemId);
        SpannableString s = new SpannableString(itemSelected.getTitle());
        s.setSpan(new ForegroundColorSpan(Color.parseColor(tema.get_color_item_seleccionado(tema_claro))), 0, s.length(), 0);
        itemSelected.setTitle(s);
        //mNavItemId



    }


    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(LightSensorListener);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Sensor LightSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if(LightSensor != null){
            //sensor_txt.setText("Sensor.TYPE_LIGHT Available");
            mSensorManager.registerListener(
                    LightSensorListener,
                    LightSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);

        }else{
            //sensor_txt.setText("Sensor.TYPE_LIGHT NOT Available");
        }
    }
}
