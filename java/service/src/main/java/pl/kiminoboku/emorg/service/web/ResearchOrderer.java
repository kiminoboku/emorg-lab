/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.kiminoboku.emorg.service.web;

import org.restlet.ext.jaxb.JaxbRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import pl.kiminoboku.emorg.service.ServiceFactory;

/**
 *
 * @author Radek
 */
public class ResearchOrderer extends ServerResource {
    @Get
    public Representation doGet() {
        return new JaxbRepresentation<>(ServiceFactory.getResearchOrderQueueService().takeOrder());
    }
}
