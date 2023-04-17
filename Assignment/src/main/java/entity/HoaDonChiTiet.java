package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hoa_don_chi_tiet")
public class HoaDonChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hoadonchitiet_hoadon",nullable = false)
    private HoaDon hoaDon;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hoadonchitiet_chitietsanpham",nullable = false)
    private ChiTietSanPham chiTietSanPham;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "don_gia")
    private Integer donGia;


}
