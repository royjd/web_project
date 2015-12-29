package dao;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CommentEntity.class)
public abstract class CommentEntity_ extends dao.PostEntity_ {

	public static volatile SingularAttribute<CommentEntity, PostEntity> postMain;
	public static volatile SingularAttribute<CommentEntity, MediaEntity> media;
	public static volatile SingularAttribute<CommentEntity, PostEntity> postParent;

}

