package edu.stevens.cs548.clinic.domain;

import static javax.persistence.CascadeType.REMOVE;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;


import edu.stevens.cs548.clinic.domain.ITreatmentDAO.TreatmentExn;

/**
 * Entity implementation class for Entity: Provider
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(
			name = "SearchProviderBynpi", 
			query = "select p from Provider p where p.npi = :pid"
			),
	@NamedQuery(
			name = "CountProviderBynpi", 
			query = "select count(p) from Provider p where p.npi = :pid"
			),
	@NamedQuery(
			name = "RemoveAllProvider", 
			query = "delete from Provider p"
			) 
})
@Table(name = "PROVIDER")

public class Provider implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;
	private String name;
	private long npi;
	private String specialization;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getNpi() {
		return npi;
	}

	public void setNpi(long npi) {
		this.npi = npi;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(mappedBy = "provider", cascade = REMOVE)
	@OrderBy
	private List<Treatment> treatments;

	public List<Treatment> getTreatments() {
		return treatments;
	}

	public void setTreatments(List<Treatment> treatments) {
		this.treatments = treatments;
	}

	public Provider() {
		super();
	}

	@Transient
	private ITreatmentDAO treatmentDAO;

	public void setTreatmentDAO(ITreatmentDAO tdao) {
		this.treatmentDAO = tdao;
	}

	public <T> T exportTreatment(long tid, ITreatmentExporter<T> visitor) throws TreatmentExn {
		// Export a treatment without violated Aggregate pattern
		// Check that the exported treatment is a treatment for this patient.
		Treatment t = treatmentDAO.getTreatment(tid);
		if (t.getProvider() != this) {
			throw new TreatmentExn("Inappropriate treatment access: provider = " + id + ", treatment = " + tid);
		}
		return t.export(visitor);
	}

	public <T> void visitTreatments(ITreatmentExporter<T> visitor) {
		for (Treatment t : this.getTreatments()) {
			t.export(visitor);
		}
	}

	public long addTreatment(Treatment t) {
		// Persist treatment and set forward and backward links
		
		this.getTreatments().add(t);
		if (t.getProvider() != this) {
			t.setProvider(this);
		}
		return t.getId();
	}

	public long addDrugTreatment(String diagnosis, String drug, float dosage, Patient patient) {
		
		DrugTreatment treatment = new DrugTreatment();
		treatment.setDiagnosis(diagnosis);
		treatment.setDosage(dosage);
		treatment.setDrug(drug);
		treatment.setPatient(patient);
		this.addTreatment(treatment);
		return treatment.getId();
	}

	public long addSurgery(String diagnosis, Date date, Patient patient) {
		SurgeryTreatment treatment = new SurgeryTreatment();
		treatment.setDiagnosis(diagnosis);
		treatment.setDate(date);
		treatment.setPatient(patient);
		this.addTreatment(treatment);
		return treatment.getId();
	}

	public long addRadiology(List<Date> radiologydates, Patient patient, String diagnosis) {
		RadiologyTreatment treatment = new RadiologyTreatment();
		treatment.setTreatmentDates(radiologydates);
		treatment.setDiagnosis(diagnosis);
		treatment.setPatient(patient);
		this.addTreatment(treatment);
		return treatment.getId();

	}

}