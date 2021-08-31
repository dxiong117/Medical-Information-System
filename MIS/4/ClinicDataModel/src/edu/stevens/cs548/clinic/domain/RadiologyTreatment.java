package edu.stevens.cs548.clinic.domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CollectionTable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

// TODO


@Entity
@Table(name = "Radiology")
@DiscriminatorValue("RA")

public class RadiologyTreatment extends Treatment {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3656673416179492428L;
	
	@GeneratedValue private long rid;
	
	public long getRid() {
		return rid;
	}

	public void setRid(long rid) {
		this.rid = rid;
	}
	

	// TODO JPA annotation

	@ElementCollection
	@Temporal(TemporalType.DATE)
	@CollectionTable(name="Radiology_date") 
	
	
	protected Collection<Date> treatmentDates;

	public Collection<Date> getTreatmentDates() {
		return treatmentDates;
	}

	public void setTreatmentDates(Collection<Date> treatmentDates) {
		this.treatmentDates = treatmentDates;
	}
	
}
