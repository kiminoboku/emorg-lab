/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.emorg.faces.controller.actions;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.kiminoboku.emorg.domain.Examination;
import pl.kiminoboku.emorg.domain.operations.ManagePeripheralsOperation;
import pl.kiminoboku.emorg.ejb.examination.ExaminationOrderQueueBean;
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
    
    @Inject
    ExaminationOrderQueueBean examinationOrderQueueBean;
    
    public void enableKeyboard() {
        examinationOrderQueueBean.submitOrder(Examination.of(ManagePeripheralsOperation.ENABLE_KEYBOARD_OPERATION));
        FacesMessageUtil.addInfoMessage(messages.getString("peryferia.klawiatura.wlaczona"));
    }
    
    public void disableKeyboard() {
        examinationOrderQueueBean.submitOrder(Examination.of(ManagePeripheralsOperation.DISABLE_KEYBOARD_OPERATION));
        FacesMessageUtil.addInfoMessage(messages.getString("peryferia.klawiatura.wylaczona"));
    }
    
    public void enableMouse() {
        examinationOrderQueueBean.submitOrder(Examination.of(ManagePeripheralsOperation.ENABLE_MOUSE_OPERATION));
        FacesMessageUtil.addInfoMessage(messages.getString("peryferia.myszka.wlaczona"));
    }
    
    public void disableMouse() {
        examinationOrderQueueBean.submitOrder(Examination.of(ManagePeripheralsOperation.DISABLE_MOUSE_OPERATION));
        FacesMessageUtil.addInfoMessage(messages.getString("peryferia.myszka.wylaczona"));
    }
}
