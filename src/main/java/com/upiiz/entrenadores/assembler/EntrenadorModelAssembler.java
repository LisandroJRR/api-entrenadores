package com.upiiz.entrenadores.assembler;

import com.upiiz.entrenadores.controller.EntrenadorController;
import com.upiiz.entrenadores.model.Entrenador;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EntrenadorModelAssembler implements RepresentationModelAssembler<Entrenador, EntityModel<Entrenador>> {

    @Override
    public EntityModel<Entrenador> toModel(Entrenador entrenador) {

        return EntityModel.of(entrenador,
                linkTo(methodOn(EntrenadorController.class).getEntrenadorById(entrenador.getId())).withSelfRel(),
                linkTo(methodOn(EntrenadorController.class).getAllEntrenadores()).withRel("entrenadores"),
                linkTo(methodOn(EntrenadorController.class).updateEntrenador(entrenador.getId(), null)).withRel("actualizar"),
                linkTo(methodOn(EntrenadorController.class).deleteEntrenador(entrenador.getId())).withRel("eliminar")
        );
    }
}