package org.booklibrary.dao;

import org.booklibrary.model.Book;
import org.booklibrary.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate
                .query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate
                .query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream()
                .filter(Objects::nonNull)
                .findAny()
                .orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate
                .update("INSERT INTO Person(full_name, year_of_born) VALUES(?, ?)",
                        person.getFullName(),
                        person.getYearOfBorn());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate
                .update("UPDATE Person SET full_name=?, year_of_born=? WHERE id=?",
                        updatedPerson.getFullName(),
                        updatedPerson.getYearOfBorn(),
                        id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }

    public List<Book> getBooksByPersonId(int id) {
        return jdbcTemplate
                .query("SELECT * FROM Book WHERE person_id=?", new Object[]{id},
                        new BeanPropertyRowMapper<>(Book.class));
    }

    public Optional<Person> getPersonByFullName(String fullName) {
        return jdbcTemplate
                .query("SELECT * FROM Person WHERE full_name=?", new Object[]{fullName},
                        new BeanPropertyRowMapper<>(Person.class))
                .stream()
                .filter(Objects::nonNull)
                .findAny();
    }
}
