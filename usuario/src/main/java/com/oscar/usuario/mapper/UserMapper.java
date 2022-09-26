package com.oscar.usuario.mapper;
import com.oscar.usuario.dto.UserDto;
import com.oscar.usuario.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface UserMapper {

    User toUser(UserDto userDto);

    UserDto toUserDto(User user);
}
