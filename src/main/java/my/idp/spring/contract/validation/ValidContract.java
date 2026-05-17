package my.idp.spring.contract.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = FrameContractValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidContract {
    String message() default "invalid contract";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
