package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;


public class PostsAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "25")
                .get("/posts")
                .then().statusCode(200);
    }

    //1) Verify that total record = 25
    @Test
    public void test001() {
        response.body("per_page", equalTo(25));


    }

    //2)Verify the if the title of id = 2730 is equal to ”Ad ipsa coruscus ipsam eos demitto
    //centum.”

    @Test
    public void test002() {
        response.body("find{it.id == 2730}.title", equalTo("Ad ipsa coruscus ipsam eos demitto centum."));
    }
    //3)Check the single user_id in the Array list (5522)
    @Test
    public void test003(){
        response.body("user_id",hasItem(5522));

    }
    //4)Check the multiple ids in the ArrayList (2693, 2684,2681)
    @Test
    public void test004() {
        response.body("id", hasItems(2665,2714,
                2670));

    }
    @Test
    public void test005(){
       response.body ("find{it.id==2563}.body",equalTo("Decipio spes ars. Cognomen viridis vel. Consectetur vel numquam. Blandior est acerbitas. Somnus comedo coepi. Caterva nemo ante. Urbs sequi tam. Surgo quidem absens. Comparo voro thermae. Studio auris tum. Votum deripio adfero. Varietas ratione dicta. Velociter vulariter ipsum. Inventore demitto creta. Auxilium correptius quaerat. Careo atavus theologus. Stips facere animadverto. Atavus curso audax. Curis voco suppono. Tutis cubo contra."));
    }

        }
