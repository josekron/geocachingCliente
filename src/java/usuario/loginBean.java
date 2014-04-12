/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package usuario;

import app.webservice.Usuario;
import app.webservice.UsuarioService_Service;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Enrique Rios
 */
@ManagedBean(name = "loginBean")
@SessionScoped
public class loginBean {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/proyecto5-war/UsuarioService.wsdl")
    private UsuarioService_Service service;
   
            
    String nick;
    String pass;

    /**
     * Creates a new instance of loginBean
     */
    public loginBean() {
    }
    
    public String doLogin(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Usuario usr =  autorizacion(nick, pass);
        if(usr!=null){
            facesContext.getExternalContext().getSessionMap().put("user", usr);
            return "/jsf/principal";
        }else
            facesContext.addMessage("formLogin", new FacesMessage("Login incorrecto. Usuario o contraseña errónea"));
        return null;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    private Usuario autorizacion(java.lang.String nick, java.lang.String pwd) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        app.webservice.UsuarioService port = service.getUsuarioServicePort();
        return port.autorizacion(nick, pwd);
    }
    
    
}
