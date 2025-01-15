package dev.thiagogonzalez.filahovetapipoc.service;

import dev.thiagogonzalez.filahovetapipoc.domain.model.Senha;
import dev.thiagogonzalez.filahovetapipoc.domain.repository.SenhaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SenhaService {

    private final SenhaRepository senhaRepository;


    public SenhaService(SenhaRepository senhaRepository) {
        this.senhaRepository = senhaRepository;
    }

    public List<Senha> findAll() {
        return senhaRepository.findAll();
    }

    public Senha findById(Long id) {
        return senhaRepository.findById(id).orElse(null);
    }

    public Senha save(Senha senha) {
        return senhaRepository.save(senha);
    }

    public Boolean delete(Long id) {
        try {
            senhaRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }
}
