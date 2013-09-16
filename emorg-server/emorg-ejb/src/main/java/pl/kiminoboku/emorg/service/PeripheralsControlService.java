/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.emorg.service;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import pl.kiminoboku.emorg.domain.Examination;
import pl.kiminoboku.emorg.domain.operation.ManagePeripheralsOperation;

/**
 *
 * @author Radek
 */
@Stateless
@LocalBean
public class PeripheralsControlService {

    @Inject
    ExaminationOrderQueueService examinationOrderQueueService;

    public void enableKeyboard() {
        examinationOrderQueueService.submitOrder(Examination.with(ManagePeripheralsOperation.ENABLE_KEYBOARD_OPERATION));
    }

    public void disableKeyboard() {
        examinationOrderQueueService.submitOrder(Examination.with(ManagePeripheralsOperation.DISABLE_KEYBOARD_OPERATION));
    }

    public void enableMouse() {
        examinationOrderQueueService.submitOrder(Examination.with(ManagePeripheralsOperation.ENABLE_MOUSE_OPERATION));
    }

    public void disableMouse() {
        examinationOrderQueueService.submitOrder(Examination.with(ManagePeripheralsOperation.DISABLE_MOUSE_OPERATION));
    }
}
