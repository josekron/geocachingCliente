package service.gloe;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Gloe {

    //url del recurso
    private String URL;
    //nombre clave del recurso
    private String nombre;
    //ámbito del recurso devuelto (restaurantes, hoteles,...)
    private String ambito;

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    @Override
    public String toString() {
        
        return "Gloe{" + "URL=" + URL + ", nombre=" + nombre + ", ambito=" + ambito + '}';
    }

    public void formatearTexto() {
        try {
            this.nombre = URLDecoder.decode(this.nombre, "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }

    }
    public boolean gloeValido(){
        if (!(this.URL.equals("") || this.URL == null || this.URL.contains("gloe") || 
                this.nombre.contains("gloe") || this.nombre.equals(""))) {
            return true;
        }
        return false;
    }

}
