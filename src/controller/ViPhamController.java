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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ViPham;
import util.ConnectDB;

/**
 *
 * @author OS
 */
public class ViPhamController {

    public int insert(ViPham vp) {
        int result = 0;
        try {

            String sql = "Insert into ViPham values (?,?,?)";
            try (Connection con = ConnectDB.connectSQLServer(); PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, vp.getMaViPham());
                ps.setString(2, vp.getMaSinhVien());
                ps.setString(3, vp.getTen());
                result = ps.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViPhamController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;

    }

    public int update(ViPham vp) {
        int result = 0;
        try {

            String sql = "Update ViPham set MaSV = ? , Ten = ? Where MaViPham = ?";
            try (Connection con = ConnectDB.connectSQLServer(); PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, vp.getMaViPham());
                ps.setString(2, vp.getMaSinhVien());
                ps.setString(3, vp.getTen());
                result = ps.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViPhamController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;

    }

    public int delete(String maViPham) {
        int result = 0;
        try {

            String sql = "DELETE FROM ViPham WHERE MaViPham=?";
            Connection con = ConnectDB.connectSQLServer();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maViPham);
            result = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ViPhamController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public ArrayList<ViPham> SearchTen(String ma) {
        ArrayList<ViPham> lists = new ArrayList<ViPham>();
        try {
            String sql = "SELECT * FROM ViPham WHERE MaViPham like '%" + ma + "%' or MaSV like '%" + ma + "%'";
            ResultSet rs;
            try (Connection con = ConnectDB.connectSQLServer(); Statement statement = con.createStatement()) {
                rs = statement.executeQuery(sql);
            }
            while (rs.next()) {
                ViPham vp = new ViPham();
                vp.setMaViPham(rs.getString(1));
                vp.setMaSinhVien(rs.getString(2));
                vp.setTen(rs.getString(3));
                lists.add(vp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lists;
    }
}
