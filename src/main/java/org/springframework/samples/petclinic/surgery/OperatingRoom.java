package org.springframework.samples.petclinic.surgery;

import java.util.Set;

import org.springframework.samples.petclinic.model.NamedEntity;
import org.springframework.samples.petclinic.pet.PetType;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class OperatingRoom extends NamedEntity {
    
    String description;

    @ManyToMany
    Set<PetType> validFor;    
}
