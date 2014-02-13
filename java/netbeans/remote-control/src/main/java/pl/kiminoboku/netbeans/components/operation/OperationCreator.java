/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.netbeans.components.operation;

import pl.kiminoboku.emorg.domain.entities.operation.AbstractOperation;

/**
 *
 * @author Radek
 */
public interface OperationCreator<T extends AbstractOperation> {

    AbstractOperation createOperation();
}
