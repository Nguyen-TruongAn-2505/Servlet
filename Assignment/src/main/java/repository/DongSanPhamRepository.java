package repository;

import entity.DongSanPham;
import entity.MauSac;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import utils.JpaUtil;

import java.util.List;

public class DongSanPhamRepository {

    private EntityManager em;

    public DongSanPhamRepository(){
        this.em = JpaUtil.getEntityManager();
    }

    public void insert(DongSanPham dsp) throws Exception{
        try{
            em.getTransaction().begin();
            em.persist(dsp);
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
            throw e;
        }
    }

    public void update(DongSanPham dsp) throws Exception{
        try{
            em.getTransaction().begin();
            em.merge(dsp);
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
            throw e;
        }
    }

    public void delete(DongSanPham dsp) throws Exception{
        try{
            em.getTransaction().begin();
            em.remove(dsp);
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
            throw e;
        }
    }

    public List<DongSanPham> getAll(){
        String jpql = "SELECT dsp FROM DongSanPham dsp";
        TypedQuery<DongSanPham> query =
                this.em.createQuery(jpql,DongSanPham.class);
        return query.getResultList();
    }

    public DongSanPham findById(int id){
        return this.em.find(DongSanPham.class,id);
    }
}
