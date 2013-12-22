/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.emorg.domain.operation;

import javax.xml.bind.annotation.XmlType;

/**
 * @author Radek
 */
@XmlType(name = "EmptyOperation")
public class EmptyOperation extends AbstractOperation {

    public static final EmptyOperation INSTANCE = new EmptyOperation();

    private EmptyOperation() {
    }

    @Override
    public OperationType getOperationType() {
        return OperationType.EMPTY;
    }

}
