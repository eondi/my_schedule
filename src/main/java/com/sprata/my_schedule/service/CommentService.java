package com.sprata.my_schedule.service;

import com.sprata.my_schedule.dto.CommentResponseDto;
import com.sprata.my_schedule.dto.ScheduleResponseDto;
import com.sprata.my_schedule.entity.Comment;
import com.sprata.my_schedule.entity.Schedule;
import com.sprata.my_schedule.entity.User;
import com.sprata.my_schedule.repository.CommentRepository;
import com.sprata.my_schedule.repository.ScheduleRepository;
import com.sprata.my_schedule.responsentity.Message;
import com.sprata.my_schedule.responsentity.StatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private  final ScheduleRepository scheduleRepository;
    private  final CommentRepository  commentRepository;
    public ResponseEntity<Message> createComment(Integer number, CommentResponseDto requestDto, User user) {

        Message message = new Message();
        HttpHeaders headers= new HttpHeaders();

        Schedule schedule = scheduleRepository.findById(number).orElseThrow(() -> new IllegalArgumentException("선택한 할일이 존재하지 않습니다. "));


        if(!schedule.getUser().getUsername().equals(user.getUsername())){
            message.setStatus(StatusEnum.NOT_FOUND);
            message.setMessage("선택한 할일 존재하지 않습니다 ");
            message.setData(null);
            return new ResponseEntity<>(message, headers, HttpStatus.NOT_FOUND);
        }

        Comment comment = commentRepository.save(new Comment(requestDto, user, schedule));

        message.setStatus(StatusEnum.OK);
        message.setMessage("댓글작성 완료.");
        message.setData(new CommentResponseDto(comment));

        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }

    public ResponseEntity<Message> updateComment(Long number, CommentResponseDto requestDto, User user) {
        Message message = new Message();
        HttpHeaders headers= new HttpHeaders();

        Comment comment = commentRepository.findById(number).orElseThrow(() -> new IllegalArgumentException("선택한 댓글이 존재하지 않습니다. "));


        if(!comment.getUser().getUsername().equals(user.getUsername())){
            message.setStatus(StatusEnum.NOT_FOUND);
            message.setMessage("선택한 댓글이 존재하지 않습니다 ");
            message.setData(null);
            return new ResponseEntity<>(message, headers, HttpStatus.NOT_FOUND);
        }

        comment.update(requestDto);

        message.setStatus(StatusEnum.OK);
        message.setMessage("선택한 댓글의 수정 성공했습니다.");
        message.setData(new CommentResponseDto(comment));

        return new ResponseEntity<>(message, headers, HttpStatus.OK);

    }

    public ResponseEntity<Message> deleteComment(Long number) {
        Message message = new Message();
        HttpHeaders headers= new HttpHeaders();

        Comment comment = commentRepository.findById(number).orElseThrow(() -> new IllegalArgumentException("선택한 댓글이 존재하지 않습니다. "));

        commentRepository.delete(comment);
        message.setStatus(StatusEnum.OK);
        message.setMessage("선택한 댓글의 삭제 성공했습니다.");
        message.setData(new CommentResponseDto(comment));

        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
}
