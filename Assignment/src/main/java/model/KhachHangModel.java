package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KhachHangModel {

    private int id;
    private String ma;
    private String hoTen;
    private String ngaySinh;
    private String sdt;
    private String diaChi;
    private String matKhau;

}
