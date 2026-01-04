package com.nicky.controlBilling.infrastructure.controller.user;

import com.nicky.controlBilling.domain.use_case.user.LoginUserUseCase;
import com.nicky.controlBilling.domain.use_case.user.RegisterUserUseCase;
import com.nicky.controlBilling.infrastructure.controller.dto.request.LoginUserDto;
import com.nicky.controlBilling.infrastructure.controller.dto.request.RegisterUserDto;
import com.nicky.controlBilling.infrastructure.controller.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor

@RestController
@RequestMapping("users")
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUserUseCase loginUserUseCase;

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody RegisterUserDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(UserDto.fromDomain(this.registerUserUseCase.execute(dto.fullName(), dto.email(), dto.password(), dto.role())));

    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginUserDto dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.loginUserUseCase.execute(dto.email(), dto.password()));

    }
}
