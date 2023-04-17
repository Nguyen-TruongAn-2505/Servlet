package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "gio_hang_chi_tiet")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GioHangChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "giohangchitiet_giohang", nullable = false)
    private GioHang gioHang;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "giohangchitiet_chitietsanpham",nullable = false)
    private ChiTietSanPham chiTietSanPham;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "don_gia")
    private Integer donGia;

    @Column(name = "don_gia_khi_gia")
    private Integer donGiaKhiGia;


}
