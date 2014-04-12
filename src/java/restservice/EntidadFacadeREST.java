/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restservice;


import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import service.geocoding.DistanceMatrixClient;
import service.geocoding.DistanceResponse;
import service.geocoding.Geocoding;
import service.geocoding.GeocodingClient;

/**
 *
 * @author Usuario
 */
@Stateless
@Path("google")
public class EntidadFacadeREST {
    

    public EntidadFacadeREST() {
        
    }


    @GET
    @Path("{zona}/{latitudT}/{longitudT}")
    @Consumes({"application/json"})
    public String getDistance(@PathParam("zona") String zona, @PathParam("latitudT") String latitudT
            , @PathParam("longitudT") String longitudT) {
        String latitud="";
        String longitud="";
        String distancia="";
        GeocodingClient geo = new GeocodingClient();
        Response response = geo.geocode(Response.class, zona, "false");
        if (response.getStatus() == 200) {
            GenericType<Geocoding> genericType = new GenericType<Geocoding>() {
            };
            Geocoding geocoding = response.readEntity(genericType);
            latitud = geocoding.getResults().get(0).getGeometry().getLocation().getLat().toString();
            longitud = geocoding.getResults().get(0).getGeometry().getLocation().getLng().toString();

            String puntoA = latitudT + "," + longitudT;
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
                distancia = c.getRows().get(0).getElements().get(0).getDistance().getText();
                
            } else {
                System.out.println("Error al recibir el recurso, estado= " + respuesta.getStatus());
            }
        }
        return  " {\"prueba\":\""+distancia+"\"}";
        
    }

    
    
}
