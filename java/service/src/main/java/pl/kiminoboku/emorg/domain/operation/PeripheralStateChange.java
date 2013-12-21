/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.emorg.domain.operation;

import javax.xml.bind.annotation.XmlType;

/**
 * Determines state change of peripheral devices
 *
 * @author Radek
 */
@XmlType(name = "PeripheralStateChange")
public enum PeripheralStateChange {

    /**
     * Turn on device
     */
    TURN_ON,
    /**
     * Turn off device
     */
    TURN_OFF,
    /**
     * Leave device state unchanged
     */
    DO_NOTHING

}
