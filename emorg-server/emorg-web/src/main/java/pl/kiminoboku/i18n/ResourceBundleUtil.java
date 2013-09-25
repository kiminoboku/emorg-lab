/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.i18n;

import java.util.Locale;
import java.util.ResourceBundle;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

/**
 * Bean responsible for producing ResourceBundleSafe delegator.
 *
 * @author Radek
 * @see ResourceBundleSafe
 */
@RequestScoped
public class ResourceBundleUtil {

    /**
     * Returns ResourceBundleSafe delegator for system resource bundle
     *
     * @return
     */
    @Produces
    public ResourceBundleSafe getResourceBundle() {
        //load locale from faces context
        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        //return new safe proxy
        return new ResourceBundleSafe(ResourceBundle.getBundle("i18n/messages", locale));
    }
}
