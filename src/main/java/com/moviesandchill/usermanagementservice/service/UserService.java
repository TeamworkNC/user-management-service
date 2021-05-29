package com.moviesandchill.usermanagementservice.service;

import com.moviesandchill.usermanagementservice.dto.login.LoginRequestDto;
import com.moviesandchill.usermanagementservice.dto.password.UpdatePasswordDto;
import com.moviesandchill.usermanagementservice.dto.user.NewUserDto;
import com.moviesandchill.usermanagementservice.dto.user.UpdateUserDto;
import com.moviesandchill.usermanagementservice.dto.user.UserDto;
import com.moviesandchill.usermanagementservice.entity.User;
import com.moviesandchill.usermanagementservice.entity.UserPassword;
import com.moviesandchill.usermanagementservice.exception.auth.IncorrectCredentialsException;
import com.moviesandchill.usermanagementservice.exception.auth.PasswordMismatchException;
import com.moviesandchill.usermanagementservice.exception.auth.UserIsBannedException;
import com.moviesandchill.usermanagementservice.exception.user.UserNotFoundException;
import com.moviesandchill.usermanagementservice.mapper.UserMapper;
import com.moviesandchill.usermanagementservice.repository.GlobalRoleRepository;
import com.moviesandchill.usermanagementservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@Slf4j
public class UserService {

    private UserRepository userRepository;
    private GlobalRoleRepository globalRoleRepository;
    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;
    private RestTemplate restTemplate;
    private String streamingServiceUrl;
    private EsService esService;

    public List<UserDto> getAllUsers() {
        var users = userRepository.findAll();
        return userMapper.mapToDto(users);
    }

    public UserDto addUser(NewUserDto dto) {
        User user = userMapper.mapToEntity(dto);
        UserPassword userPassword = new UserPassword();

        String passwordHash = passwordEncoder.encode(dto.getPassword());
        userPassword.setPasswordHash(passwordHash);

        user.setPassword(userPassword);
        userPassword.setUser(user);

        user = userRepository.save(user);
        try {
            esService.loadUser(user.getUserId());
        } catch (Exception e) {
        }
        return userMapper.mapToDto(user);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
        try {
            esService.loadIndexUsers();
        } catch (Exception ignored) {
        }
    }

    public UserDto getUser(long userId) throws UserNotFoundException {
        User user = findUserById(userId);
        return userMapper.mapToDto(user);
    }

    public void updateUser(long userId, UpdateUserDto updateUserDto) throws UserNotFoundException {
        User user = findUserById(userId);
        userMapper.updateEntity(user, updateUserDto);
    }

    public void deleteUser(long userId) {
        try {
            esService.deleteUser(userId);
        } catch (Exception e) {
        }
        userRepository.deleteById(userId);
    }

    public void updateUserPassword(long userId, UpdatePasswordDto updatePasswordDto) throws UserNotFoundException, PasswordMismatchException {
        String oldPassword = updatePasswordDto.getOldPassword();
        String newPassword = updatePasswordDto.getNewPassword();
        User user = findUserById(userId);

        if (passwordEncoder.matches(oldPassword, user.getPassword().getPasswordHash())) {
            String newPasswordHash = passwordEncoder.encode(newPassword);
            user.getPassword().setPasswordHash(newPasswordHash);
            return;
        }
        //else
        throw new PasswordMismatchException();
    }

    public void updateUserLogo(long userId, MultipartFile file) throws UserNotFoundException {
        User user = findUserById(userId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body
                = new LinkedMultiValueMap<>();
        body.add("file", file.getResource());

        HttpEntity<MultiValueMap<String, Object>> requestEntity
                = new HttpEntity<>(body, headers);

        String url = streamingServiceUrl + "video/" + userId + "/logo";

        String logoUrl = restTemplate
                .postForObject(url, requestEntity, String.class);

        user.setLogoUrl(logoUrl);
    }

    public UserDto login(LoginRequestDto loginRequestDto) throws IncorrectCredentialsException, UserIsBannedException {
        String login = loginRequestDto.getLogin();
        String password = loginRequestDto.getPassword();

        var user = userRepository.findByLogin(login).orElseThrow(IncorrectCredentialsException::new);
        var userId = user.getUserId();

        if (user.isBanned()) {
            throw new UserIsBannedException(userId);
        }

        String hash = user.getPassword().getPasswordHash();

        if (passwordEncoder.matches(password, hash)) {
            return userMapper.mapToDto(user);
        }
        throw new IncorrectCredentialsException();
    }

    public UserDto register(NewUserDto newUserDto) {
        UserDto userDto = addUser(newUserDto);
        User user = userRepository.findById(userDto.getUserId())
                .orElseThrow(IllegalStateException::new);

        var userRole = globalRoleRepository
                .findByName("USER")
                .orElseThrow(IllegalStateException::new);

        user.setGlobalRoles(Set.of(userRole));
        return userMapper.mapToDto(user);
    }

    public void ban(long userId) throws UserNotFoundException {
        var user = findUserById(userId);
        user.setBanned(true);
    }

    public void unban(long userId) throws UserNotFoundException {
        var user = findUserById(userId);
        user.setBanned(false);
    }

    public void setOnline(long userId, boolean isOnline) throws UserNotFoundException {
        var user = findUserById(userId);
        user.setOnline(isOnline);
    }

    private User findUserById(long userId) throws UserNotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Autowired
    public void setStreamingServiceUrl(@Value("${endpoint.streaming-service.url}") String streamingServiceUrl) {
        this.streamingServiceUrl = streamingServiceUrl;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setGlobalRoleRepository(GlobalRoleRepository globalRoleRepository) {
        this.globalRoleRepository = globalRoleRepository;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    @Lazy
    public void setEsService(EsService esService) {
        this.esService = esService;
    }
}
