package repository;

import entity.ChucVu;
import entity.MauSac;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import utils.JpaUtil;

import java.util.List;

public class MauSacRepository {
    private EntityManager em;
    public  MauSacRepository(){
        this.em = JpaUtil.getEntityManager();
    }

    public void insert(MauSac ms) throws Exception{
        try{
            em.getTransaction().begin();
            em.persist(ms);
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
            throw e;
        }
    }

    public void update(MauSac ms) throws Exception{
        try{
            em.getTransaction().begin();
            em.merge(ms);
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
            throw e;
        }
    }

    public void delete(MauSac ms) throws  Exception{
        try{
            em.getTransaction().begin();
            em.remove(ms);
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
            throw e;
        }
    }

    public List<MauSac> getAll(){
        String jpql = "SELECT ms FROM MauSac ms";
        TypedQuery<MauSac> query =
                this.em.createQuery(jpql,MauSac.class);
        return query.getResultList();
    }

    public MauSac findById(int id){
        return this.em.find(MauSac.class, id);
    }
}
