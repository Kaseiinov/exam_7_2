package kg.attractor.exam_7.service.impl;

import kg.attractor.exam_7.dao.UserDao;
import kg.attractor.exam_7.dto.UserDto;
import kg.attractor.exam_7.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl  implements UserService {
    private final UserDao userDao;

    public void register(UserDto userDto){
        User user = User
                .builder()
                .phone(userDto.getPhone())
                .password(userDto.getPassword())
                .username(userDto.getUsername())
                .roleId(1L)
                .enabled(true)
                .build();

        userDao.register(user);
        log.info(String.format("Registered user: %s", userDto.getUsername()));
    }

}
