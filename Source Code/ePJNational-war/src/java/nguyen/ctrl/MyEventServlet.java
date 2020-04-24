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
import nicky.entitybean.TblEnrolls;
import nicky.entitybean.TblEvents;
import nicky.entitybean.TblUsers;
import nicky.sessbean.NickySessionBeanLocal;

/**
 *
 * @author Nicky
 */
public class MyEventServlet extends HttpServlet {

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

        try {
            String action = request.getParameter("action");
            Context ctx = new InitialContext();
            NickySessionBeanLocal local = (NickySessionBeanLocal) ctx.lookup("NickySessionBeanLocalJNDI");
            String url = "error.jsp";
            HttpSession session = request.getSession(false);

            if (action.equals("Search")) {
                String username = (String) session.getAttribute("username");
                TblEvents[] events = local.getEventbyUser(username);
                TblEnrolls[] enrolls = new TblEnrolls[events.length];
                for (int i = 0; i < events.length; i++) {
                    enrolls[i] = local.getEnrollByUserEvent(events[i].getEventID(), username);
                }
                request.setAttribute("ENROLLINFO", enrolls);
                request.setAttribute("INFO", events);
                url = "MyEvent.jsp";
            } else if (action.equals("Search by Date")) {
                String from = request.getParameter("txtFrom");
                String to = request.getParameter("txtTo");
                String username = (String) session.getAttribute("username");
                if (from.equals("") || from == null) {
                    from = "01/01/1990";
                }
                if (to.equals("") || to == null) {
                    to = "12/30/2100";
                }
                TblEvents[] events = local.searchFromToMyEvent(from, to, username);
                TblEnrolls[] enrolls = new TblEnrolls[events.length];
                for (int i = 0; i < events.length; i++) {
                    enrolls[i] = local.getEnrollByUserEvent(events[i].getEventID(), username);
                }
                request.setAttribute("ENROLLINFO", enrolls);
                request.setAttribute("INFO", events);
                url = "MyEvent.jsp";
            } else if (action.equals("Search by Name")) {
                String search = request.getParameter("txtSearch");

                String username = (String) session.getAttribute("username");
                TblEvents[] events = local.searchLikeEventNameMyEvent(search, username);
                TblEnrolls[] enrolls = new TblEnrolls[events.length];
                for (int i = 0; i < events.length; i++) {
                    enrolls[i] = local.getEnrollByUserEvent(events[i].getEventID(), username);
                }
                request.setAttribute("ENROLLINFO", enrolls);
                request.setAttribute("INFO", events);
                url = "MyEvent.jsp";
            } else if (action.equals("Contents")) {
                String eventID = request.getParameter("eventID");
                request.setAttribute("eventID", eventID);
                url = "UploadContent.jsp";
            } else if (action.equals("Unenroll")) {
                String eventID = request.getParameter("eventID");
                String username = (String) session.getAttribute("username");
                TblEvents event = local.getEventDetail(Integer.parseInt(eventID));
                TblUsers user = local.getUserDetails(username);
                TblEnrolls enroll = local.getEnrollByUserEvent(Integer.parseInt(eventID), username);
                request.setAttribute("USER", user);
                request.setAttribute("EVENT", event);
                request.setAttribute("ENROLL", enroll);
                url = "UnEnrollShow.jsp";

            } else {
                request.setAttribute("ERROR", "Action is not supported");
            }
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
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
