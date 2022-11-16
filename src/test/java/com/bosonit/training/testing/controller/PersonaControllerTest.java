package com.bosonit.training.testing.controller;


import com.bosonit.training.testing.dtos.PersonaINputDto;
import com.bosonit.training.testing.dtos.PersonaOUTputDto;
import com.bosonit.training.testing.entity.Persona;
import com.bosonit.training.testing.repository.PersonaDao;
import com.bosonit.training.testing.service.ServiceImplPersona;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import java.util.*;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import java.util.Date;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;




@ContextConfiguration(classes = PersonaController.class)
@ExtendWith(MockitoExtension.class)
@WebMvcTest
class PersonaControllerTest {

    @Autowired
    public MockMvc mockMvc;
    @Mock
    public PersonaDao personaDao;
    @MockBean
    ServiceImplPersona servicePersona;
    public ObjectMapper objectMapper=new ObjectMapper();
    @InjectMocks
    PersonaController personaController;
    Persona newPerson= null;
    PersonaINputDto newPersonaInputDto= null;
    PersonaOUTputDto newPersonaPOutputDto= null;



    @BeforeEach
    public void setUp() {
        newPersonaInputDto = new PersonaINputDto(1,"daniella","jsj45","daniellaAcosta","daniellita",
                "daniella@bosonit.com","daniella@gmail.com",
                "madrid",true,new Date(),"jpeg",new Date());
        newPerson = newPersonaInputDto.transformIntoPersona();
    }
    @DisplayName("Test Guargar")
    @Test
    void guardarpersona() throws Exception {
//        personaDao.save(newPerson);
        Mockito.when(servicePersona.loadpersona(ArgumentMatchers.any())).thenReturn(new PersonaOUTputDto(newPerson));
        String json = objectMapper.writeValueAsString(newPersonaInputDto);

        System.out.println(json);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/addperson")
                .contentType(MediaType.APPLICATION_JSON).content(json);
    }

    @Test
    void testGuardarpersona() throws Exception {
        given(servicePersona.loadpersona(newPersonaInputDto)).willReturn(new PersonaOUTputDto(newPersonaInputDto.transformIntoPersona()));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/addperson")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(newPersonaInputDto));
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void CanshowById() throws Exception {
//          given
        personaDao.save(newPerson);
        when(servicePersona.viewId(newPerson.getId_persona())).thenReturn(new PersonaOUTputDto(newPerson));
//          when
        ResultActions resultActions= mockMvc.perform(get("/get/1"));
//          then
        resultActions.andExpect(status().isOk())
                .andDo(print());

    }
    @Test
    void CangetPersonByUsername() throws Exception {
//        given
        personaDao.save(newPerson);
        List<PersonaOUTputDto> listaPersona= new ArrayList<>();
        when(servicePersona.viewPerson(newPerson.getName())).thenReturn(listaPersona);
//          when
        ResultActions resultActions= mockMvc.perform(get("/view/daniellaAcosta"));
//          then
        resultActions.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[]"))
                .andDo(print());

    }

    @Test
    void testModificarpersona() throws Exception {
        newPerson.setId_persona(1);
        personaDao.save(newPerson);

        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
                .put("/put/{id}", "UriVariable","UriVariable")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();

        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new PersonaINputDto(1, "delany", "dela1425", "delany", "dela",
                        "dela@example.bosonit", "dela@example.bosonit", "valencia", true,new Date(), "jpeg", new Date()
                )));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(personaController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void testDeletepersona() throws Exception {
        doNothing().when(servicePersona).delete((Integer) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/delete/{id}", 1);
        MockMvcBuilders.standaloneSetup(personaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void Canviewall() throws Exception {
//        given
        personaDao.save(newPerson);
        List<PersonaOUTputDto> listaPersona= new ArrayList<>();
        given(servicePersona.viewall()).willReturn(listaPersona);
//        when
        ResultActions response= mockMvc.perform(get("/getall"));
//        then
        response.andExpect(status().isOk()).andExpect(content().contentType("application/json"))
                .andDo(print());
    }
}