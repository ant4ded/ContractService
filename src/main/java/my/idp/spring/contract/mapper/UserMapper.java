package my.idp.spring.contract.mapper;

import lombok.AllArgsConstructor;
import my.idp.spring.contract.dto.UserRequestDto;
import my.idp.spring.contract.dto.UserResponseDto;
import my.idp.spring.contract.entity.User;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserMapper implements EntityMapper<User, UserRequestDto, UserResponseDto> {

    @Override
    public User mapToEntity(UserRequestDto dto) {
        return User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .roles(dto.getRoles())
                .build();
    }

    @Override
    public UserResponseDto mapToDto(User entity) {
        return UserResponseDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .roles(entity.getRoles())
                .build();
    }
}
