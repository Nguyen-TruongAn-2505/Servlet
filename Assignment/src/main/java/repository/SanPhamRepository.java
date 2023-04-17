package repository;

import entity.SanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import utils.JpaUtil;

import java.util.List;

public class SanPhamRepository {

    private EntityManager em;

    public SanPhamRepository(){
        this.em = JpaUtil.getEntityManager();
    }

    public void insert(SanPham sp) throws Exception
    {
        try{
            em.getTransaction().begin();
            em.persist(sp);
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
            throw e;
        }
    }

    public void update(SanPham sp) throws Exception
    {
        try{
            em.getTransaction().begin();
            em.merge(sp);
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
            throw e;
        }
    }

    public void delete(SanPham sp) throws Exception
    {
        try{
            em.getTransaction().begin();
            em.remove(sp);
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
            throw e;
        }
    }

    public List<SanPham> getAll(){
        String jpql = "SELECT sp FROM SanPham sp";
        TypedQuery<SanPham> query =
                this.em.createQuery(jpql,SanPham.class);

        return query.getResultList();
    }

    public SanPham findById(int id){
        return this.em.find(SanPham.class,id);
    }
}
