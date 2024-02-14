package br.com.caiobruno.restspring.controllers;

import br.com.caiobruno.restspring.data.vo.v1.security.AccountCredentialVO;
import br.com.caiobruno.restspring.services.AuthServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication Endpoint")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthServices authServices;

    @SuppressWarnings("rawtypes")
    @Operation(summary = "Authenticate user and returned token")
    @PostMapping(value = "signin")
    public ResponseEntity signin(@RequestBody AccountCredentialVO data) {
        if (checkIsParamIsNotNull(data)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
        }
        var token = authServices.signin(data);

        if (token == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
        }

        return token;
    }

    private static boolean checkIsParamIsNotNull(AccountCredentialVO data) {
        return data == null || data.getUserName() == null || data.getUserName().isBlank() || data.getPassword() == null || data.getPassword().isBlank();
    }
}
