package com.sprata.my_schedule.Exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeParseException;

@ControllerAdvice   // 전역 설정을 위한 annotaion
@RestController
public class CommonControllerAdvice {
    @ExceptionHandler(DateTimeParseException.class)
    public String processValidationError(DateTimeParseException exception) {

        StringBuilder builder = new StringBuilder();
        builder.append("날짜 형식이 올바르지 않습니다 YYYY-mm-dd 형식을 써주세요 ex) 2021-09-09");
        builder.append(" 입력된 값: [");
        builder.append(exception.getParsedString());
        builder.append("]");
        return builder.toString();
    }
}
