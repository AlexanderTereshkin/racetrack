package com.company.racetrack;

import com.company.racetrack.domain.Team;
import com.company.racetrack.repositories.TeamRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TeamRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private TeamRepository teamRepository;

    @Test
    public void whenFindAllTeamsTest() {
        //Given
        Team team_one = new Team();
        team_one.setName("Barselona");
        testEntityManager.persist(team_one);
        testEntityManager.flush();

        Team team_two = new Team();
        team_two.setName("Real");
        testEntityManager.persist(team_two);
        testEntityManager.flush();

        //When
        List<Team> teamList = (List<Team>) teamRepository.findAll();

        //Then
        assertThat(teamList.size()).isEqualTo(5);
        assertThat(teamList.get(3)).isEqualTo(team_one);
        assertThat(teamList.get(4)).isEqualTo(team_two);
    }
}
