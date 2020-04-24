/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyen.ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
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
public class EnrollShowServlet extends HttpServlet {

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
            String action = request.getParameter("action");
            Context ctx = new InitialContext();
            NickySessionBeanLocal local = (NickySessionBeanLocal) ctx.lookup("NickySessionBeanLocalJNDI");
            String url = "error.jsp";
            HttpSession session = request.getSession(false);

            if (action.equals("Confirm")) {
                String eventID = request.getParameter("eventID");
                String username = request.getParameter("username");
                TblUsers user = local.getUserDetails(username);
                if (user.getUserType() == 3) {
                    TblEvents event = local.getEventDetail(Integer.parseInt(eventID));
                    request.setAttribute("FEE", event.getEventPrice());
                    request.setAttribute("EVENT", event);
                    request.setAttribute("USER", user);
                    url = "PaymentShow.jsp";
                } else if (user.getUserType() == 2) {
                    int insert = local.insertEnroll(username, Integer.parseInt(eventID));
                    if (insert == 1) {
                        TblEvents[] events = local.getEventbyUser(username);
                        request.setAttribute("INFO", events);
                        url = "MyEvent.jsp";
                    } else {
                        request.setAttribute("ERROR", "Insert Failed");
                    }
                }
            } else if (action.equals("Cancel")) {
                url = "EventShow.jsp";
            } else {
                request.setAttribute("ERROR", "Action is not supported");
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
