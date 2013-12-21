/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.emorg.domain.operation;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * Abstract operation type to be executed by examined person's PC. Contains enumerated
 * value determining actual operation type so more sophisticated (like switch clause)
 * features can be used in handling research requests instead of type checking.
 *
 * @author Radek
 * @see #getOperationType()
 */
@XmlType(name = "AbstractOperation")
@XmlSeeAlso({ManagePeripheralsOperation.class})
public abstract class AbstractOperation {

    /**
     * Returns enumerated operation type.
     *
     * @return
     */
    @XmlElement
    public abstract OperationType getOperationType();
}
