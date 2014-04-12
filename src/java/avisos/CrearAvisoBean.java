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
import java.util.Iterator;
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
@ManagedBean(name = "avisosBean")
@SessionScoped
public class CrearAvisoBean implements Serializable {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/proyecto5-war/AvisoService.wsdl")
    private AvisoService_Service service_1;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/proyecto5-war/UsuarioService.wsdl")
    private UsuarioService_Service service;

    //usuario de sesion actual
    Usuario usuario;

    String titulo;
    String mensaje;
    String nickUsuarioReceptor;

    private List<Usuario> listaUsuarios;

    public CrearAvisoBean() {
    }

    public String enviarAviso() throws ParseException {

        Calendar calendario = Calendar.getInstance();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse(calendario.get(Calendar.YEAR) + "-" + calendario.get(Calendar.MONTH) + "-" + calendario.get(Calendar.DAY_OF_MONTH) + " 00:00:00"); // mysql datetime format
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        AvisoPK pk = new AvisoPK();
        
        
        pk.setUsuarioidUsuarioEmisor(usuario.getIdUsuario());
        pk.setUsuarioidUsuarioReceptor(((Usuario) buscarUsuario(nickUsuarioReceptor)).getIdUsuario());

        Aviso aviso = new Aviso();
        aviso.setMensaje(mensaje);
        aviso.setTitulo(titulo);
        aviso.setUsuario(usuario);
        aviso.setUsuario1((Usuario) buscarUsuario(nickUsuarioReceptor));
        aviso.setLeido(Boolean.FALSE);
        aviso.setFechaEnviado(auxiliar.convertidorFecha.asXMLGregorianCalendar(calendar.getTime()));
        
        aviso.setAvisoPK(pk);
        
        createAviso(aviso);

        return "/jsf/principal.jsf";

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public String getNickUsuarioReceptor() {
        return nickUsuarioReceptor;
    }

    public void setNickUsuarioReceptor(String nickUsuarioReceptor) {
        this.nickUsuarioReceptor = nickUsuarioReceptor;
    }

    @PostConstruct
    public void create() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession) context.getExternalContext().getSession(true);
        usuario = (Usuario) sesion.getAttribute("user");
        listaUsuarios = findAllUsuario();

        int i = 0;
        Iterator<Usuario> it = listaUsuarios.iterator();
        while(it.hasNext()){
            if(it.next().getNick().equals(usuario.getNick())){
                break;
            }
            i++;
        }
        listaUsuarios.remove(i);

    }

    private Object buscarUsuario(java.lang.String nick) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        app.webservice.UsuarioService port = service.getUsuarioServicePort();
        return port.buscarUsuario(nick);
    }

    private void createAviso(app.webservice.Aviso entity) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        app.webservice.AvisoService port = service_1.getAvisoServicePort();
        port.createAviso(entity);
    }

    private java.util.List<app.webservice.Usuario> findAllUsuario() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        app.webservice.UsuarioService port = service.getUsuarioServicePort();
        return port.findAllUsuario();
    }

}
