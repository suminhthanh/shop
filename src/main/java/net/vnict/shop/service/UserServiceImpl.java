package net.vnict.shop.service;

import net.vnict.shop.domain.entities.User;
import net.vnict.shop.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	private PasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findOne(Integer id) {
		return null;
	}

	@Override
	public void delete(Integer id) {
		User user = userRepository.findById(id).get();
		userRepository.delete(user);
	}

	@Override
	@Transactional(readOnly = true)
	public net.vnict.shop.domain.model.User checkLogin(net.vnict.shop.domain.model.User user) {
		net.vnict.shop.domain.model.User ret = null;
		
		User dbUser = userRepository.findByEmail(user.getEmail());
		
		if (dbUser != null 
				&& passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
			ret = new net.vnict.shop.domain.model.User();
			ret.setId(dbUser.getId());
			ret.setName(dbUser.getName());
			ret.setEmail(dbUser.getEmail());
		}
		return ret;
	}
	
	@Override
	@Transactional
	public boolean register(User user) {
		if (userRepository.findByEmail(user.getEmail()) != null) {
			return false; 
		}
		userRepository.save(user);
		return true;
	}
}