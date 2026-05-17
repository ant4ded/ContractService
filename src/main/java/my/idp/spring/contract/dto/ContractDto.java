package my.idp.spring.contract.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.idp.spring.contract.validation.ValidContract;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@ValidContract
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractDto {
    Long id;
    @NotBlank
    @NotNull
    String title;
    @NotBlank
    String registrationDate;
    @NotBlank
    String registrationNumber;
    @NotBlank
    @NotNull
    String contractCurrency;
    @NotBlank
    @NotNull
    String paymentCurrency;
    boolean frame;
    List<ContractItemDto> items;
}
