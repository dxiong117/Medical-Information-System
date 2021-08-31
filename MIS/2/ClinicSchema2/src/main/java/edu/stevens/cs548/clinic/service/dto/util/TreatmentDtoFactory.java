package edu.stevens.cs548.clinic.service.dto.util;

import javax.xml.bind.JAXBElement;

import edu.stevens.cs548.clinic.service.dto.DrugTreatmentType;
import edu.stevens.cs548.clinic.service.dto.ObjectFactory;
import edu.stevens.cs548.clinic.service.dto.ProviderDto;
import edu.stevens.cs548.clinic.service.dto.RadiologyTreatmentType;
import edu.stevens.cs548.clinic.service.dto.SurgeryTreatmentType;

public class TreatmentDtoFactory {
	
	ObjectFactory factory;
	
	public TreatmentDtoFactory() {
		factory = new ObjectFactory();
	}
	
	/*public TreatmentDto createTreatmentDto () 
		//return factory.createTreatmentDto();
	//}
	
	/*public JAXBElement<TreatmentType> createTreatmentDto (TreatmentType Treatment) {
		return factory.createDrugTreatmentDto(Treatment);
	}
	*/
	
	public DrugTreatmentType createDrugTreatmentType() {
		return factory.createDrugTreatmentType();
	}
	
	public JAXBElement<DrugTreatmentType> createDrugTreatmentDto (DrugTreatmentType drugTreatment) {
		return factory.createDrugTreatmentDto(drugTreatment);
	}
	
	/*
	 * TODO: Repeat for other treatments.
	 */
	
	public SurgeryTreatmentType createSurgeryTreatmentType() {
		return factory.createSurgeryTreatmentType();
	}
	
	public JAXBElement<SurgeryTreatmentType> createSurgeryTreatmentDto (SurgeryTreatmentType surgeryTreatment) {
		return factory.createSurgeryTreatmentDto(surgeryTreatment);
	}
	
	public RadiologyTreatmentType createRadiologyTreatmentType() {
		return factory.createRadiologyTreatmentType();
	}
	
	public JAXBElement<RadiologyTreatmentType> createRadiologyTreatmentDto (RadiologyTreatmentType radiologyTreatment) {
		return factory.createRadiologyTreatmentDto(radiologyTreatment);
	}

	
	
}
