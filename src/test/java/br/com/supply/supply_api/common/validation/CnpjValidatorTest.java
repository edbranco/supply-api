package br.com.supply.supply_api.common.validation;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.authorization.ConditionalAuthorizationManager.when;

@ExtendWith(MockitoExtension.class)
class CnpjValidatorTest {

    private final CnpjValidator validator = new CnpjValidator();

    @Mock
    private ConstraintValidatorContext context;

    @Mock
    private ConstraintValidatorContext.ConstraintViolationBuilder violationBuilder;

    @Test
    void shouldAcceptNullCnpj() {
        assertThat(validator.isValid(null, context)).isTrue();
    }

    @Test
    void shouldAcceptBlankCnpj() {
        assertThat(validator.isValid("   ", context)).isTrue();
    }

    @Test
    void shouldAcceptValidAlphanumericCnpjWith14Characters() {
        assertThat(validator.isValid("ABCD1234EFGH56", context)).isTrue();
        assertThat(validator.isValid("1234567890ABCD", context)).isTrue();
    }

    @Test
    void shouldRejectCnpjWithLessThan14Characters() {
        when(context.buildConstraintViolationWithTemplate(anyString())).thenReturn(violationBuilder);
        when(violationBuilder.addConstraintViolation()).thenReturn(context);

        assertThat(validator.isValid("1234567890123", context)).isFalse();
        verify(context).buildConstraintViolationWithTemplate("CNPJ Inválido");
    }

    @Test
    void shouldRejectCnpjWithMoreThan14Characters() {
        when(context.buildConstraintViolationWithTemplate(anyString())).thenReturn(violationBuilder);
        when(violationBuilder.addConstraintViolation()).thenReturn(context);

        assertThat(validator.isValid("123456789012345", context)).isFalse();
        verify(context).buildConstraintViolationWithTemplate("CNPJ Inválido");
    }

    @Test
    void shouldRejectCnpjWithSpecialCharacters() {
        when(context.buildConstraintViolationWithTemplate(anyString())).thenReturn(violationBuilder);
        when(violationBuilder.addConstraintViolation()).thenReturn(context);

        assertThat(validator.isValid("12.345.678/9012", context)).isFalse();
        verify(context).buildConstraintViolationWithTemplate("CNPJ Inválido");
    }

    @Test
    void shouldRejectCnpjWithAccents() {
        when(context.buildConstraintViolationWithTemplate(anyString())).thenReturn(violationBuilder);
        when(violationBuilder.addConstraintViolation()).thenReturn(context);

        assertThat(validator.isValid("ABCD1234ÉFGH56", context)).isFalse();
        verify(context).buildConstraintViolationWithTemplate("CNPJ Inválido");
    }
}
