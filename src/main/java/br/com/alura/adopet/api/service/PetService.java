package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.DadosCadastroPetDTO;
import br.com.alura.adopet.api.dto.DadosListagemPetDTO;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public void cadastrarPet(Abrigo abrigo, DadosCadastroPetDTO dadosCadastroPet) {

        petRepository.save(new Pet(dadosCadastroPet, abrigo));
    }

    public List<DadosListagemPetDTO> listarPetsDisponveis() {

        List<Pet> pets = petRepository.findByAdotadoFalse();
        return pets.stream().map(DadosListagemPetDTO::new).toList();
    }
}
