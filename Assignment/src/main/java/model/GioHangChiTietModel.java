package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GioHangChiTietModel {
    private int id;
    private Integer gioHangId;
    private Integer chiTietSanPhamId;
    private Integer soLuong;
    private Integer donGia;
    private Integer donGiaKhiGia;

}
