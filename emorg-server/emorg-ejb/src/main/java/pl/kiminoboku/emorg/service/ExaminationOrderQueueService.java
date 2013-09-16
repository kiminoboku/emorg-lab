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
import pl.kiminoboku.emorg.domain.Examination;

/**
 *
 * @author Radek
 */
@Singleton
@LocalBean
public class ExaminationOrderQueueService {

    private Queue<Examination> examinations = new LinkedList<Examination>();

    @Lock(LockType.WRITE)
    public Examination takeOrder() {
        return examinations.poll();
    }

    @Lock(LockType.WRITE)
    public void submitOrder(Examination examinationOrder) {
        examinations.add(examinationOrder);
    }
}
