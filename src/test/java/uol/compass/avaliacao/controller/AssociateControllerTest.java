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
import uol.compass.avaliacao.dto.request.AssociateFormDTO;
import uol.compass.avaliacao.dto.response.MessageResponseDTO;
import uol.compass.avaliacao.service.impl.AssociateServiceImpl;
import uol.compass.avaliacao.utils.AssociateUtils;

@ExtendWith(MockitoExtension.class)
class AssociateControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AssociateServiceImpl associateService;

    @InjectMocks
    private AssociateController associateController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(associateController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((viewName, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenPOSTIsCalledThenAnAssociateShouldBeCreated() throws Exception {
        AssociateFormDTO fakeFormDTO = AssociateUtils.createFakeFormDTO();
        MessageResponseDTO expectedMessageResponseDTO = MessageResponseDTO.builder()
                .message(String.format("Associate with id %s created!", 1))
                .build();

        Mockito.when(associateService.create(fakeFormDTO)).thenReturn(expectedMessageResponseDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/associados")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(AssociateUtils.asJsonString(fakeFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath(
                        "$.message", Is.is(expectedMessageResponseDTO.getMessage())));
    }

    @Test
    void whenPOSTIsCalledThenReturnBadRequest() throws Exception {
        AssociateFormDTO fakeFormDTO = AssociateUtils.createFakeFormDTO();
        fakeFormDTO.setName("");

        mockMvc.perform(MockMvcRequestBuilders.post("/associados")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(AssociateUtils.asJsonString(fakeFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void whenPUTIsCalledThenAnAssociateShouldBeUpdated() throws Exception {
        Long id = 1L;
        AssociateFormDTO fakeFormDTO = AssociateUtils.createFakeFormDTO();
        MessageResponseDTO expectedMessageResponseDTO = MessageResponseDTO.builder()
                .message(String.format("Associate with id %s updated!", id))
                .build();

        Mockito.when(associateService.update(id, fakeFormDTO)).thenReturn(expectedMessageResponseDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/associados/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(AssociateUtils.asJsonString(fakeFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(
                        "$.message", Is.is(expectedMessageResponseDTO.getMessage())));
    }

    @Test
    void whenFindIsCalledThenReturnStatusOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/associados"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void whenFindIsCalledIdPathThenReturnStatusOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/associados/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void whenDeleteIsCalledIdPathThenReturnStatusOkWith() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/associados/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }


}