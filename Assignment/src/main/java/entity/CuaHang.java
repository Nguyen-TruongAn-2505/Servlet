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
@Table(name = "cua_hang")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CuaHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ma")
    private String ma;
    @Column(name = "ten")
    private String ten;
    @Column(name = "dia_chi")
    private String diaChi;
    @Column(name = "thanh_pho")
    private String thanhPho;
    @Column(name = "quoc_gia")
    private String quocGia;

    @OneToMany(mappedBy = "cuaHang")
    private List<NhanVien> nhanVienList = new ArrayList<>();


}
