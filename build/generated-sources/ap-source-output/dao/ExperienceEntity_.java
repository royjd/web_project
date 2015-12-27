package dao;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ExperienceEntity.class)
public abstract class ExperienceEntity_ {

	public static volatile SingularAttribute<ExperienceEntity, ProfileEntity> profile;
	public static volatile SingularAttribute<ExperienceEntity, LocalisationEntity> localisation;
	public static volatile SingularAttribute<ExperienceEntity, String> description;
	public static volatile SingularAttribute<ExperienceEntity, Long> id;
	public static volatile SingularAttribute<ExperienceEntity, String> title;
	public static volatile SingularAttribute<ExperienceEntity, Date> realisationDate;

}

