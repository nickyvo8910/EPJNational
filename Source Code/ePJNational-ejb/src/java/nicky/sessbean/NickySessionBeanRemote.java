/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicky.sessbean;

import java.util.Date;
import javax.ejb.Remote;
import nicky.entitybean.TblComments;
import nicky.entitybean.TblContents;
import nicky.entitybean.TblEnrolls;
import nicky.entitybean.TblEvents;
import nicky.entitybean.TblFAQ;
import nicky.entitybean.TblFeedback;
import nicky.entitybean.TblUsers;

/**
 *
 * @author Nicky
 */
@Remote
public interface NickySessionBeanRemote {

    nicky.entitybean.TblEvents[] searchLikeEventNameHome(String name);

    nicky.entitybean.TblEvents[] searchFromToEvent(String from, String to);

    nicky.entitybean.TblEvents[] getRecentEvent();

    TblEvents getEventDetail(int id);

    boolean insertEvent(String name, String host, int min, int max, float price, Date date, String des, String img);

    boolean chkDateEvent(Date date);

    int updateEvent(int id, String name, String host, int min, int max, float price, Date date, String des, String img);

    nicky.entitybean.TblContents[] getEventContent(int eventID);

    boolean insertContent(int eventID,String sender,  String url);

    boolean updateContent(int contentID, int isSelected);

    TblContents getEventContentDetails(int id);

    boolean updateContentStt(int contentID);

    nicky.entitybean.TblEnrolls[] getUserEnrolls(String username);

    nicky.entitybean.TblEnrolls[] getEventEnroll(int eventID);

    int getEventTotalEnroll(int eventID);

    boolean chkinsertEnroll(String username, int eventID);

    int insertEnroll(String username, int eventID);

    TblUsers getUserDetails(String username);

    int updateEnrollPaymentAdmin(int enrollID, int stt);

    TblEnrolls getEnrollDetails(int enrollID);

    int updateEnrollScore(int enrollID, int eventID, int score);

    int updateEnrollStt(int enrollID);

    int login(String username, String password);

    nicky.entitybean.TblUsers[] searchUserLikeUsername(String username);

    int insertForeignUser(String username, String password, String name, String DOB, String Addr, String Phone, boolean Gender, boolean isSubscribe);

    int insertUser(String username, String password, String name, boolean gender, String address, String phone, String DOB, String img);

    int updateUserProfile(String username, String name, String DOB, String Phone, String Addr, boolean Gender);

    int updateUserImg(String username, String img);

    int updateUserPass(String username, String password);

    int updateUserSubscribe(String username);

    int updateUserStt(String username);

    nicky.entitybean.TblComments[] getCommentEvent(int eventID);

    int insertComment(int eventID, String Date, String username, String comment);

    int updateCommentStt(int cmtID);

    TblComments getCommentsDetails(int cmtID);

    TblFAQ getFAQ(int faqID);

    nicky.entitybean.TblFAQ[] getFAQList();

    int insertFAQ(String question, String ans);

    int updateFAQ(int faqID, String ques, String ans);

    int updateFAQStt(int faqID);

    TblFeedback getFeedBackDetails(int fbID);

    nicky.entitybean.TblFeedback[] getFBList();

    nicky.entitybean.TblFeedback[] getFBListUser(String username);

    int insertFeedback(String username, String question);

    int updateFB(int fbID, String fbAns);

    int updateFBStt(int fbID);

    nicky.entitybean.TblUsers[] getTopActive();

    nicky.entitybean.TblEvents[] getPopularEvent();

    nicky.entitybean.TblEvents[] getLeastEvent();

    nicky.entitybean.TblUsers[] getTopWinner();

    boolean chkUserEnroll(String username, int eventID);

    int getTotalComment(int eventID);

    nicky.entitybean.TblEvents[] getEventbyUser(String username);

    nicky.entitybean.TblComments[] getCommentsByLikeUser(int eventID, String username);

    TblEnrolls getEnrollByUserEvent(int eventID, String username);

    nicky.entitybean.TblEnrolls[] getEnrollByLikeUserEvent(int eventID, String username);

    nicky.entitybean.TblFeedback[] getFBListUserOK(String username);

    nicky.entitybean.TblFAQ[] getFAQListOK();

    nicky.entitybean.TblEvents[] getEventAuto();

    nicky.entitybean.TblEnrolls[] getEnrollAuto();

    void AutoEvent();

    boolean updateContentDeselected(int eventID);

    TblContents getContentUser(int eventID, String username);

    void autoEnroll();

    int updateEnrollWinner(int enrollID);

    nicky.entitybean.TblEvents[] reportLeastEvent(String from, String to);

    boolean chkExistedWinner(int eventID);

    nicky.entitybean.TblEvents[] searchFromToMyEvent(String from, String to, String username);

    nicky.entitybean.TblEvents[] searchLikeEventNameMyEvent(String eventName, String username);

    nicky.entitybean.PopEvent[] reportLeastEventFromTo(String from, String to);

    int getTopScoreEvent(int eventID);

    nicky.entitybean.PopEvent[] reportTopEventFromTo(String from, String to);

    boolean updateEventStt(int eventID);

    nicky.entitybean.TopActive[] reportTopActiveFromTo(String from, String to);
}
