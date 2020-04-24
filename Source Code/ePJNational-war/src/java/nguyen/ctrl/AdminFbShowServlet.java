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
import nicky.entitybean.TblFeedback;
import nicky.entitybean.TblUsers;
import nicky.sessbean.NickySessionBeanLocal;

/**
 *
 * @author KieuTrinh
 */
public class AdminFbShowServlet extends HttpServlet {

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
            {
                if (action.equals("Search")) {
                    String search = request.getParameter("txtSearch");
                    TblFeedback[] feedbacks = local.getFBListUser(search);
                    request.setAttribute("INFO", feedbacks);
                    request.getRequestDispatcher("AdminFeedbackShow.jsp").forward(request, response);
                } else if (action.equals("Edit")) {
                    int id = Integer.parseInt(request.getParameter("id"));
                    TblFeedback result = local.getFeedBackDetails(id);
                    request.setAttribute("INFO", result);
                    request.getRequestDispatcher("AdminFeedbackUpdate.jsp").forward(request, response);
                } else if (action.equals("Update")) {
                    String ans = request.getParameter("txtAns");
                    int id = Integer.parseInt(request.getParameter("txtId"));
                    if (local.updateFB(id, ans) == 1) {
                        url="AdminFeedBackShow.jsp";

                    }else url = "AdminFeedbackUpdate.jsp";
                    request.getRequestDispatcher(url).forward(request, response);


                } else if (action.equals("Delete")) {
                    int id = Integer.parseInt(request.getParameter("id"));
                    if(local.updateFBStt(id) == 1){
                    request.getRequestDispatcher("AdminFeedbackShow.jsp").forward(request, response);
                    }else{
                        request.setAttribute("alert", "Delete Unsuccessful");
                        request.getRequestDispatcher("AdminFeedbackShow.jsp").forward(request, response);
                    }
                }
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
