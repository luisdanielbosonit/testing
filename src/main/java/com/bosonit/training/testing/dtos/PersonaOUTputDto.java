package com.bosonit.training.testing.dtos;

import com.bosonit.training.testing.entity.Persona;
import lombok.Data;

import java.util.Date;

/**
 * A DTO for the {@link Persona} entity
 */
@Data
public class PersonaOUTputDto {

    private Integer person_id;
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


    public PersonaOUTputDto(Persona persona){
        this.person_id = persona.getId_persona();
        this.password=persona.getPassword();
        this.username = persona.getUsername();
        this.name = persona.getName();
        this.surname = persona.getSurname();
        this.company_email = persona.getCompany_email();
        this.personal_email = persona.getPersonal_email();
        this.city = persona.getCity();
        this.active = persona.getActive();
        this.created_date = persona.getCreated_date();
        this.imagen_url = persona.getImagen_url();
        this.termination_date = persona.getTermination_date();
    }
}