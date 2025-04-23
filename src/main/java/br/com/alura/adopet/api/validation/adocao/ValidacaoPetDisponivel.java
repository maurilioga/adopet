package br.com.alura.adopet.api.validation.adocao;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoPetDisponivel implements ValidacaoAdocao{

    @Autowired
    PetRepository petRepository;

    public void validar(SolicitacaoAdocaoDTO solicitacaoAdocao) {

        if (petRepository.getAdotadoById(solicitacaoAdocao.idPet())) {
            throw new ValidacaoException("Pet já foi adotado!");
        }
    }
}
