package dao;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PhysicalEntity.class)
public abstract class PhysicalEntity_ {

	public static volatile SingularAttribute<PhysicalEntity, Character> gender;
	public static volatile SingularAttribute<PhysicalEntity, ProfileEntity> profile;
	public static volatile SingularAttribute<PhysicalEntity, Integer> weight;
	public static volatile SingularAttribute<PhysicalEntity, Long> id;
	public static volatile SingularAttribute<PhysicalEntity, Integer> height;

}

