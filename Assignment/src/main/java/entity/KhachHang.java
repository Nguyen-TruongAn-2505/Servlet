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
@Table(name = "khach_hang")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KhachHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ma")
    private String ma;

    @Column(name = "ho_ten")
    private String hoTen;

    @Column(name = "ngay_sinh")
    private Date ngaySinh;

    @Column(name = "sdt")
    private String sdt;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "mat_khau")
    private String matKhau;

    @OneToMany(mappedBy = "khachHang")
    private List<GioHang> gioHangList = new ArrayList<>();

    @OneToMany(mappedBy = "khachHang",cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<HoaDon> hoaDonList = new ArrayList<>();


}
