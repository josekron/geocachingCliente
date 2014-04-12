/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.netbeans.saas.flickr;

import java.io.IOException;
import org.netbeans.saas.RestConnection;
import org.netbeans.saas.RestResponse;

/**
 * FlickrPhotoService Service
 *
 * @author Enrique Rios Santos
 */
public class FlickrPhotoService {

    /**
     * Creates a new instance of FlickrPhotoService
     */
    public FlickrPhotoService() {
    }
    
    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Throwable th) {
        }
    }

    /**
     *
     * @param photoId
     * @param secret
     * @return an instance of RestResponse
     */
    public static RestResponse photosGetInfo(String photoId, String secret) throws IOException {
        String method = "flickr.photos.getInfo";
        FlickrPhotoServiceAuthenticator.login();
        String apiKey = FlickrPhotoServiceAuthenticator.getApiKey();
        String authToken = FlickrPhotoServiceAuthenticator.getAuthToken();
        String apiSig = FlickrPhotoServiceAuthenticator.sign(new String[][]{{"api_key", apiKey}, {"photo_id", photoId}, {"secret", secret}, {"method", method}, {"auth_token", authToken}});
        String[][] pathParams = new String[][]{};
        String[][] queryParams = new String[][]{{"api_key", "" + apiKey + ""}, {"photo_id", photoId}, {"secret", secret}, {"method", method}, {"auth_token", authToken}, {"api_sig", apiSig}};
        RestConnection conn = new RestConnection("http://api.flickr.com/services/rest", pathParams, queryParams);
        sleep(1000);
        return conn.get(null);
    }
}
