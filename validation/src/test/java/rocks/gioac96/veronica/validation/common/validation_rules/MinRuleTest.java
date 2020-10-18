package rocks.gioac96.veronica.validation.common.validation_rules;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import rocks.gioac96.veronica.core.providers.CreationException;
import rocks.gioac96.veronica.validation.ValidationException;
import rocks.gioac96.veronica.validation.common.CommonValidationFailureReasons;
import rocks.gioac96.veronica.validation.common.CommonValidationRules;
import rocks.gioac96.veronica.validation.common.CommonValidationRulesTest;

public class MinRuleTest {

    @ParameterizedTest
    @CsvSource({
        "0, -1",
        "10, 9.9999",
        "1000, -1000"
    })
    void testMinFails(
        double min,
        double value
    ) {

        CommonValidationRulesTest.assertValidationFails(
            CommonValidationRules.min(min),
            String.valueOf(value),
            CommonValidationFailureReasons.tooSmall(min)
        );

    }

    @ParameterizedTest
    @CsvSource({
        "0, 0",
        "0, 1",
        "-100, -100",
        "-134, -133.9999999",
        "1457.0139, 1457.014"
    })
    void testMinOk(
        double min,
        double value
    ) throws ValidationException {

        CommonValidationRules.min(min).validate(String.valueOf(value));

    }

    @Test
    void testInvalidMin() {

        assertThrows(CreationException.class, () -> {
            new MinRule().provide();
        });


    }

}
