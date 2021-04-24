package com.moviesandchill.usermanagementservice.service.impl;

import com.moviesandchill.usermanagementservice.dto.achievement.NewAchievementDto;
import com.moviesandchill.usermanagementservice.dto.achievement.UpdateAchievementDto;
import com.moviesandchill.usermanagementservice.exception.achievement.AchievementNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class AchievementServiceTest {

    @Autowired
    private AchievementService achievementService;

    @Test
    public void testUpdateAchievement() throws AchievementNotFoundException {
        var newAchievementDto = createNewAchievementDto("first");
        var updateAchievementDto = createUpdateAchievementDto("second");

        var achievementDto = achievementService.addAchievement(newAchievementDto);
        var achievementDtoId = achievementDto.getAchievementId();
        achievementService.updateAchievement(achievementDtoId, updateAchievementDto);

        assertThat(achievementService.getAchievement(achievementDtoId).getName()).isEqualTo("second name");
    }


    private NewAchievementDto createNewAchievementDto(String name) {
        return NewAchievementDto.builder()
                .name(name + " name")
                .description(name + " description")
                .logoUrl(name + " logo url")
                .build();
    }

    private UpdateAchievementDto createUpdateAchievementDto(String name) {
        return UpdateAchievementDto.builder()
                .name(name + " name")
                .description(name + " description")
                .logoUrl(name + " logo url")
                .build();
    }
}