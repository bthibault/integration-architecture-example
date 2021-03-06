package com.simpleworks;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.stream.*;

public class IntegrationRouteFromRabbitMQBuilder extends RouteBuilder {

    private static String ENDPOINT = "rabbitmq://clarastream-dev/default?username=guest&password=guest&autoDelete=false&queue=SyncRequestDispatcher";

    @Override
    public void configure() throws Exception {
        System.out.println("IntegrationRouteFromRabbitMQBuilder Instantiated to: " + ENDPOINT);
        from(ENDPOINT).to("stream:out");
    }

}
