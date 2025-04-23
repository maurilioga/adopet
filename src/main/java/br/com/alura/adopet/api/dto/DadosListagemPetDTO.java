package br.com.alura.adopet.api.dto;

import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.TipoPet;

public record DadosListagemPetDTO(
        Long id,
        TipoPet tipoPet,
        String nome,
        String raca,
        Integer idade,
        String cor,
        Float peso,
        Boolean adotado) {

    public DadosListagemPetDTO(Pet pet) {
        this(pet.getId(), pet.getTipo(), pet.getNome(), pet.getRaca(), pet.getIdade(), pet.getCor(), pet.getPeso(), pet.getAdotado());
    }
}
