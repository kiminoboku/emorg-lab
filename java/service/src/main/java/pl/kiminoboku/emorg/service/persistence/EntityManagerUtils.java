/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.kiminoboku.emorg.service.persistence;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Radek
 */
public class EntityManagerUtils {
    public static void a() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("emorgPU");
    }
}
