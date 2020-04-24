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
import nicky.entitybean.PopEvent;
import nicky.entitybean.TblEvents;
import nicky.sessbean.NickySessionBeanLocal;

/**
 *
 * @author QUYDNGC60257
 */
public class AdminReportLeastEventsServlet extends HttpServlet {

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
                String day = request.getParameter("drpDay");
                String month = request.getParameter("drpMonth");
                String year = request.getParameter("drpYear");
                String day1 = request.getParameter("drpDay1");
                String month1 = request.getParameter("drpMonth1");
                String year1 = request.getParameter("drpYear1");

                String date = month + "/" + day + "/" + year;
                String date1 = month1 + "/" + day1 + "/" + year1;

                PopEvent[] pop = local.reportLeastEventFromTo(date, date1);
                int b = pop.length;
                if(b>10) b=10;
                TblEvents[] events = new TblEvents[b];
                for (int i= 0; i < b; i++) {
                    events[i] = local.getEventDetail(pop[i].getEventID());
                }
                int[] totalEnroll = new int[events.length];
                for (int i = 0; i < events.length; i++) {
                    totalEnroll[i] = pop[i].getEventCount();
                }
                request.setAttribute("INFO2", totalEnroll);
                request.setAttribute("INFO", events);
                request.setAttribute("date", date);
                request.setAttribute("date1", date1);
                request.getRequestDispatcher("AdminTopLeastEvents.jsp").forward(request, response);
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
