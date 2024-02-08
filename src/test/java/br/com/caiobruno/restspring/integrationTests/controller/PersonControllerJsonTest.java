package br.com.caiobruno.restspring.integrationTests.controller;

import br.com.caiobruno.restspring.AbstractIntegrationTest;
import br.com.caiobruno.restspring.configs.TestConfig;
import br.com.caiobruno.restspring.integrationTests.vo.PersonVO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.DeserializationFeature;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonControllerJsonTest extends AbstractIntegrationTest {


	private static RequestSpecification specification;

	private static ObjectMapper objectMapper;

	private static PersonVO person;

	@BeforeAll
	public  static  void  setUp(){
		objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		person = new PersonVO();
	}

	@Test
	@Order(1)
	public void testCreate() throws IOException {
		mockPerson();
		specification = new RequestSpecBuilder()
				.addHeader(TestConfig.HEADER_PARAM_ORIGIN,TestConfig.ORIGIN_ERUDIO)
				.setBasePath("/api/person/v1")
					.setPort(TestConfig.SERVER_PORT)
					.addFilter(new RequestLoggingFilter(LogDetail.ALL))
					.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.build();

		var content = given().spec(specification)
				.contentType(TestConfig.CONTENT_TYPE_JSON)
				    .body(person)
				    .when()
				    .post()
				.then()
				   .statusCode(200)
				.extract()
				   .body()
				       .asString();

		PersonVO createdPerson = objectMapper.readValue(content , PersonVO.class);

		person = createdPerson;

		assertNotNull(createdPerson);
		assertNotNull(createdPerson.getId());
		assertNotNull(createdPerson.getFirstName());
		assertNotNull(createdPerson.getLastName());
		assertNotNull(createdPerson.getAddress());
		assertNotNull(createdPerson.getGender());


		assertTrue(createdPerson.getId() > 0);



		assertEquals("Richard",createdPerson.getFirstName());
		assertEquals("Stallman",createdPerson.getLastName());
		assertEquals("New York City, New York",createdPerson.getAddress());
		assertEquals("Male",createdPerson.getGender());
	}




	@Test
	@Order(2)
	public void testCreateWhithWhrongOrigin() throws IOException {
		mockPerson();

		specification = new RequestSpecBuilder()
				.addHeader(TestConfig.HEADER_PARAM_ORIGIN,TestConfig.ORIGIN_SEMERU)
				.setBasePath("/api/person/v1")
					.setPort(TestConfig.SERVER_PORT)
					.addFilter(new RequestLoggingFilter(LogDetail.ALL))
					.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.build();

		var content = given().spec(specification)
				.contentType(TestConfig.CONTENT_TYPE_JSON)
				    .body(person)
				    .when()
				    .post()
				.then()
				   .statusCode(200)
				.extract()
				   .body()
				       .asString();


		assertNotNull(content);

		assertEquals("Invalid CORS request",content);
	}

	@Test
	@Order(3)
	public void testFindById() throws IOException {
		mockPerson();
		specification = new RequestSpecBuilder()
				.addHeader(TestConfig.HEADER_PARAM_ORIGIN,TestConfig.ORIGIN_ERUDIO)
				.setBasePath("/api/person/v1")
				.setPort(TestConfig.SERVER_PORT)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.build();

		var content = given().spec(specification)
				.contentType(TestConfig.CONTENT_TYPE_JSON)
				.pathParams("id",person.getId())
					.when()
					.get("{id}")
				.then()
					.statusCode(200)
					.extract()
					.body()
				.asString();

		PersonVO createdPerson = objectMapper.readValue(content , PersonVO.class);

		person = createdPerson;

		assertNotNull(createdPerson);
		assertNotNull(createdPerson.getId());
		assertNotNull(createdPerson.getFirstName());
		assertNotNull(createdPerson.getLastName());
		assertNotNull(createdPerson.getAddress());
		assertNotNull(createdPerson.getGender());


		assertTrue(createdPerson.getId() > 0);



		assertEquals("Richard",createdPerson.getFirstName());
		assertEquals("Stallman",createdPerson.getLastName());
		assertEquals("New York City, New York",createdPerson.getAddress());
		assertEquals("Male",createdPerson.getGender());
	}
	@Test
	@Order(4)
	public void testFindByIdWhitWongOrigin() throws IOException {
		mockPerson();
		specification = new RequestSpecBuilder()
				.addHeader(TestConfig.HEADER_PARAM_ORIGIN,TestConfig.ORIGIN_SEMERU)
				.setBasePath("/api/person/v1")
				.setPort(TestConfig.SERVER_PORT)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.build();

		var content = given().spec(specification)
				.contentType(TestConfig.CONTENT_TYPE_JSON)
				.pathParams("id",person.getId())
				.when()
				.get("{id}")
				.then()
				.statusCode(403)
				.extract()
				.body()
				.asString();


		assertNotNull(content);

		assertEquals("Invalid CORS request",content);
	}


	private void mockPerson() {
		person.setFirstName("Richard");
		person.setLastName("Stallman");
		person.setAddress("New York City, New York");
		person.setGender("Male");
	}

}
