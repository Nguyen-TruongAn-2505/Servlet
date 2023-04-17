package controller.admin;

import entity.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.ChiTietSanPhamModel;
import model.DongSanPhamModel;
import org.apache.commons.beanutils.BeanUtils;
import repository.ChiTietSanPhamRepository;

import java.io.IOException;
import java.util.List;

@WebServlet({"/admin/chi-tiet-san-pham/create",	// GET
        "/admin/chi-tiet-san-pham/store",	// POST
        "/admin/chi-tiet-san-pham/edit",	// GET
        "/admin/chi-tiet-san-pham/update",	// POST
        "/admin/chi-tiet-san-pham/delete",	// GET
        "/admin/chi-tiet-san-pham/index",})
public class ChiTietSanPhamServlet extends HttpServlet {
    private ChiTietSanPhamRepository ctspRepo = new ChiTietSanPhamRepository();



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if(uri.contains("create")){
            this.create(request,response);
        } else if (uri.contains("edit")) {
            this.edit(request,response);
        } else if (uri.contains("delete")) {
            this.delete(request,response);
        }else{
            this.index(request,response);
        }
    }

    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        List<ChiTietSanPham> ds = this.ctspRepo.getAll();
        System.out.println(ds);
        request.setAttribute("ds",ds);
        String view = "/views/admin/chi_tiet_san_pham/chitietsanpham.jsp";
        request.getRequestDispatcher(view).forward(request,response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        request.getRequestDispatcher("/views/admin/chi_tiet_san_pham/create.jsp").forward(request,response);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        String idStr = request.getParameter("id");
        int id = -1;
        try{
            id = Integer.parseInt(idStr);
        }catch (Exception e){
            e.printStackTrace();
            response.sendRedirect("/admin/chi-tiet-san-pham/index");
        }
        ChiTietSanPham ctsp = this.ctspRepo.findById(id);
        request.setAttribute("ctsp",ctsp);
        request.getRequestDispatcher("/views/admin/chi_tiet_san_pham/edit.jsp").forward(request,response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        String idStr = request.getParameter("id");
        int id = -1;
        try{
            id = Integer.parseInt(idStr);
            ChiTietSanPham ctsp = this.ctspRepo.findById(id);
            this.ctspRepo.delete(ctsp);
        }catch (Exception e){
            e.printStackTrace();
            response.sendRedirect("/admin/chi-tiet-san-pham/index");
        }
        response.sendRedirect("/admin/chi-tiet-san-pham/index");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if(uri.contains("store")){
            this.store(request,response);
        } else if (uri.contains("update")) {
            this.update(request,response);
        }else{
            response.sendRedirect("/admin/chi-tiet-san-pham/index");
        }
    }

    private void store(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        ChiTietSanPhamModel model = new ChiTietSanPhamModel();
        try {
            BeanUtils.populate(model, request.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }

        ChiTietSanPham ctsp = new ChiTietSanPham();
        SanPham sp = new SanPham();
        sp.setId(model.getSanPhamId());
        ctsp.setSanPham(sp);

        Nsx nsx = new Nsx();
        nsx.setId(model.getNsxId());
        ctsp.setNsx(nsx);

        MauSac mauSac = new MauSac();
        mauSac.setId(model.getMauSacId());
        ctsp.setMauSac(mauSac);

        DongSanPham dsp = new DongSanPham();
        dsp.setId(model.getDongSanPhamId());
        ctsp.setDongSanPham(dsp);

        ctsp.setNamBaoHanh(model.getNamBaoHanh());
        ctsp.setMoTa(model.getMoTa());
        ctsp.setSoLuongTon(model.getSoLuongTon());
        ctsp.setGiaNhap(model.getGiaNhap());
        ctsp.setGiaBan(model.getGiaBan());

        try {
            this.ctspRepo.insert(ctsp);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("/admin/chi-tiet-san-pham/index");
    }

    private void update(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        ChiTietSanPhamModel model = new ChiTietSanPhamModel();
        try {
            BeanUtils.populate(model, request.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
        int id = Integer.parseInt(request.getParameter("id"));
        ChiTietSanPham ctsp = new ChiTietSanPham();
        ctsp.setId(id);

        SanPham sanPham = new SanPham();
        sanPham.setId(model.getId());
        ctsp.setSanPham(sanPham);

        Nsx nsx = new Nsx();
        nsx.setId(model.getId());
        ctsp.setNsx(nsx);

        MauSac mauSac = new MauSac();
        mauSac.setId(model.getId());
        ctsp.setMauSac(mauSac);

        DongSanPham dsp = new DongSanPham();
        dsp.setId(model.getId());
        ctsp.setDongSanPham(dsp);

        ctsp.setNamBaoHanh(model.getNamBaoHanh());
        ctsp.setMoTa(model.getMoTa());
        ctsp.setSoLuongTon(model.getSoLuongTon());
        ctsp.setGiaNhap(model.getGiaNhap());
        ctsp.setGiaBan(model.getGiaBan());

        try {
            this.ctspRepo.update(ctsp);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("/admin/chi-tiet-san-pham/index");
    }
}
