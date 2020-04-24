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
import nicky.entitybean.TblEnrolls;
import nicky.entitybean.TblEvents;
import nicky.sessbean.NickySessionBeanLocal;

/**
 *
 * @author Quy
 */
public class AdminEventUserUpdateServlet extends HttpServlet {

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
            String url = "AdminEventUserUpdate.jsp";
            {
                if (action.equals("Submit")) {
                    int enrollID = Integer.parseInt(request.getParameter("enrollID"));
                    TblEnrolls enroll = local.getEnrollDetails(enrollID);
                    int eventID = enroll.getTblEvents().getEventID();
                    int result = 1;
                    int result1 = 1;
                    if(enroll.getTblEvents().getEventStt()==1){
                    String score = request.getParameter("txtScore");
                    int enrollScore = Integer.parseInt(score);
                    result = local.updateEnrollScore(enrollID, eventID, enrollScore);
                    }
                    if(enroll.getTblEvents().getEventStt() ==0){
                    int stt = Integer.parseInt(request.getParameter("txtStt"));
                    result1 = local.updateEnrollPaymentAdmin(enrollID, stt);
                    }
                    if (result == 1 && result1 == 1) {
                        request.setAttribute("alert", "Update Successful");
                        url = "AdminEventUserShowServlet?action=SearchAll&EventID=" + eventID + "";
                    } else {
                        request.setAttribute("alert", "Update Unsuccessful");
                        url = "AdminEventShowServlet?action=Search&txtSearch=";
                    }
                } else if (action.equals("Edit")) {

                    String id = (String) request.getAttribute("ID");
                    int enrollID = Integer.parseInt(id);
                    TblEnrolls record = local.getEnrollDetails(enrollID);
                    TblEvents event = record.getTblEvents();
                    int isFinished = event.getEventStt();
                    request.setAttribute("EnrollStt", record.getEnrollStt());
                    request.setAttribute("ISFINISHED", isFinished);
                    request.setAttribute("INFO", record);
                    url = "AdminEventUserUpdate.jsp";

                } else {
                    url = "AdminEventUserUpdate.jsp";
                    request.setAttribute("alert", "Action is Not Supported");
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
