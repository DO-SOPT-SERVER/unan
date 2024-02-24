package com.example.seminar.domain;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;
import lombok.*;

import static jakarta.persistence.EnumType.*;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SOPT {
    private int generation;

    @Enumerated(STRING)
    private Part part;

    @Builder
    public SOPT(int generation, Part part) {
        this.generation = generation;
        this.part = part;
    }
}
