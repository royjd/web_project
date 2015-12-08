package dao;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(LocalisationEntity.class)
public abstract class LocalisationEntity_ {

	public static volatile SingularAttribute<LocalisationEntity, String> zipcode;
	public static volatile SingularAttribute<LocalisationEntity, String> stat;
	public static volatile SingularAttribute<LocalisationEntity, String> city;
	public static volatile SingularAttribute<LocalisationEntity, String> street;
	public static volatile SingularAttribute<LocalisationEntity, Long> id;
	public static volatile SingularAttribute<LocalisationEntity, ExperienceEntity> experience;

}

