package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "nhan_vien")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ma")
    private String ma;

    @Column(name = "ho_ten")
    private String hoTen;

    @Column(name = "gioi_tinh")
    private String gioiTinh;

    @Column(name = "ngay_sinh")
    private Date ngaySinh;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "mat_khau")
    private String matKhau;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuahang_id",nullable = false)
    private CuaHang cuaHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chucvu_id",nullable = false)
    private ChucVu chucVu;

    @Column(name = "trangthai")
    private Integer trangThai;

    @OneToMany(mappedBy = "nhanVien")
    private List<HoaDon> hoaDonList = new ArrayList<>();


}
