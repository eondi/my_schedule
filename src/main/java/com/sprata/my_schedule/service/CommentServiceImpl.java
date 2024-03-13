package com.sprata.my_schedule.service;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sprata.my_schedule.dto.CommentRequestDto;
import com.sprata.my_schedule.dto.CommentResponseDto;
import com.sprata.my_schedule.entity.Comment;
import com.sprata.my_schedule.entity.QComment;
import com.sprata.my_schedule.entity.Schedule;
import com.sprata.my_schedule.entity.User;
import com.sprata.my_schedule.repository.CommentQueryRepository;
import com.sprata.my_schedule.repository.CommentRepository;
import com.sprata.my_schedule.repository.ScheduleRepository;
import com.sprata.my_schedule.responsentity.Message;
import com.sprata.my_schedule.responsentity.StatusEnum;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.provider.QueryComment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements  CommentService{
    private  final ScheduleRepository scheduleRepository;
    private  final CommentRepository  commentRepository;
    private  final CommentQueryRepository  commentQueryRepository;


    @Override
    public Message createComment(Integer number, CommentRequestDto requestDto, User user) {

        Message message = new Message();
        HttpHeaders headers= new HttpHeaders();

        Schedule schedule = scheduleRepository.findById(number).orElseThrow(() -> new IllegalArgumentException("선택한 할일이 존재하지 않습니다. "));


//        if(!schedule.getUser().getUsername().equals(user.getUsername())){
//            message.setStatus(StatusEnum.NOT_FOUND);
//            message.setMessage("선택한 할일 존재하지 않습니다 ");
//            message.setData(null);
//            return message;
//        }

        Comment comment = commentRepository.save(new Comment(requestDto, user, schedule));

        message.setStatus(StatusEnum.OK);
        message.setMessage("댓글작성 완료.");
        message.setData(new CommentResponseDto(comment));

        return message;
    }

    @Override
    @Transactional
    public ResponseEntity<Message> updateComment(Long number, CommentRequestDto requestDto, User user) {
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

    @Override
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


    @Override
    public ResponseEntity<Message> getAllComment(User user) {
        Message message = new Message();
        HttpHeaders headers= new HttpHeaders();

        List<Comment> comment_list = commentQueryRepository.serchAll(user);



//        JPAQuery query = new JPAQuery(em);
//        QComment qComment = new QComment("c");
//
//        List<Comment> comment_list = query.from(qComment).where(qComment.user.eq(user)).orderBy(qComment.number.asc()).fetch();

        message.setStatus(StatusEnum.OK);
        message.setMessage("선택한 댓글의 삭제 성공했습니다.");
//        message.setData(new CommentResponseDto(comment));

        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
}
