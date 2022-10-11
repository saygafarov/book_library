package org.booklibrary.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Person")
@Data
@NoArgsConstructor
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
    @Column(name = "full_name")
    private String fullName;

    @Min(value = 1900, message = "Year of born should be greater then 1900")
    @Column(name = "year_of_born")
    private int yearOfBorn;

    @OneToMany(mappedBy = "owner")
    @ToString.Exclude
    private List<Book> books;

    public Person(String fullName, int yearOfBorn) {
        this.fullName = fullName;
        this.yearOfBorn = yearOfBorn;
    }
}
