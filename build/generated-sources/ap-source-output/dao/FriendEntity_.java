package dao;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FriendEntity.class)
public abstract class FriendEntity_ {

	public static volatile SingularAttribute<FriendEntity, UserEntity> owner;
	public static volatile SingularAttribute<FriendEntity, UserEntity> friend;
	public static volatile SingularAttribute<FriendEntity, Boolean> accepted;
	public static volatile SingularAttribute<FriendEntity, Long> id;

}

