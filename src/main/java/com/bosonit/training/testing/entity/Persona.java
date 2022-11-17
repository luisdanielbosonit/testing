package com.bosonit.training.testing.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.*;
import java.util.Date;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "persona")
@RequestMapping("add")
public class Persona  {

    private static final String fechtype = null;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_persona")
    private Integer id_persona;
    @Column(length = 10,nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private String company_email;
    @Column(nullable = false)
    private String personal_email;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private boolean active;

    @Temporal(TemporalType.DATE)
    private Date created_date;

    @Column(nullable = false)
    private String imagen_url;

    @Temporal(TemporalType.DATE)
    private Date termination_date;




    public Boolean getActive() {
        return true;
    }
}






