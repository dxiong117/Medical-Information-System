package edu.stevens.cs548.clinic.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-03-07T17:36:17.084-0500")
@StaticMetamodel(RadiologyTreatment.class)
public class RadiologyTreatment_ extends Treatment_ {
	public static volatile SingularAttribute<RadiologyTreatment, Long> rid;
	public static volatile CollectionAttribute<RadiologyTreatment, Date> treatmentDates;
}
