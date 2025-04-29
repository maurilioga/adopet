package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.DadosCadastroPetDTO;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.TipoPet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    @InjectMocks
    private PetService petService;

    @Mock
    private PetRepository petRepository;

    @Mock
    private Abrigo abrigo;

    @Mock
    private Pet pet;

    private DadosCadastroPetDTO dto;

    @Captor
    private ArgumentCaptor<Pet> petArgumentCaptor;

    @Test
    void testCadastrarPet() {

        this.dto = new DadosCadastroPetDTO(TipoPet.CACHORRO, "Nome", "Raca", 1, "Cor", 20F);

        petService.cadastrarPet(abrigo, dto);

        then(petRepository).should().save(petArgumentCaptor.capture());
        Pet petSalvo = petArgumentCaptor.getValue();
        assertEquals(dto.tipoPet(), petSalvo.getTipo());
        assertEquals(dto.nome(), petSalvo.getNome());
        assertEquals(dto.raca(), petSalvo.getRaca());
        assertEquals(dto.idade(), petSalvo.getIdade());
        assertEquals(dto.peso(), petSalvo.getPeso());
    }

    @Test
    void testListarPetsDisponiveis() {

        List<Pet> pets = new ArrayList<>();

        given(petRepository.findByAdotadoFalse()).willReturn(pets);

        assertNotNull(petService.listarPetsDisponveis());
    }
}