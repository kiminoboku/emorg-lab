/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.kiminoboku.emorg.service;

import java.util.LinkedList;
import java.util.Queue;
import pl.kiminoboku.emorg.domain.Research;
import pl.kiminoboku.emorg.domain.operation.EmptyOperation;

/**
 *
 * @author Radek
 */
public class ResearchOrderQueueService {
    
    private final Queue<Research> researches = new LinkedList<>();
    
    /**
     * Returns research order or {@code null} if there isn't any
     *
     * @return
     */
    public synchronized Research takeOrder() {
        Research ret = researches.poll();
        if(ret == null) {
            ret = Research.with(EmptyOperation.INSTANCE);
        }
        return ret;
    }

    /**
     * Submits given research to be executed
     *
     * @param researchOrder
     */
    public synchronized void submitOrder(Research researchOrder) {
        researches.add(researchOrder);
    }
}
