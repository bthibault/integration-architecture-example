package com.simpleworks;

import org.apache.camel.builder.RouteBuilder;

/**
 * @author mixueqiang
 * @since Nov 4, 2014
 */
public class IntegrationRouteBuilder extends RouteBuilder {

  @Override
  public void configure() throws Exception {
    from("direct:start-auth")
        .to("jetty://http://clarastream-services-brian.herokuapp.com/product?masterUserName=trendnation");
  }

}
