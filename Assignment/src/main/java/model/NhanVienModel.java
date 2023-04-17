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
public class NhanVienModel {
    private int id;
    private String ma;
    private String hoTen;
    private String gioiTinh;
    private String ngaySinh;
    private String diaChi;
    private String matKhau;
    private Integer cuaHangId;
    private Integer chucVuId;
    private Integer trangThai;

}
