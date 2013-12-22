/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.emorg.domain;

import com.google.common.collect.ImmutableList;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import pl.kiminoboku.emorg.domain.operation.AbstractOperation;

/**
 * Entity describing one research plan containing multiple operations in specific order.
 *
 * @author Radek
 */
@XmlRootElement(name = "research")
@XmlType(name = "Research")
public class Research {

    /**
     * Operations included in this research.
     */
    @XmlElement(required = true, name = "operation")
    private List<AbstractOperation> operations = Collections.emptyList();

    private Research() {
    }
    
    /**
     * Creates research with one given operation
     *
     * @param operation operation to be contained in newly created research
     * @return research containing given operation
     */
    public static Research with(AbstractOperation operation) {
        return new Research(Collections.singleton(operation));
    }

    /**
     * Creates research containing given operations collection. Given collection is
     * deeply copied so changes in source collection won't be reflected into created
     * object. The order of elements is sustained.
     *
     * @param operations operations to be contained in
     */
    public Research(Collection<AbstractOperation> operations) {
        this.operations = ImmutableList.copyOf(operations);
    }

    /**
     * Returns immutable operations list contained in this research.
     *
     * @return research operations
     */
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
