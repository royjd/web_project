package dao;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AlbumEntity.class)
public abstract class AlbumEntity_ extends dao.PostEntity_ {

	public static volatile SingularAttribute<AlbumEntity, MediaEntity> cover;
	public static volatile ListAttribute<AlbumEntity, MediaEntity> medias;
	public static volatile SingularAttribute<AlbumEntity, String> localisation;

}

