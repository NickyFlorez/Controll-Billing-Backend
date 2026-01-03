package com.nicky.controlBilling.domain.use_case.user;

import com.nicky.controlBilling.domain.exceptions.PasswordDontMatchException;
import com.nicky.controlBilling.domain.exceptions.UserNotFoundException;
import com.nicky.controlBilling.domain.model.User;
import com.nicky.controlBilling.domain.port.authentication.TokenPort;
import com.nicky.controlBilling.domain.port.user.UserPort;

public class LoginUserUseCase {

    private final UserPort userPort;
    private final TokenPort tokenPort;

    public LoginUserUseCase(UserPort userPort, TokenPort tokenPort) {
        this.userPort = userPort;
        this.tokenPort = tokenPort;
    }

    public String execute(String email, String password) {
        User userFounded = this.userPort.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found"));
        if(!userFounded.password().equals(password)){
            throw new PasswordDontMatchException("Invalid credentials");
        }
        return tokenPort.generateToken(userFounded.email(), userFounded.role().toString());
    }
}
