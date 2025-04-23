package br.com.alura.adopet.api.validation.abrigo;

import br.com.alura.adopet.api.dto.DadosCadastroAbrigoDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarAbrigoJaCadastrado implements ValidacaoAbrigo{

    @Autowired
    private AbrigoRepository abrigoRepository;

    public void validar(DadosCadastroAbrigoDTO dadosCadastroAbrigo) {

        boolean jaCadastrado = abrigoRepository.existsByNomeOrTelefoneOrEmail(dadosCadastroAbrigo.nome(), dadosCadastroAbrigo.telefone(), dadosCadastroAbrigo.email());

        if (jaCadastrado) {
            throw new ValidacaoException("Dados já cadastrados para outro abrigo!");
        }
    }
}
