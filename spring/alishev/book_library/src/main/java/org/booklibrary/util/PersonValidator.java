package org.booklibrary.util;


import lombok.RequiredArgsConstructor;
import org.booklibrary.model.Person;
import org.booklibrary.services.PeopleService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
@RequiredArgsConstructor
public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;

        if (peopleService.getPersonByFullName(person.getFullName()).isPresent())
            errors.rejectValue("fullName", "", "Person with that name already exists");
    }
}
