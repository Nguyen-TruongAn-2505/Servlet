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
@Table(name = "chuc_vu")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChucVu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ma")
    private String ma;

    @Column(name = "ten")
    private String ten;

    @OneToMany(mappedBy = "chucVu")
    private List<NhanVien> nhanVienList = new ArrayList<>();


}
