package com.bosonit.training.testing.service;


import com.bosonit.training.testing.Exception.EntityNotFoundException;

import com.bosonit.training.testing.dtos.PersonaINputDto;
import com.bosonit.training.testing.dtos.PersonaOUTputDto;
import com.bosonit.training.testing.entity.Persona;
import com.bosonit.training.testing.repository.PersonaDao;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ServiceImplPersonaTest {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    public PersonaDao personaDao;
    @InjectMocks
    public ServicePersona underTest= new ServiceImplPersona();
    Persona newPerson= null;
    PersonaINputDto newPersonaInputDto= null;
    PersonaOUTputDto newPersonaPOutputDto= null;



    @BeforeEach
    public void setUp() {
//        autoCloseable= MockitoAnnotations.openMocks(this);
        newPersonaInputDto = new PersonaINputDto(1,"daniella","jsj45","daniellaAcosta","daniellita",
                "daniella@bosonit.com","daniella@gmail.com",
                "madrid",true,new Date(),"jpeg",new Date());
        newPerson = newPersonaInputDto.transformIntoPersona();

    }

    @DisplayName("Test para Ver Lista de personas")
    @Test
    void CanViewallPerson() {
//        given
        List<Persona> peopleViewList = new ArrayList<>();
        peopleViewList.add(newPerson);
        when(personaDao.findAll()).thenReturn(peopleViewList);
//        when
           List<PersonaOUTputDto> ouTputDtoList= underTest.viewall();
//        then
        verify(personaDao).findAll();
        assertThat(ouTputDtoList).isNotNull();
        assertThat(ouTputDtoList.size()).isEqualTo(1);
        assertThat(ouTputDtoList).isExactlyInstanceOf(ArrayList.class);
    }

    @DisplayName("Test para Ver Lista de personas Exceptions")
    @Test
    void CanViewallPersonThrowExceptions() {
//        given
        List<Persona> peopleViewList = new ArrayList<>();
//        peopleViewList.add(newPerson);
        when(personaDao.findByname(newPerson.getName())).thenReturn(peopleViewList);
//        when
        assertThrows(EntityNotFoundException.class,()->{underTest.viewPerson(newPerson.getName());});
//        then
        verify(personaDao).findByname(newPerson.getName());
    }

    @DisplayName("Test para Buscar por ID")
    @Test
    void viewId() throws Exception {
//        given
        personaDao.save(newPerson);
        given(personaDao.findById(newPerson.getId_persona())).willReturn(Optional.of(newPerson));
//        when
        PersonaOUTputDto personaID= underTest.viewId(newPerson.getId_persona());
//        then
        verify(personaDao).findById(newPerson.getId_persona());
        assertThat(personaID).isNotNull();
    }
    @DisplayName("Test para Buscar por ID Exceptions")
    @Test
    void viewIdThrowExceptions() throws Exception {
//        given
        personaDao.save(newPerson);
//        when
        assertThrows(EntityNotFoundException.class,()->{underTest.viewId(2);});
//        then
        verify(personaDao).findById(any());
    }


    @DisplayName("Test para Buscar por Nombre")
    @Test
    void CanViewPerson() throws Exception {
        //        give
        List<Persona> peopleList = new ArrayList<>();
        peopleList.add(newPerson);
        given(personaDao.findByname(newPerson.getName())).willReturn(peopleList);
        //when
        underTest.viewPerson(newPerson.getName());
        //then
        verify(personaDao).findByname(newPerson.getName());
        assertThat(newPerson.getName()).isNotNull();
        assertThrows(EntityNotFoundException.class,()->{underTest.viewPerson(isNull());});
    }

    @DisplayName("Test para Actualizar persona")
    @Test
    void update() throws Exception {
//        give
//        When
       when(personaDao.findById(newPerson.getId_persona())).thenReturn(Optional.of(newPerson));
        personaDao.save(newPerson);

        PersonaOUTputDto personaActu= underTest.update(newPerson.getId_persona(),new PersonaINputDto(5,"Delany",
                "jsj45","Delany","daniellita",
                "delany@gmail.com","daniella@gmail.com",
                "Barcelona",true,new Date(),"jpeg",new Date()));

//        then
        assertThat(newPerson).isNotEqualTo(personaActu);
        assertThat(personaActu.getCity()).isEqualTo("Barcelona");
        assertThat(personaActu.getName()).isEqualTo("Delany");
        assertThat(personaActu.getCompany_email()).isEqualTo("delany@gmail.com");
    }
    @DisplayName("Test para Actualizar persona con Exceptions")
    @Test
    void testUpdateThrowsExceptions() throws Exception {

        Optional<Persona> ofResult = Optional.of(newPerson);

        when(personaDao.save((Persona) any())).thenThrow(new EntityNotFoundException("An error occurred",
                HttpStatus.NOT_FOUND, new Date()));
        when(personaDao.findById((Integer) any())).thenReturn(ofResult);

        assertThrows(EntityNotFoundException.class,
                () -> underTest.update(1,
                        new PersonaINputDto(1, "janedoe", "iloveyou", "Name", "Doe", "jane.doe@example.org",
                                "jane.doe@example.org", "Oxford", true, new Date(), "https://example.org/example",
                                new Date())));
        verify(personaDao).save((Persona) any());
        verify(personaDao).findById((Integer) any());
    }


    @DisplayName("Test para Eliminar por ID")
    @Test
    void CanDelete() throws Exception {
//        given
        personaDao.save(newPerson);
        when(personaDao.findById(newPerson.getId_persona())).thenReturn(Optional.of(newPerson));
//        when
        underTest.delete(newPerson.getId_persona());
//        then
        verify(personaDao).delete(newPerson);
        assertThat(newPerson).isNotIn(personaDao);
    }

    @DisplayName("Test para guardar persona")
    @Test
    void CanLoadpersona() throws Exception {

//        given
//        when
        PersonaOUTputDto personLoad = underTest.loadpersona(newPersonaInputDto);
//        then
        assertThat(personLoad).isNotNull();
        assertThat(personLoad.getPerson_id()).isGreaterThan(0);



    }
}