package com.sprata.my_schedule.service;

import com.sprata.my_schedule.dto.ScheduleRequestDto;
import com.sprata.my_schedule.dto.ScheduleResponseDto;
import com.sprata.my_schedule.dto.ScheduleResponseDto2;
import com.sprata.my_schedule.entity.Schedule;
import com.sprata.my_schedule.entity.User;
import com.sprata.my_schedule.repository.ScheduleRepository;
import com.sprata.my_schedule.responsentity.Message;
import com.sprata.my_schedule.responsentity.StatusEnum;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

@Service

public interface ScheduleService {
    /**
     * 게시글 생성
     * @param requestDto 게시글 생성 요청정보
     * @param user 게시글 생성 요청자
     * @return 게시글 생성 결과
     */
    public ResponseEntity<Message>  createSchedule(ScheduleRequestDto requestDto, User user);

    /**
     * 게시글 전체 조회
     * @return 게시글 전체 조회 결과
     */
    public Message getAllSchedules();


    /**
     * 게시글 선택 조회
     * @return 게시글 선택 조회 결과
     */
    public Message getSchedule(Integer number, User user);


    /**
     * 게시글 수정
     * @return 게시글 선택 조회 결과
     */
    public Message updateSchedule(Integer number, ScheduleRequestDto requestDto, User user);

    /**
     * 게시글 상태 완료
     * @return 게시글 선택 조회 결과
     */
    public Message updateScheduleState(Integer number, User user);
}
