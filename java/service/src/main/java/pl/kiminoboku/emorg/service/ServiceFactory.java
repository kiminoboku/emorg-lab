/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.emorg.service;

import pl.kiminoboku.emorg.service.persistence.EntityManagerFactoryService;

/**
 * @author Radek
 */
public class ServiceFactory {

    private static final ResearchOrderQueueService RESEARCH_ORDER_QUEUE_SERVICE = new ResearchOrderQueueService();
    private static final EntityManagerFactoryService ENTITY_MANAGER_FACTORY_SERVICE = new EntityManagerFactoryService();
    private static final ResourceManagerService RESOURCE_MANAGER_SERVICE = new ResourceManagerService();

    public static ResearchOrderQueueService getResearchOrderQueueService() {
        return RESEARCH_ORDER_QUEUE_SERVICE;
    }

    public static EntityManagerFactoryService getEntityManagerFactoryService() {
        return ENTITY_MANAGER_FACTORY_SERVICE;
    }

    public static ResourceManagerService getResourceManagerService() {
        return RESOURCE_MANAGER_SERVICE;
    }
}
