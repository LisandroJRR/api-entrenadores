package com.upiiz.entrenadores.controller;

import com.upiiz.entrenadores.assembler.EntrenadorModelAssembler;
import com.upiiz.entrenadores.model.Entrenador;
import com.upiiz.entrenadores.service.EntrenadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/entrenadores")
@RequiredArgsConstructor
public class EntrenadorController {

    private final EntrenadorService entrenadorService;
    private final EntrenadorModelAssembler assembler;

    @GetMapping
    public CollectionModel<EntityModel<Entrenador>> getAllEntrenadores() {
        List<EntityModel<Entrenador>> entrenadores = entrenadorService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(entrenadores,
                linkTo(methodOn(EntrenadorController.class).getAllEntrenadores()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Entrenador> getEntrenadorById(@PathVariable Long id) {
        Entrenador entrenador = entrenadorService.findById(id);
        return assembler.toModel(entrenador);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Entrenador>> createEntrenador(@RequestBody Entrenador entrenador) {
        Entrenador nuevoEntrenador = entrenadorService.save(entrenador);
        EntityModel<Entrenador> entityModel = assembler.toModel(nuevoEntrenador);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Entrenador>> updateEntrenador(@PathVariable Long id, @RequestBody Entrenador entrenadorDetails) {
        Entrenador entrenadorActualizado = entrenadorService.update(id, entrenadorDetails);
        EntityModel<Entrenador> entityModel = assembler.toModel(entrenadorActualizado);

        return ResponseEntity.ok(entityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEntrenador(@PathVariable Long id) {
        entrenadorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}