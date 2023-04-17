package controller.admin;

import entity.ChucVu;
import entity.CuaHang;
import entity.NhanVien;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.NhanVienModel;
import org.apache.commons.beanutils.BeanUtils;
import repository.NhanVienRepository;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet({"/admin/nhan-vien/create",
                "/admin/nhan-vien/edit",
                "/admin/nhan-vien/delete",
                "/admin/nhan-vien/index",
                "/admin/nhan-vien/store",
                "/admin/nhan-vien/update"})
public class NhanVienServlet extends HttpServlet {
    private NhanVienRepository nvRepo;

    public NhanVienServlet(){
        super();
        this.nvRepo = new NhanVienRepository();
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
        List<NhanVien> ds = this.nvRepo.gatAll();
        System.out.println("ds");
        request.setAttribute("ds",ds);
        String view = "/views/admin/nhan_vien/nhanvien.jsp";
        request.getRequestDispatcher(view).forward(request,response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/admin/nhan_vien/create.jsp").forward(request,response);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String idStr = request.getParameter("id");
        int id = -1;
        try{
            id = Integer.parseInt(idStr);
        }catch (Exception e){
            e.printStackTrace();
            response.sendRedirect("/admin/nhan-vien/index");
        }
        NhanVien nv = this.nvRepo.findById(id);
        request.setAttribute("nv",nv);
        request.getRequestDispatcher("/views/admin/nhan_vien/edit.jsp").forward(request,response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        String idStr = request.getParameter("id");
        int id = -1;
        try{
            id = Integer.parseInt(idStr);
            NhanVien nv = this.nvRepo.findById(id);
            this.nvRepo.delete(nv);
        }catch (Exception e){
            e.printStackTrace();
            response.sendRedirect("/admin/nhan-vien/index");
        }
        response.sendRedirect("/admin/nhan-vien/index");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if(uri.contains("store")){
            this.store(request,response);
        } else if (uri.contains("update")) {
            this.update(request,response);
        }else{
            response.sendRedirect("/admin/nhan-vien/index");
        }
    }

    private void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NhanVienModel model = new NhanVienModel();

        try {
            BeanUtils.populate(model, request.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
        NhanVien nv = new NhanVien();
        nv.setMa(model.getMa());
        nv.setHoTen(model.getHoTen());
        nv.setGioiTinh(model.getGioiTinh());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        try{
            Date ngaySinh = formatter.parse(model.getNgaySinh());
            nv.setNgaySinh(ngaySinh);
        }catch (Exception e){
            e.printStackTrace();
        }


        nv.setDiaChi(model.getDiaChi());
        nv.setMatKhau(model.getMatKhau());

        CuaHang ch = new CuaHang();
        ch.setId(model.getCuaHangId());
        nv.setCuaHang(ch);

        ChucVu cv = new ChucVu();
        cv.setId(model.getChucVuId());
        nv.setChucVu(cv);


        nv.setTrangThai(model.getTrangThai());

        try{
            this.nvRepo.insert(nv);
        }catch (Exception e){
            e.printStackTrace();
        }
        response.sendRedirect("/admin/nhan-vien/index");
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        NhanVienModel model = new NhanVienModel();
        try{
            BeanUtils.populate(model,request.getParameterMap());
        }catch (Exception e){
            e.printStackTrace();
        }

        int id = Integer.parseInt(request.getParameter("id"));
        NhanVien nv = new NhanVien();
        nv.setId(id);
        nv.setMa(model.getMa());
        nv.setHoTen(model.getHoTen());
        nv.setGioiTinh(model.getGioiTinh());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        try{
            Date ngaySinh = formatter.parse(model.getNgaySinh());
            nv.setNgaySinh(ngaySinh);
        }catch (Exception e){
            e.printStackTrace();
        }
        nv.setDiaChi(model.getDiaChi());
        nv.setMatKhau(model.getMatKhau());

        CuaHang ch = new CuaHang();
        ch.setId(model.getCuaHangId());
        nv.setCuaHang(ch);

        ChucVu cv = new ChucVu();
        cv.setId(model.getChucVuId());
        nv.setChucVu(cv);

        nv.setTrangThai(model.getTrangThai());

        try{
            this.nvRepo.insert(nv);
        }catch (Exception e){
            e.printStackTrace();
        }
        response.sendRedirect("/admin/nhan-vien/index");
    }
}
