package edu.stevens.cs548.clinic.service.dto.util;

import edu.stevens.cs548.clinic.domain.DrugTreatment;

public class TreatmentDtoFactory {
	
	ObjectFactory factory;
	
	public TreatmentDtoFactory() {
		factory = new ObjectFactory();
	}
	
	public TreatmentDto createDrugTreatmentDto () {
		TreatmentDto t = factory.createTreatmentDto();
		DrugTreatmentType dt = factory.createDrugTreatmentType();
		t.setDrugTreatment(dt);
		return t;
	}
	
	public TreatmentDto createTreatmentDto (DrugTreatment t) {
		/*
		 * TODO: Initialize the DTO from the drug treatment.
		 */
		return null;
	}
	
	/*
	 * TODO: Repeat for other treatments.
	 */

}
