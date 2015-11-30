package dao;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserEntity.class)
public abstract class UserEntity_ {

	public static volatile ListAttribute<UserEntity, FriendEntity> friendedBy;
	public static volatile ListAttribute<UserEntity, MessageEntity> messageS;
	public static volatile SingularAttribute<UserEntity, Long> id;
	public static volatile ListAttribute<UserEntity, MessageUserEntity> messageR;
	public static volatile ListAttribute<UserEntity, FriendEntity> friends;
	public static volatile SingularAttribute<UserEntity, String> username;

}

