package StepDefinition;

import com.google.gson.Gson;
import core.ApiCall;
import core.HeaderFormatHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert; //used to validate response status
import repository.remoteRepo.responseRepo.UserGetResponseModel;

import static core.CoreConstrainHelper.base_url;

public class GetStepDefs {

    String url;
    private Response responseGetApi;
    private Gson gson = new Gson();


    @Given("user has the base api")
    public void userHasTheBaseApi() {

        url = base_url + "users";
    }

    @When("user call the {string} and {string}")
    public void userCallTheParameterAndParamsValue(String paraName, String value) {

        url = url + paraName + "=" + value;
        responseGetApi = ApiCall.getCall(HeaderFormatHelper.commonHeaders(), url);

        //        *****Assert*****

        int statusCode = responseGetApi.getStatusCode();
        System.out.println('\n' + "The response status is:" + statusCode + '\n');
        Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/,
                "Correct status code returned");

        //        ******Get Api Response******

        System.out.println("The response in plain text:" + '\n' + responseGetApi.body().asString());
        System.out.println('\n' +"The response in json format:");
        responseGetApi.body().prettyPrint();

    }

    @Then("it will return valid data")
    public void itWillReturnValidData() {

        UserGetResponseModel userGetResponseModel = gson.fromJson(responseGetApi.getBody().asString(), UserGetResponseModel.class);

        System.out.println(userGetResponseModel.getData().get(0).getFirst_name());





    }
}