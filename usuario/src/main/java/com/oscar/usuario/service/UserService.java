package com.oscar.usuario.service;

import com.oscar.usuario.dto.UserDto;
import com.oscar.usuario.exceptionhandler.*;
import com.oscar.usuario.mapper.UserMapper;
import com.oscar.usuario.model.User;
import com.oscar.usuario.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService implements IUserService{

    private final IUserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * @param userDto object with user data
     */

    public void saveUser(UserDto userDto)  {
        if (userDto.getUserEmail() != null ) {
            if (userRepository.findByUserEmail(userDto.getUserEmail()).isPresent()) {
                throw new UserAlreadyExistsException();
            }
            if (userDto.getUserName() == null || userDto.getUserSurname() == null || userDto.getUserPhone() == null || userDto.getUserAddress() == null || userDto.getUserPassword() == null) {
                throw new MissingMandatoryDataException();
            }
            emailValidator(userDto.getUserEmail());
            passwordValidator(userDto.getUserPassword());
            User user = userMapper.toUser(userDto);
            userRepository.save(user);
        } else{
            throw new EmailNotPresentException();
        }

    }

    private void passwordValidator(String userPassword) {

        if (userPassword.length() > 7 && userPassword.length() < 16) {
            List<Character> lowerCase =  List.of('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
            List<Character> upperCase = List.of('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
            List<Character> numbers = List.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
            List<Character> specialCharactersAllowed = List.of('*', '_', '-');
            List<Character> specialCharactersNotAllowed = List.of('!', '"', '#', '$', '%', '&', '/', '(', ')', '=', '?', '¡', '¿', '¨', '^', '´', '`', '+', '{', '}', '[', ']', 'ç', 'Ç', 'º', 'ª', '°', '¬', '|', '·', '>', '<', ';', ':', ',', '.', ' ','@','\\');
            for (int i = 0; i < userPassword.length(); i++) {
                if (lowerCase.contains(userPassword.charAt(i))) {
                    break;
                }else {
                    if ( i == userPassword.length()-1){
                        throw new PasswordWithOutLowerCaseException();
                    }
                }

            }

            for (int p = 0; p < userPassword.length(); p++) {
                if (upperCase.contains(userPassword.charAt(p))) {
                    break;
                } else {
                    if (p == userPassword.length() - 1) {
                        throw new PasswordWithOutUpperCaseException();
                    }
                }
            }
                for (int q = 0; q < userPassword.length(); q++) {
                    if (numbers.contains(userPassword.charAt(q))) {
                        break;
                    }else {
                        if ( q == userPassword.length()-1){
                            throw new PasswordWithOutNumberException();
                        }
                    }

                }

                for (int r = 0; r < userPassword.length(); r++) {
                    if (specialCharactersAllowed.contains(userPassword.charAt(r))) {
                        break;
                    }else {
                        if ( r == userPassword.length()-1){
                            throw new PasswordWithOutCharacterException();
                        }
                    }

                }

            for (int s = 0; s < userPassword.length(); s++) {
                if (specialCharactersNotAllowed.contains(userPassword.charAt(s))) {
                    throw new PasswordWithCharacterNotAllowedException();
                }
            }

        } else{
            throw new PasswordOfSizeInvalidException();
        }
    }

    private void emailValidator(String userEmail) {
        EmailValidator emailValidator = new EmailValidator();
        if (!emailValidator.isValid(userEmail, null)) {
            throw new EmailInvaldFormatException();
        }
    }
    /**
     * @param userEmail user email to delete
     */

    public void deleteUser(String userEmail) {

        if (userRepository.findByUserEmail(userEmail).isPresent()) {
            userRepository.deleteByUserEmail(userEmail);
        } else {
            throw new UserNotFoundException();
        }
    }

    /**
     * @param userDto object with user data
     */
    @Override
    public void updateUser(UserDto userDto) {
        if (userDto.getUserEmail() != null) {
            Optional<User> newUser = userRepository.findByUserEmail(userDto.getUserEmail());
            if (newUser.isPresent()) {

                if (userDto.getUserName() != null) {
                    newUser.get().setUserName(userDto.getUserName());
                }
                if (userDto.getUserSurname() != null) {
                    newUser.get().setUserSurname(userDto.getUserSurname());
                }
                if (userDto.getUserPhone() != null) {
                    newUser.get().setUserPhone(userDto.getUserPhone());
                }
                if (userDto.getUserAddress() != null) {
                    newUser.get().setUserAddress(userDto.getUserAddress());
                }
                if (userDto.getUserEmail() != null) {
                    newUser.get().setUserEmail(userDto.getUserEmail());
                }
                if (userDto.getUserPassword() != null) {
                    newUser.get().setUserPassword(userDto.getUserPassword());
                }

                userRepository.save(newUser.get());
            } else {
                throw new UserNotFoundException();
            }
        } else {
            throw new UserNotFoundException();
        }

    }

    /**
     * @param userEmail user email to find user
     * @return the call to the method toUserDto of the UserMapper class
     */
    public UserDto getUserByEmail(String userEmail) {
        User user = userRepository.findByUserEmail(userEmail).orElseThrow(UserNotFoundException::new);
        return userMapper.toUserDto(user);
    }

}
