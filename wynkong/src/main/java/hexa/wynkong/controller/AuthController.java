package hexa.wynkong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hexa.wynkong.config.JwtProvider;
import hexa.wynkong.dao.impl.ProjectDaoImpl;
import hexa.wynkong.dao.impl.UserLoginRepository;
import hexa.wynkong.entity.UserEntity;
import hexa.wynkong.request.LoginRequest;
import hexa.wynkong.response.AuthResponse;
import hexa.wynkong.service.ProjectService;
import hexa.wynkong.service.exception.UserException;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private ProjectDaoImpl userRepository;
	private JwtProvider  jwtProvider;
	private PasswordEncoder passwordEncoder;
	private ProjectService projectService;
	
	@Autowired UserLoginRepository userLoginRepo;
	
	public AuthController( ProjectDaoImpl userRepository, ProjectService projectService,
			PasswordEncoder passwordEncoder,JwtProvider jwtProvider,UserLoginRepository userLoginRepo) {
		this.userRepository=userRepository;
		this.projectService=projectService;
		this.passwordEncoder=passwordEncoder;
		this.jwtProvider = jwtProvider;
		
	}
	
	
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse>createUserHandler(@RequestBody UserEntity user)throws UserException{
		 

		String email= user.getEmail();
		String password= user.getPassword();
		String firstName= user.getFirstName();
		String lastName= user.getLastName();
		String mobile= user.getMobile();
		
		UserEntity isEmailExist=userRepository.findByEmail(email);
		if(isEmailExist!=null) {
			throw new UserException("Email is already used with another account ");
		}
		UserEntity createdUser=new UserEntity();
		createdUser.setEmail(email);
		createdUser.setPassword(passwordEncoder.encode(password));
		createdUser.setFirstName(firstName);
		createdUser.setLastName(lastName);
		createdUser.setMobile(mobile);
		
		UserEntity savedUser= userLoginRepo.save(createdUser);
		
		Authentication authentication= new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	String token= jwtProvider.generateToken(authentication);

	AuthResponse authResponse= new AuthResponse();
	authResponse.setJwt(token);
	authResponse.setMessage("signup success");
	return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
	}

	@PostMapping("/signin")
	public ResponseEntity<AuthResponse>loginUserHandler(@RequestBody LoginRequest loginRequest)throws UserException{
		String userName=loginRequest.getEmail();
		String password=loginRequest.getPassword();
		 
		Authentication authentication=authenticate(userName,password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token= jwtProvider.generateToken(authentication);
 
		AuthResponse authRes= new AuthResponse();
		authRes.setJwt(token);
		authRes.setMessage("signup success");
		return new ResponseEntity<AuthResponse>(authRes, HttpStatus.CREATED);
		}
	
	
	private Authentication authenticate(String userName, String password){
		UserDetails  userDetails= projectService.loadUserByUsername(userName);
		if(userDetails==null)
		{
			throw new BadCredentialsException("invalid username...");
			
		}
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("invalid password..");

	}
	return new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
}
	
}