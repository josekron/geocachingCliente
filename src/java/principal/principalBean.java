/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package principal;

import app.webservice.Aviso;
import app.webservice.AvisoService_Service;
import app.webservice.Descubrimiento;
import app.webservice.DescubrimientoService_Service;
import app.webservice.Tesoro;
import app.webservice.TesoroService_Service;
import app.webservice.Usuario;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author JuanM
 */
@ManagedBean(name = "principalBean")
@SessionScoped
public class principalBean implements Serializable{
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/proyecto5-war/AvisoService.wsdl")
    private AvisoService_Service service_2;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/proyecto5-war/TesoroService.wsdl")
    private TesoroService_Service service_1;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/proyecto5-war/DescubrimientoService.wsdl")
    private DescubrimientoService_Service service;
    
    
    private List<Descubrimiento> descubrimientos;
    private Collection<Tesoro> tesorosEscondidos;
    private Usuario usuario;
    private Collection<Aviso> avisosNoLeidos;

    public Collection<Aviso> getAvisosNoLeidos() {
        return avisosNoLeidos;
    }

    public void setAvisosNoLeidos(Collection<Aviso> avisosNoLeidos) {
        this.avisosNoLeidos = avisosNoLeidos;
    }
    
    /**
     * Creates a new instance of principalBean
     */
    public principalBean() {
    }

    public Collection<Tesoro> getTesorosEscondidos() {
        return tesorosEscondidos;
    }

    public void setTesorosEscondidos(Collection<Tesoro> tesorosEncontrados) {
        this.tesorosEscondidos = tesorosEncontrados;
    }

    public Collection<Descubrimiento> getDescubrimientos() {
        return descubrimientos;
    }

    public void setDescubrimientos(List<Descubrimiento> descubrimientos) {
        this.descubrimientos = descubrimientos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String cerrarSesion(){
        FacesContext context = FacesContext.getCurrentInstance(); 
        HttpSession sesion = (HttpSession) context.getExternalContext().getSession(true);
        //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        sesion.invalidate();
        return "./index.jsf";
    }
    
    @PostConstruct
    public void inicializar(){
        FacesContext context = FacesContext.getCurrentInstance(); 
        HttpSession sesion = (HttpSession) context.getExternalContext().getSession(true);
        usuario = (Usuario) sesion.getAttribute("user");
        
        if(usuario.getIdUsuario()!=null){
            avisosNoLeidos = devolverListaAvisosNoLeidos(usuario.getIdUsuario());
            descubrimientos = devolverDescubrimientosDeUnUsuario(usuario.getIdUsuario());
            for(Descubrimiento d : descubrimientos)
                d.setTesoro(findTesoro(d.getDescubrimientoPK().getTesoroidTesoro()));
            tesorosEscondidos = devolverTesorosEscondidosPorUsuario(usuario.getIdUsuario());
        }
    
    }

    private java.util.List<app.webservice.Descubrimiento> devolverDescubrimientosDeUnUsuario(int idUsuario) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        app.webservice.DescubrimientoService port = service.getDescubrimientoServicePort();
        return port.devolverDescubrimientosDeUnUsuario(idUsuario);
    }

    private java.util.List<app.webservice.Tesoro> devolverTesorosEscondidosPorUsuario(int idUsuario) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        app.webservice.TesoroService port = service_1.getTesoroServicePort();
        return port.devolverTesorosEscondidosPorUsuario(idUsuario);
    }

    private java.util.List<app.webservice.Aviso> devolverListaAvisosNoLeidos(int idUsuario) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        app.webservice.AvisoService port = service_2.getAvisoServicePort();
        return port.devolverListaAvisosNoLeidos(idUsuario);
    }

    private Tesoro findTesoro(java.lang.Object id) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        app.webservice.TesoroService port = service_1.getTesoroServicePort();
        return port.findTesoro(id);
    }

    
    
    
    
}
