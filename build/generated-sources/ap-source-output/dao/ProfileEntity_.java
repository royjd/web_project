package dao;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProfileEntity.class)
public abstract class ProfileEntity_ {

	public static volatile SingularAttribute<ProfileEntity, String> lastName;
	public static volatile SingularAttribute<ProfileEntity, Date> birthDay;
	public static volatile SingularAttribute<ProfileEntity, String> country;
	public static volatile SingularAttribute<ProfileEntity, String> city;
	public static volatile SingularAttribute<ProfileEntity, MediaEntity> pictureCover;
	public static volatile SingularAttribute<ProfileEntity, String> description;
	public static volatile SingularAttribute<ProfileEntity, MediaEntity> pictureProfile;
	public static volatile ListAttribute<ProfileEntity, ExperienceEntity> experiences;
	public static volatile SingularAttribute<ProfileEntity, String> firstName;
	public static volatile SingularAttribute<ProfileEntity, String> phone;
	public static volatile SingularAttribute<ProfileEntity, UserEntity> profileOwner;
	public static volatile SingularAttribute<ProfileEntity, Long> id;
	public static volatile SingularAttribute<ProfileEntity, PhysicalEntity> physical;

}

