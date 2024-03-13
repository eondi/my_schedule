package com.sprata.my_schedule.controller;

import com.sprata.my_schedule.dto.*;
import com.sprata.my_schedule.responsentity.Message;
import com.sprata.my_schedule.responsentity.StatusEnum;
import com.sprata.my_schedule.security.UserDetailsImpl;
import com.sprata.my_schedule.service.CommentService;
import com.sprata.my_schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    Message message = new Message();
    HttpHeaders headers= new HttpHeaders();

    //댓글 작성
    @PostMapping("/create/{number}")
    public ResponseEntity<Message> createComment(@PathVariable Integer number, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        System.out.println("here");
        return new ResponseEntity<>(commentService.createComment(number, requestDto, userDetails.getUser()), HttpStatus.OK);
    }

    //댓글 수정
    @PutMapping("/update/{number}")
    public ResponseEntity<Message>  updateComment(@PathVariable Long number, @RequestBody CommentRequestDto requestDto,@AuthenticationPrincipal UserDetailsImpl userDetails) throws IllegalAccessException {

        return commentService.updateComment(number, requestDto, userDetails.getUser());

    }


    //댓글 삭제
    @DeleteMapping("/delete/{number}")
    public ResponseEntity<Message> deleteComment(@PathVariable Long number) throws IllegalAccessException {

        return commentService.deleteComment(number);

    }

    // 자신이 쓴 댓글 전체 검색
    @GetMapping
    public ResponseEntity<Message> getAllComment(@RequestBody PageDTO pageDTO, @AuthenticationPrincipal UserDetailsImpl userDetails) throws IllegalAccessException {

        Page<CommentResponseDto> page_result =  commentService.getAllComment(pageDTO, userDetails.getUser());

        message.setStatus(StatusEnum.OK);
        message.setMessage("댓글 전체 조회를 성공했습니다.");
        message.setData(page_result);

        return  new ResponseEntity<>(message, HttpStatus.OK );

    }
}
