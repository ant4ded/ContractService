package my.idp.spring.contract.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestDto {
    @NotBlank
    String username;

    @NotBlank
    String password;
}
