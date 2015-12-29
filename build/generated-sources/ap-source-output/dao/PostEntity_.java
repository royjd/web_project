package dao;

import java.sql.Date;
import java.sql.Time;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PostEntity.class)
public abstract class PostEntity_ {

	public static volatile SingularAttribute<PostEntity, Time> modifiedTime;
	public static volatile SingularAttribute<PostEntity, Date> createdDate;
	public static volatile ListAttribute<PostEntity, CommentEntity> comments;
	public static volatile SingularAttribute<PostEntity, UserEntity> author;
	public static volatile SingularAttribute<PostEntity, Date> modifiedDate;
	public static volatile SingularAttribute<PostEntity, Time> createdTime;
	public static volatile SingularAttribute<PostEntity, Long> id;
	public static volatile SingularAttribute<PostEntity, String> body;
	public static volatile SingularAttribute<PostEntity, String> title;
	public static volatile SingularAttribute<PostEntity, UserEntity> target;

}

