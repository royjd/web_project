package dao;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MessageUserEntity.class)
public abstract class MessageUserEntity_ {

	public static volatile SingularAttribute<MessageUserEntity, Boolean> newMessage;
	public static volatile SingularAttribute<MessageUserEntity, Long> id;
	public static volatile SingularAttribute<MessageUserEntity, MessageEntity> message;
	public static volatile SingularAttribute<MessageUserEntity, UserEntity> user;

}

