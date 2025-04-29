package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.service.AbrigoService;
import br.com.alura.adopet.api.service.PetService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class AbrigoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AbrigoService abrigoService;

    @MockBean
    private PetService petService;

    @Mock
    private Abrigo abrigo;

    @Test
    void testListarAbrigosOk() throws Exception {

        var response = mockMvc.perform(
                get("/abrigos")
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
    }

    @Test
    void testCadastrarAbrigoOk() throws Exception {

        String json = """
                {
                    "nome": "Abrigo xpto",
                    "telefone": "61977777777",
                    "email": "abrigoxpto@email.com.br"
                }
                """;

        var response = mockMvc.perform(
                post("/abrigos")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
    }

    @Test
    void testCadastrarAbrigoBadRequest() throws Exception {

        String json = """
                {}
                """;

        var response = mockMvc.perform(
                post("/abrigos")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(400, response.getStatus());
    }

    @Test
    void testListarPetsOk() throws Exception {

        var response = mockMvc.perform(
                get("/abrigos/1/pets")
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
    }

    @Test
    void testCadastrarPetOk() throws Exception {

        String json = """
                {
                    "tipoPet": "CACHORRO",
                    "nome": "bidu",
                    "raca": "SRD",
                    "idade": "5",
                    "cor": "preto",
                    "peso": "5.45"
                }""";

        var response = mockMvc.perform(
                post("/abrigos/1/pets")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
    }

    @Test
    void testCadastrarPetBadRequest() throws Exception {

        String json = """
                {}
                """;

        var response = mockMvc.perform(
                post("/abrigos/1/pets")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(400, response.getStatus());
    }
}