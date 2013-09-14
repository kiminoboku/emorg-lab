/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.emorg.faces.controller.actions;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.kiminoboku.emorg.faces.util.FacesMessageUtil;
import pl.kiminoboku.i18n.ResourceBundleSafe;

/**
 *
 * @author Radek
 */
@Named
@RequestScoped
public class PeripheralsController {
    
    @Inject
    ResourceBundleSafe messages;
    
    public void enableKeyboard() {
        FacesMessageUtil.addInfoMessage(messages.getString("peryferia.klawiatura.wlaczona"));
    }
    
    public void disableKeyboard() {
        FacesMessageUtil.addInfoMessage(messages.getString("peryferia.klawiatura.wylaczona"));
    }
    
    public void enableMouse() {
        FacesMessageUtil.addInfoMessage(messages.getString("peryferia.myszka.wlaczona"));
    }
    
    public void disableMouse() {
        FacesMessageUtil.addInfoMessage(messages.getString("peryferia.myszka.wylaczona"));
    }
}
