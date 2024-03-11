package com.sprata.my_schedule.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.sprata.my_schedule.entity.User;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Test
    @DisplayName("사용자 추가")
    void addUserTest() {
        // given
        User user =new User("name", "pw");

        // when
        User savedUser = userRepository.save(user);

        // then
        assertEquals(savedUser.getUsername(),user.getUsername());
        assertEquals(savedUser.getPassword(),user.getPassword());
    }

    @Test
    @DisplayName("userName 검색 테스트")
    void findByUsernameTest() {
        // given
        User user = new User("name", "pw");
        User savedUser = userRepository.save(user);

        // when
        User findUser = userRepository.findByUsername(savedUser.getUsername()).orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다. "));

        // then
        assertEquals(savedUser.getUsername(),findUser.getUsername());
        assertEquals(savedUser.getPassword(),findUser.getPassword());
    }


}