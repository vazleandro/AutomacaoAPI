package RestAssured.AutomacaoApi;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import org.apache.http.HttpStatus;
import org.junit.Test;

import io.restassured.http.ContentType;
public class TestApi {

	String BaseURI = "https://viacep.com.br/ws/13086902/json/";

	@Test
	public void validarStatusCode() {

		given().contentType("application/json").
		when().get(BaseURI)
		.then().log().all()
		.assertThat().statusCode(HttpStatus.SC_OK);
			
	}
	
	@Test
	public void validarBodyResponse() {

		given().contentType("application/json").
		when().get(BaseURI)
		.then()
		.body("cep", equalTo("13086-902"))
		.body("logradouro", is("Rua Doutor Ricardo Benetton Martins"))
		.body("complemento", is("s/n"))
		.body("bairro", is("Polo II de Alta Tecnologia (Campinas)"))
		.body("localidade", is("Campinas"))
		.body("uf", is("SP"))
		.body("unidade", isEmptyOrNullString())
		.body("ibge", is("3509502"))
		.body("gia", is("2446"));		
			
	}

	@Test
	public void validarSchemaJson() {

		given().contentType("application/json").
		when().get(BaseURI)
		.then()
		.assertThat()
		.body(matchesJsonSchemaInClasspath("schema/schemaCep.json"));
			
	}

	@Test
	public void validarResponseEJson() {

		given().contentType("application/json").
		when().get(BaseURI)
		.then()
		.contentType(ContentType.JSON);
			
	}

}