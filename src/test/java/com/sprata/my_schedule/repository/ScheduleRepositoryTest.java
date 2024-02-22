package com.sprata.my_schedule.repository;

import com.sprata.my_schedule.dto.ScheduleRequestDto;
import com.sprata.my_schedule.entity.Schedule;
import com.sprata.my_schedule.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ScheduleRepositoryTest {

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Test
    @DisplayName("일정 추가")
    void addScheduleTest() {
        // given
        User user =new User("name", "pw");
        ScheduleRequestDto scheduleRequestDto = new ScheduleRequestDto("testTitle","testText");
        Schedule schedule = new Schedule(scheduleRequestDto, user);

        // when
        Schedule savedSchedule = scheduleRepository.save(schedule);

        // then
        assertEquals(savedSchedule.getUser().getUsername(),user.getUsername());
        assertEquals(savedSchedule.getUser().getPassword(),user.getPassword());
        assertEquals(savedSchedule.getText(),schedule.getText());
        assertEquals(savedSchedule.getTitle(),schedule.getTitle());
    }

}