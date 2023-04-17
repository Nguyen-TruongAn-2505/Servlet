package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HoaDonChiTietModel {
    private int id;
    private Integer hoaDonId;
    private Integer chiTietSanPhamId;
    private Integer soLuong;
    private Integer donGia;

}
