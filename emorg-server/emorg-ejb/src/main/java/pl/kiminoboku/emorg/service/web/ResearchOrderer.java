/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.emorg.service.web;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import pl.kiminoboku.emorg.domain.Research;
import pl.kiminoboku.emorg.service.ResearchOrderQueueService;

/**
 * Web service responsible for giving research orders to examined person's PC
 *
 * @author Radek
 */
@Stateless
@LocalBean
@WebService
public class ResearchOrderer {

    /**
     * Injected research order queue
     */
    @Inject
    private ResearchOrderQueueService researchOrderQueueService;

    /**
     * Returns research order from queue or {@code null} if there isn't any
     *
     * @return
     */
    @WebMethod
    public Research takeOrder() {
        return researchOrderQueueService.takeOrder();
    }
}
