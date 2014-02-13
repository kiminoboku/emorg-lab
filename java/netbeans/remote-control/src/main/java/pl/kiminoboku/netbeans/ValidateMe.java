/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.netbeans;

import javax.validation.ValidationException;

/**
 *
 * @author Radek
 */
public interface ValidateMe {

    /**
     *
     * @throws ValidationException
     */
    public void isDataValid();
}
