package br.com.alura.adopet.api.model;

import br.com.alura.adopet.api.dto.DadosAtualizacaoTutorDTO;
import br.com.alura.adopet.api.dto.DadosCadastroTutorDTO;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tutores")
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String telefone;

    private String email;

    @OneToMany(mappedBy = "tutor")
    private List<Adocao> adocoes;

    public Tutor() {
    }

    public Tutor(DadosCadastroTutorDTO dadosCadastroTutor) {
        this.nome = dadosCadastroTutor.nome();
        this.telefone = dadosCadastroTutor.telefone();
        this.email = dadosCadastroTutor.email();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tutor tutor = (Tutor) o;
        return Objects.equals(id, tutor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Adocao> getAdocoes() {
        return adocoes;
    }

    public void setAdocoes(List<Adocao> adocoes) {
        this.adocoes = adocoes;
    }

    public void atualizar(DadosAtualizacaoTutorDTO dadosAtualizacaoTutor) {

        if (dadosAtualizacaoTutor.nome() != null) {
            this.nome = dadosAtualizacaoTutor.nome();
        }

        if (dadosAtualizacaoTutor.telefone() != null) {
            this.telefone = dadosAtualizacaoTutor.telefone();
        }

        if (dadosAtualizacaoTutor.email() != null) {
            this.email = dadosAtualizacaoTutor.email();
        }

    }
}
