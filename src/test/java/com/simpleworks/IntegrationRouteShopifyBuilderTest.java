package com.simpleworks;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * @author mixueqiang
 * @since Nov 4, 2014
 */
public class IntegrationRouteShopifyBuilderTest {
  private static final Log LOG = LogFactory.getLog(IntegrationRouteShopifyBuilderTest.class);

  @Test
  public void testIntegrate() throws Exception {
    CamelContext context = new DefaultCamelContext();
    context.addRoutes(new IntegrationRouteToShopifyBuilder());
    context.start();

    Map<String, Object> headers = new HashMap<String, Object>();

    ProducerTemplate template = context.createProducerTemplate();
    // pass in null body for get request, and specify return type
    String response = template.requestBodyAndHeaders("direct:start-auth", "", headers, String.class);
    LOG.info("Get response: " + response);
  }

}
