/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Sach;
import model.SinhVien;
import util.ConnectDB;

/**
 *
 * @author OS
 */
public class SachController {
    
    public ArrayList<Sach> danhSachTatCaSach() {
        ArrayList<Sach> lists = new ArrayList<Sach>();
        try {
            String sql = "SELECT * FROM SINHVIEN";
            try (Connection con = ConnectDB.connectSQLServer(); Statement stm = con.createStatement();) {
                ResultSet rs = stm.executeQuery(sql);
//            lists.clear();
                while (rs.next()) {
                    Sach sv = new Sach();
                    sv.setMaSach(rs.getString(1));
                    sv.setTenSach(rs.getString(2));
                    sv.setMaTheLoai(rs.getString(3));
                    sv.setTacGia(rs.getString(4));
                    sv.setSoLuong(rs.getInt(5));
                    sv.setMaNXB(rs.getString(6));
                    sv.setNgay(rs.getString(7));
                    sv.setNoiDung(rs.getString(8));
                    lists.add(sv);

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SinhVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lists;
    }
    
    public int insert(Sach sach) {
        int result = 0;

        try {
           
            String sql = "Insert into sach values (?,?,?,?,?,?,?,?)";

            try (Connection con = ConnectDB.connectSQLServer(); PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, sach.getMaSach());
                ps.setString(2, sach.getTenSach());
                ps.setString(3, sach.getMaTheLoai());
                ps.setString(4, sach.getTacGia());
                ps.setInt(5, sach.getSoLuong());
                ps.setString(6, sach.getMaNXB());
                
                
                
//                ps.setDate(7, sach.get);
                
          
                ps.setString(8, sach.getNoiDung());

            }
        } catch (Exception ex) {

        }
        return result;
    }

    public int update(Sach sach) {
        int result = 0;
        try {
            String sql = "update Sach set tensach=? , matheloai=?,tacgia=?,soluong=?,manxb=?,ngaynhap=?,noidung=?";
            try (Connection con = ConnectDB.connectSQLServer(); PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, sach.getTenSach());
                ps.setString(2, sach.getMaTheLoai());
                ps.setString(3, sach.getTacGia());
                ps.setInt(4, sach.getSoLuong());
                ps.setString(5, sach.getMaNXB());
//                ps.setString(6, sach.get;
                ps.setString(7, sach.getNoiDung());

                result = ps.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(SinhVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int delete(String masach) {
        int result = 0;
        try {

            String sql = "delete from sach where masach=?";
            try (Connection con = ConnectDB.connectSQLServer(); PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1,masach );
                result = ps.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(SinhVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;

    }

    public ArrayList<Sach> SearchTen(String tensach) {
        ArrayList<Sach> list = new ArrayList<>();
        try {
            String sql = "Select * from sach where tenhsach like N'%"+ tensach +"%' or matheloai like '%"+ tensach +"%' or masach like '%"+ tensach +"%' ";
            try (Connection con = ConnectDB.connectSQLServer()) {
                Statement sttm = con.createStatement();
                ResultSet rs = sttm.executeQuery(sql);
                
                while (rs.next()) {
                Sach sach = new Sach();
                sach.setMaSach(rs.getString(1));
                sach.setTenSach(rs.getString(2));
                sach.setMaTheLoai(rs.getString(3));
                sach.setTacGia(rs.getString(4));
                sach.setSoLuong(rs.getInt(5));
                sach.setMaNXB(rs.getString(6));
//                sach.setngaynhap(rs.getString(7));
                sach.setNoiDung(rs.getString(8));

                list.add(sach);

            }
            }
            

        } catch (SQLException ex) {
            Logger.getLogger(SinhVienController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
}
