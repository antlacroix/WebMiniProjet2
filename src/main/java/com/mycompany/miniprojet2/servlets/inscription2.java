/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.miniprojet2.servlets;

import com.mycompany.miniprojet2.dao.UserDao;
import com.mycompany.miniprojet2.dto.UserDto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Maison
 */
public class inscription2 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet inscription2</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet inscription2 at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        
         HttpSession session = request.getSession();
        
            String bouttonRetour = request.getParameter("Retour");
            session.setAttribute("back",bouttonRetour);
       
            if(session.getAttribute("back") != null){
                session.setAttribute("identifiant", null); 
                response.sendRedirect(request.getContextPath() + "/Views/home.jsp");
                session.setAttribute("back", null); 
                }
            else{
                if(session.getAttribute("identifiant") == null)
                    response.sendRedirect(request.getContextPath() + "/Views/login.jsp");
                else
                    response.sendRedirect(request.getContextPath() + "/Views/jeu.jsp");
                }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        
        HttpSession session = request.getSession();
        String bouttonRetour = request.getParameter("Retour");
            session.setAttribute("back",bouttonRetour);
       
            if(session.getAttribute("back") != null){
                session.setAttribute("identifiant", null); 
                response.sendRedirect(request.getContextPath() + "/Views/home.jsp");
                session.setAttribute("back", null); 
                session.setAttribute("erreur1", "false");
                }
            else{
        
        
        String identifiant = request.getParameter("new_user_data_identifiant");
        String email = request.getParameter("new_user_data_email"); 
        String mdp1 = request.getParameter("new_user_data_password");
        String mdp2 = request.getParameter("new_user_data_password2"); 
        String nom = request.getParameter("new_user_data_nom");
        String prenom = request.getParameter("new_user_data_prenom");
        
       
        

          Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
          Matcher m = p.matcher(email);
          boolean matchFound = m.matches();
          if (matchFound && !identifiant.isEmpty() && !email.isEmpty() && !mdp1.isEmpty() && !nom.isEmpty() && !prenom.isEmpty()) {
                
         if (mdp1.equals(mdp2)){
              
        UserDto userDto2 = null;
        UserDao userDao = new UserDao();        
            
      try {
            userDto2 = userDao.ValidateUser(identifiant, email);
            if(userDto2!= null){
            request.setAttribute("erreur1", "true");
            this.getServletContext().getRequestDispatcher("/Views/inscription.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        } 
           this.getServletContext().getRequestDispatcher("/inscription").forward(request, response);
          }  
             
       else{
           request.setAttribute("erreur6", "true");
           this.getServletContext().getRequestDispatcher("/Views/inscription.jsp").forward(request, response);
        } 
         } 
         
         else{
           request.setAttribute("erreur", "true");
           this.getServletContext().getRequestDispatcher("/Views/inscription.jsp").forward(request, response);
        } 
         
         
      }        
  }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}