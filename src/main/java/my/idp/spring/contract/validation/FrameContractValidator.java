package my.idp.spring.contract.validation;

import my.idp.spring.contract.dto.ContractRequestDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FrameContractValidator implements ConstraintValidator<ValidContract, ContractRequestDto> {
    @Override
    public boolean isValid(ContractRequestDto contractDTO, ConstraintValidatorContext context) {
        boolean isFrame = contractDTO.isFrame();
        boolean hasItems = !contractDTO.getItems().isEmpty();

        if (isFrame && hasItems) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("frame contract must have no items").addConstraintViolation();
            return false;
        }

        if (!isFrame && !hasItems) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("non-frame contract must have items").addConstraintViolation();
            return false;
        }

        return true;
    }
}
