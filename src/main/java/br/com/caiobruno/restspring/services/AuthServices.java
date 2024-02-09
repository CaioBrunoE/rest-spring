package br.com.caiobruno.restspring.services;

import br.com.caiobruno.restspring.data.vo.v1.security.AccountCredentialVO;
import br.com.caiobruno.restspring.data.vo.v1.security.TokenVO;
import br.com.caiobruno.restspring.reposittories.UserRepository;
import br.com.caiobruno.restspring.security.jwt.JwtTokenProvinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthServices {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvinder tokenProvinder;

    private UserRepository repository;


    @SuppressWarnings("rawtypes")
    public ResponseEntity sigbin(AccountCredentialVO data) {
        try {
            var username = data.getUserName();
            var password = data.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            var user = repository.findByUserName(username);

            var tokenResponse = new TokenVO();

            if (user != null) {
                tokenResponse = tokenProvinder.createAccessToken(username, user.getRoles());
            } else {
                throw new UsernameNotFoundException("Username " + username + " not found!");
            }

            return ResponseEntity.ok(tokenResponse);

        } catch (Exception e) {

            throw new BadCredentialsException("Invalid username /password supplied");
        }
    }
}
