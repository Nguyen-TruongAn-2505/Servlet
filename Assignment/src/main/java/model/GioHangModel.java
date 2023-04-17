package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GioHangModel {
    private int id;
    private Integer khachHangId;
    private Integer nhanVienId;
    private String ma;
    private Timestamp ngayTao;
    private Timestamp ngayThanhToan;
    private String tenNguoiNhan;
    private String diaChi;
    private String sdt;
    private Integer tinhTrang;
}
