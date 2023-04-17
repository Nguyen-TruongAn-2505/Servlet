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
public class HoaDonModel {
    private int id;
    private Integer nhanVienId;
    private Integer khachHangId;
    private String ma;
    private Timestamp ngayTao;
    private Timestamp ngayThanhToan;
    private Timestamp ngayNhan;
    private Integer tinhTrang;
    private String tenNguoiNhan;
    private String diaChi;
    private String sdt;
}
