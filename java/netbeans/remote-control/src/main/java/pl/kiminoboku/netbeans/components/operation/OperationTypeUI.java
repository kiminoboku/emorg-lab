/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.netbeans.components.operation;

import java.util.Objects;
import javax.swing.JPanel;
import org.apache.commons.lang3.Validate;
import pl.kiminoboku.emorg.domain.entities.operation.AbstractOperation;
import pl.kiminoboku.emorg.domain.entities.operation.ManagePeripheralsOperation;
import pl.kiminoboku.emorg.domain.entities.operation.SleepOperation;

/**
 * Enumeration responsible for user interface operation type
 * @author Radek
 */
public enum OperationTypeUI {

    /**
     * Set on mouse operation
     */
    SET_ON_MOUSE("icons/operations/mouse-on_32.png"),
    /**
     * Set off mouse operation
     */
    SET_OFF_MOUSE("icons/operations/mouse-off_32.png"),
    /**
     * Sleep operation
     */
    SLEEP("icons/operations/hourglass_32.png");

    /**
     * Path to operation icon resource file
     */
    private String iconPath;

    /**
     * Creates operation type with given path to icon
     * @param iconPath path to icon
     */
    private OperationTypeUI(String iconPath) {
        this.iconPath = iconPath;
    }

    /**
     * Returns default operation object bound to this type or null if there isn't any. For some operation types there isn't any
     * default operation objects and these can be created only using operation edit panels.
     * @return default operation object bound to this type or null if there isn't any
     * @see #createOperationEditPanel()
     */
    public AbstractOperation getDefaultOperation() {
        switch (this) {
            case SET_OFF_MOUSE:
                return ManagePeripheralsOperation.getDisableMouseOperation();
            case SET_ON_MOUSE:
                return ManagePeripheralsOperation.getEnableMouseOperation();
            default:
                return null; //no default operation, custom creation needed (that provides some specific properties)
        }
    }

    /**
     * Creates operation edit panel for this operation type or null if there isn't any. For some operation types there are
     * only default operation objects and no edit panels.
     * @return operation edit panel for this operation type or null if there isn't any
     * @see #getDefaultOperation()
     */
    public JPanel createOperationEditPanel() {
        switch (this) {
            case SLEEP:
                return new EditSleepOperationJPanel();
            default:
                return null; //no edit panel, only getDefaultOperation possible
        }
    }

    /**
     * Returns path to icon resource
     * @return path to icon resource
     */
    public String getIconPath() {
        return iconPath;
    }

    /**
     * Returns operation type based on given operation object
     * @param operation operation object
     * @return operation type
     */
    public static OperationTypeUI valueOf(AbstractOperation operation) {
        Validate.notNull(operation);
        if (Objects.equals(ManagePeripheralsOperation.getDisableMouseOperation(), operation)) {
            return SET_OFF_MOUSE;
        } else if (Objects.equals(ManagePeripheralsOperation.getEnableMouseOperation(), operation)) {
            return SET_ON_MOUSE;
        } else if (operation instanceof SleepOperation) {
            return SLEEP;
        } else {
            throw new RuntimeException(operation.toString());
        }
    }
}
