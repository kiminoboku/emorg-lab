/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.netbeans.components.operation;

import java.util.List;
import pl.kiminoboku.emorg.domain.entities.operation.AbstractOperation;

/**
 *
 * @author Radek
 */
public interface OperationListModelObservator {

    public void setOperationListData(List<AbstractOperation> operations, int newOperationIndex);
}
