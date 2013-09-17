/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.i18n;

import java.util.Locale;
import java.util.ResourceBundle;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

/**
 *
 * @author Radek
 */
@SessionScoped
public class ResourceBundleUtil {
    
    @Produces
    @SessionScoped
    public ResourceBundleSafe getResourceBundle() {
        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        return new ResourceBundleSafe(ResourceBundle.getBundle("i18n/messages", locale));
    }
}
