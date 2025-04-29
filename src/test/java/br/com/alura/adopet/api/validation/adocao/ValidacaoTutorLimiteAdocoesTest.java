package br.com.alura.adopet.api.validation.adocao;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ValidacaoTutorLimiteAdocoesTest {

    @InjectMocks
    private ValidacaoTutorLimiteAdocoes validacaoTutorLimiteAdocoes;

    @Mock
    private AdocaoRepository adocaoRepository;

    @Mock
    private SolicitacaoAdocaoDTO dto;

    @Test
    void testValidarTutorDentroDoLimiteAdocoes() {

        given(adocaoRepository.countByTutorIdAndStatus(dto.idTutor(), StatusAdocao.APROVADO)).willReturn(5);

        assertDoesNotThrow(() -> validacaoTutorLimiteAdocoes.validar(dto));
    }

    @Test
    void testValidarTutorForaDoLimiteAdocoes() {

        given(adocaoRepository.countByTutorIdAndStatus(dto.idTutor(), StatusAdocao.APROVADO)).willReturn(6);

        assertThrows(ValidacaoException.class, () -> validacaoTutorLimiteAdocoes.validar(dto));
    }
}