package com.simpleworks;

import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonObject;

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
public class IntegrationRouteShopifyBuilderTest {
  private static final Log LOG = LogFactory.getLog(IntegrationRouteShopifyBuilderTest.class);

  @Test
  public void testIntegrate() throws Exception {
    CamelContext context = new DefaultCamelContext();
    context.addRoutes(new IntegrationRouteToShopifyBuilder());
    context.start();

    // Generate Basic Auth header
    Map<String, Object> headers = new HashMap<String, Object>();
    String authToken = Base64.encodeBase64String("5665bffbe9fb6fadada3c108735a4cd5:ab37048bae217d3381e00785b5996da5"
        .getBytes());
    headers.put("Authorization", "Basic " + authToken);
    headers.put("Content-Type", "application/json");

    JsonObject product = Json
        .createObjectBuilder()
        .add(
            "product",
            Json.createObjectBuilder().add("title", "example_product")
                .add("body_html", "<strong>An upload from Camel!</strong>").add("vendor", "ClaraStream")
                .add("product_type", "Example").add("tags", "")).build();
    ProducerTemplate template = context.createProducerTemplate();
    System.out.println("JSON request: " + product.toString());

    // pass in null body for get request, and specify return type
    String response = template.requestBodyAndHeaders("direct:start-auth", product.toString(), headers, String.class);
    System.out.println("Get response: " + response);
  }

}
