package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.DadosCadastroAbrigoDTO;
import br.com.alura.adopet.api.dto.DadosListagemPetDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.validation.abrigo.ValidacaoAbrigo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbrigoService {

    @Autowired
    private AbrigoRepository abrigoRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private List<ValidacaoAbrigo> validacaoAbrigo;

    public void cadastrarAbrigo(DadosCadastroAbrigoDTO dadosCadastroAbrigo) {

        validacaoAbrigo.forEach(v -> v.validar(dadosCadastroAbrigo));

        Abrigo abrigo = new Abrigo(dadosCadastroAbrigo.nome(), dadosCadastroAbrigo.email(), dadosCadastroAbrigo.telefone());
        abrigoRepository.save(abrigo);
    }

    public List<DadosListagemPetDTO> listarPetsDoAbrigo(String idOuNome) {

        Abrigo abrigo = buscarAbrigo(idOuNome);

        return petRepository.findByAbrigo(abrigo).stream().map(DadosListagemPetDTO::new).toList();
    }

    public Abrigo buscarAbrigo(String idOuNome) {

        Abrigo abrigo;

        try {
            Long id = Long.parseLong(idOuNome);
            abrigo = abrigoRepository.getReferenceById(id);
        } catch (NumberFormatException ex) {
            abrigo = abrigoRepository.findByNome(idOuNome);
        }

        if(abrigo == null) {
            throw new ValidacaoException("Abrigo não encontrado");
        }

        return abrigo;
    }
}
