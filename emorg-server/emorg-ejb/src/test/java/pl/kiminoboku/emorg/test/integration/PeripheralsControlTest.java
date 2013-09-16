/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.emorg.test.integration;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import pl.kiminoboku.emorg.domain.Examination;
import pl.kiminoboku.emorg.domain.operation.ManagePeripheralsOperation;
import pl.kiminoboku.emorg.service.PeripheralsControlService;
import static pl.kiminoboku.emorg.test.integration.GlassFishTest.getBean;
import pl.kiminoboku.emorg.service.web.ExaminationOrderer;

/**
 *
 * @author Radek
 */
public class PeripheralsControlTest extends GlassFishTest {

    @Test
    public void disableKeyboard() {
        getBean(PeripheralsControlService.class).disableKeyboard();
        Examination actual = getBean(ExaminationOrderer.class).takeOrder();
        Examination expected = Examination.with(ManagePeripheralsOperation.DISABLE_KEYBOARD_OPERATION);
        assertThat(actual, is(expected));
    }
    
    @Test
    public void disableMouse() {
        getBean(PeripheralsControlService.class).disableMouse();
        Examination actual = getBean(ExaminationOrderer.class).takeOrder();
        Examination expected = Examination.with(ManagePeripheralsOperation.DISABLE_MOUSE_OPERATION);
        assertThat(actual, is(expected));
    }
    
    @Test
    public void enableKeyboard() {
        getBean(PeripheralsControlService.class).enableKeyboard();
        Examination actual = getBean(ExaminationOrderer.class).takeOrder();
        Examination expected = Examination.with(ManagePeripheralsOperation.ENABLE_KEYBOARD_OPERATION);
        assertThat(actual, is(expected));
    }
    
    @Test
    public void enableMouse() {
        getBean(PeripheralsControlService.class).enableMouse();
        Examination actual = getBean(ExaminationOrderer.class).takeOrder();
        Examination expected = Examination.with(ManagePeripheralsOperation.ENABLE_MOUSE_OPERATION);
        assertThat(actual, is(expected));
    }
}