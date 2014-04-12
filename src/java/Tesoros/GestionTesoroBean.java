/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesoros;

import app.webservice.Tesoro;
import app.webservice.TesoroService_Service;
import app.webservice.Usuario;
import app.webservice.UsuarioService_Service;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.StringTokenizer;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.xml.ws.WebServiceRef;
import service.elevation.ElevacionElevation;
import service.elevation.ElevationService;
import service.elevation.ResultElevation;
import service.geocoding.Geocoding;
import service.geocoding.GeocodingClient;

/**
 *
 * @author Joaquin
 */
@Named(value = "gestionTesoroBean")
@SessionScoped
public class GestionTesoroBean implements Serializable {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/proyecto5-war/UsuarioService.wsdl")
    private UsuarioService_Service service_1;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/proyecto5-war/TesoroService.wsdl")
    private TesoroService_Service service;

    //VARIABLES
    private Tesoro tesoro, tesoroSelecionado;
    private String codSeguridad, descripcion, estado, altitud, latitud, longitud, nombre, tamano, zona, fechaString, dificultadS;
    private int dificultad, id;
    private Date fecha;
    private Usuario usuario, usuarioSelecionado;
    private Collection<Tesoro> tesorosD;

    //GETTER AND SETTER
    public void setTesoroSeleccionado(Tesoro t) {
        this.tesoroSelecionado = t;
    }

    public Tesoro getTesoroSeleccionado() {
        return tesoroSelecionado;
    }

    public void setUsuarioSelecionado(Usuario u) {
        this.usuarioSelecionado = u;
    }

    public Usuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setTesorosD(Collection<Tesoro> t) {
        tesorosD = t;
    }

    public Collection<Tesoro> getTesorosD() {
        return tesorosD;
    }

    public void setFecha(Date d) {
        this.fecha = d;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setDificultad(int d) {
        this.dificultad = d;
    }

    public int getDificultad() {
        return this.dificultad;
    }

    public void setFechaString(String f) {
        this.fechaString = f;
    }

    public String getFechaString() {
        return this.fechaString;
    }

    public void setZona(String z) {
        this.zona = z;
    }

    public String getZona() {
        return this.zona;
    }

    public void setTamano(String t) {
        this.tamano = t;
    }

    public String getTamano() {
        return this.tamano;
    }

    public void setNombre(String n) {
        this.nombre = n;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setLongitud(String l) {
        this.longitud = l;
    }

    public String getLongitud() {
        return this.longitud;
    }

    public void setLatitud(String l) {
        this.latitud = l;
    }

    public String getLatitud() {
        return this.latitud;
    }

    public void setEstado(String e) {
        this.estado = e;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setDescripcion(String d) {
        this.descripcion = d;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setCodSeguridad(String cS) {
        this.codSeguridad = cS;
    }

    public String getCodSeguridad() {
        return this.codSeguridad;
    }

    public void setTesoro(Tesoro t) {
        this.tesoro = t;
    }

    public Tesoro getTesoro() {
        return tesoro;
    }

    public void setDificultadS(String d) {
        this.dificultadS = d;
    }

    public String getDificultadS() {
        return this.codSeguridad;
    }

    public void setAltitud(String a) {
        this.altitud = a;
    }

    public String getAltitud() {
        return this.altitud;
    }

    //CONSTRUCTOR Y POSTCONTRUCT
    public GestionTesoroBean() {

    }

    @PostConstruct
    public void inicializacion() {

        //OBTENEMOS LA FECHA DE SISTEMA
        int dia = new java.util.Date().getDate();
        int mes = new java.util.Date().getMonth() + 1;
        int ano = new java.util.Date().getYear() + 1900;

        //INICIALIZAMOS LOS VALORES
        this.codSeguridad = "";
        descripcion = "";
        estado = "";
        latitud = "";
        longitud = "";
        nombre = "";
        tamano = "";
        zona = "";
        fechaString = "";
        fechaString += dia + "/" + mes + "/" + ano;
        dificultad = 0;
        id = 0;
        dificultadS = "";
        this.altitud = "10";

        this.obtenerUsuario();
        this.tesorosDefectuosos();
        usuarioSelecionado = null;
        tesoroSelecionado = null;

    }

    //METODOS --------------------------------------
    public String insertarTesoro() {

        //OTENTEMOS EL USUARIO
        this.obtenerUsuario();

        //CREAMOS UN NUEVO TESORO
        Tesoro tesoroNuevo;
        tesoroNuevo = new Tesoro();

        //RELLENAMOS EL TESORO CON LOS DATOS DEL FORMULARIO
        tesoroNuevo.setNombre(nombre);
        tesoroNuevo.setDescripcion(descripcion);
        //Si el que inserta es usuario, se pone el estado como 
        //defectuoso para que así salga en la lista de validar tesoros
        //a los administradores
        if (usuario.getRol() == 0) {
            tesoroNuevo.setEstado("noValidado");
        } else {
            tesoroNuevo.setEstado("Correcto");
        }

        // LLamada al servicio Geocoding para actualizar latitud y longitud
        actualiarCoordenadas();
        tesoroNuevo.setLatitud(latitud);
        tesoroNuevo.setLongitud(longitud);

        // PASAMOS EL STRING DIFICULTAD A INT
        dificultad = Integer.parseInt(dificultadS);
        tesoroNuevo.setDificultad(dificultad);
        tesoroNuevo.setCodSeguridad(codSeguridad);
        tesoroNuevo.setTamanio(tamano);
        tesoroNuevo.setZona(zona);
        this.actualiarAltura();
        tesoroNuevo.setAltitud(altitud);
        //PASEAMOS LA FECHA
        if (fechaString.length() > 0) {

            StringTokenizer tokens = new StringTokenizer(fechaString, "/");
            int[] datos = new int[3];
            int i = 0;
            while (tokens.hasMoreTokens()) {
                String str = tokens.nextToken();
                datos[i] = Integer.parseInt(str);
                i++;
            }
            fecha = new java.util.Date(datos[2] - 1900, datos[1] - 1, datos[0] + 1);
            tesoroNuevo.setFechaEscondido(auxiliar.convertidorFecha.asXMLGregorianCalendar(fecha));

        }

        //ASIGNAMOS EL USUARIO QUE INSERTA EL TESORO
        tesoroNuevo.setUsuarioidUsuario(usuario);

        //INSERTAMOS EN LA BD
        createTesoro(tesoroNuevo);
        this.inicializacion();

        //MOSTRAMOS EL LISTADO DE TESOROS
        if (usuario.getRol() == 0) {
            return "/jsf/principal";
        } else {
            return "tesoros";
        }
    }

    // Realizado por Juan Frias
    // Actualiza latitud y longitud utilizando el servicio de Geocoding
    public void actualiarCoordenadas() {
        String[] res = new String[2];
        GeocodingClient geo = new GeocodingClient();
        Response response = geo.geocode(Response.class, zona, "false");
        if (response.getStatus() == 200) {
            GenericType<Geocoding> genericType = new GenericType<Geocoding>() {
            };
            Geocoding geocoding = response.readEntity(genericType);
            latitud = geocoding.getResults().get(0).getGeometry().getLocation().getLat().toString();
            longitud = geocoding.getResults().get(0).getGeometry().getLocation().getLng().toString();
        }
    }

    public void volver() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("../principal.jsf");
    }

    //METODO QUE RECOGEMOS LOS DATOS DEL USUARIO
    public void obtenerUsuario() {

        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession) context.getExternalContext().getSession(true);
        usuario = (Usuario) sesion.getAttribute("user");
    }

    public void tesorosDefectuosos() {
        tesorosD = tesorosDefectusos();

    }

    public String mostrarBoton(Tesoro t) {
        if (t.getEstado().equals("noValidado")) {
            return "Validar";
        } else {
            return "Avisar a Usuario";
        }
    }

    public String mantenimiento() {
        if (tesoroSelecionado.getEstado().equals("noValidado")) {
            tesoroSelecionado.setEstado("Correcto");
            editTesoro(tesoroSelecionado);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage("TesValid", new FacesMessage("Tesoro Validado"));
            tesorosD = tesorosDefectusos();
            Usuario usuarioEscondedor = tesoroSelecionado.getUsuarioidUsuario();
            usuarioEscondedor.setRol(1);
            editUsuario(usuarioEscondedor);
            return null;
        } else {
            return "/jsf/avisos/crearAviso";
        }
    }

    private void createTesoro(app.webservice.Tesoro entity) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        app.webservice.TesoroService port = service.getTesoroServicePort();
        port.createTesoro(entity);
    }

    private java.util.List<app.webservice.Tesoro> tesorosDefectusos() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        app.webservice.TesoroService port = service.getTesoroServicePort();
        return port.tesorosDefectusos();
    }

    private void editTesoro(app.webservice.Tesoro entity) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        app.webservice.TesoroService port = service.getTesoroServicePort();
        port.editTesoro(entity);
    }

    private void editUsuario(app.webservice.Usuario entity) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        app.webservice.UsuarioService port = service_1.getUsuarioServicePort();
        port.editUsuario(entity);
    }

    //Joaquin Garcia
    public void actualiarAltura() {

        ElevationService alt = new ElevationService();
        String coordenadas;
        double res=0.0;
        coordenadas = this.latitud + "," + this.longitud;
        
        
        Response response = alt.elevacion(Response.class, coordenadas, "false");
        

        if (response.getStatus() == 200) {
            GenericType<ElevacionElevation> genericType = new GenericType<ElevacionElevation>() {
            };
            ElevacionElevation ele = response.readEntity(genericType);
            res = ele.getResults().get(0).getElevation();
            this.altitud = "" + res;
            this.altitud = this.altitud.substring(0, 4);
            this.altitud += " m";
            

        }
    }

}
