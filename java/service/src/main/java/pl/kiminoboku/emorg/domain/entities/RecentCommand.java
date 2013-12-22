/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.emorg.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Entity holding information of recently used remote-command.
 *
 * @author Radek
 */
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"command"})})
public class RecentCommand implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Entity id
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * Actual command
     */
    @NotNull
    @Column(name = "command")
    @Size(min = 1)
    private String command;

    /**
     * Last command invocation time. Needed so we can remove commands in appropriate
     * order (from least recently used to most recently used)
     */
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastInvocation;

    /**
     * Creates object with no {@code id}, {@code command} and {@code lastInvocation}
     * set.
     */
    public RecentCommand() {
    }

    /**
     * Creates object with no {@code id}, with given {@code command} and
     * {@code lastInvocation} set to now.
     *
     * @param command actual command
     */
    public RecentCommand(String command) {
        this.command = command;
        lastInvocation = new Date();
    }

    /**
     * Returns entity id
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
     * Returns actual command content
     *
     * @return
     */
    public String getCommand() {
        return command;
    }

    /**
     * Sets actual command content
     *
     * @param command
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * Returns last invocation time of command
     *
     * @return
     */
    public Date getLastInvocation() {
        return lastInvocation;
    }

    /**
     * Sets last invocation time of command
     *
     * @param lastInvocation
     */
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
