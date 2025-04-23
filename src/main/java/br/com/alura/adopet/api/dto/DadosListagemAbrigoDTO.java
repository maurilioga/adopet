package br.com.alura.adopet.api.dto;

import br.com.alura.adopet.api.model.Abrigo;

import java.util.List;

public record DadosListagemAbrigoDTO(
        Long id,
        String nome,
        String telefone,
        String email
) {

    public DadosListagemAbrigoDTO(Abrigo abrigo) {
        this(abrigo.getId(), abrigo.getNome(), abrigo.getTelefone(), abrigo.getEmail());
    }
}
