package edu.stevens.cs548.clinic.domain;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.InheritanceType.JOINED;

/**
 * Entity implementation class for Entity: Treatment
 *
 */
/*
 * TODO
 */
@Entity
@Table(name = "Treatment")
@Inheritance(strategy = JOINED)
@DiscriminatorColumn(name="TREATMENT_TYPE")


public abstract class Treatment implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/*
	 * TODO
	 */
	@Id @GeneratedValue private long Id;
	
	

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		this.Id = id;
	}	
	
	private String diagnosis;
	
	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	
	/*
	 * TODO discriminator column
	 */
	
	@ManyToOne(cascade = { PERSIST, REMOVE })
	@Id
	private Patient patient;

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
		// More logic in the domain model.
	}
	
	@ManyToOne(cascade = { PERSIST, REMOVE })
	@Id
	private Provider providers;
	
	
	public Provider getProvider() {
		return providers;
	}

	public void setProvider(Provider provider) {
		this.providers = provider;
	}
	
	
	/*@ManyToOne(cascade = { PERSIST, REMOVE })	
	@Id
	private String treatmentType;
	
	public String getTreatmentType() {
		return treatmentType;
	}

	public void setTreatmentType(String treatmentType) {
		this.treatmentType = treatmentType;
	}

*/
	public Treatment() {
		super();
	}
   
}
