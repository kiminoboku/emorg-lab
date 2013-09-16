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
@XmlType
public class Examination {

    @XmlElement(required = true)
    private List<AbstractOperation> operations;

    public static Examination with(AbstractOperation operation) {
        return new Examination(ImmutableList.of(operation));
    }

    public Examination(List<AbstractOperation> operations) {
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
        final Examination other = (Examination) obj;
        if (this.operations != other.operations && (this.operations == null || !this.operations.equals(other.operations))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Examination{" + "operations=" + operations + '}';
    }
}
