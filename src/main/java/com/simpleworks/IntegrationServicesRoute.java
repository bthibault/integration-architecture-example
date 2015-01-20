package com.simpleworks;

import org.apache.camel.builder.RouteBuilder;

/**
 * @author mixueqiang
 * @since Nov 4, 2014
 */
public class IntegrationServicesRoute extends RouteBuilder {

  @Override
  public void configure() throws Exception {
    from("direct:start-auth")
        .to("jetty://http://clarastream-dev:8000/product?startIndex=0&count=100&masterUserName=trendnation&type=simple");
  }

}
