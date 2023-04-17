package repository;

import entity.Nsx;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import utils.JpaUtil;

import java.util.List;

public class NsxRepository {

    private EntityManager em;

    public NsxRepository(){
        this.em = JpaUtil.getEntityManager();
    }

    public void insert(Nsx nsx) throws Exception
    {
        try{
            em.getTransaction().begin();
            em.persist(nsx);
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
            throw e;
        }
    }

    public void update(Nsx nsx) throws Exception
    {
        try{
            em.getTransaction().begin();
            em.merge(nsx);
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
            throw e;
        }
    }

    public void delete(Nsx nsx) throws Exception
    {
        try{
            em.getTransaction().begin();
            em.remove(nsx);
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
            throw e;
        }
    }

    public List<Nsx> getAll(){
        String jpql = "SELECT n FROM Nsx n";
        TypedQuery<Nsx> query =
                this.em.createQuery(jpql,Nsx.class);

        return query.getResultList();
    }

    public Nsx findById(int id){
        return this.em.find(Nsx.class,id);
    }
}
