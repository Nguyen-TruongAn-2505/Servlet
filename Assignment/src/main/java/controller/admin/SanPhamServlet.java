package controller.admin;

import entity.ChucVu;
import entity.SanPham;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.ChucVuModel;
import model.SanPhamModel;
import org.apache.commons.beanutils.BeanUtils;
import repository.SanPhamRepository;

import java.io.IOException;
import java.util.List;

@WebServlet({"/admin/san-pham/create",	// GET
        "/admin/san-pham/store",	// POST
        "/admin/san-pham/edit",	// GET
        "/admin/san-pham/update",	// POST
        "/admin/san-pham/delete",	// GET
        "/admin/san-pham/index",})
public class SanPhamServlet extends HttpServlet {
    private SanPhamRepository spRepo;
    public SanPhamServlet(){
        super();
        this.spRepo = new SanPhamRepository();
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

    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        List<SanPham> ds = this.spRepo.getAll();
        System.out.println(ds);
        request.setAttribute("ds",ds);
        String view = "/views/admin/san_pham/sanpham.jsp";
        request.getRequestDispatcher(view).forward(request,response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        request.getRequestDispatcher("/views/admin/san_pham/create.jsp").forward(request,response);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        String idStr = request.getParameter("id");
        int id = -1;
        try{
            id = Integer.parseInt(idStr);
        }catch (Exception e){
            e.printStackTrace();
            response.sendRedirect("/admin/san-pham/index");
        }
        SanPham sp = this.spRepo.findById(id);
        if(sp == null){
            response.sendRedirect("/admin/san-pham/index");
        }
        request.setAttribute("sp",sp);
        request.getRequestDispatcher("/views/admin/san_pham/edit.jsp").forward(request,response);

    }

    private void delete(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        String idStr = request.getParameter("id");
        int id = -1;
        // id BẮT BUỘC phải truyền
        // id phải là số
        try {
            id = Integer.parseInt(idStr);
            SanPham sp = this.spRepo.findById(id);
            this.spRepo.delete(sp);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/admin/san-pham/index");
        }

        response.sendRedirect("/admin/san-pham/index");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if(uri.contains("store")){
            this.store(request,response);
        } else if (uri.contains("update")) {
            this.update(request,response);
        }else{
            response.sendRedirect("/admin/san-pham/index");
        }
    }

    private void store(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        SanPhamModel model = new SanPhamModel();
        try {
            BeanUtils.populate(model, request.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SanPham sp = new SanPham();
        sp.setMa(model.getMa());
        sp.setTen(model.getTen());
        try {
            this.spRepo.insert(sp);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("/admin/san-pham/index");
    }

    private void update(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        SanPhamModel model = new SanPhamModel();
        try {
            BeanUtils.populate(model, request.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }

        int id = Integer.parseInt(request.getParameter("id"));
        SanPham sp = new SanPham();
        sp.setId(id);
        sp.setMa(model.getMa());
        sp.setTen(model.getTen());
        try {
            this.spRepo.update(sp);
        } catch (Exception e) {
            // Báo lỗi
            e.printStackTrace();
        }

        response.sendRedirect("/admin/san-pham/index");
    }
}
