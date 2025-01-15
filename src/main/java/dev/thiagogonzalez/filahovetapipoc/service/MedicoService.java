package dev.thiagogonzalez.filahovetapipoc.service;

import dev.thiagogonzalez.filahovetapipoc.domain.model.Medico;
import dev.thiagogonzalez.filahovetapipoc.domain.repository.MedicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public List<Medico> findAll() {
        return medicoRepository.findAll();
    }

    public Medico findById(Long id) {
        return medicoRepository.findById(id).orElse(null);
    }

    public Medico save(Medico sala) {
        return medicoRepository.save(sala);
    }

    public Boolean delete(Long id) {
        medicoRepository.deleteById(id);
        return Boolean.TRUE;
    }
}
