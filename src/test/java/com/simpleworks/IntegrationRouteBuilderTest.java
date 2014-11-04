package com.simpleworks;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.Test;

/**
 * @author mixueqiang
 * @since Nov 4, 2014
 */
public class IntegrationRouteBuilderTest {

  @Test
  public void testIntegrate() throws Exception {
    CamelContext context = new DefaultCamelContext();
    context.addRoutes(new IntegrationRouteBuilder());
    context.start();

    Map<String, Object> headers = new HashMap<String, Object>();
    headers.put("Authorization", "Basic 1234567890");

    ProducerTemplate template = context.createProducerTemplate();
    String response = (String) template.requestBodyAndHeaders("direct:start-auth", "", headers);
    System.out.println(response);
  }

}
