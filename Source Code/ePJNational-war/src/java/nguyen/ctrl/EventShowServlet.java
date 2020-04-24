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
import nicky.entitybean.TblEvents;
import nicky.entitybean.TblUsers;
import nicky.sessbean.NickySessionBeanLocal;

/**
 *
 * @author Nicky
 */
public class EventShowServlet extends HttpServlet {

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

            if (action.equals("Search")) {
                String from = request.getParameter("txtFrom");
                String to = request.getParameter("txtTo");
                if(from.equals("")|| from == null){
                    from = "01/01/1990";
                }
                if(to.equals("")|| to == null){
                    to = "12/30/2100";
                }
                TblEvents[] result = local.searchFromToEvent(from, to);
                request.setAttribute("INFO", result);
                url = "EventShow.jsp";
            } else if (action.equals("Enroll")) {
                if (session.getAttribute("username") != null) {
                    String eventID = request.getParameter("eventID");
                    TblEvents event = local.getEventDetail(Integer.parseInt(eventID));
                    TblUsers user = local.getUserDetails((String) session.getAttribute("username"));
                    if (!local.chkUserEnroll((String) session.getAttribute("username"), Integer.parseInt(eventID))) {
                        if (event == null) {
                            request.setAttribute("ERROR", "Invalid EventID");
                        } else if (user == null) {
                            request.setAttribute("ERROR", "Invalid UserID");
                        } else {
                            if (user.getUserType() == 3) {
                                request.setAttribute("FEE", event.getEventPrice());
                            } else {
                                request.setAttribute("FEE", Double.valueOf("0"));
                            }
                            request.setAttribute("EVENT", event);
                            request.setAttribute("USER", user);
                            url = "EnrollShow.jsp";
                        }
                    } else {
                        request.setAttribute("ERROR", "User has already signed up for this event.");
                    }
                } else {
                    url = "Registration.jsp";
                }
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
