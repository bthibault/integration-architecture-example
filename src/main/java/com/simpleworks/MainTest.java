package com.simpleworks;


import org.apache.camel.main.Main;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MainTest {
    private Main main;
    private static final Log LOG = LogFactory.getLog(MainTest.class);


    public static void main(String[] args) throws Exception {
        MainTest test = new MainTest();
        test.boot(args);
    }

    public void boot(String[] args) throws Exception {
        // create a Main instance
        main = new Main();
        // enable hangup support so you can press ctrl + c to terminate the JVM
        main.enableHangupSupport();

        // add routes
        main.addRouteBuilder(new IntegrationRouteFromRabbitMQBuilder());

        // run until you terminate the JVM
        System.out.println("Starting Camel. Use ctrl + c to terminate the JVM.\n");

        main.run(args);
    }
}