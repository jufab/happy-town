package com.happytown.service;

import com.happytown.domain.entities.Habitant;
import com.happytown.domain.use_cases.HabitantPort;
import com.happytown.fixtures.HabitantFixture;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class HabitantServiceTest {

    @InjectMocks
    HabitantService habitantService;

    @Mock
    HabitantPort habitantPort;

    @Test
    void getAll_shouldReturnAllHabitants() {
        // Given
        List<Habitant> habitants = Lists.newArrayList(HabitantFixture.aHabitant());
        doReturn(habitants).when(habitantPort).getAll();

        // When
        List<Habitant> results = habitantService.getAll();

        // Then
        assertThat(results).containsExactlyElementsOf(habitants);
    }
}