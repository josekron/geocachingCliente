/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.flickr;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MultivaluedMap;
import org.netbeans.saas.flickr.FlickrPhotoService;
import org.netbeans.saas.RestResponse;

/**
 * Jersey REST client generated for REST resource:Photo Service [services/rest]<br>
 * USAGE:
 * <pre>
 *        FlickrService client = new FlickrService();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Enrique Rios
 */
public class FlickrService {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://api.flickr.com/";
    private static String api_key = "094fe80dac343326f2c1abdd7a3643c9";
    //private static String application_secret = "44e54aefe40690f9";
    private static double radio = 0.008;

    public FlickrService() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("services/rest");
    }

    /**
     * @param <T>
     * @param responseType
     * @param lat
     * @param lon
     * @return
     * @throws java.io.IOException
     * @Autor: Enrique Ríos Santos
     * @Description: Servicio para obtener fotográfias a partir de una coordenada
     */
    public <T> T photos_search(Class<T> responseType, String lat, String lon) throws ClientErrorException, IOException {
        String coordenadas = crearBBOX(lat, lon);
        String[] queryParamNames = new String[]{"method", "api_key", "bbox", "format", "nojsoncallback"};
        String[] queryParamValues = new String[]{"flickr.photos.search", api_key, coordenadas, "json", "1"};

        javax.ws.rs.core.Form form = getQueryOrFormParams(queryParamNames, queryParamValues);
        javax.ws.rs.core.MultivaluedMap<String, String> map = form.asMap();
        for (java.util.Map.Entry<String, java.util.List<String>> entry : map.entrySet()) {
            java.util.List<String> list = entry.getValue();
            String[] values = list.toArray(new String[list.size()]);
            webTarget = webTarget.queryParam(entry.getKey(), (Object[]) values);
        }
        return webTarget.request(javax.ws.rs.core.MediaType.TEXT_XML).get(responseType);
    }

    /**
     * @return @throws java.io.IOException
     * @Autor: Enrique Ríos Santos
     * @Description: Servicio para obtener información de una foto
     */
    public <T> T getInfo(Class<T> responseType, String id) throws ClientErrorException, IOException {
        String[] queryParamNames = new String[]{"method", "api_key", "photo_id", "format", "nojsoncallback"};
        String[] queryParamValues = new String[]{"flickr.photos.getInfo", api_key, id, "json", "1"};

        javax.ws.rs.core.Form form = getQueryOrFormParams(queryParamNames, queryParamValues);
        javax.ws.rs.core.MultivaluedMap<String, String> map = form.asMap();
        for (java.util.Map.Entry<String, java.util.List<String>> entry : map.entrySet()) {
            java.util.List<String> list = entry.getValue();
            String[] values = list.toArray(new String[list.size()]);
            webTarget = webTarget.queryParam(entry.getKey(), (Object[]) values);
        }
        return webTarget.request(javax.ws.rs.core.MediaType.TEXT_XML).get(responseType);
    } 

    /**
     * @Autor: Enrique Ríos Santos
     * @Description: Método para crear un String válido para el parámetro bbox
     */
    private String crearBBOX(String lat, String lon) {
        Double lat1 = Double.valueOf(lat);
        Double lon1 = Double.valueOf(lon);

        return String.format("%s,%s,%s,%s",
                String.valueOf(lon1 - radio), String.valueOf(lat1 - radio), String.valueOf(lon1 + radio), String.valueOf(lat1 + radio));
    }

    private Form getQueryOrFormParams(String[] paramNames, String[] paramValues) {
        Form form = new javax.ws.rs.core.Form();
        for (int i = 0; i < paramNames.length; i++) {
            if (paramValues[i] != null) {
                form = form.param(paramNames[i], paramValues[i]);
            }
        }
        return form;
    }

}