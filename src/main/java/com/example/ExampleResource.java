package com.example;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;

@Path("/hello")
@Slf4j
public class ExampleResource {


    @Inject
    MeterRegistry registry;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        log.info("Request received in path /hello");
        return "Hello from RESTEasy Reactive";
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@PathParam(value = "name") String name) {
        log.info("Request received in path /name/{} ", name);
        registry.counter("greeting_counter", Tags.of("name", name)).increment();
        log.info("Counter incremented");
        return "Hello from RESTEasy Reactive";
    }

}
