package com.example.seminar.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class MemberSaverTest {

    @Autowired
    private MemberSaver memberSaver;
}
