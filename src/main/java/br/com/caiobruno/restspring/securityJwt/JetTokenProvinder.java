package br.com.caiobruno.restspring.securityJwt;

import br.com.caiobruno.restspring.data.vo.v1.security.TokenVO;
import com.auth0.jwt.algorithms.Algorithm;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;


@Service
public class JetTokenProvinder {


    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey ="secret";

    @Value("${security.jwt.token.expire-length::3600000}")
    private long validityInMilliseconds = 3600000; //1Hora

    @Autowired
    UserDetailsService userDetailsService;

    Algorithm algorithm = null;

    @PostConstruct
    protected void  init(){
        secretKey= Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }
    
    public TokenVO createAccessToken(String usename, List<String> roles){
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        var accessToken = getAccessToken(usename, roles, now, validity);
        var refreshToken = getAccessToken(usename, roles, now);
        
        return new TokenVO(usename, true, now, validity, accessToken, refreshToken);
    }

    private String getAccessToken(String usename, List<String> roles, Date now) {
    }

    private String getAccessToken(String usename, List<String> roles, Date now, Date validity) {
    }
}
