/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.emorg.service;

import java.util.LinkedList;
import java.util.Queue;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import pl.kiminoboku.emorg.domain.Research;

/**
 * Service responsible for queuing research orders. Given orders are executed in FIFO
 * order
 *
 * @author Radek
 */
@Singleton
@LocalBean
public class ResearchOrderQueueService {

    private Queue<Research> researches = new LinkedList<Research>();

    /**
     * Returns research order or {@code null} if there isn't any
     *
     * @return
     */
    @Lock(LockType.WRITE)
    public Research takeOrder() {
        return researches.poll();
    }

    /**
     * Submits given research to be executed
     *
     * @param researchOrder
     */
    @Lock(LockType.WRITE)
    public void submitOrder(Research researchOrder) {
        researches.add(researchOrder);
    }
}
