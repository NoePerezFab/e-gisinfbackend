
package com.gisnet.fileManager.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gisnet.fileManager.domain.ErrorHandler;
import com.gisnet.fileManager.domain.User;
import com.gisnet.fileManager.service.UserRepositoryService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = { "http://192.168.254.186:3000" }, allowedHeaders = "*", allowCredentials = "true")
public class AuthController {
    private final String HEADER = "Authorization";
	private final String PREFIX = "Bearer ";
	private final String SECRET = "mySecretKey";
    private ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private UserRepositoryService userService;
    
    
     @PostMapping(value="/login",produces="application/json")
    public String login(@RequestBody User user, HttpSession sesion) throws JsonProcessingException{
        User userFind = userService.findByUsername(user).get();
        if(userFind != null && userFind.getPassword().compareTo(user.getPassword()) == 0){
            String token = getJWTToken(user.getUsername());
            userFind.setToken(token);
            userFind.setPassword("");
            return mapper.writeValueAsString(userFind);
        }
        return mapper.writeValueAsString(new ErrorHandler(401, "Unautorized"));
        
    }
    @GetMapping(value="/loged",produces="application/json")
    public String loged(HttpServletRequest request) throws JsonProcessingException{
        String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
    Claims claims = Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(jwtToken).getBody();
        if (claims.get("authorities") != null) {
            return mapper.writeValueAsString(new ErrorHandler(200, "Ok"));
        }
        return mapper.writeValueAsString(new ErrorHandler(401, "Unautorized"));
    }
    private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		      List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_ADMIN");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 60000000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
}
