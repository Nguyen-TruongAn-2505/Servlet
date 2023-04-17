package repository;

import entity.KhachHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Id;
import jakarta.persistence.TypedQuery;
import utils.JpaUtil;

import java.util.List;

public class KhachHangRepository {

    private EntityManager em;

    public KhachHangRepository(){
        this.em = JpaUtil.getEntityManager();
    }

    public void insert(KhachHang kh) throws Exception{
        try{
            em.getTransaction().begin();
            em.persist(kh);
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
            throw e;
        }
    }

    public void update(KhachHang kh) throws Exception{
        try{
            em.getTransaction().begin();
            em.merge(kh);
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            em.getTransaction();
            throw e;
        }
    }

    public void delete(KhachHang kh) throws Exception{
        try{
            em.getTransaction().begin();
            em.remove(kh);
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            em.getTransaction();
            throw e;
        }
    }

    public List<KhachHang> getAll(){
        String jpql = "SELECT kh FROM KhachHang kh";
        TypedQuery<KhachHang> query
                = this.em.createQuery(jpql,KhachHang.class);
        return query.getResultList();
    }

    public KhachHang findById(int id){
        return this.em.find(KhachHang.class, id);
    }
}
