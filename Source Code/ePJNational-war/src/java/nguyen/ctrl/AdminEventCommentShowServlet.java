/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyen.ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nicky.entitybean.TblComments;
import nicky.sessbean.NickySessionBeanLocal;

/**
 *
 * @author Asus
 */
public class AdminEventCommentShowServlet extends HttpServlet {

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


            if (action.equals("SearchAll")) {

                String id = (String) request.getAttribute("INFO1");
                int eventID = Integer.parseInt(id);
                TblComments[] result = local.getCommentEvent(eventID);
                ArrayList<TblComments> arrComments = new ArrayList<TblComments>();
                for (int i = 0; i < result.length; i++) {
                    if (result[i].getIsDeleted() == false) {
                        arrComments.add(result[i]);
                    }
                }
                result = new TblComments[arrComments.size()];
                arrComments.toArray(result);
                request.setAttribute("INFO", result);
                request.getRequestDispatcher("AdminEventCommentShow.jsp?ID=" + id + "").forward(request, response);

            } else if (action.equals("Search")) {
                String id = request.getParameter("ID");
                int eventID = Integer.parseInt(id);
                String username = request.getParameter("txtSearch");
                TblComments[] result1 = local.getCommentsByLikeUser(eventID, username);
                ArrayList<TblComments> arrComments = new ArrayList<TblComments>();
                for (int i = 0; i < result1.length; i++) {
                    if (result1[i].getIsDeleted() == false) {
                        arrComments.add(result1[i]);
                    }
                }
                result1 = new TblComments[arrComments.size()];
                arrComments.toArray(result1);
                request.setAttribute("INFO2", result1);
                request.getRequestDispatcher("AdminEventCommentShow.jsp").forward(request, response);
            } else if (action.equals("Delete")) {
                String ed = request.getParameter("eventID");
                String id = request.getParameter("id");
                if (local.updateCommentStt(Integer.parseInt(id)) == 1) {
                    request.setAttribute("INFO1", ed);
                    request.getRequestDispatcher("AdminEventCommentShowServlet?action=SearchAll").forward(request, response);
                } else {
                    request.setAttribute("alert", "Delete Unsuccessful");
                    request.setAttribute("INFO1", ed);
                    request.getRequestDispatcher("AdminEventCommentShowServlet?action=SearchAll").forward(request, response);
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
