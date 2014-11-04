package com.simpleworks;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.restlet.RestletConstants;
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
    headers.put("id", 10000);
    headers.put(RestletConstants.RESTLET_LOGIN, "5665bffbe9fb6fadada3c108735a4cd5");
    headers.put(RestletConstants.RESTLET_PASSWORD, "ab37048bae217d3381e00785b5996da5");

    ProducerTemplate template = context.createProducerTemplate();
    String response = (String) template.requestBodyAndHeaders("direct:start-auth", "", headers);
    System.out.println(response);
  }

}
