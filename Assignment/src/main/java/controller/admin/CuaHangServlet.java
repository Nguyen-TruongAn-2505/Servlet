package controller.admin;

import entity.CuaHang;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.CuaHangModel;
import org.apache.commons.beanutils.BeanUtils;
import repository.CuaHangRepository;

import java.io.IOException;
import java.util.List;

@WebServlet({"/admin/cua-hang/create",
            "/admin/cua-hang/edit",
            "/admin/cua-hang/delete",
            "/admin/cua-hang/index",
            "/admin/cua-hang/update",
            "/admin/cua-hang/store"})
public class CuaHangServlet extends HttpServlet {
    private CuaHangRepository chRepo;

    public CuaHangServlet(){
        this.chRepo = new CuaHangRepository();
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

    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CuaHang> ds = this.chRepo.getAll();
        System.out.println(ds);
        request.setAttribute("ds",ds);
        String view = "/views/admin/cua_hang/cuahang.jsp";
        request.getRequestDispatcher(view).forward(request,response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        request.getRequestDispatcher("/views/admin/cua_hang/create.jsp").forward(request,response);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        String idStr = request.getParameter("id");
        int id = -1;
        try{
            id = Integer.parseInt(idStr);
        }catch (Exception e){
            e.printStackTrace();
            response.sendRedirect("/admin/cua-hang/index");
        }
        CuaHang ch  = this.chRepo.findById(id);
        if(ch == null){
            request.getRequestDispatcher("/admin/cua-hang/index");
        }
        request.setAttribute("ch",ch);
        request.getRequestDispatcher("/views/admin/cua_hang/edit.jsp").forward(request,response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String idStr = request.getParameter("id");
       int id = -1;
       try{
           id = Integer.parseInt(idStr);
           CuaHang ch = this.chRepo.findById(id);
           this.chRepo.delete(ch);
       }catch (Exception e){
           e.printStackTrace();
           response.sendRedirect("/admin/cua-hang/index");
       }
        response.sendRedirect("/admin/cua-hang/index");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if(uri.contains("store")){
            this.store(request,response);
        } else if (uri.contains("update")) {
            this.update(request,response);
        }else {
            response.sendRedirect("/admin/cua-hang/index");
        }
    }

    private void store(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        CuaHangModel model = new CuaHangModel();
        try{
            BeanUtils.populate(model,request.getParameterMap());
        }catch (Exception e){
            e.printStackTrace();
        }

        CuaHang ch = new CuaHang();
        ch.setMa(model.getMa());
        ch.setTen(model.getTen());
        ch.setDiaChi(model.getDiaChi());
        ch.setThanhPho(model.getThanhPho());
        ch.setQuocGia(model.getQuocGia());

        try{
            this.chRepo.insert(ch);
        }catch (Exception e){
            e.printStackTrace();
        }
        response.sendRedirect("/admin/cua-hang/index");
    }

    private void update(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        CuaHangModel model = new CuaHangModel();
        try{
            BeanUtils.populate(model,request.getParameterMap());
        }catch (Exception e){
            e.printStackTrace();
        }

        int id = Integer.parseInt(request.getParameter("id"));
        CuaHang ch = new CuaHang();
        ch.setId(id);
        ch.setMa(model.getMa());
        ch.setTen(model.getTen());
        ch.setDiaChi(model.getDiaChi());
        ch.setThanhPho(model.getThanhPho());
        ch.setQuocGia(model.getQuocGia());

        try{
            this.chRepo.update(ch);
        }catch (Exception e){
            e.printStackTrace();
        }
        response.sendRedirect("/admin/cua-hang/index");
    }
}
