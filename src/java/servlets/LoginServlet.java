/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.User;
import java.io.IOException;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.UserFacade;

/**
 *
 * @author jvm
 */

@WebServlet(name = "LoginServlet",loadOnStartup = 1, urlPatterns = {
    "/showLogin",
    "/index",
    "/signUp"
})
public class LoginServlet extends HttpServlet {
    @EJB UserFacade userFacade;
    
    
    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        if(userFacade.count()>1) return;
        User user = new User();
        user.setFirstName("Maksim");
        user.setLastName("Dzjubenko");
        user.setPhone("53005207");
        user.setLogin("admin");
        user.setPassword("12345");
        user.setListAccountBox(new ArrayList<>());
        userFacade.create(user);
        
        
        User user1 = new User();
        user1.setFirstName("Daniil");
        user1.setLastName("Vasilek");
        user1.setPhone("55558888");
        user1.setLogin("dan");
        user1.setPassword("123");
        user1.setListAccountBox(new ArrayList<>());
        userFacade.create(user1);
    } 
        

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
         request.setCharacterEncoding("UTF-8");
         String path = request.getServletPath();
        switch (path) {
            case "/showLogin":
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/index":
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                //Authentification
                User authUser = userFacade.findByLogin(login);
                if(authUser == null){
                    request.setAttribute("info", "Неверный логин или пароль");
                    request.getRequestDispatcher("/showLogin").forward(request, response);
                    break;
                }
                //Authorization
                if(!password.equals(authUser.getPassword())){
                    request.setAttribute("info", "Неверный логин или пароль");
                    request.getRequestDispatcher("/showLogin").forward(request, response);
                    break;
                }
                HttpSession session = request.getSession(true);
                session.setAttribute("authUser", authUser);
                request.setAttribute("info", "Привет, "+authUser.getFirstName());
                request.getRequestDispatcher("/listAccounts").forward(request, response);
                break;
            case "/signUp":
                HttpSession session1 = request.getSession(true);
                request.getRequestDispatcher("/signUp.jsp").forward(request, response);
                break;
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
        processRequest(request, response);
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
        processRequest(request, response);
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
