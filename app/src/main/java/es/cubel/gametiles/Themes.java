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
    String claro_barra_superior = "#1F515F";
    String claro_barra_opciones = "#346D7D";
    String claro_item_menu_seleccionado = "#346D7D";

    //Oscuro
    String oscuro_fondo = "#0e122e";
    String oscuro_letras = "#999999";
    String oscuro_barra_superior = "#070a1a";
    String oscuro_barra_opciones = "#070A19";
    String oscuro_item_menu_seleccionado = "#FFFFFF";

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

    public String getClaro_item_menu_seleccionado() {
        return claro_item_menu_seleccionado;
    }

    public String getOscuro_item_menu_seleccionado() {
        return oscuro_item_menu_seleccionado;
    }

    public String getClaro_barra_opciones() {
        return claro_barra_opciones;
    }

    public String getOscuro_barra_opciones() {
        return oscuro_barra_opciones;
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

    public String get_color_barra_opciones(Boolean theme){
        if (theme){
            return getClaro_barra_opciones();
        }else{
            return getOscuro_barra_opciones();
        }
    }

    public String get_color_item_seleccionado(Boolean theme){
        if (theme){
            return getClaro_item_menu_seleccionado();
        } else {
            return getOscuro_item_menu_seleccionado();
        }
    }
}