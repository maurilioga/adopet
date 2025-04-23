package br.com.alura.adopet.api.validation.abrigo;


import br.com.alura.adopet.api.dto.DadosCadastroAbrigoDTO;

public interface ValidacaoAbrigo {

    void validar(DadosCadastroAbrigoDTO dadosCadastroAbrigo);
}
