package com.example.seminar.domain;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SoptTest {

    @Test
    @DisplayName("등록되는 SOPT회원은 현재 기수여야 한다.")
    void sopt() {
        short CURRENT_GENERATION = (short) 34;

        SOPT sopt = SOPT.builder()
                .part(Part.SERVER)
                .build();
      // then
        Assertions.assertThat(sopt.getGeneration()).isEqualTo(CURRENT_GENERATION);

    }
}
