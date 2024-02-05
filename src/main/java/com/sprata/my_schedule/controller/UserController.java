package com.sprata.my_schedule.controller;


import com.sprata.my_schedule.dto.SignupRequestDto;
import com.sprata.my_schedule.dto.UserInfoDto;
import com.sprata.my_schedule.responsentity.Message;
import com.sprata.my_schedule.responsentity.StatusEnum;
import com.sprata.my_schedule.security.UserDetailsImpl;
import com.sprata.my_schedule.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.nio.charset.Charset;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/login-page")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public ResponseEntity<Message> signup(@Valid SignupRequestDto requestDto, BindingResult bindingResult) {
        Message message = new Message();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        // Validation 예외처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(fieldErrors.size() > 0) {
            message.setStatus(StatusEnum.BAD_REQUEST);
            message.setMessage(fieldErrors.get(0).getDefaultMessage());
            return new ResponseEntity<>(message, headers, HttpStatus.BAD_REQUEST);
        }

        try {
            userService.signup(requestDto);
        } catch (Exception e) {
            message.setStatus(StatusEnum.BAD_REQUEST);
            message.setMessage("이미 존재하는 아이디입니다.");

            return new ResponseEntity<>(message, headers, HttpStatus.BAD_REQUEST);
        }


        message.setStatus(StatusEnum.OK);
        message.setMessage("회원가입에 성공했습니다.");

        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }

    // 회원 관련 정보 받기
    @GetMapping("/user-info")
    @ResponseBody
    public UserInfoDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUser().getUsername();


        return new UserInfoDto(username);
    }
}