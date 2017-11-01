package is.nord;
import is.nord.config.SecurityConfig;
import is.nord.controller.InformationController;
import is.nord.controller.NewsController;
import is.nord.service.AdService;
import is.nord.service.InfoBoardService;
import is.nord.service.InfoNordService;
import is.nord.service.NewsService;
import is.nord.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@RunWith(SpringRunner.class)
/**
 * Aðeins veflagið er keyrt upp en ekki allur "context"-inn
 */
@WebMvcTest(controllers={InformationController.class, SecurityConfig.class})
public class WebLayerTest {

    //Þjónninn ekki keyrður upp
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    InfoNordService infoService;
    @MockBean
    InfoBoardService boardService;
    @MockBean
    UserService userService;
    
    /**
     * aðferð til að athuga hvort virkar að senda HttpRequest á ...
     * og fá til baka ....html síðuna
     **/
    @Test
    public void nyrKennariSkilarKarl() throws Exception{
        this.mockMvc.perform(get("/infoNord/add"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("forsíðu")));
    }
}
