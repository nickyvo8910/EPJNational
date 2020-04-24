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
import nicky.entitybean.TblEvents;
import nicky.sessbean.NickySessionBeanLocal;

/**
 *
 * @author Asus
 */
public class AdminEventShowServlet extends HttpServlet {

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

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminEventShowServlet</title>");
            out.println("</head>");
            out.println("<body>");

            out.println("</body>");
            out.println("</html>");
            Context ctx = new InitialContext();
            nicky.sessbean.NickySessionBeanLocal local = (NickySessionBeanLocal) ctx.lookup("NickySessionBeanLocalJNDI");
            String action = request.getParameter("action");
            {
                if (action.equals("Search")) {
                    String search = request.getParameter("txtSearch");
                    TblEvents[] result = local.searchLikeEventNameHome(search);
                    request.setAttribute("INFO", result);
                    request.getRequestDispatcher("AdminEventShow.jsp").forward(request, response);
                } else if (action.equals("Participants")) {
                    String id = request.getParameter("txtEventID");
                    request.setAttribute("INFO1", id);
                    request.getRequestDispatcher("AdminEventUserShowServlet?action=SearchAll").forward(request, response);
                } else if (action.equals("Comments")) {
                    String id = request.getParameter("txtEventID");
                    request.setAttribute("INFO1", id);
                    request.getRequestDispatcher("AdminEventCommentShowServlet?action=SearchAll").forward(request, response);
                } else if (action.equals("Contents")) {
                    String id = request.getParameter("txtEventID");
                    request.setAttribute("INFO1", id);
                    request.getRequestDispatcher("AdminEventContentServlet?action=Search").forward(request, response);
                } else if (action.equals("Edit")) {
                    String id = request.getParameter("id");
                    request.setAttribute("INFO1", id);
                    request.getRequestDispatcher("AdminEventUpdateServlet?action=Edit").forward(request, response);
                } else if (action.equals("Delete")) {
                    String id = request.getParameter("id");
                    int eventID = Integer.parseInt(id);
                    String url ="AdminEventShow.jsp";
                    if (!local.updateEventStt(eventID)) {
                        request.setAttribute("alert", "Delete Failed");
                    }
                    request.getRequestDispatcher(url).forward(request, response);
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
