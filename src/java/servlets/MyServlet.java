package servlets;

import entity.Model;
import entity.User;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.HistoryFacade;
import session.ModelFacade;
import session.UserFacade;

/**
 *
 * @author makso
 */
@WebServlet(name = "MyServlet",urlPatterns = {
    "/showAddModel",
    "/addModel",
    "/addUser",
    "/editModel",
    "/showEditModel",
    "/editUser",
    "/showEditUser",
    "/editUserLogin",
    "/showBuyModel",
    
    "/listModels",
    "/showModel",
    "/removeModel",
        
})
public class MyServlet extends HttpServlet {
    @EJB ModelFacade modelFacade;
    @EJB UserFacade userFacade;
    @EJB HistoryFacade historyFacade;
    
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
        HttpSession session = request.getSession(false);
        if(session == null){
            request.setAttribute("info", "Авторизуйтесь");
            request.getRequestDispatcher("/showIndex").forward(request, response);
            return;
        }
        //Authentification
        User authUser = (User) session.getAttribute("authUser");
        if(authUser == null){
            request.setAttribute("info", "Авторизуйтесь");
            request.getRequestDispatcher("/showIndex").forward(request, response);
            return;
        }
        String path = request.getServletPath();
        List<Model> modelsList = modelFacade.findAll();
        List<User> usersList = userFacade.findAll();
        switch (path) {
            case "/showAddModel":
                request.getRequestDispatcher("/WEB-INF/addModel.jsp").forward(request, response);
                break;
            case "/addModel":
                String modelName = request.getParameter("modelName");
                String modelSize = request.getParameter("modelSize");
                String modelFirm = request.getParameter("modelFirm");
                
                if(modelName.isEmpty() || modelSize.isEmpty() || modelFirm.isEmpty()){
                    request.setAttribute("info", "Заполните все поля!");
                    request.setAttribute("modelName", modelName);
                    request.setAttribute("modelSize", modelSize);
                    request.setAttribute("modelFirm", modelFirm);
                    request.getRequestDispatcher("/showAddModel").forward(request, response);
                    break;
                }
                try{
                    Model addModel = new Model();
                    addModel.setModelName(modelName);
                    addModel.setModelSize(modelSize);
                    addModel.setModelFirm(modelFirm);
                    addModel.setPrice(Double.parseDouble(request.getParameter("price")));
                    modelFacade.create(addModel);
                    request.setAttribute("info", "Обувь добавлена!");
                    request.getRequestDispatcher("/showAddModel").forward(request, response);
                }
                catch(Exception e){
                    request.setAttribute("info", "Не удалось добавить модель!");
                    request.setAttribute("modelName", modelName);
                    request.setAttribute("modelSize", modelSize);
                    request.setAttribute("modelFirm", modelFirm);
                    request.setAttribute("money", null);
                    request.getRequestDispatcher("/showAddModel").forward(request, response);
                    break;
                }
                break;
            case "/addUser":
                request.getRequestDispatcher("showSignUp").forward(request, response);
                break;
            case "/showEditModel":
                request.setAttribute("models", modelsList);
                request.getRequestDispatcher("/WEB-INF/editModel.jsp").forward(request, response);
                break;
            case "/editModel":
                Model editModel = modelFacade.find(Long.parseLong(request.getParameter("theModels")));
                
                String editModelName = request.getParameter("editModelName");
                String editModelSize = request.getParameter("editModelSize");
                String editModelFirm = request.getParameter("editModelFirm");
                try {
                    editModel.setModelName(editModelName);
                    editModel.setModelSize(editModelSize);
                    editModel.setModelFirm(editModelFirm);
                    editModel.setPrice(Double.parseDouble(request.getParameter("price")));
                    modelFacade.edit(editModel);
                    request.setAttribute("info", "Данные успешно сохранены!");
                    request.getRequestDispatcher("/showEditModel").forward(request, response);
                } catch (Exception e) {
                    request.setAttribute("info", "Изменение не удалось!");
                }
                request.getRequestDispatcher("/showEditModel").forward(request, response);
                break;
            case "/showEditUser":
                request.setAttribute("users", usersList);
                request.getRequestDispatcher("/WEB-INF/editUser.jsp").forward(request, response);
                break;
            case "/editUser":
                User editUser = userFacade.find(Long.parseLong(request.getParameter("theUsers")));           
                
                String editUserFirstName = request.getParameter("editUserFirstName");
                String editUserLastName = request.getParameter("editUserLastName");
                String editUserPhone = request.getParameter("editUserPhone");
                
                try {
                    editUser.setFirstName(editUserFirstName);
                    editUser.setLastName(editUserLastName);
                    editUser.setPhone(editUserPhone);
                    editUser.setMoney(Double.parseDouble(request.getParameter("editUserMoney")));
                    userFacade.edit(editUser);
                    request.setAttribute("info", "Данные успешно сохранены!");
                    request.getRequestDispatcher("/showEditUser").forward(request, response);
                } catch (Exception e) {
                    request.setAttribute("info", "Изменение не удалось!");
                }
                request.getRequestDispatcher("/showEditUser").forward(request, response);
                break;
            case "/showBuyModel":
                request.setAttribute("users", usersList);
                request.setAttribute("models", usersList);
                request.getRequestDispatcher("/WEB-INF/buyModel.jsp").forward(request, response);
                break;
            case "/buyModel":
                Model buyModel = modelFacade.find(Long.parseLong(request.getParameter("buyModels")));
                User buyUser = userFacade.find(Long.parseLong(request.getParameter("buyUsers")));
                break;
//            case "/"
//            case "/addModelBox":
//                List<Model> models = modelFacade.findAll();
//                request.setAttribute("models", models);
//                request.getRequestDispatcher("/WEB-INF/addModelBox.jsp").forward(request, response);
//                break;  
//            case "/listModels":
//                authUser=(User) session.getAttribute("authUser");
//                request.setAttribute("listModels", authUser.getListModelBox());
//                request.getRequestDispatcher("/WEB-INF/listModels.jsp").forward(request, response);
//                break;
//            case "/showModel":
//                String modelId = request.getParameter("modelId");
//                if(modelId != null && modelId.isEmpty()){
//                    request.setAttribute("info", "Неверный запрос");
//                    request.getRequestDispatcher("/listModels").forward(request, response);
//                    break;
//                }
//                try {
//                    ModelBox ab = modelBoxFacade.find(Long.parseLong(modelId));
//                    request.setAttribute("modelBox", ab);
//                } catch (Exception e) {
//                    request.setAttribute("info", "Неверный запрос");
//                    request.getRequestDispatcher("/listModels").forward(request, response);
//                    break;
//                }
//                request.getRequestDispatcher("/WEB-INF/showModel.jsp").forward(request, response);
//                break;
//            case "/removeModel":
//                String id = request.getParameter("id");
//                try {
//                    for(ModelBox modelBox : authUser.getListModelBox()){
//                        if(modelBox.getId().equals(Long.parseLong(id))){
//                            authUser.getListModelBox().remove(modelBox);
//                            userFacade.edit(authUser);
//                            modelBoxFacade.remove(modelBox);
//                            session.setAttribute("authUser", authUser);
//                            File file = new File(modelBox.getModel().getPathToFile());
//                            file.delete();
//                            request.setAttribute("info", "Удален аккаунт: "+modelBox.getName());
//                            break;
//                        }
//                    }
//                    
//                } catch (Exception e) {
//                    request.setAttribute("info", "Удаление не удалось");
//                }
//                request.getRequestDispatcher("/listModels").forward(request, response);
//                break;
            
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