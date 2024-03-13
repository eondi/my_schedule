package com.sprata.my_schedule.repository;

import com.sprata.my_schedule.entity.ApiUseTime;
import com.sprata.my_schedule.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApiUseTimeRepository extends JpaRepository<ApiUseTime, Long> {
    Optional<ApiUseTime> findByUser(User user);
}