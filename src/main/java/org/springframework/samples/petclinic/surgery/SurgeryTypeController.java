package org.springframework.samples.petclinic.surgery;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.exceptions.ResourceNotFoundException;
import org.springframework.samples.petclinic.vet.Vet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/surgerytypes")
public class SurgeryTypeController {

    private final SurgeryTypeService surgeryTypeService;

	@Autowired
	public SurgeryTypeController(SurgeryTypeService surgeryTypeService) {
		this.surgeryTypeService=surgeryTypeService;
	}

    @GetMapping
	public ResponseEntity<List<SurgeryType>> findAll() {
		List<SurgeryType> res = (List<SurgeryType>) this.surgeryTypeService.findSurgeryTypes();
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping(value = "{surgeryTypeId}")
	public SurgeryType findById(@PathVariable("surgeryTypeId") int id) {
		SurgeryType tipoCirugia= surgeryTypeService.findSurgeryTypeById(id);
		if(tipoCirugia==null){

			throw new ResourceNotFoundException("No se encontro tipo cirugia con esa id");
		}
		else{
		return tipoCirugia;
	}
	}
}
