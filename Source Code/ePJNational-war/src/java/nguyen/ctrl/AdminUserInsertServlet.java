/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyen.ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nicky.sessbean.NickySessionBeanLocal;

/**
 *
 * @author Quy
 */
public class AdminUserInsertServlet extends HttpServlet {

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
            String url = "";
            if (action.equals("Submit")) {
                String email = request.getParameter("txtEmail");
                String pass = request.getParameter("txtConfirmPassword");
                String name = request.getParameter("txtName");
                String address = request.getParameter("txtAddress");
                String phone = request.getParameter("txtPhone");
                String gender = request.getParameter("txtGender");
                String day = request.getParameter("drpDay");
                String month = request.getParameter("drpMonth");
                String year = request.getParameter("drpYear");
                String img = request.getParameter("txtImg");
                boolean Gender = Boolean.parseBoolean(gender);
                if (img.equals("")) {
                    img = "UserImage.jpg";
                }
                String date = day + "/" + month + "/" + year;
                int insert = local.insertUser(email, pass, name, Gender, address, phone, date, img);

                if (insert == 1) {
                    request.setAttribute("alert", "Insert Successful");
                    url = "AdminUserShow.jsp";
                } else if (insert == -1) {
                    request.setAttribute("alert", "Existed Username Has Been Found.Please Choose Another One.");
                    url = "AdminUserShow.jsp";
                } else {
                    request.setAttribute("alert", "Insert Unsuccessful");
                    url = "AdminUserInsert.jsp";
                }
                request.getRequestDispatcher(url).forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
