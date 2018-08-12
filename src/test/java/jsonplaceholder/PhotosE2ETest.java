package jsonplaceholder;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.*;

import java.util.HashMap;
import java.util.Map;

public class PhotosE2ETest {

    @BeforeClass
    public static void setup() {
        String baseHost = System.getProperty("server.host");
        if(baseHost==null){
            baseHost = "https://jsonplaceholder.typicode.com";
        }
        RestAssured.baseURI = baseHost;
    }


    @Test
    public void testPostResource() {
        //    POST /photos
        Map<String, Object>  jsonAsMap = new HashMap<>();
        jsonAsMap.put("albumId", "1");
        jsonAsMap.put("id", "5001");
        jsonAsMap.put("title", "test title");
        jsonAsMap.put("url", "https://placehold.it/600/32c052");
        jsonAsMap.put("thumbnailUrl", "https://placehold.it/150/32c052");

        given().
                contentType("application/json").
                body(jsonAsMap).
        when().
                post("/photos").
        then().
                statusCode(201);
    }

    @Test
    public void testPutReplaceResource() {
        //    PUT  /photos/1
        Map<String, Object>  jsonAsMap = new HashMap<>();
        jsonAsMap.put("albumId", "1");
        jsonAsMap.put("id", "1");
        jsonAsMap.put("title", "replaced");
        jsonAsMap.put("url", "https://placehold.it/600/32c052");
        jsonAsMap.put("thumbnailUrl", "https://placehold.it/150/32c052");

        given().
                contentType("application/json").
                body(jsonAsMap).
        when().
                put("/photos/1").
        then().
                statusCode(200);
    }

    @Test
    public void testPatchResource() {
        //    PATCH    /photos/1
        Map<String, Object>  jsonAsMap = new HashMap<>();
        jsonAsMap.put("albumId", "1");
        jsonAsMap.put("id", "1");
        jsonAsMap.put("title", "updated");
        jsonAsMap.put("url", "https://placehold.it/600/32c052");
        jsonAsMap.put("thumbnailUrl", "https://placehold.it/150/32c052");

        given().
                contentType("application/json").
                body(jsonAsMap).
        when().
                put("/photos/1").
        then().
                statusCode(200);
    }

    @Test
    public void testGetAll() {
        //    GET  /photos
        given().
        when().
                get("/photos").
        then().
                statusCode(200).
        and().
                body("size()", is(5000));
    }

    @Test
    public void testGetStatus() {
        //    GET  /photos/1
        given().
        when().
                get("/photos/1").
        then().
                statusCode(200);
    }

    @Test
    public void testGetValues() {
        //    GET  /photos/1/title
        given().
        when().
                get("/photos/1").
        then().
                body("albumId", equalTo(1)).
                and().
                body("id", equalTo(1)).
                and().
                body("title", equalTo("accusamus beatae ad facilis cum similique qui sunt")).
                and().
                body("url", equalTo("http://placehold.it/600/92c952")).
                and().
                body("url", equalTo("http://placehold.it/600/92c952"));
    }


    @Test
    public void testGetPhotosInAlbum() {
        //    GET  /photos?albumId=1
        given().
        when().
                get("/photos?albumId=1").
        then().
                statusCode(200).
                and().
                body("size()", is(50));
    }

    @Test
    public void testGetAlbumNested() {
        //    GET  /albums/1/photos
        given().
        when().
                get("/albums/1/photos").
        then().statusCode(200).
                and().
                body("size()", is(50));
    }

    @Test
    public void testDeleteResource() {
        //    DELETE   /posts/1
        given().
        when().
                delete("/photos/1").
        then().
                statusCode(200);
    }
}