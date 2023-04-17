package entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "san_pham")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ma")
    private String ma;

    @Column(name = "ten")
    private String ten;

    //Cascade.ALL có nghĩa là nếu chúng ta thực hiện một thao tác CRUD (Create, Read, Update, Delete)
    // trên đối tượng gốc (Customer), thì nó sẽ tự động lan truyền thao tác đó tới các đối tượng phụ thuộc (Order) của nó.
    @OneToMany(mappedBy = "sanPham")
    private List<ChiTietSanPham> chiTietSanPhamList = new ArrayList<>();


}
