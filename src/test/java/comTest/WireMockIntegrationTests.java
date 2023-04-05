package comTest;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;


@WireMockTest(httpPort = 8082)
public class WireMockIntegrationTests {


    @Test
    public void create_stub_for_get_an_employee(){
        stubFor(get("/api/employee")
                .withHeader("Content-Type", containing("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withStatusMessage("ok")
                        .withBody("employee service response"))

        );

        //test code
        //call the endpoint running on port

        TestRestTemplate testRestTemplate = new TestRestTemplate();

        HttpHeaders httpHeaders  = new HttpHeaders();
        httpHeaders.add("Content-Type","application/json");
        HttpEntity<Object> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<String> exchange = testRestTemplate.exchange("http://localhost:8082/api/employee",
                HttpMethod.GET, httpEntity, String.class);

        Assertions.assertThat(exchange.getBody()).isEqualTo("employee service response");

    }


}
