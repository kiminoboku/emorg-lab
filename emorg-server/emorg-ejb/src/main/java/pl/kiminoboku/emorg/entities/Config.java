/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.emorg.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Radek
 */
@Entity
public class Config implements Serializable {

    @Id
    private Integer id = 1;
    private int maxRecentCommandsCount = 10;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaxRecentCommandsCount() {
        return maxRecentCommandsCount;
    }

    public void setMaxRecentCommandsCount(int maxRecentCommandsCount) {
        this.maxRecentCommandsCount = maxRecentCommandsCount;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 89 * hash + this.maxRecentCommandsCount;
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
        final Config other = (Config) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.maxRecentCommandsCount != other.maxRecentCommandsCount) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SystemConfig{" + "id=" + id + ", maxRecentCommandsCount=" + maxRecentCommandsCount + '}';
    }
}
