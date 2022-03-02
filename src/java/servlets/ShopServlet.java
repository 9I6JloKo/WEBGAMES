/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Client;
import entity.History;
import entity.Product;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.ClientFacade;
import session.HistoryFacade;
import session.ProductFacade;

/**
 *
 * @author anana
 */
@WebServlet(name = "ShopServlet", urlPatterns = {
    "/addClient",
    "/addProduct",
    "/Buying",
    "/ChangeClient",
    "/ChangeProduct",
    "/addingClient",
    "/addingProduct",
    "/Buying",
    "/BuyingProduct"
    
})
public class ShopServlet extends HttpServlet {
    @EJB ClientFacade clientFacade;
    @EJB ProductFacade productFacade;
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
        String path = request.getServletPath();
        List<Client> clientList = clientFacade.findAll();
        List<Product> productList = productFacade.findAll();
        switch (path) {
            case "/addClient":
                request.getRequestDispatcher("/WEB-INF/addClient.jsp").forward(request, response);
                break;
            case "/addProduct":
                request.getRequestDispatcher("/WEB-INF/addProduct.jsp").forward(request, response);
                break;
            case "/Buying":
                request.setAttribute("clients", clientList);
                request.setAttribute("products", productList);
                request.getRequestDispatcher("/WEB-INF/buying.jsp").forward(request, response);
                break;
            case "/ChangeClient":
                request.getRequestDispatcher("/WEB-INF/addClient.jsp").forward(request, response);
                break;
            case "/ChangeProduct":
                request.getRequestDispatcher("/WEB-INF/addClient.jsp").forward(request, response);
                break;
            case "/addingClient":
                try{
                    Client client = new Client();
                    client.setClientName(request.getParameter("clientName"));
                    client.setClientSurname(request.getParameter("clientSurname"));
                    client.setClientNumber(request.getParameter("clientNumber"));
                    client.setClientMoney(Double.parseDouble(request.getParameter("clientMoney")));
                    client.setLogin(request.getParameter("clientLogin"));
                    client.setPassword(request.getParameter("clientPassword"));
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
            case "/BuyingProduct":
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
                request.setAttribute("clients", clientList);
                request.setAttribute("products", productList);
                request.getRequestDispatcher("Buying").forward(request, response);
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
