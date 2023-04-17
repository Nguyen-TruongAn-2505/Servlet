package repository;

import entity.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import utils.JpaUtil;

import java.util.List;

public class NhanVienRepository {

    private EntityManager em;

    public NhanVienRepository(){
        this.em = JpaUtil.getEntityManager();
    }

    public void insert(NhanVien nv)throws Exception{
        try{
            em.getTransaction().begin();
            em.flush();
            nv = em.merge(nv);
            em.persist(nv);
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
            throw e;
        }
    }

    public void update(NhanVien nv) throws Exception{
        try{
            em.getTransaction().begin();
            em.merge(nv);
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
            throw e;
        }
    }

    public void delete(NhanVien nv) throws Exception{
        try {
            em.getTransaction().begin();
            em.remove(nv);
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
            throw e;
        }
    }

    public List<NhanVien> gatAll(){
        String jpql = "SELECT nv FROM NhanVien nv";
        TypedQuery<NhanVien> query =
                this.em.createQuery(jpql,NhanVien.class);
        return query.getResultList();
    }

    public NhanVien findById(int id){
        return this.em.find(NhanVien.class,id);
    }
}
