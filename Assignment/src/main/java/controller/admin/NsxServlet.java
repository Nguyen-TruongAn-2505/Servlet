package controller.admin;

import entity.MauSac;
import entity.Nsx;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.MauSacModel;
import model.NsxModel;
import org.apache.commons.beanutils.BeanUtils;
import repository.NsxRepository;

import java.io.IOException;
import java.util.List;

@WebServlet({"/admin/nsx/create",	// GET
        "/admin/nsx/store",	// POST
        "/admin/nsx/edit",	// GET
        "/admin/nsx/update",	// POST
        "/admin/nsx/delete",	// GET
        "/admin/nsx/index",})
public class NsxServlet extends HttpServlet {

    private NsxRepository nsxRepo;

    public NsxServlet(){
        super();
        this.nsxRepo = new NsxRepository();
    }
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

    private void index(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        List< Nsx> ds = this.nsxRepo.getAll();
        request.setAttribute("ds",ds);
        String view = "/views/admin/nsx/nsx.jsp";
        request.getRequestDispatcher(view).forward(request,response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        request.getRequestDispatcher("/views/admin/nsx/create.jsp").forward(request,response);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        String idStr = request.getParameter("id");
        int id = -1;
        try{
            id = Integer.parseInt(idStr);
        }catch (Exception e){
            e.printStackTrace();
            response.sendRedirect("/admin/nsx/index");
        }
        Nsx nsx = this.nsxRepo.findById(id);
        if(nsx == null){
            response.sendRedirect("/admin/nsx/index");
        }
        request.setAttribute("nsx",nsx);
        request.getRequestDispatcher("/views/admin/nsx/edit.jsp").forward(request,response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        String idStr = request.getParameter("id");
        int id = -1;
        try{
            id = Integer.parseInt(idStr);
            Nsx nsx = this.nsxRepo.findById(id);
            this.nsxRepo.delete(nsx);
        }catch (Exception e){
            e.printStackTrace();
            response.sendRedirect("/admin/nsx/index");
        }
        response.sendRedirect("/admin/nsx/index");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if(uri.contains("store")){
            this.store(request,response);
        } else if (uri.contains("update")) {
            this.update(request,response);
        }else{
            response.sendRedirect("/admin/nsx/index");
        }
    }

    private void store(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        NsxModel model = new NsxModel();
        try {
            BeanUtils.populate(model, request.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Nsx nsx = new Nsx();
        nsx.setMa(model.getMa());
        nsx.setTen(model.getTen());
        try {
            this.nsxRepo.insert(nsx);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("/admin/nsx/index");
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws  ServletException,IOException {
        NsxModel model = new NsxModel();
        try {
            BeanUtils.populate(model, request.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }

        int id = Integer.parseInt(request.getParameter("id"));
        Nsx nsx = new Nsx();
        nsx.setId(id);
        nsx.setMa(model.getMa());
        nsx.setTen(model.getTen());
        try {
            this.nsxRepo.update(nsx);
        } catch (Exception e) {
            // Báo lỗi
            e.printStackTrace();
        }

        response.sendRedirect("/admin/nsx/index");
    }
}
