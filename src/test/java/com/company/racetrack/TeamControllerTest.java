package com.company.racetrack;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.company.racetrack.controllers.LogCustomService;
import com.company.racetrack.controllers.TeamController;

import com.company.racetrack.log.LogAdditional;
import com.company.racetrack.repositories.TeamRepository;
import com.company.racetrack.security.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TeamController.class/*, secure = false*/)
//@ContextConfiguration(classes={Application.class, SecurityConfig.class})
@Import(SecurityConfig.class)
public class TeamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeamController teamController;

    /*@MockBean
    private TeamRepository teamRepository;

    @MockBean
    private LogCustomService logCustomService;

    @MockBean
    private LogAdditional logAdditional;*/

    @Test
    public void testGetListOfTeams() throws Exception {
        mockMvc.perform(get("/teams"))
                .andExpect(status().isOk())
                .andExpect(view().name("teams"))
                .andExpect(content().string(containsString("Teams of RaceTrack")));
    }
}
