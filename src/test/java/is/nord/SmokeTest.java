package is.nord;

import is.nord.controller.InformationController;
import is.nord.controller.LoginController;
import is.nord.controller.NewsController;
import is.nord.controller.RegistrationController;
import is.nord.service.InfoBoardService;
import is.nord.service.InfoNordService;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@PropertySource(value="classpath:main/resources/application.properties", ignoreResourceNotFound=true)
//@SpringBootTest(webEnvironment=NONE)  // Does not provide any servlet environment 
@SpringBootTest
//@TestPropertySource(
//    properties = {
//        "spring.mvc.view.prefix=/WEB-INF/vefvidmot/",
//"spring.mvc.view.suffix=.jsp",
//        "nord.db.driver = org.postgresql.Driver",
//        "nord.db.url= jdbc:postgresql://localhost:5432/HBV",
//"nord.db.username=postgres",
//"nord.db.password=ebba" })

public class SmokeTest {

    @Autowired
    InformationController infoController;
    LoginController loginController;
    RegistrationController regController;
    /**
     * Apferp til að athuga hvort controllerinn hefur verið búinn til
     */

    @Test
    public void contextLoadsInfo(){
        assertThat(InformationController.class).isNotNull();
    }

    @Test
    public void contextLoadsLogin(){
        assertThat(LoginController.class).isNotNull();
    }

    @Test
    public void contextLoadsRegistration(){
        assertThat(RegistrationController.class).isNotNull();
    }
}
