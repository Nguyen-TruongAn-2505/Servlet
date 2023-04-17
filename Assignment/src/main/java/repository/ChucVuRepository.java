package repository;

import entity.ChucVu;
import utils.JpaUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class ChucVuRepository {
    private EntityManager em;

    public ChucVuRepository(){
        this.em = JpaUtil.getEntityManager();
    }

    public void insert(ChucVu cv) throws Exception
    {
        try {
            em.getTransaction().begin();
            em.persist(cv);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw e;
        }
    }


    public void update(ChucVu cv) throws Exception
    {
        try {
            em.getTransaction().begin();
            em.merge(cv);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw e;
        }
    }

    public void delete(ChucVu cv) throws Exception
    {
        try {
            em.getTransaction().begin();
            em.remove(cv);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw e;
        }
    }

    public List<ChucVu> getAll()
    {
        String jpql = "SELECT cv FROM ChucVu cv";
        TypedQuery<ChucVu> query =
                this.em.createQuery(jpql, ChucVu.class);

        return query.getResultList();
    }

    public ChucVu findById(int id)
    {
        return this.em.find(ChucVu.class, id);
    }

}
