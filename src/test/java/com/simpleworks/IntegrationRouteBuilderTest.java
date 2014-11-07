package com.simpleworks;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.util.Base64;
import org.junit.Test;

/**
 * @author mixueqiang
 * @since Nov 4, 2014
 */
public class IntegrationRouteBuilderTest {
  private static final Log LOG = LogFactory.getLog(IntegrationRouteBuilderTest.class);

  @Test
  public void testIntegrate() throws Exception {
    CamelContext context = new DefaultCamelContext();
    context.addRoutes(new IntegrationRouteBuilder());
    context.start();

    Map<String, Object> headers = new HashMap<String, Object>();
    String authToken = Base64.encodeBase64String("trendnation:demo123".getBytes());
    
    headers.put("Authorization", "Basic " + authToken);

    ProducerTemplate template = context.createProducerTemplate();
    // pass in null body for get request, and specify return type
    String response = template.requestBodyAndHeaders("direct:start-auth", null, headers, String.class);
    LOG.info("Get response: " + response);
  }

}
