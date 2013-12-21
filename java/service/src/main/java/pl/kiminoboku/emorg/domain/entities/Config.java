/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.emorg.domain.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Entity containing system-wide configuration. Class contains parameterless constructor
 * which creates object initialized with default configuration values (in case if there
 * isn't configuration in database).
 *
 * @author Radek
 */
@Entity
public class Config implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Entity id, there's only one system configuration so default value is set.
     */
    @Id
    private Integer id = Integer.valueOf(1);

    /**
     * Maximum amount of recent commands to be hold by system.
     */
    private int maxRecentCommandsCount = 10;

    /**
     * Returns entity id, equivalent of {@code Integer.valueOf(1)}
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets entity id
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns maximum amount of recent commands to be hold by system.
     *
     * @return
     */
    public int getMaxRecentCommandsCount() {
        return maxRecentCommandsCount;
    }

    /**
     * Sets maximum amount of recent commands to be hold by system.
     *
     * @param maxRecentCommandsCount
     */
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
        return "Config{" + "id=" + id + ", maxRecentCommandsCount=" + maxRecentCommandsCount + '}';
    }
}
