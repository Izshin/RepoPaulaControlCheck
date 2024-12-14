package org.springframework.samples.petclinic.award;

import static org.junit.jupiter.api.Assertions.assertEquals; //MUCHSIMO CUIDADO CON LOS IMPORT

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.pet.PetType;
import org.springframework.samples.petclinic.surgery.award.AwardAlgorithm;

import org.springframework.samples.petclinic.surgery.award.ValidAwardAlgorithm;
import org.springframework.samples.petclinic.vet.Vet;
import org.springframework.samples.petclinic.visit.Visit;

public class AwardTest {
    // This is your SUT:
    AwardAlgorithm algorithm=new ValidAwardAlgorithm();
    // Please specify as many tests as you need using structures similar to this:
  
    @Test
    public void testEmptySet(){
        // Do something
        HashSet<Visit> testVisits=new HashSet<>();

        // Run the subject under test
        Set<Vet> vets = algorithm.selectAwardedVets(testVisits);

        // Asssert something
        assertEquals(Set.of(), vets);
    }        
    @Test
    public void setShouldNotBeEmpty(){
        // Do something
        Vet vetMaxVisit1=createVet("Pepe", "Garcia");
        Vet vetMaxVisit2=createVet("Antonio", "Lopez");
        Vet vetNoVisits=createVet("Funcionario", "AntiCurro");

        Visit v1=createVisit(vetMaxVisit1);
        Visit v2=createVisit(vetMaxVisit1);
        Visit v3=createVisit(vetMaxVisit2);
        Visit v4=createVisit(vetMaxVisit2);
        HashSet<Visit> testVisits=new HashSet<>();
        testVisits.add(v1);
        testVisits.add(v2);
        testVisits.add(v3);
        testVisits.add(v4);

        // Run the subject under test
        Set<Vet> vets = algorithm.selectAwardedVets(testVisits);

        // Asssert something
        assertEquals(2,vets.size());
    }        

    @Test
    public void vetMostVisitsSelected(){
        // Do something
        Vet vetMostVisits=createVet("Pepe", "Garcia");
        Vet vetOneVisit=createVet("Antonio", "Lopez");
        Visit v1=createVisit(vetMostVisits);
        Visit v2=createVisit(vetMostVisits);
        Visit v3=createVisit(vetOneVisit);
        HashSet<Visit> testVisits=new HashSet<>();
        testVisits.add(v1);
        testVisits.add(v2);
        testVisits.add(v3);

        // Run the subject under test
        Set<Vet> vets = algorithm.selectAwardedVets(testVisits);

        // Asssert something
        assertEquals(true, vets.contains(vetMostVisits));
    }        

    @Test
    public void vetOneVisitNotSelected(){
        // Do something
        Vet vetMostVisits=createVet("Pepe", "Garcia");
        Vet vetOneVisit=createVet("Antonio", "Lopez");
        Visit v1=createVisit(vetMostVisits);
        Visit v2=createVisit(vetMostVisits);
        Visit v3=createVisit(vetOneVisit);
        HashSet<Visit> testVisits=new HashSet<>();
        testVisits.add(v1);
        testVisits.add(v2);
        testVisits.add(v3);

        // Run the subject under test
        Set<Vet> vets = algorithm.selectAwardedVets(testVisits);

        // Asssert something
        assertEquals(false, vets.contains(vetOneVisit));
    }        

    @Test
    public void vetMaxVisitsSelected(){
        // Do something
        Vet vetMaxVisit1=createVet("Pepe", "Garcia");
        Vet vetMaxVisit2=createVet("Antonio", "Lopez");
        Vet vetNoVisits=createVet("Funcionario", "AntiCurro");

        Visit v1=createVisit(vetMaxVisit1);
        Visit v2=createVisit(vetMaxVisit1);
        Visit v3=createVisit(vetMaxVisit2);
        Visit v4=createVisit(vetMaxVisit2);
        HashSet<Visit> testVisits=new HashSet<>();
        testVisits.add(v1);
        testVisits.add(v2);
        testVisits.add(v3);
        testVisits.add(v4);

        // Run the subject under test
        Set<Vet> vets = algorithm.selectAwardedVets(testVisits);

        // Asssert something
        assertEquals(Set.of(vetMaxVisit1,vetMaxVisit2), vets);
    }   
    @Test
    public void vetNoVisitsNotSelected(){
        // Do something
        Vet vetMaxVisit1=createVet("Pepe", "Garcia");
        Vet vetMaxVisit2=createVet("Antonio", "Lopez");
        Vet vetNoVisits=createVet("Funcionario", "AntiCurro");

        Visit v1=createVisit(vetMaxVisit1);
        Visit v2=createVisit(vetMaxVisit1);
        Visit v3=createVisit(vetMaxVisit2);
        Visit v4=createVisit(vetMaxVisit2);
        HashSet<Visit> testVisits=new HashSet<>();
        testVisits.add(v1);
        testVisits.add(v2);
        testVisits.add(v3);
        testVisits.add(v4);

        // Run the subject under test
        Set<Vet> vets = algorithm.selectAwardedVets(testVisits);

        // Asssert something
        assertEquals(false, vets.contains(vetNoVisits));
    }  
   
    // We provide these methods so that you can use them in your tests to 
    // make the creation of valid Visits easier
    public Visit createVisit(Vet vet){
        Visit v=new Visit();
        v.setDatetime(LocalDateTime.now());
        v.setPet(createValidPet());
        v.setVet(vet);
        return v;
    }

    public Vet createVet(String first, String last) {
        Vet v = new Vet();
        v.setFirstName(first);
        v.setLastName(last);
        return v;
    }

    public Pet createValidPet(){
        PetType pt=new PetType();
        pt.setName("Turtle");
        Owner splinter=new Owner();
        splinter.setFirstName("Splinter");
        splinter.setLastName("Master");
        Pet p=new Pet();
        p.setType(pt);
        p.setBirthDate(LocalDate.now());
        p.setOwner(splinter);
        p.setName(List.of("Leo","Donnie","Raph","Mickie").get((int)(Math.random()*4)));        
        return p;
    }

    // This method should not be modified
    public void setAlgorithm(AwardAlgorithm value){
        this.algorithm=value;
        System.out.println(value);
    }
}
