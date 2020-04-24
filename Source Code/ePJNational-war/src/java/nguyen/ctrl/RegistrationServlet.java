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
import nicky.sessbean.NickySessionBeanLocal;

/**
 *
 * @author Nicky
 */
public class RegistrationServlet extends HttpServlet {

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
            NickySessionBeanLocal local = (NickySessionBeanLocal) ctx.lookup("NickySessionBeanLocalJNDI");
            String url = "error.jsp";
            String action = request.getParameter("action");

            String username = request.getParameter("txtMail");
            String pass = request.getParameter("txtPass");
            String fullname = request.getParameter("txtName");
            String DOB = request.getParameter("txtDOB");
            String addr = request.getParameter("txtAddr");
            String phone = request.getParameter("txtPhone");
            String gender = request.getParameter("cmbGender");
            boolean sex = false;
            if (gender.equals("1")) {
                sex = true;
            }
            boolean subscribe = false;
            if (request.getParameter("chkSubscribe") != null) {
                subscribe = true;
            }

            int insert = local.insertForeignUser(username, pass, fullname, DOB, addr, phone, sex, subscribe);
            if (insert == 1) {
                url = "Home.jsp";
            } else if (insert == -1) {
                request.setAttribute("ERROR", "Existed Username Has Been Found.Please Choose Another One.");
            } else {
                request.setAttribute("ERROR", "Registration Failed");
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
