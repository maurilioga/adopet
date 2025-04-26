package br.com.alura.adopet.api.validation.adocao;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ValidacaoPetDisponivelTest {

    @InjectMocks
    private ValidacaoPetDisponivel validacaoPetDisponivel;

    @Mock
    private PetRepository petRepository;

    @Mock
    private Pet pet;

    @Mock
    private SolicitacaoAdocaoDTO dto;

    @Test
    public void testValidarPetDisponivel() {

        BDDMockito.given(petRepository.getAdotadoById(dto.idPet())).willReturn(false);

        Assertions.assertDoesNotThrow(() -> validacaoPetDisponivel.validar(dto));
    }

    @Test
    public void testValidarPetIndisponivel() {

        BDDMockito.given(petRepository.getAdotadoById(dto.idPet())).willReturn(true);

        Assertions.assertThrows(ValidacaoException.class,() -> validacaoPetDisponivel.validar(dto));
    }

}