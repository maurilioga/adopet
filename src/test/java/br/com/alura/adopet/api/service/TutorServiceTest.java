package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.DadosAtualizacaoTutorDTO;
import br.com.alura.adopet.api.dto.DadosCadastroTutorDTO;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validation.tutor.ValidacaoTutor;
import br.com.alura.adopet.api.validation.tutor.ValidacaoTutorJaCadastrado;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class TutorServiceTest {

    @InjectMocks
    private TutorService tutorService;

    @Mock
    private TutorRepository tutorRepository;

    @Spy
    private List<ValidacaoTutor> validacaoTutors = new ArrayList<>();

    @Mock
    private ValidacaoTutorJaCadastrado validacaoTutorJaCadastrado;

    private DadosCadastroTutorDTO dadosCadastroTutorDTO;

    @Mock
    private DadosAtualizacaoTutorDTO dadosAtualizacaoTutorDTO;

    @Mock
    private Tutor tutor;

    @Captor
    private ArgumentCaptor<Tutor> tutorArgumentCaptor;

    @Test
    void testCadastarTutor() {

        this.dadosCadastroTutorDTO = new DadosCadastroTutorDTO("Nome", "123456789", "email@email.com");

        tutorService.cadastrar(dadosCadastroTutorDTO);

        then(tutorRepository).should().save(tutorArgumentCaptor.capture());
        Tutor tutorSalvo = tutorArgumentCaptor.getValue();
        assertEquals(tutorSalvo.getEmail(), dadosCadastroTutorDTO.email());
        assertEquals(tutorSalvo.getNome(), dadosCadastroTutorDTO.nome());
        assertEquals(tutorSalvo.getTelefone(), dadosCadastroTutorDTO.telefone());
    }

    @Test
    void testValidarTutor() {

        this.dadosCadastroTutorDTO = new DadosCadastroTutorDTO("Nome", "123456789", "email@email.com");

        validacaoTutors.add(validacaoTutorJaCadastrado);

        tutorService.cadastrar(dadosCadastroTutorDTO);

        then(validacaoTutorJaCadastrado).should().validar(dadosCadastroTutorDTO);
    }

    @Test
    void testAtualizarTutor() {

        given(tutorRepository.getReferenceById(dadosAtualizacaoTutorDTO.id())).willReturn(tutor);

        tutorService.atualizar(dadosAtualizacaoTutorDTO);

        then(tutor).should().atualizar(dadosAtualizacaoTutorDTO);
    }
}