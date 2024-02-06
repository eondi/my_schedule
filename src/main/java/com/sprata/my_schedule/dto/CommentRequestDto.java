package com.sprata.my_schedule.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentRequestDto {
    @NotNull(message = "필수 입니다")
    private String text;
}
