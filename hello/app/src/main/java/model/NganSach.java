package model;

import java.io.Serializable;

public class NganSach implements Serializable {
    private int id;
    private float nganSach;
    private LoaiGD loaiGD;
    private String thongBao;

    public NganSach() {
    }

    public NganSach(int id, float nganSach, LoaiGD loaiGD, String thongBao) {
        this.id = id;
        this.nganSach = nganSach;
        this.loaiGD = loaiGD;
        this.thongBao = thongBao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getNganSach() {
        return nganSach;
    }

    public void setNganSach(float nganSach) {
        this.nganSach = nganSach;
    }

    public LoaiGD getLoaiGD() {
        return loaiGD;
    }

    public void setLoaiGD(LoaiGD loaiGD) {
        this.loaiGD = loaiGD;
    }

    public String getThongBao() {
        return thongBao;
    }

    public void setThongBao(String thongBao) {
        this.thongBao = thongBao;
    }
}
