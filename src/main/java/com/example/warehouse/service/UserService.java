package com.example.warehouse.service;

import com.example.warehouse.entity.Role;
import com.example.warehouse.entity.User;
import com.example.warehouse.exception.RoleNotFoundException;
import com.example.warehouse.repository.RoleRepository;
import com.example.warehouse.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private final static String ROLE_USER = "ROLE_USER";
    private final static String ROLE_MANAGER = "ROLE_MANAGER";

    @Transactional
    public void register(User user) {
        Role role = roleRepository.findByName(ROLE_USER)
                .orElseThrow(() -> new RoleNotFoundException(String.format("Роли %s нет в Базе данных", ROLE_USER)));
        user.setRoles(Set.of(role));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public User getUser(Principal principal) {
        return userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("Возникла ошибка, вы не можете получить достпу к системе"));
    }

    @Transactional
    public void update(User user) {

        User userDb = userRepository.findById(user.getId())
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Пользователь с id %d не найден", user.getId())));
        String previousEmail = userDb.getEmail();

        userDb.setName(user.getName());
        userDb.setEmail(user.getEmail());
        User userSaved = userRepository.save(userDb);

        if (!previousEmail.equals(userSaved.getEmail())) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
            UsernamePasswordAuthenticationToken newAuth =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(newAuth);
        }
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public void banUser(Long id) {
        User userDb = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Пользователь с id %d не найден", id)));
        userDb.setBun(!userDb.isBun());
        userRepository.save(userDb);
    }

    @Transactional
    public void madeManagerUser(Long id) {
        User userDb = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Пользователь с id %d не найден", id)));
        Role role = roleRepository.findByName(ROLE_MANAGER)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Роли %s нет в Базе данных", ROLE_MANAGER)));
        if (!userDb.getRoles().contains(role)) {
            userDb.getRoles().add(role);
        } else {
            userDb.getRoles().remove(role);
        }
        userRepository.save(userDb);
    }

    @Transactional
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UsernameNotFoundException(String.format("Пользователь с id %d не найден", id));
        }
        userRepository.deleteById(id);
    }


}
