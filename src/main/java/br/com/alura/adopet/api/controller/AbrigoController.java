package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.DadosCadastroAbrigoDTO;
import br.com.alura.adopet.api.dto.DadosCadastroPetDTO;
import br.com.alura.adopet.api.dto.DadosListagemAbrigoDTO;
import br.com.alura.adopet.api.dto.DadosListagemPetDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.service.AbrigoService;
import br.com.alura.adopet.api.service.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/abrigos")
public class AbrigoController {

    @Autowired
    private AbrigoRepository repository;

    @Autowired
    private AbrigoService abrigoService;

    @Autowired
    private PetService petService;

    @GetMapping
    public ResponseEntity<List<DadosListagemAbrigoDTO>> listar() {

        List<Abrigo> abrigoList = repository.findAll();
        return ResponseEntity.ok(abrigoList.stream().map(DadosListagemAbrigoDTO::new).toList());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid DadosCadastroAbrigoDTO abrigo) {

        try {
            abrigoService.cadastrarAbrigo(abrigo);
            return ResponseEntity.ok().build();
        } catch (ValidacaoException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }

    @GetMapping("/{idOuNome}/pets")
    public ResponseEntity<List<DadosListagemPetDTO>> listarPets(@PathVariable String idOuNome) {

        try {
            List<DadosListagemPetDTO> dadosListagemPet = abrigoService.listarPetsDoAbrigo(idOuNome);
            return ResponseEntity.ok(dadosListagemPet);
        } catch (ValidacaoException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{idOuNome}/pets")
    @Transactional
    public ResponseEntity<String> cadastrarPet(@PathVariable String idOuNome, @RequestBody @Valid DadosCadastroPetDTO dadosCadastroPet) {

        try {
            Abrigo abrigo = abrigoService.buscarAbrigo(idOuNome);
            petService.cadastrarPet(abrigo, dadosCadastroPet);
            return ResponseEntity.ok().build();
        } catch (ValidacaoException ex) {
            return ResponseEntity.notFound().build();
        }
    }

}
