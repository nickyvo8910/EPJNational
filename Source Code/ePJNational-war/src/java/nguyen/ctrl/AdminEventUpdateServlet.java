/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyen.ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
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
 * @author Quy
 */
public class AdminEventUpdateServlet extends HttpServlet {

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
            {
                if (action.equals("Submit")) {
                    String name = request.getParameter("txtName");
                    String host = request.getParameter("txtHost");
                    int min = Integer.parseInt(request.getParameter("txtMin"));
                    int max = Integer.parseInt(request.getParameter("txtMax"));
                    float price = Float.parseFloat(request.getParameter("txtPrice"));
                    String des = request.getParameter("txtDes");

                    String date = request.getParameter("txtDay");
                    String[] arr = date.split("/");
                    Calendar cal = Calendar.getInstance();
                    cal.set(Integer.parseInt(arr[2]), Integer.parseInt(arr[1]) - 1, Integer.parseInt(arr[0]), 0, 0, 0);
                    Date date1 = cal.getTime();


                    int id = Integer.parseInt(request.getParameter("txtID"));
                    String img = request.getParameter("txtImg");
                    TblEvents event = local.getEventDetail(id);
                    if (img.equals("")) {
                        img = event.getEventImg();
                    }
                    int result = local.updateEvent(id, name, host, min, max, price, date1, des, img);
                    if (result == 1) {
                        request.setAttribute("alert", "Update Successful");
                        request.getRequestDispatcher("AdminEventShow.jsp").forward(request, response);
                    } else {
                        request.setAttribute("alert", "Update Unsuccessful");
                        request.getRequestDispatcher("AdminEventUpdate.jsp").forward(request, response);
                    }
                } else if (action.equals("Edit")) {
                    String id = (String) request.getAttribute("INFO1");
                    int EventID = Integer.parseInt(id);
                    TblEvents record = local.getEventDetail(EventID);
                    boolean hadUser = false;
                    //   if(record.getTblEnrollsCollection()!=null &&record.getTblEnrollsCollection().size() > 0)
                    //      hadUser= true;
                    //   request.setAttribute("hadUser", hadUser);
                    request.setAttribute("INFO1", record);
                    request.getRequestDispatcher("AdminEventUpdate.jsp").forward(request, response);
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
