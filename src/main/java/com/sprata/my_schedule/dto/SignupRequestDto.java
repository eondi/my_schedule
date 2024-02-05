package com.sprata.my_schedule.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    @NotBlank
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z]).{4,10}", message = "이름은 최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)를 사용하세요.")
    private String username;
    @NotBlank
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z]).{8,15}", message = "비밀번호는 최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9)를 사용하세요.")
    private String password;
    @Email
    @NotBlank
    private String email;
    private boolean admin = false;
    private String adminToken = "";
}