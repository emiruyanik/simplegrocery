package com.inar.usermanagement.stepdefinition;

import com.inar.usermanagement.context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.assertj.core.api.Assertions;

@AllArgsConstructor
public class CommonSteps {

    private final TestContext TEST_CONTEXT;

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int httpStatusCode) {
        Response response = TEST_CONTEXT.getResponse();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(httpStatusCode);
    }


}