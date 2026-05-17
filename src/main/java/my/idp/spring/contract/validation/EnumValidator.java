package my.idp.spring.contract.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class EnumValidator implements ConstraintValidator<PossibleValues, String> {
    private Pattern pattern;

    @Override
    public void initialize(PossibleValues annotation) {
        pattern = Pattern.compile(Arrays.stream(annotation.value().getEnumConstants()).map(Enum::name).collect(Collectors.joining("|")));
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        Matcher m = pattern.matcher(value);
        boolean matches = m.matches();
        if (!matches) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("invalid value. possible values: " + pattern.pattern()).addConstraintViolation();
        }
        return matches;
    }
}
