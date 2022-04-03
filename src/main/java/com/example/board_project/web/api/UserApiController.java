package com.example.board_project.web.api;

import com.example.board_project.config.auth.PrincipalDetails;
import com.example.board_project.domain.user.User;
import com.example.board_project.service.UserService;
import com.example.board_project.web.dto.CMRespDTO;
import com.example.board_project.web.dto.auth.SignupDto;
import com.example.board_project.web.dto.user.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/api/getUser")
    public ResponseEntity<?> getUser(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        User user = principalDetails.getUser();
        UserInfo userInfo = new UserInfo(user);

        return new ResponseEntity<>(new CMRespDTO<>(1, "회원정보를 불러 왔습니다", userInfo),
            HttpStatus.OK);
    }

    @PostMapping("/api/signup")
    public ResponseEntity<?> signup(@RequestBody SignupDto signupDto) {

        System.out.println(signupDto.toString());
        User userEntity = signupDto.toEntity();
        String rawPassword = userEntity.getPassword();
        String password = bCryptPasswordEncoder.encode(rawPassword);
        userEntity.setPassword(password);
        userEntity.setRoles("ROLE_USER");
        userService.signup(userEntity);

        return new ResponseEntity<>(new CMRespDTO<>(1, "회원가입 성공", null), HttpStatus.OK);
    }

}
