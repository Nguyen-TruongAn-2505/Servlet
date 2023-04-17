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
@Table(name = "dong_san_pham")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DongSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ma")
    private String ma;

    @Column(name = "ten")
    private String ten;

    @OneToMany(mappedBy = "dongSanPham")
    private List<ChiTietSanPham> chiTietSanPhamList = new ArrayList<>();


}
