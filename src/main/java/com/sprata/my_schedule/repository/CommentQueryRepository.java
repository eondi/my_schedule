package com.sprata.my_schedule.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sprata.my_schedule.entity.Comment;
import com.sprata.my_schedule.entity.QComment;
import com.sprata.my_schedule.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class CommentQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;


    public List<Comment> serchAll(User user) {

        QComment qComment = new QComment("c");
        List<Comment> comment_list = jpaQueryFactory.selectFrom(qComment).where(qComment.user.id.eq(user.getId())).orderBy(qComment.number.asc()).fetch();
        return  comment_list;
    }
}
