package dao;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PhotoEntity.class)
public abstract class PhotoEntity_ extends dao.MediaTypeEntity_ {

	public static volatile SingularAttribute<PhotoEntity, Boolean> mediumSource;
	public static volatile SingularAttribute<PhotoEntity, Boolean> smallSource;
	public static volatile SingularAttribute<PhotoEntity, String> caption;
	public static volatile SingularAttribute<PhotoEntity, Boolean> largeSource;

}

