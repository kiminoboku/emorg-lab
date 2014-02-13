/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.netbeans.components.operation;

import javax.swing.JPanel;
import javax.validation.ValidationException;
import pl.kiminoboku.emorg.domain.entities.operation.AbstractOperation;
import pl.kiminoboku.emorg.domain.entities.operation.ManagePeripheralsOperation;
import pl.kiminoboku.netbeans.ValidateMe;

/**
 *
 * @author Radek
 */
public enum OperationTypeUI {

    SET_ON_MOUSE("/icons/operations/mouse-on_32.png"),
    SET_OFF_MOUSE("/icons/operations/mouse-off_32.png"),
    SLEEP("/icons/operations/hourglass_32.png");

    private String iconPath;

    private OperationTypeUI(String iconPath) {
        this.iconPath = iconPath;
    }

    public AbstractOperation getDefaultOperation() {
        switch (this) {
            case SET_OFF_MOUSE:
                return ManagePeripheralsOperation.DISABLE_MOUSE_OPERATION;
            case SET_ON_MOUSE:
                return ManagePeripheralsOperation.ENABLE_MOUSE_OPERATION;
            default:
                return null; //no default operation, custom creation needed (that provides some specific properties)
        }
    }

    public JPanel createOperationEditPanel() {
        switch (this) {
            case SLEEP:
                return new JPanell();
            default:
                return null;
        }
    }

    public String getIconPath() {
        return iconPath;
    }

    private static final class JPanell extends JPanel implements ValidateMe {

        @Override
        public void isDataValid() {
            throw new ValidationException("I will never be valid :(");
        }
    }
}
