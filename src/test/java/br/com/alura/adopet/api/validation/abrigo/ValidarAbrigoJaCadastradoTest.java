package br.com.alura.adopet.api.validation.abrigo;

import br.com.alura.adopet.api.dto.DadosCadastroAbrigoDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ValidarAbrigoJaCadastradoTest {

    @InjectMocks
    private ValidarAbrigoJaCadastrado validarAbrigoJaCadastrado;

    @Mock
    private AbrigoRepository abrigoRepository;

    @Mock
    private DadosCadastroAbrigoDTO dto;

    @Test
    void testValidarAbrigoNaoCadastrado() {

        given(abrigoRepository.existsByNomeOrTelefoneOrEmail(dto.nome(), dto.telefone(), dto.email())).willReturn(false);

        assertDoesNotThrow(() -> validarAbrigoJaCadastrado.validar(dto));
    }

    @Test
    void testValidarAbrigoCadastrado() {

        given(abrigoRepository.existsByNomeOrTelefoneOrEmail(dto.nome(), dto.telefone(), dto.email())).willReturn(true);

        assertThrows(ValidacaoException.class, () -> validarAbrigoJaCadastrado.validar(dto));
    }
}