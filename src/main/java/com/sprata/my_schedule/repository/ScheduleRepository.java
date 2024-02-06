package com.sprata.my_schedule.repository;

import com.sprata.my_schedule.entity.Schedule;
import com.sprata.my_schedule.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

}
