package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.DadosAtualizacaoTutorDTO;
import br.com.alura.adopet.api.dto.DadosCadastroTutorDTO;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validation.tutor.ValidacaoTutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorService {

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private List<ValidacaoTutor> validacaoTutors;

    public void cadastrar(DadosCadastroTutorDTO dadosCadastroTutor) {

        validacaoTutors.forEach(v -> v.validar(dadosCadastroTutor));

        Tutor tutor = new Tutor(dadosCadastroTutor);
        tutorRepository.save(tutor);
    }

    public void atualizar(DadosAtualizacaoTutorDTO dadosAtualizacaoTutor) {

        Tutor tutor = tutorRepository.getReferenceById(dadosAtualizacaoTutor.id());

        tutor.atualizar(dadosAtualizacaoTutor);
        tutorRepository.save(tutor);
    }
}
