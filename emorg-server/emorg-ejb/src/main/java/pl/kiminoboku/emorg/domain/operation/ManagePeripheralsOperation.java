/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.emorg.domain.operation;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Operation that orders to manage keyboard and/or mouse state.
 *
 * @author Radek
 */
@XmlType(name = "ManagePeripheralsOperation")
public class ManagePeripheralsOperation extends AbstractOperation {

    /**
     * Operation that orders to set keyboard on and don't change state of mouse.
     */
    public static final ManagePeripheralsOperation ENABLE_KEYBOARD_OPERATION = new ManagePeripheralsOperation(PeripheralStateChange.DO_NOTHING, PeripheralStateChange.TURN_ON);

    /**
     * Operation that orders to set keyboard off and don't change state of mouse.
     */
    public static final ManagePeripheralsOperation DISABLE_KEYBOARD_OPERATION = new ManagePeripheralsOperation(PeripheralStateChange.DO_NOTHING, PeripheralStateChange.TURN_OFF);

    /**
     * Operation that orders to set mouse on and don't change state of keyboard.
     */
    public static final ManagePeripheralsOperation ENABLE_MOUSE_OPERATION = new ManagePeripheralsOperation(PeripheralStateChange.TURN_ON, PeripheralStateChange.DO_NOTHING);

    /**
     * Operation that orders to set mouse off and don't change state of keyboard.
     */
    public static final ManagePeripheralsOperation DISABLE_MOUSE_OPERATION = new ManagePeripheralsOperation(PeripheralStateChange.TURN_OFF, PeripheralStateChange.DO_NOTHING);

    /**
     * Change of mouse state contained in this operation.
     */
    @XmlElement(required = true, name = "mouseStateChange")
    private PeripheralStateChange mouseStateChange;

    /**
     * Change of keyboard state contained in this operation.
     */
    @XmlElement(required = true, name = "keyboardStateChange")
    private PeripheralStateChange keyboardStateChange;

    /**
     * Creates object with given peripherals state changes.
     *
     * @param mouseStateChange
     * @param keyboardStateChange
     * @throws NullPointerException if {@code mouseStateChange == null} or
     * {@code keyboardStateChange == null}
     */
    public ManagePeripheralsOperation(PeripheralStateChange mouseStateChange, PeripheralStateChange keyboardStateChange) {
        if (mouseStateChange == null) {
            throw new NullPointerException();
        }
        if (keyboardStateChange == null) {
            throw new NullPointerException();
        }
        this.mouseStateChange = mouseStateChange;
        this.keyboardStateChange = keyboardStateChange;
    }

    /**
     * Returns mouse state change
     *
     * @return
     */
    public PeripheralStateChange getMouseStateChange() {
        return mouseStateChange;
    }

    /**
     * Returns keyboard states change
     *
     * @return
     */
    public PeripheralStateChange getKeyboardStateChange() {
        return keyboardStateChange;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (this.mouseStateChange != null ? this.mouseStateChange.hashCode() : 0);
        hash = 59 * hash + (this.keyboardStateChange != null ? this.keyboardStateChange.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ManagePeripheralsOperation other = (ManagePeripheralsOperation) obj;
        if (this.mouseStateChange != other.mouseStateChange) {
            return false;
        }
        if (this.keyboardStateChange != other.keyboardStateChange) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ManagePeripheralsOperation{" + "mouseStateChange=" + mouseStateChange + ", keyboardStateChange=" + keyboardStateChange + '}';
    }

    @Override
    public OperationType getOperationType() {
        return OperationType.MANAGE_PERIPHERALS;
    }
}
