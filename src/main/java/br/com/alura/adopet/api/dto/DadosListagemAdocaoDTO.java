package br.com.alura.adopet.api.dto;

import br.com.alura.adopet.api.model.StatusAdocao;

public record DadosListagemAdocaoDTO(Long id, DadosListagemTutorDTO tutor, DadosListagemPetDTO pet, String motivo, StatusAdocao status, String justificativa) {
}
