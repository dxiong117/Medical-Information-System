package edu.stevens.cs548.clinic.domain;

import edu.stevens.cs548.clinic.domain.Provider.ProviderType;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-03-16T17:22:08.501-0400")
@StaticMetamodel(Provider.class)
public class Provider_ {
	public static volatile SingularAttribute<Provider, Long> id;
	public static volatile SingularAttribute<Provider, Long> npi;
	public static volatile SingularAttribute<Provider, String> name;
	public static volatile ListAttribute<Provider, Treatment> treatment;
	public static volatile SingularAttribute<Provider, ProviderType> providerType;
}
