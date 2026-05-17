package my.idp.spring.contract.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EnumValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PossibleValues {
    Class<? extends Enum<?>> value();
    String message() default "invalid value. possible values: {values}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
