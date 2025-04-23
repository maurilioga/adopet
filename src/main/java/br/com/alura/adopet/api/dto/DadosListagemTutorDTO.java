package br.com.alura.adopet.api.dto;

public record DadosListagemTutorDTO(Long id, String nome, String telefone, String email, DadosListagemAdocaoDTO adocao) {
}
