package dao;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MessageEntity.class)
public abstract class MessageEntity_ {

	public static volatile SingularAttribute<MessageEntity, String> groupName;
	public static volatile SingularAttribute<MessageEntity, String> subject;
	public static volatile SingularAttribute<MessageEntity, Long> id;
	public static volatile SingularAttribute<MessageEntity, String> content;
	public static volatile ListAttribute<MessageEntity, MessageUserEntity> target;
	public static volatile SingularAttribute<MessageEntity, UserEntity> sendBy;

}

