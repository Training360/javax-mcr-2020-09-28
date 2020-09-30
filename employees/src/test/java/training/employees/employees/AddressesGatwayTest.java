package training.employees.employees;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.common.ContentTypes;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.util.MimeType;
import org.springframework.util.SocketUtils;
import training.employees.employees.gateway.AddressGateway;
import training.employees.employees.gateway.GatewayProperties;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;


public class AddressesGatwayTest {

    static String host = "127.0.0.1";
    static int port;
    static WireMockServer wireMockServer;

    @BeforeAll
    static void startServer() {
        port = SocketUtils.findAvailableTcpPort();
        wireMockServer = new WireMockServer(wireMockConfig().port(port));
        WireMock.configureFor(host, port);
        wireMockServer.start();
    }

    @AfterAll
    static void stopServer() {
        wireMockServer.stop();
    }

    @BeforeEach
    void resetServer() {
        WireMock.reset();
    }

    @Test
    void testGetAddress() {
        stubFor(get(urlPathEqualTo("/api/addresses"))
                .willReturn(
                        aResponse()
                                .withHeader("Content-Type", "application/json")
                                .withBody("{\"city\": \"Budapest\", \"address\": \"Andrássy u. 2.\"}")));

        var url = String.format("http://%s:%d/api/addresses?name={name}", host, port);

        var properties = new GatewayProperties(url);
        var gateway = new AddressGateway(properties, new RestTemplateBuilder());
        var address = gateway.getAddressByName("John Doe");
        verify(getRequestedFor(urlPathEqualTo("/api/addresses"))
                .withQueryParam("name", equalTo("John Doe")));

        assertThat(address.getCity()).isEqualTo("Budapest");
        assertThat(address.getAddress()).isEqualTo("Andrássy u. 2.");
    }
}
