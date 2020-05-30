package hr.vsite.njp;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;

import java.io.Serializable;

public class GenId extends IdentityGenerator {
    public static final String CLASS_NAME = "hr.vsite.njp.GenId";

    public Serializable generate(final SharedSessionContractImplementor session, final Object object)
            throws HibernateException {
        Serializable id = session.getEntityPersister(null, object).getClassMetadata().getIdentifier(object, session);
        return id != null ? id : super.generate(session, object);
    }
}
