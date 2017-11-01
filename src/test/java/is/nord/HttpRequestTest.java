package is.nord;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.context.annotation.PropertySource;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    //Fyrir random port
    @LocalServerPort
    private int port;

    //Client klasi til að nota í integration test(samþættingarpróf og notar Http API
    @Autowired
    private TestRestTemplate restTemplate;

    /*
    * Aðferð til að athuga hvort virkar að senda HttpRequest á heimaslóðina og
    * fá til baka síðu sem inniheldur
    *
    * @throws java.lang.Exception
     */

    @Test
    public void heimaSkilarForsidu() throws Exception{
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/", String.class).contains("Nemendafélag"));
    }
}
