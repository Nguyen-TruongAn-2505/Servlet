package repository;

import entity.ChucVu;
import entity.CuaHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import utils.JpaUtil;

import java.util.List;

public class CuaHangRepository {
    private EntityManager em;

    public CuaHangRepository(){
        this.em = JpaUtil.getEntityManager();
    }

    public void insert(CuaHang ch) throws Exception{
        try{
            em.getTransaction().begin();
            em.persist(ch);
            em.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
            throw e;
        }
    }

    public void update(CuaHang ch)throws Exception{
        try {
            em.getTransaction().begin();
            em.merge(ch);
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
            throw e;
        }
    }

    public void delete(CuaHang ch)throws Exception{
        try {
            em.getTransaction().begin();
            em.remove(ch);
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
            throw e;
        }
    }

    public List<CuaHang> getAll(){
        String jpql = "SELECT ch FROM CuaHang ch";
        TypedQuery<CuaHang> query =
                this.em.createQuery(jpql,CuaHang.class);
        return query.getResultList();
    }

    public CuaHang findById(int id){
        return this.em.find(CuaHang.class,id);
    }
}
