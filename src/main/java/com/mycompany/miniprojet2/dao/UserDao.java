/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.miniprojet2.dao;

import com.mycompany.miniprojet2.dto.UserDto;
import com.mycompany.miniprojet2.utils.Db_Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maison
 */
public class UserDao {
     private String SQL_GetUser = "SELECT * FROM users WHERE identifiant=? AND mdp=?";
     private String SQL_UpdateUser = "UPDATE users SET nom=?, prenom=?, ddn=?, mdp=? WHERE username=?";
     private String SQL_CreateUser = "INSERT INTO users(nom, prenom, ddn, identifiant, email, mdp) VALUES(?, ?, ?, ?, ?, ?)";
     
    private Db_Connect db_connect;
    private Connection connection;
    private PreparedStatement ps;
    private ResultSet rs;
    
     public UserDao(){
        this.db_connect = new Db_Connect();
        this.connection = null;
        this.rs = null;
        this.ps = null;
    } 
     
     public UserDto GetUser(String identifiant, String mdp) throws SQLException{
        
        try{
            this.connection = this.db_connect.OpenConnect();
            this.ps = this.connection.prepareStatement(this.SQL_GetUser);
            this.ps.setString(1, identifiant);
            this.ps.setString(2, mdp);   
            this.rs = this.ps.executeQuery();
            
            UserDto user = new UserDto();
            
            if(this.rs != null){
                while(this.rs.next()){   

                    user.setNom(rs.getString(2));
                    user.setPrenom(rs.getString(3));
                    user.setDdn(rs.getString(4));
                    user.setIdentifiant(rs.getString(5));
                    user.setEmail(rs.getString(6));
                    user.setMdp(rs.getString(7));

                    return user;
                }                
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.rs.close();
                this.db_connect.CloseConnect();
            } catch (SQLException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return null;  
    }
     
     public void UpdateUser(String nom, String prenom, String ddn, String identifiant, String mdp) throws SQLException{

        try{
            this.connection = this.db_connect.OpenConnect();
            this.ps = this.connection.prepareStatement(SQL_UpdateUser);
            this.ps.setString(1, nom);
            this.ps.setString(2, prenom);
            this.ps.setString(3, ddn);
            this.ps.setString(5, mdp);
            this.ps.executeUpdate();      
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.ps.close();
                this.db_connect.CloseConnect();
            } catch (SQLException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     
     
    public void CreateUser(String nom, String prenom, String ddn, String identifiant, String email, String mdp) throws SQLException{
        
        try{
            this.connection = this.db_connect.OpenConnect();
            this.ps = this.connection.prepareStatement(SQL_CreateUser);
            this.ps.setString(1, nom);
            this.ps.setString(2, prenom);
            this.ps.setString(3, ddn);
            this.ps.setString(4, identifiant);
            this.ps.setString(5, email);
            this.ps.setString(6, mdp);
            this.ps.executeUpdate();   
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.ps.close();
                this.db_connect.CloseConnect();
            } catch (SQLException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
