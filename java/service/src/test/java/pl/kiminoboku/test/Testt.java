/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.kiminoboku.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.restlet.Component;
import org.restlet.Server;
import org.restlet.data.Protocol;
import pl.kiminoboku.emorg.domain.operation.ManagePeripheralsOperation;
import pl.kiminoboku.emorg.service.web.ResearchOrderer;

/**
 *
 * @author Radek
 */
public class Testt {
    
    private static Component component;
    
    @BeforeClass
    public static void init() throws Exception {
        component = new Component();
        component.getServers().add(Protocol.HTTP, 8080);
        component.getDefaultHost().attach("/ResearchOrderer", ResearchOrderer.class);
        component.start();
    }
    
    @AfterClass
    public static void shutdown() throws Exception {
        component.stop();
    }
    
    @Test
    public void t() throws IOException {
        Scanner s = new Scanner(System.in);
        s.nextLine();
    }
}
