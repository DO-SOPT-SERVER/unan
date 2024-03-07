package com.example.seminar.repository;


import com.example.seminar.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    @PersistenceContext
    private final EntityManager em;

    @Transactional
    public void remove(Member member) {
        em.remove(member);
    }
}
