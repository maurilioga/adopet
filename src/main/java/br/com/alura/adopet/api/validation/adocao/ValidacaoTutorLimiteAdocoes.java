package br.com.alura.adopet.api.validation.adocao;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoTutorLimiteAdocoes implements ValidacaoAdocao{

    @Autowired
    AdocaoRepository adocaoRepository;

    @Autowired
    TutorRepository tutorRepository;

    public void validar(SolicitacaoAdocaoDTO solicitacaoAdocao) {

        if(adocaoRepository.countByTutorIdAndStatus(solicitacaoAdocao.idTutor(), StatusAdocao.APROVADO) > 5) {
            throw new ValidacaoException("Tutor chegou ao limite máximo de 5 adoções!");
        }
    }
}
