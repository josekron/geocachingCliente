/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service.elevation;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Joaquin
 */
public class a {
    
public static void main(String[] args) {
        
     ElevationService alt = new ElevationService();
        String coordenadas;
        double res = 0.0;
        coordenadas = "1,1";

        Response response = alt.elevacion(Response.class, coordenadas, "false");

        if (response.getStatus() == 200) {
            System.out.println(response.toString());
            GenericType<ElevacionElevation> genericType = new GenericType<ElevacionElevation>() {
            };
            ElevacionElevation ele = response.readEntity(genericType);
            res = ele.getResults().get(0).getElevation();
            System.out.println(res);

        }
       
    }
}
