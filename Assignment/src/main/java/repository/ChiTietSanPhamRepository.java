package repository;

import entity.ChiTietSanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import utils.JpaUtil;

import java.util.List;

public class ChiTietSanPhamRepository {

    private EntityManager em;

    public ChiTietSanPhamRepository(){
        this.em = JpaUtil.getEntityManager();
    }

    public void insert(ChiTietSanPham ctsp) throws Exception {
        try{
            em.getTransaction().begin();
            em.flush();
            ctsp = em.merge(ctsp);
            em.persist(ctsp);
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
            throw e;
        }
    }

    public void update(ChiTietSanPham ctsp) throws Exception {
        try{
            em.getTransaction().begin();
            em.merge(ctsp);
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
            throw e;
        }
    }

    public void delete(ChiTietSanPham ctsp) throws Exception {
        try{
            em.getTransaction().begin();
            em.remove(ctsp);
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
            throw e;
        }
    }

    public List<ChiTietSanPham> getAll(){
        String jpql = "SELECT ctsp FROM ChiTietSanPham ctsp";
        System.out.println(jpql);
        TypedQuery<ChiTietSanPham> query
                = this.em.createQuery(jpql,ChiTietSanPham.class);
        List<ChiTietSanPham> results = query.getResultList();
        System.out.println("Số lượng kết quả trả về: " + results.size());
        return results;
    }

    public ChiTietSanPham findById(int id){
        return this.em.find(ChiTietSanPham.class,id);
    }
}
