package my.idp.spring.contract.dto;

import lombok.*;
import my.idp.spring.contract.entity.Role;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String username;
    private Set<Role> roles;
}