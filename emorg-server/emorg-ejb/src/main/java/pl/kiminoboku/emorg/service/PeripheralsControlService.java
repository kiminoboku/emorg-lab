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
 *
 * @author Radek
 */
@Stateless
@LocalBean
public class PeripheralsControlService {

    @Inject
    ResearchOrderQueueService researchOrderQueueService;

    public void enableKeyboard() {
        researchOrderQueueService.submitOrder(Research.with(ManagePeripheralsOperation.ENABLE_KEYBOARD_OPERATION));
    }

    public void disableKeyboard() {
        researchOrderQueueService.submitOrder(Research.with(ManagePeripheralsOperation.DISABLE_KEYBOARD_OPERATION));
    }

    public void enableMouse() {
        researchOrderQueueService.submitOrder(Research.with(ManagePeripheralsOperation.ENABLE_MOUSE_OPERATION));
    }

    public void disableMouse() {
        researchOrderQueueService.submitOrder(Research.with(ManagePeripheralsOperation.DISABLE_MOUSE_OPERATION));
    }
}
