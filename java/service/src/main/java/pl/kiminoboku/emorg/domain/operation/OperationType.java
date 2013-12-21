/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.emorg.domain.operation;

import javax.xml.bind.annotation.XmlType;

/**
 * Determines specific operation type. Can be used instead of static type checking on
 *
 * @author Radek
 */
@XmlType(name = "OperationType")
public enum OperationType {

    /**
     * ManagePeripheralsOperation
     */
    MANAGE_PERIPHERALS;

}
