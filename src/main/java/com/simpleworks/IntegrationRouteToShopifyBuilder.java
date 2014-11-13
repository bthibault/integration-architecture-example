package com.simpleworks;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jetty.JettyHttpComponent;
import org.apache.camel.util.jsse.KeyStoreParameters;
import org.apache.camel.util.jsse.SSLContextParameters;
import org.apache.camel.util.jsse.TrustManagersParameters;

/**
 * @author mixueqiang
 * @since Nov 4, 2014
 */
public class IntegrationRouteToShopifyBuilder extends RouteBuilder {

  public IntegrationRouteToShopifyBuilder() {
    configureSslForJetty();
  }

  @Override
  public void configure() throws Exception {
    // 5665bffbe9fb6fadada3c108735a4cd5:ab37048bae217d3381e00785b5996da5@
    from("direct:start-auth").to("jetty://https://sample-shop-8.myshopify.com/admin/products.json");
  }

  private void configureSslForJetty() {
    KeyStoreParameters ksp = new KeyStoreParameters();
    ksp.setResource("/home/data/security/TrustStore");
    ksp.setPassword("1070379103");

    TrustManagersParameters tmp = new TrustManagersParameters();
    tmp.setKeyStore(ksp);

    SSLContextParameters scp = new SSLContextParameters();
    scp.setTrustManagers(tmp);

    JettyHttpComponent jettyComponent = getContext().getComponent("jetty", JettyHttpComponent.class);
    jettyComponent.setSslContextParameters(scp);
  }

}
