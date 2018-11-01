package com.happytown.dataproviders.database;

import com.happytown.core.entities.Habitant;
import com.happytown.fixtures.HabitantJpaFixture;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class HabitantDatabaseProviderTest {

    @InjectMocks
    HabitantDatabaseProvider habitantDatabaseProvider;

    @Mock
    HabitantJpaRepository habitantJpaRepository;

    @Test
    void getAll_shouldReturnHabitantsFromRepository() {
        // Given
        HabitantJpa habitantJpa = HabitantJpaFixture.aHabitantJpaSansCadeau();
        List<HabitantJpa> habitantsJpas = Lists.newArrayList(habitantJpa);
        BDDMockito.doReturn(habitantsJpas).when(habitantJpaRepository).findAll();

        // When
        List<Habitant> results = habitantDatabaseProvider.getAll();

        // Then
        Habitant habitant = results.get(0);
        assertThat(habitant).isEqualToComparingFieldByField(habitantJpa);
    }

    @Test
    void getEligiblesCadeaux_shouldReturnHabitantsFromRepository() {
        // Given
        HabitantJpa habitantJpa = HabitantJpaFixture.aHabitantJpaSansCadeau();
        List<HabitantJpa> habitantsJpas = Lists.newArrayList(habitantJpa);
        LocalDate dateArriveeCommune = LocalDate.now().minusYears(1);
        BDDMockito.doReturn(habitantsJpas)
                .when(habitantJpaRepository)
                .findByDateArriveeCommuneLessThanEqualAndCadeauOffertIsNullAndDateAttributionCadeauIsNullOrderByDateArriveeCommune(dateArriveeCommune);

        // When
        List<Habitant> results = habitantDatabaseProvider.getEligiblesCadeaux(dateArriveeCommune);

        // Then
        Habitant habitant = results.get(0);
        assertThat(habitant).isEqualToComparingFieldByField(habitantJpa);
    }
}