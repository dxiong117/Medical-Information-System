package edu.stevens.cs548.clinic.domain;

import java.io.Serializable;

import java.util.List;

//import javax.persistence.CollectionTable;
//import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;

import static javax.persistence.CascadeType.REMOVE;
/**
 * Entity implementation class for Entity: Patient
 *
 */
@NamedQueries({
	@NamedQuery(
		name="SearchPatientByPatientID",
		query="select p from Patient p where p.patientId = :pid"),
	@NamedQuery(
		name="CountPatientByPatientID",
		query="select count(p) from Patient p where p.patientId = :pid"),
	@NamedQuery(
		name = "RemoveAllPatients", 
		query = "delete from Patient p")
})

// TODO
@Entity
@Table(name="Patient")


public class Patient implements Serializable {	
	
	// TODO JPA annotations
	
	@Id @GeneratedValue
	
	private long id;
	
	private long patientId;
	
	private String name;
	
	private static final long serialVersionUID = 1L;
	
	public Patient() {
		super();
	}   
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	// TODO JPA annotations (propagate deletion of patient to treatments)
	//@ElementCollection
	//@Temporal(TemporalType.DATE)
	//@CollectionTable(name="Treatment") 
	
	@OneToMany(cascade = REMOVE, orphanRemoval = true,mappedBy = "patient")
	
	private List<Treatment> treatments;
	
	

	protected List<Treatment> getTreatments() {
		return treatments;
	}

	protected void setTreatments(List<Treatment> treatments) {
		this.treatments = treatments;
	}
	
	/*
	 * Addition and deletion of treatments should be done in the provider aggregate.
	 */

	
		/*
		 * TODO initialize lists
		 */
	}
   

