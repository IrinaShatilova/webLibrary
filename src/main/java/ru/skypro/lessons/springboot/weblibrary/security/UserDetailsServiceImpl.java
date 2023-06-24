package ru.skypro.lessons.springboot.weblibrary.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.weblibrary.dto.UserDTO;
import ru.skypro.lessons.springboot.weblibrary.repository.UserRepository;
import ru.skypro.lessons.springboot.weblibrary.service.UserMapper;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserDetailsImpl userDetails;
    private final UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userDto = userRepository
                .findByLogin(username)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь " + username + " не найден"));
        userDetails.setUserDTO(userDto);
        return userDetails;
    }
}