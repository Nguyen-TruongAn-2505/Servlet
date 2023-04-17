package utils;



import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class JpaUtil {
    private static jakarta.persistence.EntityManagerFactory factory;
    private static EntityManager em;

    private static EntityManagerFactory getFactory()
    {
        if (factory == null || factory.isOpen() == false) {
            factory = Persistence.createEntityManagerFactory("Assignment");
        }

        return factory;
    }
    public static EntityManager getEntityManager()
    {
        if (em == null || em.isOpen() == false) {
            em = getFactory().createEntityManager();
        }

        return em;
    }
}
