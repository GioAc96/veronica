package org.gioac96.veronica.routing.pipeline.validation.rules;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.gioac96.veronica.routing.pipeline.validation.CommonValidationFailureReason;
import org.gioac96.veronica.routing.pipeline.validation.ValidationException;
import org.gioac96.veronica.routing.pipeline.validation.ValidationFailureData;
import org.gioac96.veronica.routing.pipeline.validation.ValidationFailureResponse;
import org.gioac96.veronica.routing.pipeline.validation.ValidationRule;


/**
 * Validation rule that checks that a value is above a specified minimum.
 */
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public final class MinRule implements ValidationRule {

    @Getter
    @Setter
    private Double minValue;

    @Getter
    @Setter
    @Builder.Default
    private Boolean inclusive = true;


    @Override
    public void validate(String fieldName, String fieldValue) throws ValidationException {

        double value;

        try {

            value = Double.parseDouble(fieldValue);

        } catch (NumberFormatException e) {

            ValidationFailureData failureData = ValidationFailureData.builder()
                .failureReason(CommonValidationFailureReason.OUT_OF_RANGE)
                .fieldName(fieldName)
                .build();

            ValidationFailureResponse failureResponse = ValidationFailureResponse.builder()
                .validationFailureData(failureData)
                .build();

            throw new ValidationException(failureResponse, failureData);

        }

        if (
            (inclusive && value < minValue) || (!inclusive && value <= minValue)
        ) {

            ValidationFailureData failureData = ValidationFailureData.builder()
                .failureReason(CommonValidationFailureReason.OUT_OF_RANGE)
                .fieldName(fieldName)
                .build();

            ValidationFailureResponse failureResponse = ValidationFailureResponse.builder()
                .validationFailureData(failureData)
                .build();

            throw new ValidationException(failureResponse, failureData);

        }

    }

}
