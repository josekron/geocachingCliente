/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avisos;

import app.webservice.Aviso;
import app.webservice.AvisoPK;
import app.webservice.AvisoService_Service;
import app.webservice.Usuario;
import app.webservice.UsuarioService_Service;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author david
 */
@ManagedBean(name = "verAvisosBean")
@SessionScoped
public class VerAvisosBean implements Serializable {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/proyecto5-war/UsuarioService.wsdl")
    private UsuarioService_Service service_1;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/proyecto5-war/AvisoService.wsdl")
    private AvisoService_Service service;


    Usuario usuario;
    List<Aviso> listaAvisos;

    Aviso leido;

    //AJAX
    Aviso avisoSelec;

    /**
     * Creates a new instance of VerAvisosBean
     */
    public VerAvisosBean() {
    }

    public List<Aviso> getListaAvisos() {
        return listaAvisos;
    }

    public void setListaAvisos(List<Aviso> listaAvisos) {
        this.listaAvisos = listaAvisos;
    }

    public Aviso getLeido() {
        return leido;
    }

    public void setLeido(Aviso leido) {
        this.leido = leido;
    }

    public Aviso getAvisoSelec() {
        return avisoSelec;
    }

    public void setAvisoSelec(Aviso avisoSelec) {
        this.avisoSelec = avisoSelec;
    }

    public String desmarcarLeido() {
        this.leido.setLeido(Boolean.FALSE);
        editAviso(this.leido);

        return "/jsf/avisos/verAvisos.jsf";
    }

    public String marcarLeido() {
        this.leido.setLeido(Boolean.TRUE);
        editAviso(this.leido);

        return "/jsf/avisos/verAvisos.jsf";
    }

    public void verAviso() {

        String valor = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("avisoSeleccionado");

        avisoSelec = (Aviso) buscarAvisoId(Integer.parseInt(valor));
        avisoSelec.setLeido(Boolean.TRUE);
        editAviso(avisoSelec);

    }

    public void borrarAviso() {
        String valor = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("avisoAEliminar");

        listaAvisos.remove((Aviso) buscarAvisoId(Integer.parseInt(valor)));
        removeAviso((Aviso) buscarAvisoId(Integer.parseInt(valor)));
    }

    public void responderAviso() throws ParseException {

        Calendar calendario = Calendar.getInstance();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse(calendario.get(Calendar.YEAR) + "-" + calendario.get(Calendar.MONTH) + "-" + calendario.get(Calendar.DAY_OF_MONTH) + " 00:00:00"); // mysql datetime format
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        String valor1 = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("tituloEscrito");
        String valor2 = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("mensajeEscrito");

        String avisoS = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("avisoVerContestar");

        avisoSelec = (Aviso) buscarAvisoId(Integer.parseInt(avisoS));

        Aviso aviso = new Aviso();
        AvisoPK pk = new AvisoPK();

        pk.setUsuarioidUsuarioEmisor(usuario.getIdUsuario());
        pk.setUsuarioidUsuarioReceptor(avisoSelec.getAvisoPK().getUsuarioidUsuarioEmisor());
        aviso.setAvisoPK(pk);
        aviso.setFechaEnviado(auxiliar.convertidorFecha.asXMLGregorianCalendar(calendar.getTime()));
        aviso.setLeido(Boolean.FALSE);
        aviso.setTitulo(valor1);
        aviso.setMensaje(valor2);

        createAviso(aviso);

    }

    @PostConstruct
    private void create() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession) context.getExternalContext().getSession(true);
        usuario = (Usuario) sesion.getAttribute("user");
        
        listaAvisos = devolverListaAvisosDeUnUsuario(usuario.getIdUsuario());
        for(Aviso a : listaAvisos)
            a.setUsuario(findUsuario(a.getAvisoPK().getUsuarioidUsuarioEmisor()));
    }

    private void editAviso(app.webservice.Aviso entity) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        app.webservice.AvisoService port = service.getAvisoServicePort();
        port.editAviso(entity);
    }

    private Object buscarAvisoId(java.lang.Integer id) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        app.webservice.AvisoService port = service.getAvisoServicePort();
        return port.buscarAvisoId(id);
    }

    private void removeAviso(app.webservice.Aviso entity) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        app.webservice.AvisoService port = service.getAvisoServicePort();
        port.removeAviso(entity);
    }

    private void createAviso(app.webservice.Aviso entity) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        app.webservice.AvisoService port = service.getAvisoServicePort();
        port.createAviso(entity);
    }

    private java.util.List<app.webservice.Aviso> devolverListaAvisosDeUnUsuario(int idUsuario) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        app.webservice.AvisoService port = service.getAvisoServicePort();
        return port.devolverListaAvisosDeUnUsuario(idUsuario);
    }

    private Object buscarUsuario(java.lang.String nick) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        app.webservice.UsuarioService port = service_1.getUsuarioServicePort();
        return port.buscarUsuario(nick);
    }

    private Usuario findUsuario(java.lang.Object id) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        app.webservice.UsuarioService port = service_1.getUsuarioServicePort();
        return port.findUsuario(id);
    }

}
