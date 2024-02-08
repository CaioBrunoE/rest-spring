package br.com.caiobruno.restspring.integrationTests.Swegger;

import br.com.caiobruno.restspring.configs.TestConfig;
import br.com.caiobruno.restspring.AbstractIntegrationTest;

import static io.restassured.RestAssured.given;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SweggerIntegrationTest extends AbstractIntegrationTest {

	@Test
	public void shouldDisplaySwaggerUiPage() {
		var content =
		given()
				.basePath("/swagger-ui/index.html")
				.port(TestConfig.SERVER_PORT)
				.when()
				   .get()
				.then()
				   .statusCode(200)
				.extract()
				   .body().asString();
		assertTrue(content.contains("Swagger UI"));
	}
}
