package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AprovacaoAdocaoDTO;
import br.com.alura.adopet.api.dto.ReprovacaoAdocaoDTO;
import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.model.*;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validation.adocao.ValidacaoAdocao;
import br.com.alura.adopet.api.validation.adocao.ValidacaoPetAdocaoEmAndamento;
import br.com.alura.adopet.api.validation.adocao.ValidacaoTutorAdocaoEmAndamento;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class AdocaoServiceTest {

    @InjectMocks
    private AdocaoService adocaoService;

    @Mock
    private AdocaoRepository adocaoRepository;

    @Mock
    private PetRepository petRepository;

    @Mock
    private TutorRepository tutorRepository;

    @Mock
    private EmailService emailService;

    @Spy
    private List<ValidacaoAdocao> validacaoAdocaoList = new ArrayList<>();

    @Mock
    private ValidacaoPetAdocaoEmAndamento validacaoPetAdocaoEmAndamento;

    @Mock
    private ValidacaoTutorAdocaoEmAndamento validacaoTutorAdocaoEmAndamento;

    private SolicitacaoAdocaoDTO solicitacaoAdocaoDTO;

    @Mock
    private AprovacaoAdocaoDTO aprovacaoAdocaoDTO;

    @Mock
    private ReprovacaoAdocaoDTO reprovacaoAdocaoDTO;

    @Mock
    private Pet pet;

    @Spy
    private Adocao adocao;

    @Mock
    private Tutor tutor;

    @Mock
    private Abrigo abrigo;

    @Captor
    private ArgumentCaptor<Adocao> adocaoArgumentCaptor;

    @Test
    void testSalvarAdocaoAoSolicitar() {

        this.solicitacaoAdocaoDTO = new SolicitacaoAdocaoDTO(1L, 1L, "Adoção");

        given(petRepository.getReferenceById(solicitacaoAdocaoDTO.idPet())).willReturn(pet);
        given(tutorRepository.getReferenceById(solicitacaoAdocaoDTO.idTutor())).willReturn(tutor);
        given(pet.getAbrigo()).willReturn(abrigo);

        adocaoService.solicitar(solicitacaoAdocaoDTO);

        then(adocaoRepository).should().save(adocaoArgumentCaptor.capture());
        Adocao adocaoSalva = adocaoArgumentCaptor.getValue();
        assertEquals(pet, adocaoSalva.getPet());
        assertEquals(tutor, adocaoSalva.getTutor());
        assertEquals(solicitacaoAdocaoDTO.motivo(), adocaoSalva.getMotivo());
    }

    @Test
    void testValidarAdocaoAoSolicitar() {

        this.solicitacaoAdocaoDTO = new SolicitacaoAdocaoDTO(1L, 1L, "Adoção");

        given(petRepository.getReferenceById(solicitacaoAdocaoDTO.idPet())).willReturn(pet);
        given(tutorRepository.getReferenceById(solicitacaoAdocaoDTO.idTutor())).willReturn(tutor);
        given(pet.getAbrigo()).willReturn(abrigo);

        validacaoAdocaoList.add(validacaoPetAdocaoEmAndamento);
        validacaoAdocaoList.add(validacaoTutorAdocaoEmAndamento);

        adocaoService.solicitar(solicitacaoAdocaoDTO);

        then(validacaoPetAdocaoEmAndamento).should().validar(solicitacaoAdocaoDTO);
        then(validacaoTutorAdocaoEmAndamento).should().validar(solicitacaoAdocaoDTO);
    }

    @Test
    void testAprovarAdocao() {

        given(adocaoRepository.getReferenceById(aprovacaoAdocaoDTO.idAdocao())).willReturn(adocao);
        given(adocao.getPet()).willReturn(pet);
        given(pet.getAbrigo()).willReturn(abrigo);
        given(adocao.getTutor()).willReturn(tutor);
        given(tutor.getNome()).willReturn("Teste");
        given(adocao.getData()).willReturn(LocalDateTime.now());

        adocaoService.aprovar(aprovacaoAdocaoDTO);

        then(adocao).should().aprovar();
        assertEquals(StatusAdocao.APROVADO, adocao.getStatus());

    }

    @Test
    void testReprovarAdocao() {

        given(adocaoRepository.getReferenceById(reprovacaoAdocaoDTO.idAdocao())).willReturn(adocao);
        given(adocao.getPet()).willReturn(pet);
        given(pet.getAbrigo()).willReturn(abrigo);
        given(adocao.getTutor()).willReturn(tutor);
        given(tutor.getNome()).willReturn("Teste");
        given(adocao.getData()).willReturn(LocalDateTime.now());

        adocaoService.reprovar(reprovacaoAdocaoDTO);

        then(adocao).should().reprovar();
        assertEquals(StatusAdocao.REPROVADO, adocao.getStatus());

    }
}