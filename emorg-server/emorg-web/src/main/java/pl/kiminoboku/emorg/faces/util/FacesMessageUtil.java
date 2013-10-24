/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.emorg.faces.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Utility class responsible for simplified faces message management.
 *
 * @author Radek
 */
public class FacesMessageUtil {

    private FacesMessageUtil() { //this is an util class
    }

    /**
     * Adds message to faces context with info severity and given summary content.
     * Equivalent of {@code addMessage(FacesMessage.SEVERITY_INFO, text)}
     *
     * @param summary faces message summary
     * @see #addMessage(javax.faces.application.FacesMessage.Severity, java.lang.String)
     */
    public static void addInfoMessage(String summary) {
        addMessage(FacesMessage.SEVERITY_INFO, summary);
    }

    /**
     * Adds message to faces context with error severity and given summary content.
     * Equivalent of {@code addMessage(FacesMesssage.SEVERITY_ERROR, text)}
     *
     * @param summary faces message summary
     * @see #addMessage(javax.faces.application.FacesMessage.Severity, java.lang.String)
     */
    public static void addErrorMessage(String summary) {
        addMessage(FacesMessage.SEVERITY_ERROR, summary);
    }

    /**
     * Adds message to faces context with given severity and summary content. Message
     * has no detail content and isn't associated to any clientId
     *
     * @param severity severity level
     * @param summary  summary content
     * @see FacesMessage
     * @see FacesContext#addMessage(java.lang.String,
     *      javax.faces.application.FacesMessage)
     */
    public static void addMessage(FacesMessage.Severity severity, String summary) {
        FacesMessage message = new FacesMessage(severity, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
