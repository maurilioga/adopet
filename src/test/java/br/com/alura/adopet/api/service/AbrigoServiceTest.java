package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.DadosCadastroAbrigoDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.validation.abrigo.ValidacaoAbrigo;
import br.com.alura.adopet.api.validation.abrigo.ValidarAbrigoJaCadastrado;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class AbrigoServiceTest {

    @InjectMocks
    private AbrigoService abrigoService;

    @Mock
    private AbrigoRepository abrigoRepository;

    @Mock
    private PetRepository petRepository;

    @Spy
    private List<ValidacaoAbrigo> validacaoAbrigo = new ArrayList<>();

    @Mock
    private ValidarAbrigoJaCadastrado validarAbrigoJaCadastrado;

    @Mock
    private Abrigo abrigo;

    private DadosCadastroAbrigoDTO dadosCadastroAbrigoDTO;

    @Captor
    private ArgumentCaptor<Abrigo> abrigoArgumentCaptor;

    @Test
    void testCadastrarAbrigo() {

        this.dadosCadastroAbrigoDTO = new DadosCadastroAbrigoDTO("Nome", "123456", "email@email.com");

        abrigoService.cadastrarAbrigo(dadosCadastroAbrigoDTO);

        then(abrigoRepository).should().save(abrigoArgumentCaptor.capture());
        Abrigo abrigoSalvo = abrigoArgumentCaptor.getValue();
        assertEquals(dadosCadastroAbrigoDTO.email(), abrigoSalvo.getEmail());
        assertEquals(dadosCadastroAbrigoDTO.nome(), abrigoSalvo.getNome());
        assertEquals(dadosCadastroAbrigoDTO.telefone(), abrigoSalvo.getTelefone());
    }

    @Test
    void testValidarAbrigo() {

        this.dadosCadastroAbrigoDTO = new DadosCadastroAbrigoDTO("Nome", "123456", "email@email.com");

        validacaoAbrigo.add(validarAbrigoJaCadastrado);

        abrigoService.cadastrarAbrigo(dadosCadastroAbrigoDTO);

        then(validarAbrigoJaCadastrado).should().validar(dadosCadastroAbrigoDTO);
    }

    @Test
    void testListarPetsDoAbrigoPorId() {

        given(abrigoRepository.getReferenceById(anyLong())).willReturn(abrigo);

        abrigoService.listarPetsDoAbrigo("1");

        assertNotNull(abrigoService.listarPetsDoAbrigo("1"));
    }

    @Test
    void testListarPetsDoAbrigoPorNome() {

        given(abrigoRepository.findByNome(any())).willReturn(abrigo);

        abrigoService.listarPetsDoAbrigo("teste");

        assertNotNull(abrigoService.listarPetsDoAbrigo("teste"));
    }

    @Test
    void testBuscarAbrigoPorId() {

        given(abrigoRepository.getReferenceById(anyLong())).willReturn(abrigo);

        abrigoService.buscarAbrigo("1");

        assertNotNull(abrigoService.buscarAbrigo("1"));
    }

    @Test
    void testBuscarAbrigoPorNome() {

        given(abrigoRepository.findByNome(anyString())).willReturn(abrigo);

        abrigoService.buscarAbrigo("teste");

        assertNotNull(abrigoService.buscarAbrigo("teste"));
    }

    @Test
    void testBuscarAbrigoNull() {

        given(abrigoRepository.findByNome(anyString())).willReturn(null);

        assertThrows(ValidacaoException.class, () -> abrigoService.buscarAbrigo("teste"));
    }
}