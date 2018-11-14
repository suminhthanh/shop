package net.vnict.shop;

import net.vnict.shop.domain.entities.User;
import net.vnict.shop.repository.UserRepository;
import net.vnict.shop.service.RoleService;
import net.vnict.shop.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void userTest_findOneOK() {
        User user = userService.findOne(1);
        assertThat(user.getEmail()).isEqualTo("smthanh@gmail.com");
    }

    @Test
    public void userTest_findOneNotFound() {
        User user = userService.findOne(123);
        assertThat(user).isEqualTo(null);
    }


    @Test
    public void userTest_findAllOK() {
        List<User> users = userService.findAll();
        assertThat(users.size()).isGreaterThan(0);
    }

    @Test
    public void userTest_checkLoginOK() {
        net.vnict.shop.domain.model.User user = new net.vnict.shop.domain.model.User();
        user.setEmail("smthanh@gmail.com");
        user.setPassword("12345678");
        net.vnict.shop.domain.model.User checkUser = userService.checkLogin(user);
        assertThat(checkUser).isNotEqualTo(null);
    }

    @Test
    public void userTest_checkLoginWrongPass() {
        net.vnict.shop.domain.model.User user = new net.vnict.shop.domain.model.User();
        user.setEmail("smthanh@gmail.com");
        user.setPassword("sai pass");
        net.vnict.shop.domain.model.User checkUser = userService.checkLogin(user);
        assertThat(checkUser).isEqualTo(null);
    }

    @Test
    public void userTest_checkLoginEmailNotFound() {
        net.vnict.shop.domain.model.User user = new net.vnict.shop.domain.model.User();
        user.setEmail("smthanh@gmail.com.vn");
        net.vnict.shop.domain.model.User checkUser = userService.checkLogin(user);
        assertThat(checkUser).isEqualTo(null);
    }

    @Test
    public void userTest_registerOK() {
        net.vnict.shop.domain.model.User user = new net.vnict.shop.domain.model.User();
        user.setEmail("smthanh@live.com");
        user.setPassword("12345678");
        user.setName("Thanh tester");
        User dbUser = new User();
        dbUser.setName(user.getName());
        dbUser.setEmail(user.getEmail());
        dbUser.setPassword(passwordEncoder.encode(user.getPassword()));
        dbUser.addRole(roleService.findByName("ROLE_CUSTOMER"));
        assertThat(userService.register(dbUser)).isEqualTo(true);
    }

    @Test
    public void userTest_registerExistEmail() {
        net.vnict.shop.domain.model.User user = new net.vnict.shop.domain.model.User();
        user.setEmail("smthanh@gmail.com");
        user.setPassword("12345678");
        user.setName("Thanh tester");
        User dbUser = new User();
        dbUser.setName(user.getName());
        dbUser.setEmail(user.getEmail());
        dbUser.setPassword(passwordEncoder.encode(user.getPassword()));
        dbUser.addRole(roleService.findByName("ROLE_CUSTOMER"));
        assertThat(userService.register(dbUser)).isEqualTo(false);
    }

    @Test
    public void userTest_deleteFail() {
        assertThrows(NoSuchElementException.class, () -> {
            userService.delete(222);
        });
    }
}
