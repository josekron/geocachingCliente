/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service.flickr;

import app.webservice.Tesoro;
import app.webservice.TesoroService_Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.ws.rs.ClientErrorException;
import javax.xml.ws.WebServiceRef;
import service.flickr.utils.Photo;
import service.flickr.utils.Photo_Info;
import usuario.loginBean;

/**
 *
 * @author Enrique Rios Santos
 */
@ManagedBean(name = "flickrBean")
@ViewScoped
public class FlickrBean {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/proyecto5-war/TesoroService.wsdl")
    private TesoroService_Service service;
    private static final int numFotosAMostrar = 5;
    boolean fotosDisponibles=true;
    List<String> listaUrlFotos;
    List<Photo> listaFotos;
    private FlickrService fs;
    
    @PostConstruct
    public void cargaPrevia(){
       FacesContext context = FacesContext.getCurrentInstance();
       HttpSession sesion = (HttpSession) context.getExternalContext().getSession(true);
       int idTesoro = (int) sesion.getAttribute("idTesoro");
       Tesoro tesoro = devolverTesoro(idTesoro);
       fs = new FlickrService();
       listaUrlFotos = new ArrayList<>();
       fotosDisponibles=false;
        try {
            System.out.println("servicio flickr");
            FlickrResponsePhotosSearch response = 
                    fs.photos_search(FlickrResponsePhotosSearch.class, tesoro.getLatitud(), tesoro.getLongitud());
            listaFotos = response.getPhotos().getPhoto();
            listaUrlFotos = obtenerURLFoto();
            fotosDisponibles=true;
        } catch (IOException | RuntimeException ex) {
            fotosDisponibles=false;
            System.out.println("ERROR FLICKR ----------------");
            Logger.getLogger(loginBean.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    private List<String> obtenerURLFoto(){
        List<String> listaURLFotos=new ArrayList<>();
            try {
                for(int i=0; i<numFotosAMostrar; i++){
                        Photo_Info pInfo = fs.getInfo(Photo_Info.class, listaFotos.get(i).getId());
                        listaFotos.get(i).setLocation(pInfo.getPhoto().getLocation());
                        listaFotos.get(i).setOwner(pInfo.getPhoto().getOwner());
                    listaURLFotos.add(String.format(
                            "http://farm%d.staticflickr.com/%s/%s_%s_m.jpg", 
                            listaFotos.get(i).getFarm(),
                            listaFotos.get(i).getServer(),
                            listaFotos.get(i).getId(),
                            listaFotos.get(i).getSecret()));
                }
            } catch (ClientErrorException | IOException ex) {
                Logger.getLogger(FlickrBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return listaURLFotos;
    }

    private Tesoro devolverTesoro(int idTesoro) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        app.webservice.TesoroService port = service.getTesoroServicePort();
        return port.devolverTesoro(idTesoro);
    }

    public boolean isFotosDisponibles() {
        return fotosDisponibles;
    }

    public void setFotosDisponibles(boolean fotosDisponibles) {
        this.fotosDisponibles = fotosDisponibles;
    }

    public List<String> getListaUrlFotos() {
        return listaUrlFotos;
    }

    public void setListaUrlFotos(List<String> listaUrlFotos) {
        this.listaUrlFotos = listaUrlFotos;
    }

    public List<Photo> getListaFotos() {
        return listaFotos;
    }

    public void setListaFotos(List<Photo> listaFotos) {
        this.listaFotos = listaFotos;
    }
    
}
