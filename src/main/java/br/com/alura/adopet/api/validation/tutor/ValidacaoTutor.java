package br.com.alura.adopet.api.validation.tutor;

import br.com.alura.adopet.api.dto.DadosCadastroTutorDTO;

public interface ValidacaoTutor {

    void validar(DadosCadastroTutorDTO dadosCadastroTutor);
}
