package edu.stevens.cs548.clinic.domain;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Entity implementation class for Entity: Radiology
 *
 */
@Entity
@Table(name = "RADIOLOGYTREATMENT")
@DiscriminatorValue("R")
public class RadiologyTreatment extends Treatment {

	
	private static final long serialVersionUID = -3656673416179492428L;

	@ElementCollection
	@Temporal(TemporalType.DATE)
	@CollectionTable(name="RADIOLOGYDATE", joinColumns = @JoinColumn(name = "Radiology_fk")) 
	protected List<Date> treatmentDates;
	
	public List<Date> getTreatmentDates() {
		return treatmentDates;
	}

	public void setTreatmentDates(List<Date> treatmentDates) {
		this.treatmentDates = treatmentDates;
	}

	public RadiologyTreatment() {
		super();
		this.setTreatmentType("R");
	}

	@Override
	public <T> T export(ITreatmentExporter<T> visitor) {
		// TODO Auto-generated method stub
		List<Date> dateList = new ArrayList<Date>();
		return visitor.exportRadiology(this.getId(), 
										this.getPatient().getId(),
										this.getProvider().getId(),
										this.getDiagnosis(), 
										dateList);
	}
   
}