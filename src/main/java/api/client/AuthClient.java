package api.client;

import io.restassured.response.Response;
import api.model.UserRegisterRequest;
import constants.Urls;

import static io.restassured.RestAssured.given;

public class AuthClient {

    private String baseUri;

    public AuthClient() {
        this(Urls.BASE_URI);
    }

    public AuthClient(String baseUri) {
        this.baseUri = baseUri;
    }

    public Response registerUser(UserRegisterRequest userRegisterRequest) {
        return given()
                .baseUri(this.baseUri)
                .header("Content-type", "application/json")
                .body(userRegisterRequest)
                .when()
                .post(Urls.API_SIGNUP);
    }
}