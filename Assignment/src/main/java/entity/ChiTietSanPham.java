package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "chi_tiet_san_pham")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChiTietSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sanpham_id", nullable=false)
    private SanPham sanPham;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="nsx_id", nullable=false)
    private Nsx nsx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mausac_id", nullable=false)
    private MauSac mauSac;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="dongsanpham_id", nullable=false)
    private DongSanPham dongSanPham;

    @Column(name = "nam_bao_hanh")
    private Integer namBaoHanh;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "so_luong_ton")
    private Integer soLuongTon;

    @Column(name = "gia_nhap")
    private Integer giaNhap;

    @Column(name = "gia_ban")
    private Integer giaBan;

    @OneToMany(mappedBy = "chiTietSanPham")
    private List<GioHangChiTiet> gioHangChiTietList = new ArrayList<>();

    @OneToMany(mappedBy = "chiTietSanPham")
    private  List<HoaDonChiTiet> hoaDonChiTietList = new ArrayList<>();


}
