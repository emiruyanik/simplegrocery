package com.inar.usermanagement.stepdefinition;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public abstract class BaseSteps {

    protected Response response; // This needs to be accessible to subclasses
    protected RequestSpecification request;
    protected final String BASE_URI="https://simple-grocery-store-api.glitch.me";

//    protected String authEndpoint;
//
//    protected String getEndpoint;
//
//    protected String postEndpoint;
//
//    protected String deleteEndpoint;
//
//    protected String updateEndpoint;
//
//    public BaseSteps() {
//        baseURI = ConfigManager.getProperty("base.uri");
//        authEndpoint = ConfigManager.getProperty("api.auth.endpoint");
//        getEndpoint = ConfigManager.getProperty("api.get.Product.endpoint");
//        postEndpoint = ConfigManager.getProperty("api.create.cart.endpoint");
//        deleteEndpoint = ConfigManager.getProperty("api.delete.endpoint");
//        updateEndpoint = ConfigManager.getProperty("api.update.endpoint");
//    }

}
