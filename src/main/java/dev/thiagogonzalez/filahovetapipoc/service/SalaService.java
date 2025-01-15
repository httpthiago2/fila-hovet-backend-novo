package dev.thiagogonzalez.filahovetapipoc.service;

import dev.thiagogonzalez.filahovetapipoc.domain.model.Sala;
import dev.thiagogonzalez.filahovetapipoc.domain.repository.SalaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalaService {

    private final SalaRepository salaRepository;


    public SalaService(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    public List<Sala> findAll() {
        return salaRepository.findAll();
    }

    public Sala findById(Long id) {
        return salaRepository.findById(id).orElse(null);
    }

    public Sala save(Sala sala) {
        return salaRepository.save(sala);
    }

    public Boolean delete(Long id) {
        salaRepository.deleteById(id);
        return Boolean.TRUE;
    }
}
