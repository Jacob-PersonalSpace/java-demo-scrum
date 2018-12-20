package com.demo.scrum.service;

import com.demo.scrum.domain.User;
import com.demo.scrum.repository.UserRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {

    class InnerUser extends User {
        public InnerUser() {
        }

        @Override
        public boolean equals(Object o) {
            User u = (User) o;

            if (this.getId() == u.getId() && this.getName() == u.getName() && this.getPassword() == u.getPassword()
                    && this.getActive() == u.getActive()) {
                return true;
            } else {
                return false;
            }
        }
    }

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void create() throws Exception {
        String encodePassword = "encodePassword";
        given(this.bCryptPasswordEncoder.encode("password1")).willReturn(encodePassword);
        given(this.userRepository.save(any())).willReturn(new User("name2", encodePassword));

        User actualResult = userService.create(new User("name1", "password1"));

        InnerUser expectResult = new InnerUser();
        expectResult.setName("name2");
        expectResult.setPassword(encodePassword);

        assertEquals(expectResult, actualResult);
    }
}