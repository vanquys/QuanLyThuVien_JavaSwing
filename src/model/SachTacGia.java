/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ADMIN
 */
public class SachTacGia {
    String ma;
    String maTacGia;
    String maSach;

    public SachTacGia() {
    }

    public SachTacGia(String ma, String maTacGia, String maSach) {
        this.ma = ma;
        this.maTacGia = maTacGia;
        this.maSach = maSach;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getMaTacGia() {
        return maTacGia;
    }

    public void setMaTacGia(String maTacGia) {
        this.maTacGia = maTacGia;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }
    
    
}
