package com.sprata.my_schedule.service;

import com.sprata.my_schedule.dto.CommentRequestDto;
import com.sprata.my_schedule.dto.CommentResponseDto;
import com.sprata.my_schedule.dto.PageDTO;
import com.sprata.my_schedule.entity.User;
import com.sprata.my_schedule.responsentity.Message;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    /**
     * 댓글 생성
     * @param requestDto 댓글 생성 요청정보
     * @param user 댓글 생성 요청자
     * @return 댓글 생성 결과
     */
    public Message createComment(Integer number, CommentRequestDto requestDto, User user);

    /**
     * 댓글 수정
     * @param requestDto 댓글 수정 요청정보
     * @param user 댓글 수정 요청자
     * @return 댓글 수정 결과
     */
    public ResponseEntity<Message> updateComment(Long number, CommentRequestDto requestDto, User user);

    /**
     * 댓글 삭제
     * @param number 삭제 번호 댓글
     * @return 댓글 수정 결과
     */
    public ResponseEntity<Message> deleteComment(Long number);

    /**
     * 댓글 전체 조회 (자신)
     * @param user 전체 댓글 조회 요청자
     * @return 댓글 수정 결과
     */
    public Page<CommentResponseDto> getAllComment(PageDTO pageDTO, User user);
}
