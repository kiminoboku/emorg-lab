/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.emorg.domain;

import com.google.common.collect.ImmutableList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import pl.kiminoboku.emorg.domain.operations.Operation;

/**
 *
 * @author Radek
 */
@XmlType
public class Examination {
    
    @XmlElement(required = true)
    private List<Operation> operations;
    
    public static Examination of(Operation operation) {
        return new Examination(ImmutableList.of(operation));
    }

    public Examination(List<Operation> operations) {
        this.operations = ImmutableList.copyOf(operations);
    }

    public List<Operation> getOperations() {
        return operations;
    }
}
