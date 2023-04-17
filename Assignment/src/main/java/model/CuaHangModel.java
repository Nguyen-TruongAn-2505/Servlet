package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CuaHangModel {
    private int id;
    private String ma;
    private String ten;
    private String diaChi;
    private String thanhPho;
    private String quocGia;
}
