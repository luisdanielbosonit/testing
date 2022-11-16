package com.bosonit.training.testing.service;

import com.bosonit.training.testing.dtos.PersonaINputDto;
import com.bosonit.training.testing.dtos.PersonaOUTputDto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Component
@Service
public interface ServicePersona {

    public PersonaOUTputDto viewId(Integer id) throws Exception; //ok

    public List<PersonaOUTputDto> viewPerson(String name) throws Exception ; //ok

    PersonaOUTputDto update(Integer id, PersonaINputDto personaINputDto) throws Exception;//ok

    public void delete(Integer id) throws Exception; //ok

    public List<PersonaOUTputDto> viewall();

    public PersonaOUTputDto loadpersona(PersonaINputDto personaINputDto)throws Exception; //ok

}
