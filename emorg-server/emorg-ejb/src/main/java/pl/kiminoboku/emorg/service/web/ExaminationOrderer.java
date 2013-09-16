/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.emorg.service.web;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.jws.WebMethod;
import pl.kiminoboku.emorg.domain.Examination;
import pl.kiminoboku.emorg.service.ExaminationOrderQueueService;

/**
 *
 * @author Radek
 */
@Stateless
@LocalBean
@WebService
public class ExaminationOrderer {

    @Inject
    private ExaminationOrderQueueService examinationOrderQueueService;

    @WebMethod
    public Examination takeOrder() {
        return examinationOrderQueueService.takeOrder();
    }
}
