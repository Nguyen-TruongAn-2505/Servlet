package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "hoa_don")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nhanvien_id", nullable = false)
    private NhanVien nhanVien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "khachhang_id", nullable = false)
    private KhachHang khachHang;

    @Column(name = "ma")
    private String ma;

    @Column(name = "ngay_tao")
    @CreationTimestamp
    private Date ngayTao;

    @Column(name = "ngay_thanh_toan")
    @UpdateTimestamp
    private Date ngayThanhToan;

    @Column(name = "ngay_ship")
    @UpdateTimestamp
    private Date ngayShip;

    @Column(name = "ngay_nhan")
    @UpdateTimestamp
    private Date ngayNhan;

    @Column(name = "tinh_trang")
    private Integer tinhTrang;

    @Column(name = "ten_nguoi_nhan")
    private String tenNguoiNhan;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "sdt")
    private String sdt;

    @OneToMany(mappedBy = "hoaDon")
    private List<HoaDonChiTiet> hoaDonChiTietList = new ArrayList<>();


}
