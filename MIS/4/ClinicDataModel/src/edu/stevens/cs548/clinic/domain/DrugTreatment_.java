package edu.stevens.cs548.clinic.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-03-06T16:57:44.827-0500")
@StaticMetamodel(DrugTreatment.class)
public class DrugTreatment_ extends Treatment_ {
	public static volatile SingularAttribute<DrugTreatment, String> drug;
	public static volatile SingularAttribute<DrugTreatment, Float> dosage;
}
