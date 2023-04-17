package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChiTietSanPhamModel {
    private int id;
    private Integer sanPhamId;
    private Integer nsxId;
    private Integer mauSacId;
    private Integer dongSanPhamId;
    private Integer namBaoHanh;
    private String moTa;
    private Integer soLuongTon;
    private Integer giaNhap;
    private Integer giaBan;
}
