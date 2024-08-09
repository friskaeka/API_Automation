package Helper;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import org.json.JSONObject;

import static Helper.Utility.generateRandomEmail;


public class Models {
    private static RequestSpecification request;

    public static void setupHeaders(){
        request = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Bearer 3dd65d663e13c669085fb30613d83761d64be5b230a6f6e5f98f4c0f29d02885");
    }

    public static Response getListUser(String endpoint){
        setupHeaders();
        return request.when().get(endpoint);
    }

    public static Response WrongPostCreateUser(String endpoint){
        String name = " ";
        String gender = "female";
        String email = generateRandomEmail();
        String status = "active";
        JSONObject payload = new JSONObject();
        payload.put("name", name);
        payload.put("gender", gender);
        payload.put("email", email);
        payload.put("status", status);

        System.out.println("Payload yang dikirim: " + payload.toString());

        // Setup headers
        setupHeaders();

        // Mengirim request dan menyimpan respons
        Response res = request.body(payload.toString()).when().post(endpoint);

        // Mencetak respons dari API
        System.out.println("Respons dari API: " + res.getBody().asString());

        // Mengembalikan respons
        return res;
    }


    public static Response PostCreateUser(String endpoint){
        String name = "Friska Eka";
        String gender = "female";
        String email = generateRandomEmail();
        String status = "active";
        JSONObject payload = new JSONObject();
        payload.put("name", name);
        payload.put("gender", gender);
        payload.put("email", email);
        payload.put("status", status);

        setupHeaders();
        return request.body(payload.toString()).when().post(endpoint);
    }
}
