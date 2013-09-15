/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.emorg.domain.operations;

import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Radek
 */
@XmlType
public enum PeripheralStateChange {
    TURN_ON,
    TURN_OFF,
    DO_NOTHING
}
