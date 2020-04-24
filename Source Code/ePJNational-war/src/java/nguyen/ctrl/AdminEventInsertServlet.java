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
import java.util.Date;
import java.util.Locale;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nicky.sessbean.NickySessionBeanLocal;

/**
 *
 * @author Quy
 */
public class AdminEventInsertServlet extends HttpServlet {

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
            String url = "AdminEventShow.jsp";
            {
                if (action.equals("Submit")) {
                    String name = request.getParameter("txtName");
                    String host = request.getParameter("txtHost");
                    int min = Integer.parseInt(request.getParameter("txtMin"));
                    int max = Integer.parseInt(request.getParameter("txtMax"));
                    float price = Float.parseFloat(request.getParameter("txtPrice"));
                    String des = request.getParameter("txtDes");
                    String img = request.getParameter("txtImg");
                    if(img.equals("") || img == null){
                        img ="imageEvent.jpg";
                    }

                    String dateString = request.getParameter("txtDay");
                    String[] arrDate = dateString.split("/");
                    Calendar cal = Calendar.getInstance();
                    cal.set(Integer.parseInt(arrDate[2]), Integer.parseInt(arrDate[1])-1, Integer.parseInt(arrDate[0]), 0, 0, 0);
                    Date date = cal.getTime();

                    boolean result = local.insertEvent(name, host, min, max, price, date, des, img);
                    if (result == true) {
                        request.setAttribute("alert", "Insert Successful");
                        url = "AdminEventShow.jsp";
                    } else {
                        request.setAttribute("alert", "Insert Unsuccessful");
                        url = "AdminEventInsert.jsp";
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
