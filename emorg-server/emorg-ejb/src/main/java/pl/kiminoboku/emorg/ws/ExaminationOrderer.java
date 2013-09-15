/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.emorg.ws;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.jws.WebMethod;
import pl.kiminoboku.emorg.domain.Examination;
import pl.kiminoboku.emorg.ejb.examination.ExaminationOrderQueueBean;

/**
 *
 * @author Radek
 */
@Stateless
@WebService(serviceName = "ExaminationOrderer")
public class ExaminationOrderer {

    @Inject
    private ExaminationOrderQueueBean orderQueueBean;
    
    @WebMethod
    public Examination takeOrder() {
        return orderQueueBean.takeOrder();
    }
}
