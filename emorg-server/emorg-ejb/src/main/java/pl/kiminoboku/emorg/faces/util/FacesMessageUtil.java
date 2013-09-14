/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.emorg.faces.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Radek
 */
public class FacesMessageUtil {
    private FacesMessageUtil() {}
    
    public static void addInfoMessage(String text) {
        addMessage(FacesMessage.SEVERITY_INFO, text);
    }
    
    public static void addErrorMessage(String text) {
        addMessage(FacesMessage.SEVERITY_ERROR, text);
    }
    
    public static void addMessage(FacesMessage.Severity severity, String text) {
        FacesMessage message = new FacesMessage(severity, text, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
