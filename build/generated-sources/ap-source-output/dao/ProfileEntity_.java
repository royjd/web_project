package dao;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProfileEntity.class)
public abstract class ProfileEntity_ {

	public static volatile SingularAttribute<ProfileEntity, String> firstname;
	public static volatile SingularAttribute<ProfileEntity, String> phone;
	public static volatile SingularAttribute<ProfileEntity, Long> id;
	public static volatile SingularAttribute<ProfileEntity, PhysicalEntity> physical;
	public static volatile SingularAttribute<ProfileEntity, UserEntity> user;
	public static volatile ListAttribute<ProfileEntity, ExperienceEntity> experiences;
	public static volatile SingularAttribute<ProfileEntity, String> lastname;

}

