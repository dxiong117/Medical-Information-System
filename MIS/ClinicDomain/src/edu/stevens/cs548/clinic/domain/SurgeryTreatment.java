package edu.stevens.cs548.clinic.domain;

import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Surgery
 *
 */
@Entity
@Table(name = "SURGERYTREATMENT")
@DiscriminatorValue("S")
public class SurgeryTreatment extends Treatment {

	
	private static final long serialVersionUID = 4173146640306267418L;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	
	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public SurgeryTreatment() {
		super();
		this.setTreatmentType("S");
	}
   
	
	@Override
	public <T> T export(ITreatmentExporter<T> visitor) {
		// TODO Auto-generated method stub
		return visitor.exportSurgery(this.getId(), 
									this.getPatient().getId(),
									this.getProvider().getId(),
									this.getDiagnosis(),
									this.getDate());
				
	}
	
   
}