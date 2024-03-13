package com.sprata.my_schedule.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sprata.my_schedule.entity.Comment;
import com.sprata.my_schedule.entity.QComment;
import com.sprata.my_schedule.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class CommentQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;


    public Page<Comment> serchAll(User user, Pageable pageable) {

        QComment qComment = new QComment("c");
        List<Comment> comment_list = jpaQueryFactory.selectFrom(qComment).where(qComment.user.id.eq(user.getId()))
                .offset(pageable.getOffset()).limit(pageable.getPageSize())
                .orderBy(qComment.number.asc()).fetch();

        long totalSize = jpaQueryFactory.select(qComment.count()).from(qComment).where(qComment.user.id.eq(user.getId())).fetchOne();

        return PageableExecutionUtils.getPage(comment_list, pageable, () -> totalSize);
    }
}
