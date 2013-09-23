/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.emorg.domain.operation;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Radek
 */
@XmlType(name = "AbstractOperation")
@XmlSeeAlso({ManagePeripheralsOperation.class})
public abstract class AbstractOperation {

    @XmlElement
    public abstract OperationType getOperationType();
}
