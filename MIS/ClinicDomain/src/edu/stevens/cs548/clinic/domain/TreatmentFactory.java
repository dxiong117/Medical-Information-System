package edu.stevens.cs548.clinic.domain;

import java.util.Date;
import java.util.List;

public class TreatmentFactory implements ITreatmentFactory {

	@Override
	public Treatment createDrugTreatment(String diagnosis, String drug, float dosage) {
		DrugTreatment treatment = new DrugTreatment();
		treatment.setDiagnosis(diagnosis);
		treatment.setDrug(drug);
		treatment.setDosage(dosage);
		treatment.setTreatmentType(TreatmentType.DRUG_TREATMENT.getTag());
		return treatment;
	}

	@Override
	public Treatment createSurgery(Date surgeryDate, String diagnosis) {
		SurgeryTreatment treatment = new SurgeryTreatment();
		treatment.setDate(surgeryDate);
		treatment.setDiagnosis(diagnosis);
		treatment.setTreatmentType(TreatmentType.SURGERY.getTag());
		return treatment;
	}

	@Override
	public Treatment createRadiology(List<Date> rdv, String diagnosis) {
		RadiologyTreatment treatment = new RadiologyTreatment();
		treatment.setTreatmentDates(rdv);;
		treatment.setDiagnosis(diagnosis);
		treatment.setTreatmentType(TreatmentType.RADIOLOGY.getTag());
		return treatment;
	}



}