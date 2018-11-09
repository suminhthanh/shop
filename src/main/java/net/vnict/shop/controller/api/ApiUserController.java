package net.vnict.shop.controller.api;

import net.vnict.shop.domain.entities.User;
import net.vnict.shop.service.RoleService;
import net.vnict.shop.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class ApiUserController {

	private UserService userService;
	private RoleService roleService;
	private PasswordEncoder passwordEncoder;

	public ApiUserController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.roleService = roleService;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping(value = "/login")
	public ResponseEntity<?> login(@RequestBody net.vnict.shop.domain.model.User user) {
		return new ResponseEntity<>(userService.checkLogin(user), HttpStatus.OK);
	}
	
	@PostMapping(value = "/register")
	public ResponseEntity<?> register(@RequestBody net.vnict.shop.domain.model.User user) {
		User dbUser = new User();
		dbUser.setName(user.getName());
		dbUser.setEmail(user.getEmail());
		dbUser.setPassword(passwordEncoder.encode(user.getPassword()));
		dbUser.addRole(roleService.findByName("ROLE_CUSTOMER"));
    	return new ResponseEntity<>(userService.register(dbUser), HttpStatus.OK);
	}

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
