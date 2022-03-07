/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.User;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.*;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import session.UserFacade;
import tools.PasswordProtected;

/**
 *
 * @author jvm
 */
@WebServlet(name = "LoginServlet",loadOnStartup = 1, urlPatterns = {
    "/showLogin",
    "/login",
    "/logout",
    "/showRegistration",
    "/registration",
})
public class LoginServlet extends HttpServlet {
    @EJB UserFacade userFacade;
    
    
    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        if(userFacade.count()>0) return;
        User user = new User();
        user.setFirstName("Juri");
        user.setLastName("Melnikov");
        user.setPhone("545454545");
        user.setLogin("admin");
        PasswordProtected passwordProtected = new PasswordProtected();
        String salt = passwordProtected.getSalt();
        user.setSalt(salt);
        String password = passwordProtected.getProtectedPassword("12345", salt);
        user.setPassword(password);
        user.setListAccountBox(new ArrayList<>());
        userFacade.create(user);
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
                request.getRequestDispatcher("/showLogin.jsp").forward(request, response);
                break;
            case "/login":
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
                String salt = authUser.getSalt();
                PasswordProtected passwordProtected = new PasswordProtected();
                password = passwordProtected.getProtectedPassword(password, salt);
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
            case "/logout":
                session = request.getSession(false);
                if(session != null){
                    session.invalidate();
                    request.setAttribute("info", "Вы вышли");
                }
                request.getRequestDispatcher("/listAccounts").forward(request, response);
                break;
            case "/showRegistration":
                request.getRequestDispatcher("/showRegistration.jsp").forward(request, response);
                break;
            case "/registration":
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String phone = request.getParameter("phone");
                login = request.getParameter("login");
                String password1 = request.getParameter("password1");
                String password2 = request.getParameter("password2");
                if(!password1.equals(password2)){
                    request.setAttribute("firstName", firstName);
                    request.setAttribute("lastName", lastName);
                    request.setAttribute("phone", phone);
                    request.setAttribute("login", login);
                    request.setAttribute("info", "Пароли не совпадают");
                    request.getRequestDispatcher("/showRegistration").forward(request, response);
                    break;
                }
                if("".equals(firstName)
                        || "".equals(lastName)
                        || "".equals(phone)
                        || "".equals(login)
                        || "".equals(password1)
                        || "".equals(password2)
                        ){
                    request.setAttribute("firstName", firstName);
                    request.setAttribute("lastName", lastName);
                    request.setAttribute("phone", phone);
                    request.setAttribute("login", login);
                    request.setAttribute("info", "Заполните все поля");
                    request.getRequestDispatcher("/showRegistration").forward(request, response);
                    break;
                }
                User newUser = new User();
                newUser.setFirstName(firstName);
                newUser.setLastName(lastName);
                newUser.setPhone(phone);
                newUser.setLogin(login);
                passwordProtected = new PasswordProtected();
                salt = passwordProtected.getSalt();
                newUser.setSalt(salt);
                password1 = passwordProtected.getProtectedPassword(password1, salt);
                newUser.setPassword(password1);
                userFacade.create(newUser);
                request.setAttribute("info", "Привет, "+newUser.getFirstName()+"! Авторизуйтесь");
                request.getRequestDispatcher("/showLogin").forward(request, response);
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
