/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.emorg.domain;

import com.google.common.collect.ImmutableList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import pl.kiminoboku.emorg.domain.operation.AbstractOperation;

/**
 *
 * @author Radek
 */
@XmlType(name = "Research")
public class Research {

    @XmlElement(required = true, name = "operation")
    private List<AbstractOperation> operations;

    public static Research with(AbstractOperation operation) {
        return new Research(ImmutableList.of(operation));
    }

    public Research(List<AbstractOperation> operations) {
        this.operations = ImmutableList.copyOf(operations);
    }

    public List<AbstractOperation> getOperations() {
        return operations;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (this.operations != null ? this.operations.hashCode() : 0);
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
        final Research other = (Research) obj;
        if (this.operations != other.operations && (this.operations == null || !this.operations.equals(other.operations))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Research{" + "operations=" + operations + '}';
    }
}
