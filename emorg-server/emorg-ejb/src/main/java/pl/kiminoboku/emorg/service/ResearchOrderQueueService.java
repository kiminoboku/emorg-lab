/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.emorg.service;

import java.util.LinkedList;
import java.util.Queue;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import pl.kiminoboku.emorg.domain.Research;

/**
 *
 * @author Radek
 */
@Singleton
@LocalBean
public class ResearchOrderQueueService {

    private Queue<Research> researches = new LinkedList<Research>();

    @Lock(LockType.WRITE)
    public Research takeOrder() {
        return researches.poll();
    }

    @Lock(LockType.WRITE)
    public void submitOrder(Research examinationOrder) {
        researches.add(examinationOrder);
    }
}
