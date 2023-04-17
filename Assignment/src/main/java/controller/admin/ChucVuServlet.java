package controller.admin;

import entity.ChucVu;
import model.ChucVuModel;
import org.apache.commons.beanutils.BeanUtils;
import repository.ChucVuRepository;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet({"/admin/chuc-vu/create",	// GET
        "/admin/chuc-vu/store",	// POST
        "/admin/chuc-vu/edit",	// GET
        "/admin/chuc-vu/update",	// POST
        "/admin/chuc-vu/delete",	// GET
        "/admin/chuc-vu/index",})
public class ChucVuServlet extends HttpServlet {
    private ChucVuRepository cvRepo;
    public ChucVuServlet(){
        super();
        this.cvRepo = new ChucVuRepository();
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
        List<ChucVu> ds = this.cvRepo.getAll();
        System.out.println(ds);
        request.setAttribute("ds", ds);
        String view = "/views/admin/chuc_vu/chucvu.jsp";
        request.getRequestDispatcher(view)
                .forward(request, response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        request.getRequestDispatcher("/views/admin/chuc_vu/create.jsp").forward(request,response);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String idStr = request.getParameter("id");
        int id = -1;
        // id BẮT BUỘC phải truyền
        // id phải là số
        try {
            id = Integer.parseInt(idStr);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/admin/chuc-vu/index");
        }

        ChucVu cv = this.cvRepo.findById(id);
        if (cv == null) {
            response.sendRedirect("/admin/chuc-vu/index");
        }

        request.setAttribute("cv", cv);
        request.getRequestDispatcher("/views/admin/chuc_vu/edit.jsp")
                .forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String idStr = request.getParameter("id");
        int id = -1;
        // id BẮT BUỘC phải truyền
        // id phải là số
        try {
            id = Integer.parseInt(idStr);
            ChucVu cv = this.cvRepo.findById(id);
            this.cvRepo.delete(cv);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/admin/chuc-vu/index");
        }

        response.sendRedirect("/admin/chuc-vu/index");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
        String uri = request.getRequestURI();
        if(uri.contains("store")){
            this.store(request,response);
        } else if (uri.contains("update")) {
            this.update(request,response);
        }else{
            response.sendRedirect("/admin/chuc-vu/index");
        }
    }

    private void store(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        ChucVuModel model = new ChucVuModel();
        try {
            BeanUtils.populate(model, request.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }

        ChucVu cv = new ChucVu();
        cv.setMa(model.getMa());
        cv.setTen(model.getTen());
        try {
            this.cvRepo.insert(cv);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("/admin/chuc-vu/index");
    }

    private void update(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        ChucVuModel model = new ChucVuModel();
        try {
            BeanUtils.populate(model, request.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }

        int id = Integer.parseInt(request.getParameter("id"));
        ChucVu cv = new ChucVu();
        cv.setId(id);
        cv.setMa(model.getMa());
        cv.setTen(model.getTen());
        try {
            this.cvRepo.update(cv);
        } catch (Exception e) {
            // Báo lỗi
            e.printStackTrace();
        }

        response.sendRedirect("/admin/chuc-vu/index");
    }
}
