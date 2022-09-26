package com.oscar.usuario.service;

import com.oscar.usuario.dto.UserDto;
import com.oscar.usuario.exceptionhandler.*;
import com.oscar.usuario.factory.FactoryUserDataTest;
import com.oscar.usuario.mapper.UserMapper;
import com.oscar.usuario.model.User;
import com.oscar.usuario.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    IUserRepository userRepository;
    @Mock
    UserMapper userMapper;

    @Test
    void mustSaveAUser() {
        //Given
        //yo como usuario hago la solictud para guardar un usuario
        User expectedUser = FactoryUserDataTest.getUser();
        UserDto userDto = FactoryUserDataTest.getUserDto();

        //When
        //le envio los valores correctamente
        when(userRepository.findByUserEmail(anyString())).thenReturn(Optional.empty());
        when(userMapper.toUser(any())).thenReturn(expectedUser);

        userService.saveUser(userDto);
        //Then
        //el sistema e guarda un nuevo usuario
        verify(userRepository).save(any(User.class));
    }

    @Test
    void trowUserAlreadyExistsExceptionWhenAttemptSaveUserThatAlreadyExists() {
        //Given
        //yo como usuario intento guardar un usuario que ya existe
        User expectedUser = FactoryUserDataTest.getUser();
        UserDto userDto = FactoryUserDataTest.getUserDto();
        //When
        //le envio un usuario que ya existe
        when(userRepository.findByUserEmail(anyString())).thenReturn(Optional.of(expectedUser));

        //Then
        //el sistema e genera una excepcion deltipo UserAlreadyExistsException
        assertThrows(UserAlreadyExistsException.class, () -> userService.saveUser(userDto));
    }

    @Test
    void trowMissingMandatoryDataExceptionWhenAttemptSaveUserWithOutUserName() {
        //Given
        //yo como usuario intento guardar un usuario sin nombre
        UserDto userDto = FactoryUserDataTest.getUserDto();
        userDto.setUserName(null);
        //When
        //le envio un usuario sin nombre
        when(userRepository.findByUserEmail(anyString())).thenReturn(Optional.empty());

        //Then
        //el sistema e genera una excepcion deltipo MissingMandatoryDataException
        assertThrows(MissingMandatoryDataException.class, () -> userService.saveUser(userDto)
        );
    }

    @Test
    void trowMissingMandatoryDataExceptionWhenAttemptSaveUserWithOutSurName() {
        //Given
        //yo como usuario intento guardar un usuario sin apellido
        UserDto userDto = FactoryUserDataTest.getUserDto();
        userDto.setUserSurname(null);
        //When
        //le envio un usuario sin nombre
        when(userRepository.findByUserEmail(anyString())).thenReturn(Optional.empty());

        //Then
        //el sistema e genera una excepcion deltipo MissingMandatoryDataException
        assertThrows(MissingMandatoryDataException.class, () -> userService.saveUser(userDto)
        );
    }

    @Test
    void trowEmailInvalidFormatExceptionWhenAttemptSaveUserWithInvalidEmail() {
        //Given
        //yo como usuario intento guardar un usuario con un email incorrecto
        UserDto userDto = FactoryUserDataTest.getUserDto();
        userDto.setUserEmail("oscar");
        //When
        //le envio un usuario con un email invalido
        when(userRepository.findByUserEmail(anyString())).thenReturn(Optional.empty());

        //Then
        //el sistema e genera una excepcion deltipo EmailInvalidFormatException
        assertThrows(EmailInvaldFormatException.class, () -> userService.saveUser(userDto)
        );
    }
    @Test
    void trowPasswordOfSizeInvalidExceptionWhenAttemptSaveUserWithPasswordOfSiseInvalid() {
        //Given
        //yo como usuario intento guardar un usuario con un una contraseña incorrecto
        UserDto userDto = FactoryUserDataTest.getUserDto();
        userDto.setUserPassword("123acd*");
        //When
        //le envio un usuario con un password invalido
        when(userRepository.findByUserEmail(anyString())).thenReturn(Optional.empty());

        //Then
        //el sistema e genera una excepcion deltipo PasswordOfSizeInvalidException
        assertThrows(PasswordOfSizeInvalidException.class, () -> userService.saveUser(userDto)
        );
    }

    @Test
    void throwPasswordWithOutLowerCaseWhenAttemptSaveUserWithPasswordWithoutLowerCase() {
        //Given
        //yo como usuario intento guardar un usuario con un una contraseña sin minusculas
        UserDto userDto = FactoryUserDataTest.getUserDto();
        userDto.setUserPassword("123ABCD*");
        //When
        //le envio un usuario con un password sin minusculas
        when(userRepository.findByUserEmail(anyString())).thenReturn(Optional.empty());

        //Then
        //el sistema e genera una excepcion deltipo PasswordInvalidFormatException
        assertThrows(PasswordWithOutLowerCaseException.class, () -> userService.saveUser(userDto)
        );
    }
    @Test
    void throwPasswordWithOutUpperCaseExceptionWhenAttemptSaveUserWithPasswordWithoutUpperCase() {
        //Given
        //yo como usuario intento guardar un usuario con un una contraseña sin mayusculas
        UserDto userDto = FactoryUserDataTest.getUserDto();
        userDto.setUserPassword("123dacd*");
        //When
        //le envio un usuario con un password sin mayusculas
        when(userRepository.findByUserEmail(anyString())).thenReturn(Optional.empty());

        //Then
        //el sistema e genera una excepcion deltipo PasswordWithOutUpperCaseException
        assertThrows(PasswordWithOutUpperCaseException.class, () -> userService.saveUser(userDto)
        );
    }

    @Test
    void throwPasswordWithOutNumberExceptionWhenAttemptSaveUserWithPasswordWithoutNumber() {
        //Given
        //yo como usuario intento guardar un usuario con un una contraseña sin numeros
        UserDto userDto = FactoryUserDataTest.getUserDto();
        userDto.setUserPassword("ABCDdacd*");
        //When
        //le envio un usuario con un password sin numeros
        when(userRepository.findByUserEmail(anyString())).thenReturn(Optional.empty());

        //Then
        //el sistema e genera una excepcion deltipo PasswordWithOutNumberException
        assertThrows(PasswordWithOutNumberException.class, () -> userService.saveUser(userDto)
        );
    }

    @Test
    void throwPasswordWithOutSpecialCharacterExceptionWhenAttemptSaveUserWithPasswordWithoutSpecialCharacterAllowed() {
        //Given
        //yo como usuario intento guardar un usuario con un una contraseña sin los caracteres permitidos
        UserDto userDto = FactoryUserDataTest.getUserDto();
        userDto.setUserPassword("ABCDdacd1");
        //When
        //le envio un usuario con un password sin mayusculas
        when(userRepository.findByUserEmail(anyString())).thenReturn(Optional.empty());

        //Then
        //el sistema e genera una excepcion deltipo PasswordWithOutCharacterException
        assertThrows(PasswordWithOutCharacterException.class, () -> userService.saveUser(userDto)
        );
    }

    @Test
    void throwPasswordWithCharacterNotAllowedExceptionWhenAttemptSaveUserWithPasswordWithCharacterNotAllowed() {
        //Given
        //yo como usuario intento guardar un usuario con un una contraseña con caracteres no permitidos
        UserDto userDto = FactoryUserDataTest.getUserDto();
        userDto.setUserPassword("ABCDdacd1*@");
        //When
        //le envio un usuario con un password con caracteres no permitidos
        when(userRepository.findByUserEmail(anyString())).thenReturn(Optional.empty());

        //Then
        //el sistema e genera una excepcion del tipo PasswordWithCharacterNotAllowedException
        assertThrows(PasswordWithCharacterNotAllowedException.class, () -> userService.saveUser(userDto)
        );
    }
    @Test
    void throwEmailNotPresentExceptionWhenAttemptSaveUserWithOutEmail() {
        //Given
        //yo como usuario intento guardar un usuario sin email
        UserDto userDto = FactoryUserDataTest.getUserDto();
        userDto.setUserEmail(null);
        //When
        //le envio un usuario sin email
        when(userRepository.findByUserEmail(anyString())).thenReturn(Optional.empty());

        //Then
        //el sistema e genera una excepcion deltipo EmailNotPresentException
        assertThrows(EmailNotPresentException.class, () -> userService.saveUser(userDto)
        );
    }
    @Test
    void deleteUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void getUserByEmail() {
    }
}