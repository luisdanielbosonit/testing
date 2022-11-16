package com.bosonit.training.testing.controller;


import com.bosonit.training.testing.dtos.PersonaINputDto;
import com.bosonit.training.testing.dtos.PersonaOUTputDto;
import com.bosonit.training.testing.service.ServicePersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/persona")
@CrossOrigin
public class PersonaController {

    @Autowired
    ServicePersona servicepersona;


    @PostMapping("/addperson")
    public PersonaOUTputDto guardarpersona(@RequestBody PersonaINputDto personaINputDto) throws Exception {
        return servicepersona.loadpersona(personaINputDto);
    }

    @GetMapping("get/{id}")
    public PersonaOUTputDto show(@PathVariable Integer id) throws Exception {

        return servicepersona.viewId(id);

    }
    @GetMapping("/view/{name}")
    public List<PersonaOUTputDto> getPersonByUsername(@PathVariable String name) throws Exception {
        return servicepersona.viewPerson(name);
    }


    @PutMapping("/put/{id}")
    public PersonaOUTputDto modificarpersona(@PathVariable Integer id,@RequestBody PersonaINputDto personaINputDto) throws Exception{

        return servicepersona.update(id,personaINputDto);
    }
    @DeleteMapping("/delete/{id}")
    public void deletepersona(@PathVariable Integer id) throws Exception {
        servicepersona.delete(id);
        }

    @GetMapping("/getall")
    public List<PersonaOUTputDto> viewall(){
        return (List<PersonaOUTputDto>) servicepersona.viewall();
    }


}

