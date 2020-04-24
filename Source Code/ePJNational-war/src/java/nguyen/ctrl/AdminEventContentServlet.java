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
import nicky.entitybean.TblContents;
import nicky.sessbean.NickySessionBeanLocal;

/**
 *
 * @author Asus
 */
public class AdminEventContentServlet extends HttpServlet {

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


            if (action.equals("Search")) {

                String id = (String) request.getAttribute("INFO1");

                String ed = request.getParameter("EventID");
                if (ed == null) {
                    int eventID = Integer.parseInt(id);
                    TblContents[] result = local.getEventContent(eventID);
                    ArrayList<TblContents> arrContents = new ArrayList<TblContents>();
                    for (int i = 0; i < result.length; i++) {
                        if (result[i].getIsDeleted() == false) {
                            arrContents.add(result[i]);
                        }
                    }
                    result = new TblContents[arrContents.size()];
                    arrContents.toArray(result);
                    request.setAttribute("INFO", result);
                } else {
                    TblContents[] result = local.getEventContent(Integer.parseInt(ed));
                    ArrayList<TblContents> arrContents = new ArrayList<TblContents>();
                    for (int i = 0; i < result.length; i++) {
                        if (result[i].getIsDeleted() == false) {
                            arrContents.add(result[i]);
                        }
                    }
                    result = new TblContents[arrContents.size()];
                    arrContents.toArray(result);
                    request.setAttribute("INFO", result);
                }
                request.getRequestDispatcher("AdminEventContentShow.jsp").forward(request, response);
            } else if (action.equals("Edit")) {
                String id = request.getParameter("id");
                request.setAttribute("ID", id);
                request.getRequestDispatcher("AdminEventContentUpdateServlet?action=Edit").forward(request, response);
            } else if (action.equals("Delete")) {
                String ed = request.getParameter("eventID");
                String id = request.getParameter("id");
                if (local.updateContentStt(Integer.parseInt(id))) {
                    request.getRequestDispatcher("AdminEventContentServlet?action=SearchAll&EventID=" + ed + "").forward(request, response);
                } else {
                    request.setAttribute("alert", "Delete Unsuccessful");
                    request.getRequestDispatcher("AdminEventContentServlet?action=SearchAll&EventID=" + ed + "").forward(request, response);
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
