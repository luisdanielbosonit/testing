package com.bosonit.training.testing.service;


import com.bosonit.training.testing.Exception.EntityNotFoundException;
import com.bosonit.training.testing.dtos.PersonaINputDto;
import com.bosonit.training.testing.dtos.PersonaOUTputDto;
import com.bosonit.training.testing.entity.Persona;
import com.bosonit.training.testing.repository.PersonaDao;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ServiceImplPersonaTest {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    public PersonaDao personaDao;
//    public AutoCloseable autoCloseable;
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
//    @AfterEach
//    void tearDown() throws Exception{
//        autoCloseable.close();
//    }

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


    @Test
    void viewId() throws Exception {
//        given
        personaDao.save(newPerson);
        given(personaDao.findById(newPerson.getId_persona())).willReturn(Optional.of(newPerson));
//        when
        underTest.viewId(newPerson.getId_persona());
//        then
        verify(personaDao).findById(newPerson.getId_persona());

//         assertThat(newPerson).isEqualTo(underTest.viewId(newPerson.getId_persona()));
        //assertEquals(newPerson,personaDao.findById(1));

    }

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


    }

    @Test
    @Disabled
    void update() {
        //        give
//        When
//        then

    }

    @Test
    void CanDelete() throws Exception {
//        given
        personaDao.save(newPerson);
        given(personaDao.findById(newPerson.getId_persona())).willReturn(Optional.of(newPerson));
//        when
        underTest.delete(newPerson.getId_persona());
//        then
        verify(personaDao).delete(newPerson);
    }

    @DisplayName("Test para guardar persona")
    @Test
    void CanLoadpersona() throws Exception {
//        give
//        when
        PersonaOUTputDto personLoad = underTest.loadpersona(newPersonaInputDto);
//        then
        assertThat(personLoad).isNotNull();
        assertThat(personLoad.getPerson_id()).isGreaterThan(0);


    }
}