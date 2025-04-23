package br.com.alura.adopet.api.validation.adocao;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;

public interface ValidacaoAdocao {

    void validar(SolicitacaoAdocaoDTO solicitacaoAdocao);
}
