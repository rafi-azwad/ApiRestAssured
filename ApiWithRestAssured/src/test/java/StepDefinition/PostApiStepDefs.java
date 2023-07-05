package StepDefinition;

import com.google.gson.Gson;
import core.ApiCall;
import core.FileHandleHelper;
import core.HeaderFormatHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import repository.remoteRepo.requestRepo.UserRegPostReqModel;
import repository.remoteRepo.responseRepo.UserRegPostResponseModel;

import static core.CoreConstrainHelper.base_url;
import static core.FilePathHelper.postApiPath;

public class PostApiStepDefs {

    private Gson gson = new Gson();

    private String requestModel;

    Response postApiResponse;

    UserRegPostReqModel userRegPostReqModel;

    String url;



    @Given("user has the api {string}")
    public void userHasTheApiPath(String path) {

        url = base_url + path;

    }

    @When("user hit {string} and {string}")
    public void userHitNameAndJob(String name, String job) {

        JSONObject requestBody = new FileHandleHelper().readJsonFile(postApiPath);
        userRegPostReqModel = new Gson().fromJson(requestBody.toJSONString(),UserRegPostReqModel.class);
        userRegPostReqModel.setJob(job);
        userRegPostReqModel.setName(name);
        requestModel = gson.toJson(userRegPostReqModel);

    }

    @And("call the api with body")
    public void callTheApiWithBody() {

        postApiResponse = ApiCall.postCall(HeaderFormatHelper.commonHeaders(),requestModel,url);

        //        *****Assert*****

        int statusCode = postApiResponse.getStatusCode();
        System.out.println('\n' + "The response status is:" + statusCode + '\n');
        Assert.assertEquals(statusCode /*actual value*/, 201 /*expected value*/,
                "Correct status code returned");

//        ******POST Api Response******

        System.out.println("The response in plain text:" + '\n');
        System.out.println(postApiResponse.body().asString());
        System.out.println('\n' +"The response in json format:");
        postApiResponse.body().prettyPrint();

    }

    @Then("it will return created data")
    public void itWillReturnCreatedData() {

        UserRegPostResponseModel userRegPostResponseModel = gson.fromJson(postApiResponse.getBody().asString(), UserRegPostResponseModel.class);
        System.out.println(userRegPostResponseModel.getJob());
        System.out.println(userRegPostResponseModel.getName());
        System.out.println(userRegPostResponseModel.getCreatedAt());
    }
}
