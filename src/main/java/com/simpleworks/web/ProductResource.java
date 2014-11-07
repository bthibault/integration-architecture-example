package com.simpleworks.web;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.commons.net.util.Base64;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.simpleworks.IntegrationRouteToShopifyBuilder;

@Path("/product")
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class ProductResource {

	@GET
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String get(@PathParam("id") long id) throws Exception {		
		CamelContext context = new DefaultCamelContext();
	    context.addRoutes(new IntegrationRouteToShopifyBuilder());
	    context.start();

	    //Generate Basic Auth header 
	    Map<String, Object> headers = new HashMap<String, Object>();
	    String authToken = Base64.encodeBase64String("5665bffbe9fb6fadada3c108735a4cd5:ab37048bae217d3381e00785b5996da5".getBytes());
	    headers.put("Authorization", "Basic " + authToken);

	    /*JsonObject product = Json.createObjectBuilder()
	    		.add("products", arg1)
	    		.build();*/
	    ProducerTemplate template = context.createProducerTemplate();
	    // pass in null body for get request, and specify return type
	    String response = template.requestBodyAndHeaders("direct:start-auth", null, headers, String.class);
		
		return new Gson().toJson(response);
	}

}
