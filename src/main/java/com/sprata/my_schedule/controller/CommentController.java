package com.sprata.my_schedule.controller;

import com.sprata.my_schedule.dto.CommentRequestDto;
import com.sprata.my_schedule.dto.CommentResponseDto;
import com.sprata.my_schedule.dto.ScheduleRequestDto;
import com.sprata.my_schedule.dto.ScheduleResponseDto;
import com.sprata.my_schedule.responsentity.Message;
import com.sprata.my_schedule.security.UserDetailsImpl;
import com.sprata.my_schedule.service.CommentService;
import com.sprata.my_schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    //댓글 작성
    @PostMapping("/create/{number}")
    public ResponseEntity<Message> createProduct(@PathVariable Integer number, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        System.out.println("here");
        return commentService.createComment(number, requestDto, userDetails.getUser());
    }

    //댓글 수정
    @PutMapping("/update/{number}")
    public ResponseEntity<Message>  updateSchedule(@PathVariable Long number, @RequestBody CommentRequestDto requestDto,@AuthenticationPrincipal UserDetailsImpl userDetails) throws IllegalAccessException {

        return commentService.updateComment(number, requestDto, userDetails.getUser());

    }


    //댓글 삭제
    @DeleteMapping("/delete/{number}")
    public ResponseEntity<Message> deleteSchedule(@PathVariable Long number) throws IllegalAccessException {

        return commentService.deleteComment(number);

    }
}
