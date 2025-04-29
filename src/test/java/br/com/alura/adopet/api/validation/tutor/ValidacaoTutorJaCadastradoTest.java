package br.com.alura.adopet.api.validation.tutor;

import br.com.alura.adopet.api.dto.DadosCadastroTutorDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ValidacaoTutorJaCadastradoTest {

    @InjectMocks
    private ValidacaoTutorJaCadastrado validacaoTutorJaCadastrado;

    @Mock
    private TutorRepository tutorRepository;

    @Mock
    private DadosCadastroTutorDTO dto;

    @Test
    void testValidarTutorNaoCadastrado() {

        given(tutorRepository.existsByTelefoneOrEmail(dto.telefone(), dto.email())).willReturn(false);

        assertDoesNotThrow(() -> validacaoTutorJaCadastrado.validar(dto));
    }

    @Test
    void testValidarTutorCadastrado() {

        given(tutorRepository.existsByTelefoneOrEmail(dto.telefone(), dto.email())).willReturn(true);

        assertThrows(ValidacaoException.class, () -> validacaoTutorJaCadastrado.validar(dto));
    }
}