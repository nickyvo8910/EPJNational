/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyen.ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nicky.entitybean.TblUsers;
import nicky.sessbean.NickySessionBeanLocal;

/**
 *
 * @author Nicky
 */
public class LoginServlet extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            Context ctx = new InitialContext();
            nicky.sessbean.NickySessionBeanLocal local = (NickySessionBeanLocal) ctx.lookup("NickySessionBeanLocalJNDI");
            String action = request.getParameter("action");
            String url = "error.jsp";
            HttpSession session = request.getSession(false);
            if (action.equals("LogIn")) {
                String username = request.getParameter("txtEmail");
                String password = request.getParameter("txtPass");
                if (local.login(username, password) == 1) {
                    if (session != null) {
                        session.setMaxInactiveInterval(21600);
                        session.setAttribute("admin", "abc");
                        url = "Admin.jsp";
                    }
                } else if (local.login(username, password) == 2) {

                    if (session != null) {
                        session.setMaxInactiveInterval(21600);
                        session.setAttribute("userType", 2);
                        session.setAttribute("username", username);
                        TblUsers user = local.getUserDetails(username);
                        session.setAttribute("USERINFO_PANEL", user);
                    }
                    url = "Home.jsp";
                } else if (local.login(username, password) == 3) {

                    if (session != null) {
                        session.setMaxInactiveInterval(21600);
                        session.setAttribute("userType", 3);
                        session.setAttribute("username", username);
                        TblUsers user = local.getUserDetails(username);
                        session.setAttribute("USERINFO_PANEL", user);
                    }
                    url = "Home.jsp";
                } else {
                    request.setAttribute("ERROR", "Invalid Login");
                }
            }
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
