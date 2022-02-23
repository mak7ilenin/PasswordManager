/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.AccountBox;
import entity.Picture;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.AccountBoxFacade;
import session.PictureFacade;

/**
 *
 * @author Melnikov
 */
@WebServlet(name = "MyServlet", urlPatterns = {
    "/addAccountBox",
    "/createAccountBox",
    "/listAccounts",
    "/showAccount",
    "/removeAccount"
    
    
        
})
public class MyServlet extends HttpServlet {
    @EJB AccountBoxFacade accountBoxFacade;
    @EJB PictureFacade pictureFacade;
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
            case "/addAccountBox":
                List<Picture> pictures = pictureFacade.findAll();
                request.setAttribute("pictures", pictures);
                request.getRequestDispatcher("/WEB-INF/addAccountBox.jsp").forward(request, response);
                break;
            
            case "/createAccountBox":
                String name = request.getParameter("name");
                String picture = request.getParameter("picture");
                String url = request.getParameter("url");
                String urlLogin = request.getParameter("urlLogin");
                String urlPassword = request.getParameter("urlPassword");
                if(picture.isEmpty() || name.isEmpty() || url.isEmpty() || urlLogin.isEmpty() || urlPassword.isEmpty()){
                    request.setAttribute("info", "Заполните все поля");
                    request.setAttribute("name", name);
                    request.setAttribute("picture", picture);
                    request.setAttribute("url", url);
                    request.setAttribute("urlLogin", urlLogin);
                    request.setAttribute("urlPassword", urlPassword);
                    request.getRequestDispatcher("/addAccountBox").forward(request, response);
                    break;
                }
                Picture pic = null;
                try {
                    pic = pictureFacade.find(Long.parseLong(picture));
                    AccountBox accountBox = new AccountBox();
                    accountBox.setName(name);
                    accountBox.setPicture(pic);
                    accountBox.setUrl(url);
                    accountBox.setUrlLogin(urlLogin);
                    accountBox.setUrlPassword(urlPassword);
                    accountBoxFacade.create(accountBox);
                    request.setAttribute("info", "Данные записаны успешно");
                    request.getRequestDispatcher("/addAccountBox").forward(request, response);
                } catch (Exception e) {
                    request.setAttribute("info", "Заполните все поля");
                    request.setAttribute("name", name);
                    request.setAttribute("picture", picture);
                    request.setAttribute("url", url);
                    request.setAttribute("urlLogin", urlLogin);
                    request.setAttribute("urlPassword", urlPassword);
                    request.getRequestDispatcher("/addAccountBox").forward(request, response);
                    break;
                }
                break;
            case "/listAccounts":
                List<AccountBox> listAccounts = accountBoxFacade.findAll();
                request.setAttribute("listAccounts", listAccounts);
                request.getRequestDispatcher("/WEB-INF/listAccounts.jsp").forward(request, response);
                break;
            case "/showAccount":
                String accountId = request.getParameter("accountId");
                if(accountId != null && accountId.isEmpty()){
                    request.setAttribute("info", "Неверный запрос");
                    request.getRequestDispatcher("/listAccounts").forward(request, response);
                    break;
                }
                try {
                    AccountBox ab = accountBoxFacade.find(Long.parseLong(accountId));
                    request.setAttribute("accountBox", ab);
                } catch (Exception e) {
                    request.setAttribute("info", "Неверный запрос");
                    request.getRequestDispatcher("/listAccounts").forward(request, response);
                    break;
                }
                request.getRequestDispatcher("/WEB-INF/showAccount.jsp").forward(request, response);
                break;
            case "/removeAccount":
                String id = request.getParameter("id");
                AccountBox accountBox = accountBoxFacade.find(Long.parseLong(id));
//                Picture pictureDelete = accountBox.getPicture();
                File file = new File(accountBox.getPicture().getPathToFile());
                file.delete();
//                pictureFacade.remove(accountBox.getPicture());
                accountBoxFacade.remove(accountBox);
                request.setAttribute("info", "Удален аккаунт: " + accountBox.getName());
                request.getRequestDispatcher("/listAccounts").forward(request, response);
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
