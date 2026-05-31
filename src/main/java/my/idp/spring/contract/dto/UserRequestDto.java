package my.idp.spring.contract.dto;

import lombok.*;
import my.idp.spring.contract.entity.Role;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotEmpty
    private Set<Role> roles;
}