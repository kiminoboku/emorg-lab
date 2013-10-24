/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.emorg.service;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import pl.kiminoboku.emorg.domain.Research;
import pl.kiminoboku.emorg.domain.operation.ManagePeripheralsOperation;

/**
 * Service dedicated to take control over peripheral devices state
 *
 * @author Radek
 */
@Stateless
@LocalBean
public class PeripheralsControlService {

    /**
     * Service responsible for queuing research orders
     */
    @Inject
    ResearchOrderQueueService researchOrderQueueService;

    /**
     * Enables keyboard on examined person's PC
     */
    public void enableKeyboard() {
        //submit research order with managing peripheral device operation - enabling keyboard
        researchOrderQueueService.submitOrder(Research.with(ManagePeripheralsOperation.ENABLE_KEYBOARD_OPERATION));
    }

    public void disableKeyboard() {
        //submit research order with managing peripheral device operation - disabling keyboard
        researchOrderQueueService.submitOrder(Research.with(ManagePeripheralsOperation.DISABLE_KEYBOARD_OPERATION));
    }

    public void enableMouse() {
        //submit research order with managing peripheral device operation - enabling mouse
        researchOrderQueueService.submitOrder(Research.with(ManagePeripheralsOperation.ENABLE_MOUSE_OPERATION));
    }

    public void disableMouse() {
        //submit research order with managing peripheral device operation - disabling mouse
        researchOrderQueueService.submitOrder(Research.with(ManagePeripheralsOperation.DISABLE_MOUSE_OPERATION));
    }
}
