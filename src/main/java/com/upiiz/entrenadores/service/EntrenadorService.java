package com.upiiz.entrenadores.service;

import com.upiiz.entrenadores.model.Entrenador;
import com.upiiz.entrenadores.repository.EntrenadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EntrenadorService {

    private final EntrenadorRepository repository;

    public List<Entrenador> findAll() {
        return repository.findAll();
    }

    public Entrenador findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrenador no encontrado con id: " + id));
    }

    public Entrenador save(Entrenador entrenador) {
        return repository.save(entrenador);
    }

    public Entrenador update(Long id, Entrenador entrenadorDetails) {
        Entrenador entrenador = findById(id);
        entrenador.setNombre(entrenadorDetails.getNombre());
        entrenador.setEspecialidad(entrenadorDetails.getEspecialidad());
        return repository.save(entrenador);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}