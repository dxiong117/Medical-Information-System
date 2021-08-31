package edu.stevens.cs548.clinic.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import static javax.persistence.CascadeType.REMOVE;

/**
 * Entity implementation class for Entity: Patient
 *
 */
@NamedQueries({
	@NamedQuery(
		name="SearchProviderByNPI",
		query="select p from Provider p where p.npi = :npi"),
	@NamedQuery(
		name="CountProviderByNPI",
		query="select count(p) from Provider p where p.npi = :npi"),
	@NamedQuery(
		name = "RemoveAllPatients", 
		query = "delete from Patient p")
})

@Entity
@Table(name = "Provider")

public class Provider implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static enum ProviderType {
	 	INTERNIST,
		SURGEON,
		RADIOLOGIST
	};
	
	@Id @GeneratedValue private long id;
	private long npi; 
	
	private String name;
	
	private ProviderType providerType;
	
	public ProviderType getProviderType() {
		return providerType;
	}

	public void setProviderType(ProviderType providerType) {
		this.providerType = providerType;
	}
	
	public long getNpi() {
		return npi;
	}

	public void setNpi(long npi) {
		this.npi = npi;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public Provider() {
		super();
		treatment = Collections.emptyList();		
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@OneToMany(cascade = REMOVE, mappedBy = "providers", orphanRemoval = true)
	private List<Treatment> treatment;

	public List<Treatment> getTreatment() {
		return treatment;
	}

	public void setTreatment(List<Treatment> treatment) {
		this.treatment = treatment;
	}
   
}
