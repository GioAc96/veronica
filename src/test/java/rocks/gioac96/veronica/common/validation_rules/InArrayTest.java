package rocks.gioac96.veronica.common.validation_rules;

import static org.junit.jupiter.api.Assertions.*;
import static rocks.gioac96.veronica.common.CommonValidationRulesTest.assertValidationFails;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import rocks.gioac96.veronica.common.CommonValidationFailureReasons;
import rocks.gioac96.veronica.providers.CreationException;
import rocks.gioac96.veronica.validation.ValidationException;

class InArrayTest {

    @ParameterizedTest
    @ValueSource(strings = {
        "",
        "on",
        "wo",
        "tree",
        "for",
        "five",
        "six",
        "seven"
    })
    void testInArrayFails(String value) {

        assertValidationFails(
            new InArray()
                .allowedValue("one")
                .allowedValue("two")
                .allowedValue("three")
                .allowedValue("four")
                .provide(),
            "five",
            CommonValidationFailureReasons.notInArray()
        );

    }

    @ParameterizedTest
    @ValueSource(strings = {
        "five",
        "six",
        "seven"
    })
    void testInArraySucceeds(String value) throws ValidationException {

        new InArray()
            .allowedValue("five")
            .allowedValue("six")
            .allowedValue("seven")
            .provide()
            .validate(value);

    }

    @Test
    void testInArrayInvalid() {

        assertThrows(CreationException.class, () -> new InArray().provide());

    }

}