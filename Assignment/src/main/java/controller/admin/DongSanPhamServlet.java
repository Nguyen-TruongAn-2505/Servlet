package controller.admin;

import entity.ChucVu;
import entity.DongSanPham;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.ChucVuModel;
import model.DongSanPhamModel;
import org.apache.commons.beanutils.BeanUtils;
import repository.DongSanPhamRepository;

import java.io.IOException;
import java.util.List;

@WebServlet({"/admin/dong-san-pham/create",	// GET
        "/admin/dong-san-pham/store",	// POST
        "/admin/dong-san-pham/edit",	// GET
        "/admin/dong-san-pham/update",	// POST
        "/admin/dong-san-pham/delete",	// GET
        "/admin/dong-san-pham/index",})
public class DongSanPhamServlet extends HttpServlet {
    private DongSanPhamRepository dspRepo;

    public DongSanPhamServlet(){
        this.dspRepo = new DongSanPhamRepository();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("create")) {
            this.create(request, response);
        } else if (uri.contains("edit")) {
            this.edit(request, response);
        } else if (uri.contains("delete")) {
            this.delete(request, response);
        } else {
            this.index(request, response);
        }
    }

    private void index(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        List<DongSanPham> ds = this.dspRepo.getAll();
        System.out.println(ds);
        request.setAttribute("ds",ds);
        String view = "/views/admin/dong_san_pham/dongsp.jsp";
        request.getRequestDispatcher(view).forward(request,response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
        request.getRequestDispatcher("/views/admin/dong_san_pham/create.jsp").forward(request,response);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        String idStr =  request.getParameter("id");
        int id = -1;
        try{
            id = Integer.parseInt(idStr);
        }catch (Exception e){
            e.printStackTrace();
            response.sendRedirect("/admin/dong-san-pham/index");
        }

        DongSanPham dsp = this.dspRepo.findById(id);
        if(dsp == null){
            response.sendRedirect("/admin/dong-san-pham/index");
        }
        request.setAttribute("dsp",dsp);
        request.getRequestDispatcher("/views/admin/dong_san_pham/edit.jsp").forward(request,response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        String idStr =  request.getParameter("id");
        int id = -1;
        try{
            id = Integer.parseInt(idStr);
            DongSanPham dsp = this.dspRepo.findById(id);
            this.dspRepo.delete(dsp);
        }catch (Exception e){
            e.printStackTrace();
            response.sendRedirect("/admin/dong-san-pham/index");
        }
        response.sendRedirect("/admin/dong-san-pham/index");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if(uri.contains("store")){
            this.store(request,response);
        } else if (uri.contains("update")) {
            this.update(request,response);
        }else{
            response.sendRedirect("/admin/dong-san-pham/index");
        }
    }

    private void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DongSanPhamModel model = new DongSanPhamModel();
        try {
            BeanUtils.populate(model, request.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }

        DongSanPham dsp = new DongSanPham();
        dsp.setMa(model.getMa());
        dsp.setTen(model.getTen());
        try {
            this.dspRepo.insert(dsp);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("/admin/dong-san-pham/index");
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        DongSanPhamModel model = new DongSanPhamModel();
        try {
            BeanUtils.populate(model, request.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }

        int id = Integer.parseInt(request.getParameter("id"));
        DongSanPham dsp = new DongSanPham();
        dsp.setId(id);
        dsp.setMa(model.getMa());
        dsp.setTen(model.getTen());
        try {
            this.dspRepo.update(dsp);
        } catch (Exception e) {
            // Báo lỗi
            e.printStackTrace();
        }

        response.sendRedirect("/admin/dong-san-pham/index");
    }
}
