package controller.admin;

import entity.KhachHang;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.KhachHangModel;
import org.apache.commons.beanutils.BeanUtils;
import repository.KhachHangRepository;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet({"/admin/khach-hang/create",	// GET
        "/admin/khach-hang/store",	// POST
        "/admin/khach-hang/edit",	// GET
        "/admin/khach-hang/update",	// POST
        "/admin/khach-hang/delete",	// GET
        "/admin/khach-hang/index",})
public class KhachHangServlet extends HttpServlet {

    private KhachHangRepository khRepo;

    public KhachHangServlet(){
        super();
        this.khRepo = new KhachHangRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if(uri.contains("create")){
            this.create(request,response);
        } else if (uri.contains("edit")) {
            this.edit(request,response);
        }else if(uri.contains("delete")){
            this.delete(request,response);
        }else{
            this.index(request,response);
        }
    }

    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        List<KhachHang> ds = this.khRepo.getAll();
        System.out.println(ds);
        request.setAttribute("ds",ds);
        String view = "/views/admin/khach_hang/khachhang.jsp";
        request.getRequestDispatcher(view).forward(request,response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        request.getRequestDispatcher("/views/admin/khach_hang/create.jsp").forward(request,response);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        String idStr = request.getParameter("id");
        int id = -1;
        try{
            id = Integer.parseInt(idStr);
        }catch (Exception e){
            e.printStackTrace();
            response.sendRedirect("/admin/khach-hang/index");
        }
        KhachHang kh = this.khRepo.findById(id);
        if(kh == null){
            response.sendRedirect("/admin/khach-hang/index");
        }
        request.setAttribute("kh",kh);
        request.getRequestDispatcher("/views/admin/khach_hang/edit.jsp").forward(request,response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        String idStr = request.getParameter("id");
        int id = -1;
        try{
            id = Integer.parseInt(idStr);
            KhachHang kh = this.khRepo.findById(id);
            this.khRepo.delete(kh);

        }catch (Exception e){
            e.printStackTrace();
            response.sendRedirect("/admin/khach-hang/index");
        }
        response.sendRedirect("/admin/khach-hang/index");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if(uri.contains("store")){
            this.store(request,response);
        } else if (uri.contains("update")) {
            this.update(request,response);
        }else{
            response.sendRedirect("/admin/khach-hang/index");
        }
    }

    private void store(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        KhachHangModel model = new KhachHangModel();
        try{
            BeanUtils.populate(model, request.getParameterMap());
        }catch (Exception e){
            e.printStackTrace();
        }
        KhachHang kh = new KhachHang();
        kh.setMa(model.getMa());
        kh.setHoTen(model.getHoTen());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        try{
            Date ngaySinh = formatter.parse(model.getNgaySinh());
            kh.setNgaySinh(ngaySinh);
        }catch (Exception e){
            e.printStackTrace();
        }
        kh.setSdt(model.getSdt());
        kh.setDiaChi(model.getDiaChi());
        kh.setMatKhau(model.getMatKhau());
        try{
            this.khRepo.insert(kh);
        }catch (Exception e){
            e.printStackTrace();
        }
        response.sendRedirect("/admin/khach-hang/index");
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        KhachHangModel model = new KhachHangModel();
        try{
            BeanUtils.populate(model, request.getParameterMap());
        }catch (Exception e){
            e.printStackTrace();
        }
        int id = Integer.parseInt(request.getParameter("id"));
        KhachHang kh = new KhachHang();
        kh.setId(id);
        kh.setMa(model.getMa());
        kh.setHoTen(model.getHoTen());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        try{
            Date ngaySinh = formatter.parse(model.getNgaySinh());
            kh.setNgaySinh(ngaySinh);
        }catch (Exception e){
            e.printStackTrace();
        }
        kh.setSdt(model.getSdt());
        kh.setDiaChi(model.getDiaChi());
        kh.setMatKhau(model.getMatKhau());
        try{
            this.khRepo.update(kh);
        }catch (Exception e){
            e.printStackTrace();
        }
        response.sendRedirect("/admin/khach-hang/index");
    }
}
