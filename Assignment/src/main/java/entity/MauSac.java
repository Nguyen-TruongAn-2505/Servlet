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
@Table(name = "mau_sac")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MauSac {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ma")
    private String ma;

    @Column(name = "ten")
    private String ten;

    @OneToMany(mappedBy = "mauSac")
    private List<ChiTietSanPham> chiTietSanPhamList = new ArrayList<>();


}
