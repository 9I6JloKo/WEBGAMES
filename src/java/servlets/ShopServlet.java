/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.sun.el.lang.ELArithmetic;
import entity.Client;
import entity.History;
import entity.Product;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.ClientFacade;
import session.HistoryFacade;
import session.ProductFacade;
import tools.PasswordProtected;

/**
 *
 * @author anana
 */
@WebServlet(name = "ShopServlet", urlPatterns = {
    "/addClient",
    "/addProduct",
    "/buying",
    "/changeClient",
    "/changeProduct",
    "/addingClient",
    "/addingProduct",
    "/buyingProduct",
    "/changingClient",
    "/changingProduct",
    "/showLogin",
    "/login",
    "/logout",
})
public class ShopServlet extends HttpServlet {
    @EJB ClientFacade clientFacade;
    @EJB ProductFacade productFacade;
    @EJB HistoryFacade historyFacade;
    
    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        if(clientFacade.count()>0) return;
        Client client = new Client();
        client.setClientName("Maksim");
        client.setClientSurname("Grishin");
        client.setClientNumber("538275623");
        client.setLogin("admin");
        client.setLevel("3");
        PasswordProtected passwordProtected = new PasswordProtected();
        String salt = passwordProtected.getSalt();
        client.setSalt(salt);
        String password = passwordProtected.getProtectedPassword("12345", salt);
        client.setPassword(password);
        clientFacade.create(client);
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
        List<Client> clientList = clientFacade.findAll();
        List<Product> productList = productFacade.findAll();
        PasswordProtected passwordProtected = new PasswordProtected();
        HttpSession session = request.getSession();
        switch (path) {
            case "/showLogin":
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                break;
            case "/addClient":
                request.getRequestDispatcher("/WEB-INF/addClient.jsp").forward(request, response);
                break;
            case "/addProduct":
                request.getRequestDispatcher("/WEB-INF/addProduct.jsp").forward(request, response);
                break;
            case "/buying":
                request.setAttribute("clients", clientList);
                request.setAttribute("products", productList);
                request.getRequestDispatcher("/WEB-INF/buying.jsp").forward(request, response);
                break;
            case "/changeClient":
                request.setAttribute("clients", clientList);
                request.getRequestDispatcher("/WEB-INF/changeClient.jsp").forward(request, response);
                break;
            case "/changeProduct":
                request.setAttribute("products", productList);
                request.getRequestDispatcher("/WEB-INF/changeProduct.jsp").forward(request, response);
                break;
            case "/addingClient":
                try{
                    Client client = new Client();
                    client.setClientName(request.getParameter("clientName"));
                    client.setClientSurname(request.getParameter("clientSurname"));
                    client.setClientNumber(request.getParameter("clientNumber"));
                    client.setClientMoney(Double.parseDouble(request.getParameter("clientMoney")));
                    client.setLogin(request.getParameter("clientLogin"));
                    String salt = passwordProtected.getSalt();
                    client.setSalt(salt);
                    String password = passwordProtected.getProtectedPassword(request.getParameter("clientPassword"), salt);
                    client.setPassword(password);
                    client.setLevel("1");
                    clientFacade.create(client);
                    request.setAttribute("info", "Successfull!");
                }
                catch(Exception e){
                    request.setAttribute("info", "Type error");
                    request.setAttribute("clientName", request.getParameter("clientName"));
                    request.setAttribute("clientSurname", request.getParameter("clientSurname"));
                    request.setAttribute("clientNumber", request.getParameter("clientNumber"));
                    request.setAttribute("clientMoney", request.getParameter("clientMoney"));
                    request.setAttribute("clientLogin", request.getParameter("clientLogin"));
                    request.setAttribute("clientPassword", request.getParameter("clientPassword"));
                }
                request.getRequestDispatcher("/WEB-INF/addClient.jsp").forward(request, response);
                break;
            case "/addingProduct":
                try{
                    Product product = new Product();
                    product.setModell(request.getParameter("productModell"));
                    product.setSize(Double.parseDouble(request.getParameter("productSize")));
                    product.setBywho(request.getParameter("productByWho"));
                    product.setPrice(Double.parseDouble(request.getParameter("productPrice")));
                    product.setPiece(Integer.parseInt(request.getParameter("productPiece")));
                    product.setMaxPiece(Integer.parseInt(request.getParameter("productPiece")));
                    productFacade.create(product);
                    request.setAttribute("info", "Successfull!");
                }
                catch(Exception e){
                    request.setAttribute("info", "Type error");
                    request.setAttribute("productModell", request.getParameter("productModell"));
                    request.setAttribute("productSize", request.getParameter("productSize"));
                    request.setAttribute("productByWho", request.getParameter("productByWho"));
                    request.setAttribute("productPrice", request.getParameter("productPrice"));
                    request.setAttribute("productPiece", request.getParameter("productPiece"));
                }
                request.getRequestDispatcher("/WEB-INF/addProduct.jsp").forward(request, response);
                break;
            case "/buyingProduct":
                try{
                    Client client = clientFacade.find(Long.parseLong(request.getParameter("clientt")));
                    Product product = productFacade.find(Long.parseLong(request.getParameter("productt")));
                    if(    !clientList.isEmpty()
                        && !productList.isEmpty()
                        && client.getClientMoney() >= product.getPrice()
                        && product.getPiece() >= 1){
                            client.setClientMoney(client.getClientMoney() - product.getPrice());
                            clientFacade.edit(client);
                            product.setPiece(product.getPiece()-1);
                            productFacade.edit(product);
                            History history = new History();
                            history.setLocalDate(LocalDate.now().plusWeeks(2));
                            history.setClientName(client.getClientName());
                            history.setClientNumber(client.getClientNumber());
                            history.setProduct(product.getModell());
                            history.setSize(product.getSize());
                            Calendar c = new GregorianCalendar();
                            history.setDateOfBuying(c.getTime());
                            historyFacade.create(history);
                            request.setAttribute("info", "Successfull!");
                    }
                    else{
                        request.setAttribute("info", "Error");
                    }
                }
                catch(Exception e){
                    request.setAttribute("info", "Error");
                }
                request.setAttribute("clients", clientList);
                request.setAttribute("products", productList);
                request.getRequestDispatcher("buying").forward(request, response);   
                break;
            case "/changingClient":
                Long find = Long.parseLong(request.getParameter("clientt2"));
                try{
                    Client client = clientFacade.find(find);
                    switch(request.getParameter("optionsRadios")){
                        case "1":
                                client.setClientName(request.getParameter("responsse"));
                                clientFacade.edit(client);
                                request.setAttribute("info", "Successfull!");   
                            break;
                        case "2":
                            client.setClientSurname(request.getParameter("responsse"));
                            clientFacade.edit(client);
                            request.setAttribute("info", "Successfull!");
                            break;
                        case "3":
                            if(Long.parseLong(request.getParameter("responsse")) > 5000000){
                                client.setClientNumber(request.getParameter("responsse"));
                                clientFacade.edit(client);
                                request.setAttribute("info", "Successfull!");
                            }
                            else{
                                request.setAttribute("info", "Error");
                            }
                            break;
                        case "4":
                                client.setClientMoney(Double.parseDouble(request.getParameter("responsse")));
                                clientFacade.edit(client);
                                request.setAttribute("info", "Successfull!");   
                            break;
                        case "5":
                            client.setLogin(request.getParameter("responsse"));
                            clientFacade.edit(client);
                            request.setAttribute("info", "Successfull!");
                            break;
                        case "6":
                            if(request.getParameter("responsse").length() > 5){
                                String salt = passwordProtected.getSalt();
                                client.setSalt(salt);
                                client.setPassword(passwordProtected.getProtectedPassword(request.getParameter("responsse"), salt));
                                clientFacade.edit(client);
                                request.setAttribute("info", "Successfull!");   
                            }
                            else{
                                request.setAttribute("info", "Error");
                            }
                            break;
                        case "7":
                            client.setLevel(request.getParameter("responsse"));
                            clientFacade.edit(client);
                            request.setAttribute("info", "Successfull!");
                            break;
                        default:
                            request.setAttribute("info", "Choose radiobutton");
                            break;
                    }
                }
                catch(Exception e){
                    request.setAttribute("info", "Error");
                    request.setAttribute("responsse", request.getParameter("responsse"));    
                }
                request.getRequestDispatcher("/changeClient").forward(request, response);
            case "/changingProduct":
                Long find2 = Long.parseLong(request.getParameter("product2"));
                try{
                    Product product = productFacade.find(find2);
                    switch(request.getParameter("optionsRadios")){
                        case "1":
                                product.setBywho(request.getParameter("responsse"));
                                productFacade.edit(product);
                                request.setAttribute("info", "Successfull!");   
                            break;
                        case "2":
                                product.setModell(request.getParameter("responsse"));
                                productFacade.edit(product);
                                request.setAttribute("info", "Successfull!");
                            break;
                        case "3":
                                product.setPrice(Double.parseDouble(request.getParameter("responsse")));
                                productFacade.edit(product);
                                request.setAttribute("info", "Successfull!");
                            break;
                        case "4":
                                product.setSize(Double.parseDouble(request.getParameter("responsse")));
                                productFacade.edit(product);
                                request.setAttribute("info", "Successfull!");   
                            break;
                        case "5":
                                if(product.getMaxPiece() >= Integer.parseInt(request.getParameter("responsse"))){
                                }
                                else {
                                    product.setMaxPiece(Integer.parseInt(request.getParameter("responsse")));   
                                }
                                product.setPiece(Integer.parseInt(request.getParameter("responsse")));
                                productFacade.edit(product);
                                request.setAttribute("info", "Successfull!");
                            break;
                        default:
                            request.setAttribute("info", "Choose radiobutton");
                            break;
                    }
                }
                catch(Exception e){
                    request.setAttribute("info", "Error");
                    request.setAttribute("responsse", request.getParameter("responsse"));    
                }
                request.getRequestDispatcher("/changeProduct").forward(request, response);
                break;
            case "/login":
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                Client client = clientFacade.findByLogin(login);
                if(client == null){
                    request.setAttribute("info", "Неверный логин или пароль");
                    request.setAttribute("login", login);
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                    break;
                }
                String salt = client.getSalt();
                password = passwordProtected.getProtectedPassword(password, salt);
                if(!password.equals(client.getPassword())){
                    request.setAttribute("info", "Неверный логин или пароль");
                    request.setAttribute("login", login);
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                    break;
                }
                session = request.getSession(true);
                session.setAttribute("authUser", client);
                request.getRequestDispatcher("/buying").forward(request, response);
                break;
            case "/logout":
                session = request.getSession(false);
                if(session != null){
                    session.invalidate();
                    request.setAttribute("info", "Вы вышли");
                }
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
