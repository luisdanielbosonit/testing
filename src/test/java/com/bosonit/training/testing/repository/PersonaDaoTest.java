package com.bosonit.training.testing.repository;

import com.bosonit.training.testing.dtos.PersonaINputDto;
import com.bosonit.training.testing.dtos.PersonaOUTputDto;
import com.bosonit.training.testing.entity.Persona;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@ExtendWith(MockitoExtension.class)
@DataJpaTest
class PersonaDaoTest {
    @Autowired
    PersonaDao testPersonaDao;
    Persona newPerson;
    PersonaINputDto newPersonaInputDto;
    PersonaOUTputDto newPersonaPOutputDto= null;

    @BeforeEach
    public void beforePersonaName(){
        newPersonaInputDto = new PersonaINputDto(1,"daniella","jsj45","daniellaAcosat",
                "daniellita","daniella@bosonit.com","daniella@gmail.com",
                "madrid",true,new Date(),"jpeg",new Date());

    }

    @Test
    void itShouldCheckIfPersonfindByname() {

        //given  @BeforeEach
        //when
        newPerson = testPersonaDao.save(newPersonaInputDto.transformIntoPersona());

        List<Persona> personaList= new ArrayList<>();
        personaList.add(newPerson);

        List<Persona> personaDaoBynamenaGuar = testPersonaDao.findByname(newPerson.getName());

        //then
        assertThat(personaList).isEqualTo(personaDaoBynamenaGuar);

    }
}