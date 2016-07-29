package es.cubel.gametiles;

/**
 * Created by cubel on 29/07/16.
 */
public class Themes {

    /**
     * Temas disponibles para la aplicacion
     */
    // Claro
    String claro_fondo = "#ffffff";
    String claro_letras = "#000000";
    String claro_barra_superior = "#303F9F";

    //Oscuro
    String oscuro_fondo = "#0e122e";
    String oscuro_letras = "#999999";
    String oscuro_barra_superior = "#070a1a";

    public String getClaro_fondo() {
        return claro_fondo;
    }

    public String getClaro_letras() {
        return claro_letras;
    }

    public String getOscuro_fondo() {
        return oscuro_fondo;
    }

    public String getOscuro_letras() {
        return oscuro_letras;
    }

    public String getClaro_barra_superior() {
        return claro_barra_superior;
    }

    public String getOscuro_barra_superior() {
        return oscuro_barra_superior;
    }

    public String get_color_texto(Boolean theme) {
        if (theme) {
            return getClaro_letras();
        } else {
            return getOscuro_letras();
        }

    }

    public String get_color_fondo(Boolean theme) {
        if (theme) {
            return getClaro_fondo();
        } else {
            return getOscuro_fondo();
        }
    }

    public String get_color_barra(Boolean theme) {
        if (theme) {
            return getClaro_barra_superior();
        } else {
            return getOscuro_barra_superior();
        }
    }
}