package com.eventvisualizer.app;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Priority(Priorities.HEADER_DECORATOR)
public class CustomCorsFilter implements ContainerRequestFilter, ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if (isPreflightRequest(requestContext)) {
			requestContext.abortWith(Response.ok().build());
		}
	}

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		String origin = requestContext.getHeaderString("Origin");
		if (origin != null) {
			responseContext.getHeaders().add("Access-Control-Allow-Origin", origin);
			responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
			responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
			responseContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
			responseContext.getHeaders().add("Access-Control-Max-Age", "1209600");
		}
	}

	private boolean isPreflightRequest(ContainerRequestContext requestContext) {
		return requestContext.getMethod().equalsIgnoreCase("OPTIONS") &&
				requestContext.getHeaderString("Origin") != null &&
				requestContext.getHeaderString("Access-Control-Request-Method") != null;
	}
}