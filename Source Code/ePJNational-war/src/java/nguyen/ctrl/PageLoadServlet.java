/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyen.ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nicky.entitybean.TblEvents;
import nicky.entitybean.TblUsers;
import nicky.sessbean.NickySessionBeanLocal;

/**
 *
 * @author Nicky
 */
public class PageLoadServlet extends HttpServlet {

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
            String target = request.getParameter("target");
            String action = request.getParameter("action");
            HttpSession session = request.getSession(false);
            if (target == null) {
                target = "Home.jsp";
            }
            if (action != null) {
                if (action.equals("Submit")) {
                    if (session.getAttribute("username") != null && !session.getAttribute("username").equals("guest@yahoo.com")) {
                        String username = session.getAttribute("username").toString();
                        String feedback = request.getParameter("txtFeedback");
                        Calendar cal = Calendar.getInstance();
                        local.insertFeedback(username, feedback);
                    } else {
                        target = "Registration.jsp";
                    }
                } else if (action.equals("Go")) {
                    if (session.getAttribute("username") != null && !session.getAttribute("username").equals("guest@yahoo.com")) {
                        String username = session.getAttribute("username").toString();
                        if (local.updateUserSubscribe(username) != 1) {
                            target = "error.jsp";
                            request.setAttribute("ERROR", "Update Subscribe Failed");
                        }

                    } else {
                        target = "Registration.jsp?txtUser=" + request.getParameter("txtSubscribe");
                    }
                } else if (action.equals("get")) {
                    TblEvents[] recentEvent = local.getRecentEvent();
                    TblUsers[] topActive = local.getTopActive();
                    TblEvents[] topPopular = local.getPopularEvent();
                    TblUsers[] topWinner = local.getTopWinner();
                    if (session != null) {
                        session.setAttribute("recentEvent", recentEvent);
                        session.setAttribute("topActive", topActive);
                        session.setAttribute("topPopular", topPopular);
                        session.setAttribute("topWinner", topWinner);
                    }
                }
            }
            local.AutoEvent();
            local.autoEnroll();
            boolean updateEvent = true;
            session.setAttribute("updateEvent", updateEvent);

            request.getRequestDispatcher(target).forward(request, response);
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
