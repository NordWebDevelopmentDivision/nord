package is.nord;
import is.nord.config.SecurityConfig;
import is.nord.controller.InformationController;
import is.nord.controller.NewsController;
import is.nord.controller.RegistrationController;
import is.nord.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)

@WebMvcTest(controllers={NewsController.class, SecurityConfig.class})
public class WebMockTest {
    //Þjóninn(Tomcat) ekki keyrður upp
    @Autowired
    private MockMvc mockMvc;

    //Bætum við prófunar (e. Mock) service klasa - kemur frá springframework safninu (fyrir Mockito
    // serstaklega gert fyrir Mockito safnið
    @MockBean
    RegistrationService regService;
    @MockBean
    InfoNordService infoService;
    @MockBean
    InfoBoardService boardService;
    @MockBean
    UserService userService;
    @MockBean
    NewsService newsService;
    @MockBean
    AdService adService;
    @MockBean
    EventBanService eventBanService;

    /*@Test
    public void test1() throws Exception{
        this.mockMvc.perform(get("/"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Nemendafélag")));
    }*/

    @Test
    public void testaLifirTrue() throws Exception {
        // Látum erNafnRett() skila true
        // Notum Mockito í prófanirnar - Mockito er Framework fyrir unit testing í Java
        // http://site.mockito.org/

        // Prófið ætti að takast - prófum sönnu leiðina í if-setningunni
        when(newsService.erALifi()).thenReturn(true);
        this.mockMvc.perform(get("/lifir"))
                .andDo(print())
                .andExpect(status()
                        .isOk())
                .andExpect(content()
                        .string(containsString("Nýr kennari")));

    }
/*
    @Test
    public void testaLifirFalse() throws Exception {

        // Prófið ætti að takast - prófum ósönnu leiðina í if-setningunni
        when(newsService.erALifi()).thenReturn(false);
        this.mockMvc.perform(get("/lifir"))
                .andDo(print())
                .andExpect(status()
                        .isOk())
                .andExpect(content()
                        .string(containsString("Listi yfir kennara")));
    }*/

    /**
     * Prófið ætti að mistakast - prófum ósönnu leiðina erALifi() en berum
     * saman við rangan streng
     * @throws Exception
     */


/*
    @Test
    public void testaLifirFalseMedRongumStreng() throws Exception {

        // Prófið ætti að ekki takast - prófum ósönnu leiðina í if-setningunni
        when(newsService.erALifi()).thenReturn(false);
        this.mockMvc.perform(get("/lifir")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("blabla")));
    }
*/
}

