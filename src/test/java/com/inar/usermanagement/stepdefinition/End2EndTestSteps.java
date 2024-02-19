package com.inar.usermanagement.stepdefinition;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inar.reqres.user.management.pojo.Request.Add2CartReq;
import com.inar.reqres.user.management.pojo.Request.CreateANewOrder;
import com.inar.reqres.user.management.pojo.Request.Register;
import com.inar.reqres.user.management.pojo.Response.GetAllProductsRes;
import com.inar.usermanagement.context.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.sl.In;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import lombok.RequiredArgsConstructor;
import org.junit.Assert;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class End2EndTestSteps extends BaseSteps {

    private final TestContext testContext;

    ObjectMapper mapper = new ObjectMapper();

    String token;
    String cartId;
    List<Integer> ids;
    String itemId;
    String orderId;

    @Given("Register new a client with {string} and {string}")
    public String registerNewAClientWithAnd(String clientName, String clientEmail) {

        request = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .build();

        Register body = new Register();
        body.setClientName(clientName);
        body.setClientEmail(clientEmail);

        response = RestAssured.given()
                .spec(request)
                .contentType("application/json")
                .body(body)
                .when()
                .post("/api-clients");

        testContext.setResponse(response);

        token = response.jsonPath().getString("accessToken");
        return token;
    }


    @When("Get all products")
    public void getAllProducts() {

        response = RestAssured.given()
                .spec(request)
                .when()
                .get("/products");

        testContext.setResponse(response);
    }

    @And("the response should have correct products")
    public void theResponseShouldHaveCorrectProducts() throws IOException {

        List<GetAllProductsRes> responseData =
                mapper.readValue(
                        response.asString(),
                        new TypeReference<>() {
                        });


        File file = new File("src/test/resources/testData/GetAllProducts.json");
        List<GetAllProductsRes> testData = mapper.readValue(
                file,
                new TypeReference<>() {
                });

        testData.forEach(
                item -> {
                    Assert.assertEquals(
                            item.getId(),
                            responseData.get(testData.indexOf(item)).getId()
                    );
                    Assert.assertEquals(
                            item.getName(),
                            responseData.get(testData.indexOf(item)).getName()
                    );
                    Assert.assertEquals(
                            item.getCategory(),
                            responseData.get(testData.indexOf(item)).getCategory()
                    );
                    Assert.assertEquals(
                            item.getInStock(),
                            responseData.get(testData.indexOf(item)).getInStock()
                    );

                });


    }

    @And("Create a new cart")
    public void createANewCart() {

        response = RestAssured.given()
                .spec(request)
                .when()
                .post("/carts");

        testContext.setResponse(response);
        cartId = response.jsonPath().getString("cartId");
    }

    @And("Add three items {int}, {int}, {int} to Cart")
    public void addThreeItemsToCart(int item01, int item02, int item03) {

        ids = List.of(item01, item02, item03);


        ids.forEach(
                id -> {
                    Add2CartReq body = new Add2CartReq(id);

                    response = RestAssured.given()
                            .spec(request)
                            .contentType("application/json")
                            .body(body)
                            .when()
                            .post("/carts/" + cartId + "/items");

                }
        );

        testContext.setResponse(response);
        itemId = response.jsonPath().getString("itemId");

    }

    @Then("Check if the products you added to the cart are correct")
    public void checkIfTheProductsYouAddedToTheCartAreCorrect() {

        response = RestAssured.given()
                .spec(request)
                .when()
                .get("/carts/" + cartId + "/items");
        
        testContext.setResponse(response);
        List<Integer> responseData = response.jsonPath().getList("productId");
        
        responseData.forEach(
                item -> {
                    Assert.assertTrue(ids.contains(item));
                }
        );
    }

    @And("Let's cancel purchasing a product and remove it from the cart")
    public void letSCancelPurchasingAProductAndRemoveItFromTheCart() {
        response = RestAssured.given()
                .spec(request)
                .when()
                .delete("/carts/" + cartId + "/items/" + itemId);

        testContext.setResponse(response);
    }


    @Then("Check if the product you removed from the cart are correct")
    public void checkIfTheProductYouRemovedFromTheCartAreCorrect() {

        response = RestAssured.given()
                .spec(request)
                .when()
                .get("/carts/" + cartId);

        testContext.setResponse(response);
        List<Integer> responseData = response.jsonPath().getList("items.id");
        Assert.assertFalse(responseData.contains(itemId));
    }

    @And("Create an new order with name {string}")
    public void createAnNewOrderWithName(String custumerName) {

        CreateANewOrder body = new CreateANewOrder(cartId, custumerName);

        response = RestAssured.given()
                .spec(request)
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(body)
                .when()
                .post("/orders");

        testContext.setResponse(response);
        orderId = response.jsonPath().getString("orderId");
    }

    @Then("Check the order {string} have created")
    public void checkTheOrderHaveCreated(String customerName) {

        response = RestAssured.given()
                .spec(request)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/orders/" + orderId);

        testContext.setResponse(response);
        Assert.assertEquals(response.jsonPath().getString("customerName"), customerName);
    }

    @And("Delete an order")
    public void deleteAnOrder() {

            response = RestAssured.given()
                    .spec(request)
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .delete("/orders/" + orderId);

            testContext.setResponse(response);
    }
}
