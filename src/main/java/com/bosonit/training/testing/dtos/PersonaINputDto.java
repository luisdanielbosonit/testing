package com.bosonit.training.testing.dtos;

import com.bosonit.training.testing.entity.Persona;
import lombok.Data;

import java.util.Date;

/**
 * A DTO for the {@link Persona} entity
 */
@Data
public class PersonaINputDto {

    private Integer id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private boolean active;
    private Date created_date;
    private String imagen_url;
    private Date termination_date;

    public Persona transformIntoPersona(){
        Persona person = new Persona();
        person.setId_persona(this.id);
        person.setPassword(this.password);
        person.setUsername(this.username);
        person.setName(this.name);
        person.setSurname(this.surname);
        person.setCompany_email(this.company_email);
        person.setPersonal_email(this.personal_email);
        person.setCity(this.city);
        person.setActive(this.active);
        person.setCreated_date(this.created_date);
        person.setImagen_url(this.imagen_url);
        person.setTermination_date(this.termination_date);

        return person;
    }

    public PersonaINputDto(Integer id,String username, String password, String name, String surname, String company_email, String personal_email, String city, boolean active, Date created_date, String imagen_url, Date termination_date) {
        this.id= id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.company_email = company_email;
        this.personal_email = personal_email;
        this.city = city;
        this.active = active;
        this.created_date = created_date;
        this.imagen_url = imagen_url;
        this.termination_date = termination_date;
    }

    public boolean getPersona() {
        return this.active;
    }

    public boolean getActive() {
        return this.active;
    }
}