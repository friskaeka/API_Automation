package pages;

import Helper.EndPoint;
import Helper.Utility;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.module.jsv.JsonSchemaValidator;

import java.io.File;
import java.util.List;
import java.util.Objects;

import static Helper.Models.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ApiPage {

    String setUrl;
    Response res;


    public void prepareUrlValidFor(String url ){
        switch (url){
            case "GET_LIST_USER":
                setUrl = EndPoint.GET_LIST_USER;
                break;
            case "CREATE_NEW_USER":
                setUrl = EndPoint.CREATE_NEW_USER;
                break;
            case "DELETE_USER":
                setUrl = EndPoint.DELETE_USER;
                break;
            default:
                System.out.println("input url yang benar");
        }
    }

    public void hitApiGetListUser(){
        res = getListUser(setUrl);
        System.out.println(res.getBody().asString());
    }

    public void hitApiPostCreateUser(){
        res = PostCreateUser(setUrl);
    }

    public void hitApiPostCreateWrongNewUser(){
        res = WrongPostCreateUser(setUrl);
    }

    public void validationStatusCodeIsEqual(int status_code){
        assertThat(res.statusCode()).isEqualTo(status_code);
    }

    public void validationResponseBodyGetListUser(){
        List<Object> id = res.jsonPath().getList("id");
        List<Object> name = res.jsonPath().getList("name");
        List<Object> email = res.jsonPath().getList("email");
        List<Object> gender = res.jsonPath().getList("gender");
        List<Object> status = res.jsonPath().getList("status");

        assertThat(id.get(0)).isNotNull();
        assertThat(name.get(0)).isNotNull();
        assertThat(email.get(0)).isNotNull();
        assertThat(gender.get(0)).isIn("female", "male");
        assertThat(status.get(0)).isIn("active", "inactive");
    }

    public void validationResponseJsonWithJSONschema(String filename){
        File JSONFile = Utility.getJsonSchemaFile(filename);
        res.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(JSONFile));
    }

    public void validationResponseBodyPostCreateNewUser(){
        JsonPath jsonPathEvaluator = res.jsonPath();
        Integer id = jsonPathEvaluator.get("id");
        String name = jsonPathEvaluator.get("name");
        String email = jsonPathEvaluator.get("email");
        String gender = jsonPathEvaluator.get("gender");
        String status = jsonPathEvaluator.getString("status");

        assertThat(id).isNotNull();
        assertThat(name).isNotNull();
        assertThat(email).isNotNull();
        assertThat(gender).isIn("female", "male");
        assertThat(status).isIn("active","inactive");

    }
}
