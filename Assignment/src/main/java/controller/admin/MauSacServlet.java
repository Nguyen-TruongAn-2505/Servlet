package controller.admin;

import entity.ChucVu;
import entity.MauSac;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.ChucVuModel;
import model.MauSacModel;
import org.apache.commons.beanutils.BeanUtils;
import repository.ChucVuRepository;
import repository.MauSacRepository;

import java.io.IOException;
import java.util.List;

@WebServlet({"/admin/mau-sac/create",	// GET
        "/admin/mau-sac/store",	// POST
        "/admin/mau-sac/edit",	// GET
        "/admin/mau-sac/update",	// POST
        "/admin/mau-sac/delete",	// GET
        "/admin/mau-sac/index",})
public class MauSacServlet extends HttpServlet {
    private MauSacRepository msRepo;
    public MauSacServlet(){
        super();
        this.msRepo = new MauSacRepository();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri  = request.getRequestURI();
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

    private void index(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        List<MauSac> ds = this.msRepo.getAll();
        request.setAttribute("ds",ds);
        String view = "/views/admin/mau_sac/mausac.jsp";
        request.getRequestDispatcher(view).forward(request,response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/admin/mau_sac/create.jsp").forward(request,response);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        String idStr = request.getParameter("id");
        int id = -1;
        try{
            id = Integer.parseInt(idStr);
        }catch (Exception e){
            e.printStackTrace();
            response.sendRedirect("/admin/mau-sac/index");
        }
        MauSac ms = this.msRepo.findById(id);
        if (ms == null){
            response.sendRedirect("/admin/mau-sac/index");
        }
        request.setAttribute("ms",ms);
        request.getRequestDispatcher("/views/admin/mau_sac/edit.jsp").forward(request,response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String idStr = request.getParameter("id");
        int id = -1;
        try{
            id = Integer.parseInt(idStr);
            MauSac ms = this.msRepo.findById(id);
            this.msRepo.delete(ms);
        }catch (Exception e){
            e.printStackTrace();
            response.sendRedirect("/admin/mau-sac/index");
        }
        response.sendRedirect("/admin/mau-sac/index");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if(uri.contains("store")){
            this.store(request,response);
        } else if (uri.contains("update")) {
            this.update(request,response);
        }else{
            response.sendRedirect("/admin/mau-sac/index");
        }
    }

    private void store(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        MauSacModel model = new MauSacModel();
        try {
            BeanUtils.populate(model, request.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }

        MauSac ms = new MauSac();
        ms.setMa(model.getMa());
        ms.setTen(model.getTen());
        try {
            this.msRepo.insert(ms);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("/admin/mau-sac/index");
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        MauSacModel model = new MauSacModel();
        try {
            BeanUtils.populate(model, request.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }

        int id = Integer.parseInt(request.getParameter("id"));
        MauSac ms = new MauSac();
        ms.setId(id);
        ms.setMa(model.getMa());
        ms.setTen(model.getTen());
        try {
            this.msRepo.update(ms);
        } catch (Exception e) {
            // Báo lỗi
            e.printStackTrace();
        }

        response.sendRedirect("/admin/mau-sac/index");
        
    }
}
