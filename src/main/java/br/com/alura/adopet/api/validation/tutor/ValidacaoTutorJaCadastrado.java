package br.com.alura.adopet.api.validation.tutor;

import br.com.alura.adopet.api.dto.DadosCadastroTutorDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoTutorJaCadastrado implements ValidacaoTutor{

    @Autowired
    private TutorRepository tutorRepository;

    public void validar(DadosCadastroTutorDTO dadosCadastroTutor) {

        boolean telefoneJaCadastrado = tutorRepository.existsByTelefone(dadosCadastroTutor.telefone());
        boolean emailJaCadastrado = tutorRepository.existsByEmail(dadosCadastroTutor.email());

        if (telefoneJaCadastrado || emailJaCadastrado) {
            throw new ValidacaoException("Dados já cadastrados para outro tutor!");
        }
    }
}
