package model;
public class DanhMucItem {
    private String tenDanhMuc;
    private String soTien;
    private double phanTram;
    private int color;

    public DanhMucItem(String tenDanhMuc, String soTien, double phanTram) {
        this.tenDanhMuc = tenDanhMuc;
        this.soTien = soTien;
        this.phanTram = phanTram;
        this.color = color;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public String getSoTien() {
        return soTien;
    }

    public double getPhanTram() {
        return phanTram;
    }

    public int getColor() {
        return color;
    }
}
