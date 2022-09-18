/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PhieuMuon;
import util.ConnectDB;

/**
 *
 * @author OS
 */
public class PhieuMuonController {

    public int insert(PhieuMuon pm) {
        int result = 0;
        try {

            String sql = "Insert into PhieuMuon values(?,?,?,?)";
            Connection con = ConnectDB.connectSQLServer();
            CallableStatement ps = con.prepareCall(sql);
            ps.setString(1, pm.getMaPhieuMuon());
            ps.setString(2, pm.getMaSinhVien());
            ps.setString(3, pm.getNgayMuon());
            ps.setString(4, pm.getNgayHetHan());
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PhieuMuonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;

    }

    public int update(PhieuMuon pm) {
        int result = 0;
        try {
            String sql = "Update PhieuMuon SET MaSV = ?, NgayMuon = ?, NgayHenTra  = ? WHERE MaPhieuMuon = ?";
            try (Connection con = ConnectDB.connectSQLServer(); CallableStatement ps = con.prepareCall(sql)) {
                ps.setString(1, pm.getMaSinhVien());
                ps.setString(2, pm.getNgayMuon());
                ps.setString(3, pm.getNgayHetHan());
                ps.setString(4, pm.getMaPhieuMuon());
                result = ps.executeUpdate();
            }
        } catch (Exception ex) {
            System.err.println("Lỗi: " + ex);
        }
        return result;
    }

    public int delete(String maPhieuMuon) {
        int result = 0;
        try {
            String sql = "DELETE FROM PhieuMuon WHERE MaPhieuMuon=?";
            try (Connection con = ConnectDB.connectSQLServer(); PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, maPhieuMuon);
                result = ps.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public ArrayList<PhieuMuon> SearchMaSVMaPhieu(String ma) {
        ArrayList<PhieuMuon> lists = new ArrayList<PhieuMuon>();
        try {
            String sql = "SELECT * FROM PhieuMuon WHERE MaSV like '%" + ma + "%'  or MaPhieuMuon like '%" + ma + "%'";
            Connection con = ConnectDB.connectSQLServer();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                PhieuMuon pm = new PhieuMuon();
                pm.setMaPhieuMuon(rs.getString(1));
                pm.setMaSinhVien(rs.getString(2));
                pm.setNgayMuon(rs.getString(3));
                pm.setNgayHetHan(rs.getString(4));
                lists.add(pm);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lists;
    }

    public ArrayList listPhieuMuon() {
        String sql = "select * from PhieuMuon";
        ArrayList list = new ArrayList();
        try {
            Connection connection = util.ConnectDB.connectSQLServer();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                list.add(new PhieuMuon(rs.getString(1), rs.getString(2), rs.getString(3), Integer.parseInt(rs.getString(5)), rs.getString(5), rs.getString(6)));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public boolean themMoiPhieuMuon(String maPhieuMuon, String maSinhVien, String maSach, String soLuong, String ngayHen, String ngayTra) {
        String sql = "Insert into PhieuMuon values(?,?,?,?,?,?)";
        try (Connection connection = util.ConnectDB.connectSQLServer(); PreparedStatement preparedStatement = connection.prepareCall(sql)) {
            preparedStatement.setString(1, maPhieuMuon);
            preparedStatement.setString(2, maSinhVien);
            preparedStatement.setString(3, maSach);
            preparedStatement.setString(4, soLuong);
            preparedStatement.setString(5, ngayHen);
            preparedStatement.setString(6, ngayTra);
            int row = preparedStatement.executeUpdate();
            if (row != 0) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public boolean xoaDuLieuPhieuMuon(String maPhieuMuon) {
        String sql = "delete from PhieuMuon where MaPhieuMuon = '" + maPhieuMuon + "'";
        try (Connection connection = util.ConnectDB.connectSQLServer(); Statement stmt = connection.createStatement()) {
            int row = stmt.executeUpdate(sql);
            if (row != 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
        return false;
    }

    public String layMaPMTheoMaSV(String maSv) {
        String sql = "select maphieumuon from phieumuon where masv = '" + maSv + "' ";
        String result = null;
        try {
            try (Connection conn = util.ConnectDB.connectSQLServer(); Statement stm = conn.createStatement()) {
                ResultSet rs = stm.executeQuery(sql);
                if (rs.next()) {
                    result = rs.getString(1);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PhieuMuonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
