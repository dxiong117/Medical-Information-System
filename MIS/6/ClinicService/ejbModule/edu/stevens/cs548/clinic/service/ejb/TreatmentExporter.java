package edu.stevens.cs548.clinic.service.ejb;

import java.util.Date;
import java.util.List;

import edu.stevens.cs548.clinic.domain.ITreatmentExporter;
import edu.stevens.cs548.clinic.service.dto.util.DrugTreatmentType;
import edu.stevens.cs548.clinic.service.dto.util.ObjectFactory;
import edu.stevens.cs548.clinic.service.dto.util.RadiologyType;
import edu.stevens.cs548.clinic.service.dto.util.SurgeryType;
import edu.stevens.cs548.clinic.service.dto.util.TreatmentDto;


public class TreatmentExporter implements ITreatmentExporter<TreatmentDto> {
	
	private ObjectFactory factory = new ObjectFactory();
	
	private TreatmentDto dto;
	public TreatmentDto getDto(){
		return dto;
	}
	
	@Override
	public TreatmentDto exportDrugTreatment(long tid, String diagnosis, String drug,
			float dosage) {
		TreatmentDto dto = factory.createTreatmentDto();
		dto.setDiagnosis(diagnosis);
		DrugTreatmentType drugInfo = factory.createDrugTreatmentType();
		drugInfo.setDosage(dosage);
		drugInfo.setName(drug);
		dto.setDrugTreatment(drugInfo);
		return dto;
	}

	@Override
	public TreatmentDto exportRadiology(long tid, String diagnosis, List<Date> dates) {
		 
		TreatmentDto dto = factory.createTreatmentDto();
		dto.setDiagnosis(diagnosis);
		RadiologyType radioInfo = factory.createRadiologyType();
		radioInfo.setDate(dates);
		dto.setRadiology(radioInfo);
		return dto;
		
	}

	@Override
	public TreatmentDto exportSurgery(long tid, String diagnosis, Date date) {
		 
		TreatmentDto dto = factory.createTreatmentDto();
		dto.setDiagnosis(diagnosis);
		SurgeryType surgeryInfo = factory.createSurgeryType();
		surgeryInfo.setDate(date);
		dto.setSurgery(surgeryInfo);
		return dto;
		
	}
	
}