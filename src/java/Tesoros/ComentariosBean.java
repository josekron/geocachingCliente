/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesoros;

import app.webservice.Comentario;
import app.webservice.ComentarioTesoro_Service;
import app.webservice.Descubrimiento;
import app.webservice.DescubrimientoPK;
import app.webservice.DescubrimientoService_Service;
import app.webservice.Tesoro;
import app.webservice.TesoroService_Service;
import app.webservice.Usuario;
import app.webservice.UsuarioService_Service;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.xml.ws.WebServiceRef;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import service.geocoding.DistanceMatrixClient;
import service.geocoding.DistanceResponse;
import service.geocoding.Geocoding;
import service.geocoding.GeocodingClient;
import service.gloe.Gloe;
import service.gloe.RESTCLientService;

/**
 *
 * @author José Antonio Herrera Peña
 */
@Named(value = "comentariosBean")
@RequestScoped
public class ComentariosBean implements Serializable {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/proyecto5-war/UsuarioService.wsdl")
    private UsuarioService_Service service_3;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/proyecto5-war/DescubrimientoService.wsdl")
    private DescubrimientoService_Service service_2;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/proyecto5-war/ComentarioTesoro.wsdl")
    private ComentarioTesoro_Service service_1;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/proyecto5-war/TesoroService.wsdl")
    private TesoroService_Service service;

    Usuario usuario;
    String textoComentar = "";
    String tituloComentar = "";
    int numDescubrimientos;
    Tesoro tesoro;
    List<Comentario> listaComentarios;
    List<Descubrimiento> listaDescubrimientos;
    int kmDesc;
    int dificultadDesc = 1;
    String codSegDesc;
    int idModifComentario;
    String textoModifComentario;
    String tituloModifComentario;
    private String altitud;
    //2º iteracion Jose:
    String distancia;
    String longitud;
    String latitud;
    String zona;

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    ///DAVID
    List<Gloe> listaGloes;

    public List<Gloe> getListaGloes() {
        return listaGloes;
    }

    public void setListaGloes(List<Gloe> listaGloes) {
        this.listaGloes = listaGloes;
    }

    public String getTextoModifComentario() {
        return textoModifComentario;
    }

    public void setTextoModifComentario(String textoModifComentario) {
        this.textoModifComentario = textoModifComentario;
    }

    public String getTituloModifComentario() {
        return tituloModifComentario;
    }

    public void setTituloModifComentario(String tituloModifComentario) {
        this.tituloModifComentario = tituloModifComentario;
    }

    public int getIdModifComentario() {
        return idModifComentario;
    }

    public void setIdModifComentario(int idModifComentario) {
        this.idModifComentario = idModifComentario;
    }

    public int getKmDesc() {
        return kmDesc;
    }

    public void setKmDesc(int kmDesc) {
        this.kmDesc = kmDesc;
    }

    public int getDificultadDesc() {
        return dificultadDesc;
    }

    public void setDificultadDesc(int dificultadDesc) {
        this.dificultadDesc = dificultadDesc;
    }

    public String getCodSegDesc() {
        return codSegDesc;
    }

    public void setCodSegDesc(String codSegDesc) {
        this.codSegDesc = codSegDesc;
    }

    public List<Descubrimiento> getListaDescubrimientos() {
        return listaDescubrimientos;
    }

    public void setListaDescubrimientos(List<Descubrimiento> listaDescubrimientos) {
        this.listaDescubrimientos = listaDescubrimientos;
    }

    public int getNumDescubrimientos() {
        return numDescubrimientos;
    }

    public void setNumDescubrimientos(int numDescubrimientos) {
        this.numDescubrimientos = numDescubrimientos;
    }

    public String getTituloComentar() {
        return tituloComentar;
    }

    public void setTituloComentar(String tituloComentar) {
        this.tituloComentar = tituloComentar;
    }

    public String getTextoComentar() {
        return textoComentar;
    }

    public void setTextoComentar(String textoComentar) {
        this.textoComentar = textoComentar;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Comentario> getListaComentarios() {
        return listaComentarios;
    }

    public void setListaComentarios(List<Comentario> listaComentarios) {
        this.listaComentarios = listaComentarios;
    }

    public String getAltitud() {
        return altitud;
    }

    public void setAltitud(String a) {
        this.altitud = a;
    }

    /**
     * Creates a new instance of ComentariosBean
     */
    /**
     * Creates a new instance of ComentariosBean
     *
     * @return
     */
    public Tesoro getTesoro() {
        return tesoro;
    }

    public void setTesoro(Tesoro tesoro) {
        this.tesoro = tesoro;
    }

    public ComentariosBean() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession) context.getExternalContext().getSession(true);
        usuario = (Usuario) sesion.getAttribute("user");
    }

    /**
     * @Autor: Jose Antonio Herrera Peña
     * @Description: método postconstruct que recoge el idTesoro pasado por
     * Session y busca el tesoro correspondiente.
     */
    @PostConstruct
    public void inicializarVariables() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession) context.getExternalContext().getSession(true);
        int idTesoro = (int) sesion.getAttribute("idTesoro");

        System.out.println("ID TESORO:" + idTesoro);

        tesoro = devolverTesoro(idTesoro);
        if (tesoro == null) {
            tesoro = new Tesoro();
        }

        this.listaComentarios = devolverListaComentarios(idTesoro);
        if (listaComentarios == null) {
            listaComentarios = new ArrayList<Comentario>();
        }
        this.quitarComentariosBorrados();

        List<Descubrimiento> listaDesc = devolverDescubrimientosDeUnTesoro(idTesoro);
        if (listaDesc == null) {
            this.numDescubrimientos = 0;
            this.listaDescubrimientos = new ArrayList<Descubrimiento>();
        } else {
            this.numDescubrimientos = listaDesc.size();
            this.listaDescubrimientos = listaDesc;
        }
        //2º iteracion Jose:
        distancia = "";
        zona = "";
        longitud = "";
        latitud = "";
        ///DAVID
        try {

            listaGloes = crearListaGloe();
        } catch (ClientErrorException ex) {
            Logger.getLogger(ComentariosBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(ComentariosBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // Realizado por José Antonio Herrera
    // Devuelve la latitud y longitud utilizando el servicio de Geocoding
    // Con esa latitud y longitud calcula la distancia con el tesoro llamando al servicio de Geocoding
    public void calcularDistancia() {
        GeocodingClient geo = new GeocodingClient();
        Response response = geo.geocode(Response.class, zona, "false");
        if (response.getStatus() == 200) {
            GenericType<Geocoding> genericType = new GenericType<Geocoding>() {
            };
            Geocoding geocoding = response.readEntity(genericType);
            latitud = geocoding.getResults().get(0).getGeometry().getLocation().getLat().toString();
            longitud = geocoding.getResults().get(0).getGeometry().getLocation().getLng().toString();

            String puntoA = this.tesoro.getLatitud().toString() + "," + this.tesoro.getLongitud().toString();
            System.out.println("Punto tesoro: " + puntoA);
            String puntoB = latitud + "," + longitud;
            System.out.println("Punto usuario: " + puntoB);
            DistanceMatrixClient dmc = new DistanceMatrixClient();
            String d = dmc.geocode(String.class, puntoA, puntoB);
            System.out.println("DMC->" + d);
            Response respuesta = dmc.geocode(Response.class, puntoA, puntoB);

            if (respuesta.getStatus() == 200) {
                System.out.println("Respuesta con éxito!");
                GenericType<DistanceResponse> genericType2 = new GenericType<DistanceResponse>() {
                };
                DistanceResponse c = respuesta.readEntity(genericType2);
                System.out.println("Distance --> " + c.getRows().get(0).getElements().get(0).getDistance().getText());
                this.distancia = c.getRows().get(0).getElements().get(0).getDistance().getText();

            } else {
                System.out.println("Error al recibir el recurso, estado= " + respuesta.getStatus());
            }
        }
    }

    //JOAQUIN GARCIA
    public String calcularDistancia2() {

        String puntoA = this.tesoro.getLatitud().toString() + "," + this.tesoro.getLongitud().toString();
        System.out.println("Punto tesoro: " + puntoA);
        String puntoB = latitud + "," + longitud;
        System.out.println("Punto usuario: " + puntoB);
        DistanceMatrixClient dmc = new DistanceMatrixClient();
        String d = dmc.geocode(String.class, puntoA, puntoB);
        System.out.println("DMC->" + d);
        Response respuesta = dmc.geocode(Response.class, puntoA, puntoB);
        
        
            if (respuesta.getStatus() == 200) {
                System.out.println("Respuesta con éxito!");
                GenericType<DistanceResponse> genericType2 = new GenericType<DistanceResponse>() {
                };
                DistanceResponse c = respuesta.readEntity(genericType2);
                System.out.println("Distance --> " + c.getRows().get(0).getElements().get(0).getDistance().getText());
                this.distancia = c.getRows().get(0).getElements().get(0).getDistance().getText();

            } else {
                System.out.println("Error al recibir el recurso, estado= " + respuesta.getStatus());
            }

        return null;
    }

    /**
     * @Autor: José Antonio Herrera
     * @Description: método que quita de la lista de comentarios los comentarios
     * que tienen una fecha de borrado
     */
    public void quitarComentariosBorrados() {
        List<Comentario> listaux = new ArrayList<Comentario>();
        for (Comentario comen : listaComentarios) {
            if (comen.getFechaBorrado() == null) {
                listaux.add(comen);
            }
        }
        listaComentarios = listaux;
    }

    /**
     * @Autor: José Antonio Herrera
     * @Description: recoge el texto y titulo y añade un comentario a la bd
     */
    public void addComentario() {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (tituloComentar.length() > 0) {
            Comentario comentario;
            comentario = new Comentario();
            comentario.setTitulo(tituloComentar);
            comentario.setTexto(textoComentar);
            comentario.setFechaPublicacion(auxiliar.convertidorFecha.asXMLGregorianCalendar(new Date()));
            comentario.setTesoroidTesoro(tesoro);
            comentario.setUsuarioidUsuario(usuario);
            createComentario(comentario);
            this.listaComentarios.add(comentario);
        } else {
            FacesMessage msg = new FacesMessage("No debes dejar el campo titulo vacio");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage("Texto vacio", msg);
            fc.renderResponse();
        }

    }

    /**
     * @Autor: José Antonio Herrera
     * @return
     */
    public boolean tesoroEsEscondidoPorUsuario() {
        return tesoro.getUsuarioidUsuario().getIdUsuario() == usuario.getIdUsuario();
    }

    /**
     * @Autor: José Antonio Herrera
     * @Description: comprueba si el usuario logueado ha descubierto el tesoro
     * @return
     */
    public boolean yaDescubierto() {
        return this.descubiertoSiONoPorUsuario(usuario.getIdUsuario());
    }

    public boolean mostrarFormularioDescubrimiento() {
        if (yaDescubierto()) {
            return false;
        } else if (tesoroEsEscondidoPorUsuario()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * @Autor: José Antonio Herrera Peña
     * @Description: añade un descubrimiento
     */
    public void addDescubrimiento() {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (!this.codSegDesc.equals(tesoro.getCodSeguridad())) {
            FacesMessage msg = new FacesMessage("El codigo de seguridad no es valido");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage("codSeg no valid", msg);
            fc.renderResponse();
        } else if (this.dificultadDesc <= 0 || this.dificultadDesc > 5) {
            FacesMessage msg = new FacesMessage("Dificultad debe ser un valor comprendido entre 1 y 5");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage("dificultad no valid", msg);
            fc.renderResponse();
        } else if (this.descubiertoSiONoPorUsuario(usuario.getIdUsuario())) {
            FacesMessage msg = new FacesMessage("Ya has descubierto este tesoro");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage("tesoro ya descubierto", msg);
            fc.renderResponse();
        } else {
            Descubrimiento descubrimiento = new Descubrimiento();
            descubrimiento.setDificultad(this.dificultadDesc);
            descubrimiento.setFechaDescubrimiento(auxiliar.convertidorFecha.asXMLGregorianCalendar(new Date()));
            descubrimiento.setKmRecorridos(kmDesc);
            descubrimiento.setTesoro(tesoro);
            descubrimiento.setUsuario(usuario);
            DescubrimientoPK desPK = new DescubrimientoPK();
            desPK.setTesoroidTesoro(tesoro.getIdTesoro());
            desPK.setUsuarioidUsuario(usuario.getIdUsuario());
            descubrimiento.setDescubrimientoPK(desPK);
            createDescubrimiento(descubrimiento);
            this.listaDescubrimientos.add(descubrimiento);

            List<Descubrimiento> listaDescubrimientosUsuario = devolverDescubrimientosDeUnUsuario(usuario.getIdUsuario());
            if (listaDescubrimientosUsuario.size() > 10) {
                usuario.setRango("Master Geocacher");
            } else if (listaDescubrimientosUsuario.size() > 3) {
                usuario.setRango("Geocacher Junior");
            }
            editUsuario(usuario);
        }
    }

    public boolean descubiertoSiONoPorUsuario(int idUsuario) {
        listaDescubrimientos = devolverDescubrimientosDeUnTesoro(tesoro.getIdTesoro());
        for (Descubrimiento des : this.listaDescubrimientos) {
            if (des.getDescubrimientoPK().getUsuarioidUsuario() == idUsuario) {
                return true;
            }
        }
        return false;
    }

    /**
     * @Autor: José Antonio Herrera
     * @Description: método que añade fecha de borrado a un comentario
     * @param idComen
     */
    public void eliminarComentario(int idComen) {
        System.out.println("IDCOMENT: " + idComen);
        addFechaBorradoComentarioByID(idComen);
        this.listaComentarios = devolverListaComentarios(tesoro.getIdTesoro());
        if (listaComentarios == null) {
            listaComentarios = new ArrayList<Comentario>();
        }
        this.quitarComentariosBorrados();

    }

    /**
     * @Autor: José Antonio Herrera
     * @return
     */
    public String irADescubrimientos() {
        return "/jsf/tesoros/descubrimientoTesoro";
    }

    /**
     * @Autor: José Antonio Herrera
     * @Description: comprueba si el usuario del comentario que se le pasa es el
     * usuario logueado o si tiene rol de administrador
     * @param idUsuario
     * @param rol
     * @return
     */
    public boolean devolverSiComentarioDeUsuarioOAdmin(int idUsuario, int rol) {
        if (usuario.getIdUsuario() == idUsuario || usuario.getRol() == 2) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @Autor: José Antonio Herrera
     * @param idComen
     * @param textoComentario
     * @param tituloComentario
     * @return
     */
    public String irAEditarComentario(int idComen, String textoComentario, String tituloComentario) {
        this.idModifComentario = idComen;
        this.textoModifComentario = textoComentario;
        this.tituloModifComentario = tituloComentario;
        return "/jsf/tesoros/modificarComentario";
    }

    /**
     * @Autor: José Antonio Herrera
     * @return
     */
    public String editarComentario() {
        Comentario comentario = findComentario(idModifComentario);
        comentario.setTitulo(tituloModifComentario);
        comentario.setTexto(textoModifComentario);
        comentario.setFechaUltModificacion(auxiliar.convertidorFecha.asXMLGregorianCalendar(new Date()));
        editComentario(comentario);
        this.listaComentarios = devolverListaComentarios(tesoro.getIdTesoro());
        this.quitarComentariosBorrados();
        return "/jsf/tesoros/comentarioTesoro";

    }

    public String irATesoros() {
        return "/jsf/tesoros/tesoros";
    }

    public String irAComentarios() {
        return "/jsf/tesoros/comentarioTesoro";
    }

    public String irABorrar() {
        return "/jsf/tesoros/borrarTesoro";
    }

    public String borrarTesoroNo() {
        return "/jsf/tesoros/comentarioTesoro";
    }

    /**
     * @Autor: José Antonio Herrera
     * @Description: devuelve si el usuario logueado escondio el tesoro o si es
     * admin
     * @return
     */
    public boolean devolverSiEscondioTesoroOAdmin() {
        if (usuario.getIdUsuario() == tesoro.getUsuarioidUsuario().getIdUsuario()) {
            return true;
        } else if (usuario.getRol() == 2) {
            return true;
        } else {
            return false;
        }
    }

    public String borrarTesoro() {
        removeTesoro(tesoro);
        return "/jsf/tesoros/tesoros";
    }

    private void removeTesoro(app.webservice.Tesoro entity) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        app.webservice.TesoroService port = service.getTesoroServicePort();
        port.removeTesoro(entity);
    }

    private void modificarComentario(app.webservice.Comentario comentario) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        app.webservice.ComentarioTesoro port = service_1.getComentarioTesoroPort();
        port.modificarComentario(comentario);
    }

    private java.util.List<app.webservice.Comentario> devolverListaComentarios(int idTesoro) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        app.webservice.ComentarioTesoro port = service_1.getComentarioTesoroPort();
        return port.devolverListaComentarios(idTesoro);
    }

    private void addFechaBorradoComentarioByID(int idComentario) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        app.webservice.ComentarioTesoro port = service_1.getComentarioTesoroPort();
        port.addFechaBorradoComentarioByID(idComentario);
    }

    private void createDescubrimiento(app.webservice.Descubrimiento entity) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        app.webservice.DescubrimientoService port = service_2.getDescubrimientoServicePort();
        port.createDescubrimiento(entity);
    }

    private void createComentario(app.webservice.Comentario entity) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        app.webservice.ComentarioTesoro port = service_1.getComentarioTesoroPort();
        port.createComentario(entity);
    }

    private java.util.List<app.webservice.Descubrimiento> devolverDescubrimientosDeUnTesoro(int idTesoro) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        app.webservice.DescubrimientoService port = service_2.getDescubrimientoServicePort();
        return port.devolverDescubrimientosDeUnTesoro(idTesoro);
    }

    private java.util.List<app.webservice.Comentario> devolverListaComentarios_1(int idTesoro) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        app.webservice.ComentarioTesoro port = service_1.getComentarioTesoroPort();
        return port.devolverListaComentarios(idTesoro);
    }

    private Tesoro devolverTesoro(int idTesoro) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        app.webservice.TesoroService port = service.getTesoroServicePort();
        return port.devolverTesoro(idTesoro);
    }

    private Object buscarUsuario(java.lang.String nick) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        app.webservice.UsuarioService port = service_3.getUsuarioServicePort();
        return port.buscarUsuario(nick);
    }

    private Usuario findUsuario(java.lang.Object id) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        app.webservice.UsuarioService port = service_3.getUsuarioServicePort();
        return port.findUsuario(id);
    }

    private void editComentario(app.webservice.Comentario entity) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        app.webservice.ComentarioTesoro port = service_1.getComentarioTesoroPort();
        port.editComentario(entity);
    }

    private Comentario findComentario(java.lang.Object id) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        app.webservice.ComentarioTesoro port = service_1.getComentarioTesoroPort();
        return port.findComentario(id);
    }

    private java.util.List<app.webservice.Descubrimiento> devolverDescubrimientosDeUnUsuario(int idUsuario) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        app.webservice.DescubrimientoService port = service_2.getDescubrimientoServicePort();
        return port.devolverDescubrimientosDeUnUsuario(idUsuario);
    }

    private void editUsuario(app.webservice.Usuario entity) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        app.webservice.UsuarioService port = service_3.getUsuarioServicePort();
        port.editUsuario(entity);
    }

    private static JSONArray conectarGloe(String lat, String lon) throws ClientErrorException, JSONException {
        RESTCLientService res = new RESTCLientService();
        Response response = res.obtener_JSON(Response.class, lat, lon);
        if (response.getStatus()
                == 200) {
            System.out.println("Datos recibidos");
            GenericType<String> genericType = new GenericType<String>() {
            };
            String lc = response.readEntity(genericType);
            return new JSONArray(lc);

        } else {
            System.out.println("Error al recibir el recurso, estado= " + response.getStatus());
            return null;
        }
    }

    private static List<Gloe> obtenerDatos(JSONArray ar) throws JSONException {
        List<Gloe> lGloe = new ArrayList<Gloe>();
        Gloe aux;
        for (int i = 0; i < ar.length(); i++) {
            aux = new Gloe();
            aux.setURL(ar.getJSONArray(i).getString(1));
            aux.setNombre(ar.getJSONArray(i).getString(2));
            aux.setAmbito(ar.getJSONArray(i).getString(5));
            if (aux.gloeValido()) {
                aux.formatearTexto();
                lGloe.add(aux);
            }
        }
        return lGloe;
    }

    private List<Gloe> crearListaGloe() throws ClientErrorException, JSONException {
        List<Gloe> lista;
        JSONArray jArray = conectarGloe(String.valueOf(tesoro.getLatitud()), String.valueOf(tesoro.getLongitud()));

        lista = obtenerDatos(jArray);

        return lista;
    }

}
