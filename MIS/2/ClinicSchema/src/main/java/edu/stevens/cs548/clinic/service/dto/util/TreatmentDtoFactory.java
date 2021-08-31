package edu.stevens.cs548.clinic.service.dto.util;

import edu.stevens.cs548.clinic.service.dto.DrugTreatmentType;
import edu.stevens.cs548.clinic.service.dto.ObjectFactory;
import edu.stevens.cs548.clinic.service.dto.RadiologyTreatmentType;
import edu.stevens.cs548.clinic.service.dto.SurgeryTreatmentType;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;

public class TreatmentDtoFactory {
	
	ObjectFactory factory;
	
	public TreatmentDtoFactory() {
		factory = new ObjectFactory();
	}
	
	public TreatmentDto createDrugTreatmentDto () {
		DrugTreatmentType drugTreatmentType = factory.createDrugTreatmentType();
		TreatmentDto treatmentDto = factory.createTreatmentDto();
		treatmentDto.setDrugTreatment(drugTreatmentType);
		return treatmentDto;
	}
	
	public TreatmentDto createSurgeryTreatmentDto () {
		
		SurgeryTreatmentType surgeryTreatmentType = factory.createSurgeryTreatmentType();
		TreatmentDto treatmentDto = factory.createTreatmentDto();
		treatmentDto.setSurgeryTreatment(surgeryTreatmentType);
		return treatmentDto;
	}
	
	public TreatmentDto createRadiologyTreatmentDto () {
		
		RadiologyTreatmentType radiologyTreatmentType = factory.createRadiologyTreatmentType();
		TreatmentDto treatmentDto = factory.createTreatmentDto();
		treatmentDto.setRadiologyTreatment(radiologyTreatmentType);
		return treatmentDto;
	}
	
	/*
	 * TODO: Repeat for other treatments.
	 */

}
