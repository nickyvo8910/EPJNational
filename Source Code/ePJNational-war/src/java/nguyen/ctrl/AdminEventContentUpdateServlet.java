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
import nicky.entitybean.TblContents;
import nicky.sessbean.NickySessionBeanLocal;

/**
 *
 * @author Quy
 */
public class AdminEventContentUpdateServlet extends HttpServlet {

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

            if (action.equals("Submit")) {
                int isSelect = 0;
                String id = request.getParameter("textID");
                int ContentId = Integer.parseInt(id);
                String select = request.getParameter("ChkIsSelect");
                if (select != null) {
                    isSelect = 1;
                }
                TblContents contents = local.getEventContentDetails(ContentId);
                int eventID = contents.getTblEvents().getEventID();

                Boolean result2 = local.updateContentDeselected(eventID);
                Boolean result = local.updateContent(ContentId, isSelect);
                if (result == true && result2 == true) {
                    request.setAttribute("alert", "Update Successful");
                    request.getRequestDispatcher("AdminEventContentServlet?action=Search&EventID=" + eventID + "").forward(request, response);
                } else {
                    request.setAttribute("alert", "Update Unsuccessful");
                    request.getRequestDispatcher("AdminEventContentUpdate.jsp").forward(request, response);
                }
            } else if (action.equals("Edit")) {
                String id = (String) request.getAttribute("ID");
                int eventID = Integer.parseInt(id);
                TblContents record = local.getEventContentDetails(eventID);
                request.setAttribute("INFO", record);
                request.getRequestDispatcher("AdminEventContentUpdate.jsp").forward(request, response);
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
