package uol.compass.avaliacao.controller;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import uol.compass.avaliacao.dto.request.PartyFormDTO;
import uol.compass.avaliacao.dto.response.MessageResponseDTO;
import uol.compass.avaliacao.service.impl.PartyServiceImpl;
import uol.compass.avaliacao.utils.PartyUtils;

@ExtendWith(MockitoExtension.class)
class PartyControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PartyServiceImpl partyService;

    @InjectMocks
    private PartyController partyController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(partyController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((viewName, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenPOSTIsCalledThenAPartyShouldBeCreated() throws Exception {
        PartyFormDTO formDTO = PartyUtils.createFakeFormDTO();
        MessageResponseDTO expectedMessageResponseDTO = MessageResponseDTO.builder()
                .message(String.format("Party with id %s created!", 1))
                .build();

        Mockito.when(partyService.create(formDTO)).thenReturn(expectedMessageResponseDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/partidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(PartyUtils.asJsonString(formDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath(
                        "$.message", Is.is(expectedMessageResponseDTO.getMessage())));
    }

    @Test
    void whenPOSTIsCalledThenReturnBadRequest() throws Exception {
        PartyFormDTO formDTO = PartyUtils.createFakeFormDTO();
        formDTO.setName("");

        mockMvc.perform(MockMvcRequestBuilders.post("/partidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(PartyUtils.asJsonString(formDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void whenPUTIsCalledThenAPartyShouldBeUpdated() throws Exception {
        Long id = 1L;
        PartyFormDTO formDTO = PartyUtils.createFakeFormDTO();
        MessageResponseDTO expectedMessageResponseDTO = MessageResponseDTO.builder()
                .message(String.format("Party with id %s updated!", id))
                .build();

        Mockito.when(partyService.update(id, formDTO)).thenReturn(expectedMessageResponseDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/partidos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(PartyUtils.asJsonString(formDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(
                        "$.message", Is.is(expectedMessageResponseDTO.getMessage())));
    }

    @Test
    void whenFindIsCalledThenReturnStatusOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/partidos"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void whenFindIsCalledIdPathThenReturnStatusOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/partidos/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void whenDeleteIsCalledIdPathThenReturnStatusOkWith() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/partidos/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}