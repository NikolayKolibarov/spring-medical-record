package nnk.areas.users.annotations;

import nnk.areas.users.models.binding.user.UserRegistrationModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<FieldMatch, UserRegistrationModel> {
    @Override
    public void initialize(FieldMatch passwordsMatching) {

    }

    @Override
    public boolean isValid(UserRegistrationModel user, ConstraintValidatorContext constraintValidatorContext) {

        return user.getPassword().equals(user.getConfirmPassword());
    }
}