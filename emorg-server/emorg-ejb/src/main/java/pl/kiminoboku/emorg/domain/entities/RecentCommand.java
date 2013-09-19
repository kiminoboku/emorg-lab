/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.emorg.domain.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Radek
 */
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"command"})})
public class RecentCommand implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    @Column(name = "command")
    private String command;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastInvocation;

    public RecentCommand() {
    }

    public RecentCommand(String command) {
        this.command = command;
        lastInvocation = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Date getLastInvocation() {
        return lastInvocation;
    }

    public void setLastInvocation(Date lastInvocation) {
        this.lastInvocation = lastInvocation;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 83 * hash + (this.command != null ? this.command.hashCode() : 0);
        hash = 83 * hash + (this.lastInvocation != null ? this.lastInvocation.hashCode() : 0);
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
        final RecentCommand other = (RecentCommand) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.command == null) ? (other.command != null) : !this.command.equals(other.command)) {
            return false;
        }
        if (this.lastInvocation != other.lastInvocation && (this.lastInvocation == null || !this.lastInvocation.equals(other.lastInvocation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RecentCommand{" + "id=" + id + ", command=" + command + ", lastInvocation=" + lastInvocation + '}';
    }
}
