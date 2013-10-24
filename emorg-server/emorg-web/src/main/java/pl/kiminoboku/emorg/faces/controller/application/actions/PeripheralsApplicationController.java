/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.emorg.faces.controller.application.actions;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pl.kiminoboku.emorg.faces.util.FacesMessageUtil;
import pl.kiminoboku.emorg.service.PeripheralsControlService;
import pl.kiminoboku.i18n.ResourceBundleSafe;

/**
 * Application controller responsible for handling manage peripherals view requests.
 *
 * @author Radek
 */
@Named
@RequestScoped
public class PeripheralsApplicationController {

    /**
     * System messages (l10n)
     */
    @Inject
    ResourceBundleSafe messages;

    /**
     * Service responsible for actual control of peripherals.
     */
    @Inject
    PeripheralsControlService peripheralsControlService;

    /**
     * Handles keyboard enable request
     */
    public void enableKeyboard() {
        //enable keyboard
        peripheralsControlService.enableKeyboard();
        //add success notification
        FacesMessageUtil.addInfoMessage(messages.getString("peryferia.klawiatura.wlaczona"));
    }

    /**
     * Handles keyboard disable request
     */
    public void disableKeyboard() {
        //disable keyboard
        peripheralsControlService.disableKeyboard();
        //add success notification
        FacesMessageUtil.addInfoMessage(messages.getString("peryferia.klawiatura.wylaczona"));
    }

    /**
     * Handles enable mouse request
     */
    public void enableMouse() {
        //enable mouse
        peripheralsControlService.enableMouse();
        //add success notification
        FacesMessageUtil.addInfoMessage(messages.getString("peryferia.myszka.wlaczona"));
    }

    /**
     * Handles disable mouse request
     */
    public void disableMouse() {
        //disable mouse
        peripheralsControlService.disableMouse();
        //add success notification
        FacesMessageUtil.addInfoMessage(messages.getString("peryferia.myszka.wylaczona"));
    }
}
