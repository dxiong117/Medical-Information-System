package edu.stevens.cs548.clinic.service.dto.util;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import edu.stevens.cs548.clinic.service.dto.DrugTreatmentType;
import edu.stevens.cs548.clinic.service.dto.RadiologyTreatmentType;
import edu.stevens.cs548.clinic.service.dto.SurgeryTreatmentType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "diagnosis",
    "patient",
    "provider",
    "drugTreatment",
    "surgeryTreatment",
    "radiologyTreatment"
    
})

@XmlRootElement(name = "treatment-dto")
public class TreatmentDto implements Serializable {
	private final static long serialVersionUID = 1L;
    protected long id;
    @XmlElement(required = true)
    protected String diagnosis;
    protected long patient;
    protected long provider;
    @XmlElement(name = "drug-treatment")
    protected String drugTreatment;
    @XmlElement(name = "surgery-treatment")
    protected String surgeryTreatment;
    @XmlElement(name = "radiology-treatment")
    protected String radiologyTreatment;

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the diagnosis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiagnosis() {
        return diagnosis;
    }

    /**
     * Sets the value of the diagnosis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiagnosis(String value) {
        this.diagnosis = value;
    }

    /**
     * Gets the value of the patient property.
     * 
     */
    public long getPatient() {
        return patient;
    }

    /**
     * Sets the value of the patient property.
     * 
     */
    public void setPatient(long value) {
        this.patient = value;
    }

    /**
     * Gets the value of the provider property.
     * 
     */
    public long getProvider() {
        return provider;
    }

    /**
     * Sets the value of the provider property.
     * 
     */
    public void setProvider(long value) {
        this.provider = value;
    }

    /**
     * Gets the value of the drugTreatment property.
     * 
     * @return
     *     possible object is
     *     {@link DrugTreatmentType }
     *     
     */
    public String getDrugTreatment() {
        return drugTreatment;
    }

    /**
     * Sets the value of the drugTreatment property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrugTreatmentType }
     *     
     */
    
    public void setDrugTreatment(String value) {
        this.drugTreatment = value;
    }
    
    
    public String getSurgeryTreatment() {
        return surgeryTreatment;
    }

    /**
     * Sets the value of the drugTreatment property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrugTreatmentType }
     *     
     */
    
    public void setSurgeryTreatment(String value) {
        this.surgeryTreatment = value;
    }
    
    public String getRadiologyTreatment() {
        return radiologyTreatment;
    }
    /**
     * Sets the value of the drugTreatment property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrugTreatmentType }
     *     
     */
    public void setRadiologyTreatment(String value) {
        this.drugTreatment = value;
    }
    
    
    
    /*public void setDrugTreatment(DrugTreatmentType value) {
        this.drugTreatment = value;
    }
    
    public void setSurgeryTreatment(SurgeryTreatmentType value) {
        this.surgeryTreatment = value;
    }
    
    public void setRadiologyTreatment(RadiologyTreatmentType value) {
        this.radiologyTreatment = value;
    }
    */
}


