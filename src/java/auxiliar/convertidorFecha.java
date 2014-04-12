/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliar;

import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Enrique Rios
 */
public class convertidorFecha {
    private static DatatypeFactory df;
    
    public static XMLGregorianCalendar asXMLGregorianCalendar(java.util.Date date) {
        try {
            df = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException e) {
            throw new IllegalStateException(
                    "Error while trying to obtain a new instance of DatatypeFactory", e);
        }
        if (date == null) {
            return null;
        } else {
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTimeInMillis(date.getTime());
            return df.newXMLGregorianCalendar(gc);
        }
    }
// Converts an XMLGregorianCalendar to an instance of java.util.Date

    public static java.util.Date asDate(XMLGregorianCalendar xmlGC) {
        if (xmlGC == null) {
            return null;
        } else {
            return xmlGC.toGregorianCalendar().getTime();
        }
    }
}
