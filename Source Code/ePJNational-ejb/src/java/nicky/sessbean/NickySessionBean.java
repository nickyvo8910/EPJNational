/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicky.sessbean;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import nicky.entitybean.PopEvent;
import nicky.entitybean.TblComments;
import nicky.entitybean.TblContents;
import nicky.entitybean.TblEnrolls;
import nicky.entitybean.TblEvents;
import nicky.entitybean.TblFAQ;
import nicky.entitybean.TblFeedback;
import nicky.entitybean.TblUsers;
import nicky.entitybean.TopActive;

/**
 *
 * @author Nicky
 */
@Stateless
public class NickySessionBean implements NickySessionBeanRemote, NickySessionBeanLocal {

    @PersistenceContext(unitName = "ePJNational-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

//tblEvents
//View
    //Code block #1 :
    //Method name: searchLikeEventNameHome
    //Parameter:
    //Purpose: Get a list of event that will be shown on homeside/ admin side
    public nicky.entitybean.TblEvents[] searchLikeEventNameHome(String name) {
        String ejbQL = "SELECT t FROM TblEvents t WHERE t.eventName LIKE :eventName and t.isDeleted = :isDeleted Order by t.eventRegBegin Desc ";
        Query query = em.createQuery(ejbQL);

        query.setParameter("eventName", "%" + name + "%");
        query.setParameter("isDeleted", false);
        List list = query.getResultList();
        TblEvents[] result = new TblEvents[list.size()];
        list.toArray(result);
        return result;
    }

    //Code block #2 :
    //Method name: searchFromToEvent
    //Parameter:
    //Purpose: Get a list of event that will be shown on homeside/admin side
    public nicky.entitybean.TblEvents[] searchFromToEvent(String from, String to) {
        try {
            String ejbQL = "SELECT t FROM TblEvents t WHERE t.eventDate >= :from and t.eventDate <= :to and t.isDeleted = :isDeleted Order by t.eventRegBegin Desc";
            Query query = em.createQuery(ejbQL);
            String[] fromArr = from.split("/");
            String[] toArr = to.split("/");// mm/dd/yyyy

            Calendar calFrom = Calendar.getInstance();
            Calendar calTo = Calendar.getInstance();
//Thang -1
            calFrom.set(Integer.parseInt(fromArr[2]), Integer.parseInt(fromArr[0]) - 1, Integer.parseInt(fromArr[1]), 0, 0, 0);
            calTo.set(Integer.parseInt(toArr[2]), Integer.parseInt(toArr[0]) - 1, Integer.parseInt(toArr[1]), 0, 0, 0);
            query.setParameter("from", calFrom.getTime());
            query.setParameter("to", calTo.getTime());
            query.setParameter("isDeleted", Boolean.FALSE);
            List list = query.getResultList();
            TblEvents[] result = new TblEvents[list.size()];
            list.toArray(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //Code block #4 :
    //Method name: getRecentEvent
    //Parameter:
    //Purpose: Get a list of event that happened which will be shown on homeside
    public nicky.entitybean.TblEvents[] getRecentEvent() {
        String ejbQL = "SELECT t FROM TblEvents t WHERE t.eventStt = :eventStt and t.isDeleted = :isDeleted ORDER BY t.eventRegBegin,t.eventDate DESC LIMIT 5";
        Query query = em.createQuery(ejbQL);

        query.setParameter("eventStt", 1);
        query.setParameter("isDeleted", false);
        List list = query.getResultList();
        TblEvents[] result = new TblEvents[list.size()];
        list.toArray(result);
        return result;
    }

    //Code block #5 :
    //Method name: getEventDetail
    //Parameter:
    //Purpose: Get details of an Event
    public TblEvents getEventDetail(int id) {
        TblEvents event = em.find(TblEvents.class, id);
        return event;
    }
    //Code block #5 :
    //Method name: getEventbyUser
    //Parameter:
    //Purpose: Get details of an Event

    public nicky.entitybean.TblEvents[] getEventbyUser(String username) {
        try {
            TblUsers user = getUserDetails(username);
            TblEnrolls[] enrolls = getUserEnrolls(username);
            ArrayList<TblEvents> eventsTemp = new ArrayList<TblEvents>();
            for (int i = 0; i < enrolls.length; i++) {
                if (!enrolls[i].getIsDeleted()) {
                    eventsTemp.add(enrolls[i].getTblEvents());
                }
            }
            TblEvents[] events = new TblEvents[eventsTemp.size()];
            eventsTemp.toArray(events);
            return events;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
//Insert
    //Code block #7 :
    //Method name: insertEvent
    //Parameter:
    //Purpose: Insert an event into database

    public boolean insertEvent(String name, String host, int min, int max, float price, Date date, String des, String img) {
        boolean chkStt = false;
        try {
            Calendar cal = Calendar.getInstance();

            TblEvents event = new TblEvents();
            event.setEventDate(date);
            event.setEventDes(des);
            event.setEventHost(host);
            event.setEventImg(img);
            event.setEventMin(min);
            event.setEventMax(max);
            event.setEventName(name);
            event.setEventPrice(Double.valueOf(price));
            event.setEventRegBegin(cal.getTime());
            event.setEventStt(0);
            event.setIsDeleted(false);
            event.setTblUsers(em.find(TblUsers.class, "Default event"));

            persist(event);
            chkStt = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chkStt;
    }

    //Code block #8 :[Optional]
    //Method name: insertTriggerUpload
    //Parameter:
    //Purpose: Update an event info
// Update (+ Delete)
    //Code block #9 :[Optional]
    //Method name: chkEvent
    //Parameter:
    //Purpose: Check if Event is valid
    //Code block #10 :
    //Method name: chkDateEvent
    //Parameter:
    //Purpose: Check if Event Date is valid
    public boolean chkDateEvent(Date date) {
        try {
            Calendar cal = Calendar.getInstance();
            Calendar calNew = Calendar.getInstance();
            calNew.setTime(date);

            if (calNew.getTime().before(cal.getTime())) {
                return false;
            }
        } catch (Exception e) {
        }
        return true;
    }

    //Code block #11 :
    //Method name: updateEvent
    //Parameter:
    //Purpose: Update an event info
    public int updateEvent(int id, String name, String host, int min, int max, float price, Date date, String des, String img) {
        try {
            TblEvents event = em.find(TblEvents.class, id);
            if ((event.getEventPrice() != price) && event.getTblEnrollsCollection().isEmpty()) {
                event.setEventDate(date);
                event.setEventDes(des);
                event.setEventHost(host);
                event.setEventImg(img);
                event.setEventMin(min);
                event.setEventMax(max);
                event.setEventName(name);
                event.setEventPrice(Double.valueOf(price));
                persist(event);
            } else if ((event.getEventPrice() != price) && event.getTblEnrollsCollection().size() > 0) {
                return -1;
            } else if (event.getEventPrice() == price) {
                event.setEventDate(date);
                event.setEventDes(des);
                event.setEventHost(host);
                event.setEventImg(img);
                event.setEventMin(min);
                event.setEventMax(max);
                event.setEventName(name);
                event.setEventPrice(Double.valueOf(price));
                persist(event);
            }
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    //tblContent
//View
    public TblContents getContentUser(int eventID, String username) {
        TblContents[] contents = getEventContent(eventID);
        for (int i = 0; i < contents.length; i++) {
            if (contents[i].getSender().equals(username)) {
                return contents[i];
            }
        }
        return null;
    }

    //Code block #12 :
    //Method name: getEventContent
    //Parameter:
    //Purpose: Get a list of content that had been uploaded
    public nicky.entitybean.TblContents[] getEventContent(int eventID) {
        TblEvents event = em.find(TblEvents.class, eventID);
        Collection listContents = event.getTblContentsCollection();
        TblContents[] result = new TblContents[listContents.size()];
        listContents.toArray(result);
        return result;
    }
    //Code block #12 :
    //Method name: getEventContentDetails
    //Parameter:
    //Purpose: Get a specific content details

    public TblContents getEventContentDetails(int id) {
        return em.find(TblContents.class, id);
    }

//Insert
    //Code block #13 :
    //Method name: insertContent
    //Parameter:
    //Purpose: Insert an content into database and upload it to server
    public boolean insertContent(int eventID, String sender, String url) {
        try {
            TblEvents event = em.find(TblEvents.class, eventID);
            TblContents[] contents = getEventContent(eventID);
            TblContents content = new TblContents();
            for (int i = 0; i < contents.length; i++) {
                if (contents[i].getSender().equals(sender)) {
                    content = contents[i];
                }
            }
            content.setContentURL(url);
            content.setSender(sender);
            content.setIsDeleted(false);
            content.setIsSelected(0);
            content.setTblEvents(event);
            event.getTblContentsCollection().add(content);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

// Update (+ Delete)
    //Code block #14 :
    //Method name: updateContent
    //Parameter:
    //Purpose: Update content info
    public boolean updateContent(int contentID, int isSelected) {
        try {
            TblContents contents = getEventContentDetails(contentID);
            contents.setIsSelected(isSelected);
            persist(contents);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateContentDeselected(int eventID) {
        try {
            TblContents[] contents = getEventContent(eventID);
            for (int i = 0; i < contents.length; i++) {
                if (contents[i].getIsSelected() == 1) {
                    contents[i].setIsSelected(0);
                    persist(contents[i]);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    //Code block #15 :
    //Method name: updateContentStt
    //Parameter:
    //Purpose: Update content status(Delete Content Record)
    public boolean updateContentStt(int contentID) {
        try {
            TblContents contents = getEventContentDetails(contentID);
            contents.setIsDeleted(true);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

//tblEnrolls
//View
    //Code block #15 :
    //Method name: getEnrollByUserEvent
    //Parameter:
    //Purpose: Get an Enrollments that made by specific user for specific event
    public nicky.entitybean.TblEnrolls[] getEnrollByLikeUserEvent(int eventID, String username) {
        try {
            TblEnrolls[] listEnrolls = getUserEnrolls(username);
            ArrayList<TblEnrolls> resultTmp = new ArrayList<TblEnrolls>();
            for (int i = 0; i < listEnrolls.length; i++) {
                if (listEnrolls[i].getTblEvents().getEventID() == eventID) {
                    resultTmp.add(listEnrolls[i]);
                }
            }
            TblEnrolls[] result = new TblEnrolls[resultTmp.size()];
            resultTmp.toArray(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public TblEnrolls getEnrollByUserEvent(int eventID, String username) {
        try {
            TblEnrolls[] listEnrolls = getUserEnrolls(username);
            for (int i = 0; i < listEnrolls.length; i++) {
                if (listEnrolls[i].getTblEvents().getEventID() == eventID) {
                    return listEnrolls[i];
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //Code block #15 :
    //Method name: getUserEnrolls
    //Parameter:
    //Purpose: Get a list of Enrollments that made by specific user

    public nicky.entitybean.TblEnrolls[] getUserEnrolls(String username) {
        TblUsers user = em.find(TblUsers.class, username);
        Collection listEnroll = user.getTblEnrollsCollection();
        TblEnrolls[] result = new TblEnrolls[listEnroll.size()];
        listEnroll.toArray(result);
        return result;
    }

    //Code block #16 :
    //Method name: getEventEnroll
    //Parameter:
    //Purpose: Get a list of Enrollments that belongs to an event
    public nicky.entitybean.TblEnrolls[] getEventEnroll(int eventID) {
        TblEvents event = em.find(TblEvents.class, eventID);
        Collection listEnroll = event.getTblEnrollsCollection();
        TblEnrolls[] result = new TblEnrolls[listEnroll.size()];
        listEnroll.toArray(result);
        return result;
    }

    //Code block #17 :
    //Method name: getEventTotalEnroll
    //Parameter:
    //Purpose: Get a total number of Enrollments that belongs to an event
    public int getEventTotalEnroll(int eventID) {
        TblEvents event = em.find(TblEvents.class, eventID);
        Collection listEnroll = event.getTblEnrollsCollection();
        return listEnroll.size();
    }
    //Code block #17 :
    //Method name: getEnrollDetails
    //Parameter:
    //Purpose: Get details of an enroll

    public TblEnrolls getEnrollDetails(int enrollID) {
        return em.find(TblEnrolls.class, enrollID);
    }

//Insert
    //Code block #18 :
    //Method name: chkinsertEnroll
    //Parameter:
    //Purpose: Check if this user has enrolled this event before.
    public boolean chkinsertEnroll(String username, int eventID) {
        boolean isValid = true;
        TblUsers user = em.find(TblUsers.class, username);
        Collection listEnroll = user.getTblEnrollsCollection();
        TblEnrolls[] enrolls = new TblEnrolls[listEnroll.size()];
        for (int i = 0; i < listEnroll.size(); i++) {
            if (enrolls[i].getTblEvents().getEventID() == eventID) {
                isValid = false;
            }
        }
        return isValid;
    }

    //Code block #18 :
    //Method name: insertEnroll
    //Parameter:
    //Purpose: Insert an enrollment into database
    public int insertEnroll(String username, int eventID) {
        try {
            int userType = getUserDetails(username).getUserType();
            TblEnrolls enroll = new TblEnrolls();
            TblEvents event = getEventDetail(eventID);
            TblUsers user = getUserDetails(username);
            Calendar cal = Calendar.getInstance();
            if (getEventTotalEnroll(eventID) + 1 <= event.getEventMax()) {
                if (userType == 2) {
                    enroll.setEnrollDate(cal.getTime());
                    enroll.setEnrollFee(Double.valueOf(0));
                    enroll.setEnrollStt(1);
                    enroll.setIsDeleted(false);
                    enroll.setTblEvents(event);
                    enroll.setTblUsers(user);

                    event.getTblEnrollsCollection().add(enroll);
                    user.getTblEnrollsCollection().add(enroll);
                    return 1;
                } else if (userType == 3) {
                    enroll.setEnrollDate(cal.getTime());
                    enroll.setEnrollFee(event.getEventPrice());
                    enroll.setEnrollStt(0);
                    enroll.setIsDeleted(false);
                    enroll.setTblEvents(event);
                    enroll.setTblUsers(user);

                    event.getTblEnrollsCollection().add(enroll);
                    user.getTblEnrollsCollection().add(enroll);
                    return 1;
                }
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

// Update (+ Delete)
    //Code block #19 :
    //Method name: updateEnrollPaymentAdmin
    //Parameter:
    //Purpose: Update Enrollment on Admin Demands
    public int updateEnrollPaymentAdmin(int enrollID, int stt) {
        try {
            Calendar cal = Calendar.getInstance();
            TblEnrolls enroll = em.find(TblEnrolls.class, enrollID);
            enroll.setEnrollStt(stt);
            if (stt == 1) {
                enroll.setPaymentDate(cal.getTime());
            } else {
                enroll.setPaymentDate(null);
            }
            persist(enroll);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    //Code block #20 :
    //Method name: updateEnrollWinner
    //Parameter:
    //Purpose: Determine which user is an event winner

    public int updateEnrollScore(int enrollID, int eventID, int score) {
        try {
            int maxScore = getTopScoreEvent(eventID);
            TblEnrolls enroll = getEnrollDetails(enrollID);
            TblEvents event = enroll.getTblEvents();
            TblUsers user = enroll.getTblUsers();
            TblEnrolls[] enrolls = getEventEnroll(eventID);
            TblEnrolls crrWinner = new TblEnrolls();
            for (int i = 0; i < enrolls.length; i++) {
                if (enrolls[i].getEventScore() != null && enrolls[i].getEventScore() == maxScore) {
                    crrWinner = enrolls[i];
                }
            }
            if (score == maxScore) {
                if (crrWinner.getEnrollID() != null) {
                    crrWinner.setEventScore(score - 1);
                    persist(crrWinner);
                }
                //update Enroll & User
                enroll.setEventScore(score);
                persist(enroll);
                user.setUserScore(user.getUserScore() + score);
                persist(user);
                //update Winner
                updateEnrollWinner(enrollID);
                return 1;
            } else if (score > maxScore) {
                //update Enroll & User
                enroll.setEventScore(score);
                persist(enroll);
                user.setUserScore(user.getUserScore() + score);
                persist(user);
                //update Winner
                updateEnrollWinner(enrollID);
                return 1;
            } else {
                //update Enroll & User
                enroll.setEventScore(score);
                persist(enroll);
                user.setUserScore(user.getUserScore() + score);
                persist(user);
                return 1;
            }

//            } else if (maxScore == 100) {
//                if (score < maxScore) {
//                    enroll.setEventScore(score);
//                    persist(enroll);
//                    user.setUserScore(user.getUserScore() + score);
//                    persist(user);
//                    return 1;
//                } else {
//                    return -1;
//                }
//            } else {
//                return -2;
//            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //Code block #21 :
    //Method name: updateEnrollStt
    //Parameter:
    //Purpose: Update Enrollment Status(Delete an enrollment)
    public int updateEnrollStt(int enrollID) {
        TblEnrolls enrolls = getEnrollDetails(enrollID);
        enrolls.setIsDeleted(true);
        return 1;
    }
    //tblUsers
//View

    //Code block #22 :
    //Method name: login
    //Parameter:
    //Purpose: Log user into system
    public int login(String username, String password) {
        String ejbQL = "SELECT t FROM TblUsers t WHERE t.username = :username and t.password = :password and t.isDeleted = :isDeleted";
        Query query = em.createQuery(ejbQL);

        query.setParameter("username", username);
        query.setParameter("password", password);
        query.setParameter("isDeleted", false);
        List list = query.getResultList();
        if (list.size() > 0) {
            TblUsers user = em.find(TblUsers.class, username);
            return user.getUserType();
        }
        return 0;
    }
    //Code block #23 :
    //Method name: getUserDetails
    //Parameter:
    //Purpose: Get all details of a user

    public TblUsers getUserDetails(String username) {
        if (username == null) {
            return em.find(TblUsers.class, "Brroke1404@yahoo.com");
        }
        return em.find(TblUsers.class, username);
    }

    //Code block #25 :
    //Method name: searchUserLikeUsername
    //Parameter:
    //Purpose: Get a list of user whose username is similar with the given keyword
    public nicky.entitybean.TblUsers[] searchUserLikeUsername(String username) {
        String ejbQL = "SELECT t FROM TblUsers t WHERE t.username LIKE :username and t.isDeleted = :isDeleted";
        Query query = em.createQuery(ejbQL);

        query.setParameter("username", "%" + username + "%");
        query.setParameter("isDeleted", false);
        List list = query.getResultList();
        TblUsers[] result = new TblUsers[list.size()];
        list.toArray(result);
        return result;
    }

//Insert
    //Code block #26 :
    //Method name: insertForeignUser
    //Parameter:
    //Purpose: Insert a user into database with usertype = 3 (user not belongs to KSC University)
    public int insertForeignUser(String username, String password, String name, String DOB, String Addr, String Phone, boolean Gender, boolean isSubscribe) {
        try {
            if (getUserDetails(username) == null) {
                TblUsers user = new TblUsers(username, password, 3, false);
                user.setFullname(name);
                user.setDob(DOB);
                user.setUserAddress(Addr);
                user.setUserPhone(Phone);
                user.setUserImg("def");
                user.setUserScore(0);
                user.setGender(Gender);
                user.setIsSubscribed(isSubscribe);
                persist(user);
                return 1;
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //Code block #27 :
    //Method name: insertUser
    //Parameter:
    //Purpose: Insert a user into database with usertype = 2 (user belongs to KSC University)
    public int insertUser(String username, String password, String name, boolean gender, String address, String phone, String DOB, String img) {
        try {
            if (getUserDetails(username) == null) {
                TblUsers user = new TblUsers(username, password, 2, false);
                user.setFullname(name);
                user.setDob(DOB);
                user.setUserAddress(address);
                user.setUserPhone(phone);
                user.setUserImg(img);
                user.setUserScore(0);
                user.setGender(gender);
                user.setIsSubscribed(true);
                persist(user);
                return 1;
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

// Update (+ Delete)
    //Code block #28 :
    //Method name: updateUserProfile
    //Parameter:
    //Purpose: Update User Profile
    public int updateUserProfile(String username, String name, String DOB, String Phone, String Addr, boolean Gender) {
        try {
            TblUsers user = getUserDetails(username);

            if (user != null) {
                user.setFullname(name);
                user.setDob(DOB);
                user.setUserPhone(Phone);
                user.setUserAddress(Addr);
                user.setGender(Gender);
                persist(user);
                return 1;
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //Code block #29 :
    //Method name: updateUserImg
    //Parameter:
    //Purpose: Update User Image
    public int updateUserImg(String username, String img) {
        try {
            TblUsers user = getUserDetails(username);
            if (user != null) {
                user.setUserImg(img);
                persist(user);
                return 1;
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //Code block #30 :
    //Method name: updateUserScore
    //Parameter:
    //Purpose: Update user total score when an specific enrollment score is determined
    //Code block #31 :
    //Method name: updateUserPass
    //Parameter:
    //Purpose: Update user password
    public int updateUserPass(String username, String password) {
        try {
            TblUsers user = getUserDetails(username);
            if (user != null) {
                user.setPassword(password);
                persist(user);
                return 1;
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //Code block #32 :
    //Method name: updateUserSubscribe
    //Parameter:
    //Purpose: Update Subscribe Status of a specific user
    public int updateUserSubscribe(String username) {
        try {
            TblUsers user = getUserDetails(username);
            if (user != null) {
                user.setIsSubscribed(!user.getIsSubscribed());
                persist(user);
                return 1;
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //Code block #33 :
    //Method name: updateUserStt
    //Parameter:
    //Purpose: Update User Status(Delete a user)
    public int updateUserStt(String username) {
        try {
            TblUsers user = getUserDetails(username);
            if (user != null) {
                user.setIsDeleted(true);
                persist(user);
                return 1;
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

//tblComments
//View
    //Code block #34 :
    //Method name: getCommentEvent
    //Parameter:
    //Purpose: Get all comments of an event
    public nicky.entitybean.TblComments[] getCommentEvent(int eventID) {
        try {
            TblEvents event = getEventDetail(eventID);
            Collection listComments = event.getTblCommentsCollection();
            TblComments[] result = new TblComments[listComments.size()];
            listComments.toArray(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //Code block #34 :
    //Method name: getCommentsByLikeUser
    //Parameter:
    //Purpose: Get all comments of an event by a user

    public nicky.entitybean.TblComments[] getCommentsByLikeUser(int eventID, String username) {
        try {

            TblComments[] arrCmts = getCommentEvent(eventID);
            ArrayList<TblComments> resultTemp = new ArrayList<TblComments>();
            for (int i = 0; i < arrCmts.length; i++) {
                if (arrCmts[i].getTblUsers().getUsername().matches(".*" + username + "*.")) {
                    resultTemp.add(arrCmts[i]);
                }
            }
            TblComments[] finalRS = new TblComments[resultTemp.size()];
            resultTemp.toArray(finalRS);
            return finalRS;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //Code block #35 :
    //Method name: getCommentsDetails
    //Parameter:
    //Purpose: Get details of comment

    public TblComments getCommentsDetails(int cmtID) {
        return em.find(TblComments.class, cmtID);
    }

//Insert
    //Code block #36 :
    //Method name: insertComment
    //Parameter:
    //Purpose: Insert a comment into database
    public int insertComment(int eventID, String Date, String username, String comment) {
        try {
            TblUsers user = getUserDetails(username);
            TblEvents event = getEventDetail(eventID);

            TblComments comments = new TblComments();
            comments.setTblEvents(event);
            comments.setTblUsers(user);
            comments.setCmtDate(Date);
            comments.setEventCom(comment);
            comments.setIsDeleted(false);
            persist(comments);

            event.getTblCommentsCollection().add(comments);
            user.getTblCommentsCollection().add(comments);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

// Update (+ Delete)
    //Code block #37 :
    //Method name: updateCommentStt
    //Parameter:
    //Purpose: Update Comment Status(Delete a Comment)
    public int updateCommentStt(int cmtID) {
        try {
            TblComments comment = getCommentsDetails(cmtID);
            if (comment != null) {
                comment.setIsDeleted(true);
                persist(comment);
                return 1;
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

//tblFAQ
//View
    //Code block #38 :
    //Method name: getFAQ
    //Parameter:
    //Purpose: Get FAQ Details
    public TblFAQ getFAQ(int faqID) {
        return em.find(TblFAQ.class, faqID);
    }
    //Code block #39 :
    //Method name: getFAQList
    //Parameter:
    //Purpose: Get a list of all FAQ

    public nicky.entitybean.TblFAQ[] getFAQList() {
        try {
            String ejbQL = "SELECT t FROM TblFAQ t WHERE t.isDeleted = :isDeleted";
            Query query = em.createQuery(ejbQL);

            query.setParameter("isDeleted", false);
            List list = query.getResultList();
            TblFAQ[] result = new TblFAQ[list.size()];
            list.toArray(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public nicky.entitybean.TblFAQ[] getFAQListOK() {
        try {
            String ejbQL = "SELECT t FROM TblFAQ t WHERE t.isDeleted = :isDeleted";
            Query query = em.createQuery(ejbQL);

            query.setParameter("isDeleted", false);
            List list = query.getResultList();
            TblFAQ[] result = new TblFAQ[list.size()];
            list.toArray(result);
            ArrayList<TblFAQ> RS = new ArrayList<TblFAQ>();
            for (int i = 0; i < result.length; i++) {
                if (result[i].getFaqAns().trim().length() > 0) {
                    RS.add(result[i]);
                }
            }
            result = new TblFAQ[RS.size()];
            RS.toArray(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//Insert
    //Code block #40 :
    //Method name: insertFAQ
    //Parameter:
    //Purpose: Insert a FAQ into database
    public int insertFAQ(String question, String ans) {
        try {
            TblFAQ faq = new TblFAQ();
            faq.setFaqQuestion(question);
            faq.setFaqAns(ans);
            faq.setIsDeleted(false);
            persist(faq);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

// Update (+ Delete)
    //Code block #41 :
    //Method name: updateFAQ
    //Parameter:
    //Purpose: Update FAQ Info
    public int updateFAQ(int faqID, String ques, String ans) {
        try {
            TblFAQ faq = getFAQ(faqID);
            if (faq != null) {
                faq.setFaqQuestion(ques);
                faq.setFaqAns(ans);
                persist(faq);
                return 1;
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //Code block #42 :
    //Method name: updateFAQStt
    //Parameter:
    //Purpose: Update FAQ Status(Delete a FAQ)
    public int updateFAQStt(int faqID) {
        try {
            TblFAQ faq = getFAQ(faqID);
            if (faq != null) {
                faq.setIsDeleted(true);
                persist(faq);
                return 1;
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

//tblFeedback
//View
    //Code block #43 :
    //Method name: getFeedBackDetails
    //Parameter:
    //Purpose: Get all Feedback details
    public TblFeedback getFeedBackDetails(int fbID) {
        return em.find(TblFeedback.class, fbID);
    }
    //Code block #44 :
    //Method name: getFBList
    //Parameter:
    //Purpose: Get a list of all Feedback

    public nicky.entitybean.TblFeedback[] getFBList() {
        try {
            String ejbQL = "SELECT t FROM TblFeedback t WHERE t.isDeleted = :isDeleted Order By t.fbID Desc";
            Query query = em.createQuery(ejbQL);

            query.setParameter("isDeleted", false);
            List list = query.getResultList();
            TblFeedback[] result = new TblFeedback[list.size()];
            list.toArray(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //Code block #44 :
    //Method name: getFBListUser
    //Parameter:
    //Purpose: Get a list of all Feedback of User

    public nicky.entitybean.TblFeedback[] getFBListUser(String username) {
        try {
            String ejbQL = "SELECT t FROM TblFeedback t WHERE t.fbFrom LIKE :fbFrom and t.isDeleted = :isDeleted Order By t.fbDate Desc";
            Query query = em.createQuery(ejbQL);

            query.setParameter("fbFrom", "%" + username + "%");
            query.setParameter("isDeleted", false);
            List list = query.getResultList();
            TblFeedback[] result = new TblFeedback[list.size()];
            list.toArray(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //Code block #44 :
    //Method name: getFBListUser
    //Parameter:
    //Purpose: Get a list of all Feedback of User

    public nicky.entitybean.TblFeedback[] getFBListUserOK(String username) {
        try {
            String ejbQL = "SELECT t FROM TblFeedback t WHERE t.fbFrom = :fbFrom and t.isDeleted = :isDeleted Order by t.fbDate Desc";
            Query query = em.createQuery(ejbQL);

            query.setParameter("fbFrom", username);
            query.setParameter("isDeleted", false);
            List list = query.getResultList();
            TblFeedback[] result = new TblFeedback[list.size()];
            list.toArray(result);
            ArrayList<TblFeedback> RS = new ArrayList<TblFeedback>();
            for (int i = 0; i < result.length; i++) {
                if (result[i].getFbAns() != null && result[i].getFbAns().trim().length() > 0) {
                    RS.add(result[i]);
                }
            }
            result = new TblFeedback[RS.size()];
            RS.toArray(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
//Insert
    //Code block #41 :
    //Method name: insertFeedback
    //Parameter:
    //Purpose: Insert a Feedback into database

    public int insertFeedback(String username, String question) {
        try {
            Calendar cal = Calendar.getInstance();

            String date = cal.get(Calendar.MONTH) + "/" + (cal.get(Calendar.DAY_OF_MONTH) + 1) + "/" + cal.get(Calendar.YEAR);

            TblUsers user = getUserDetails(username);
            if (user != null) {
                TblFeedback fb = new TblFeedback();
                fb.setFbDate(date);
                fb.setFbFrom(username);
                fb.setFbQuestion(question);
                fb.setIsDeleted(false);
                persist(fb);
                return 1;
            } else {
                return -1;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

// Update (+ Delete)
    //Code block #45 :
    //Method name: updateFB
    //Parameter:
    //Purpose: Update Feedback Info
    public int updateFB(int fbID, String fbAns) {
        try {
            TblFeedback fb = getFeedBackDetails(fbID);
            if (fb != null) {
                fb.setFbAns(fbAns);
                persist(fb);
                return 1;
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    //Code block #46 :
    //Method name: updateFBStt
    //Parameter:
    //Purpose: Update FB Status(Delete a FB)

    public int updateFBStt(int fbID) {
        try {
            TblFeedback fb = getFeedBackDetails(fbID);
            if (fb != null) {
                fb.setIsDeleted(true);
                persist(fb);
                return 1;
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //REPORT
    //Code block #46 :
    //Method name: getTopActive
    //Parameter:
    //Purpose: getTopActiveUsers
    private void swapUser(TblUsers a, TblUsers b) {
        TblUsers temp = a;
        a = b;
        b = temp;
    }

    private void InsertionSortUser(TblUsers[] arrUser, int n) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (arrUser[j].getTblEnrollsCollection().size() > arrUser[j - 1].getTblEnrollsCollection().size()) {
                    swapUser(arrUser[j], arrUser[j - 1]);
                }
            }
        }
    }

    public nicky.entitybean.TblUsers[] getTopActive() {
        try {
            String ejbQL = "SELECT t FROM TblUsers t WHERE t.isDeleted = :isDeleted";
            Query query = em.createQuery(ejbQL);

            query.setParameter("isDeleted", false);
            List list = query.getResultList();
            TblUsers[] arrUser = new TblUsers[list.size()];
            list.toArray(arrUser);
            InsertionSortUser(arrUser, list.size());
            TblUsers[] arrUserSorted = new TblUsers[10];
            for (int i = 0; i < 10; i++) {
                arrUserSorted[i] = arrUser[i];
            }
            return arrUserSorted;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void swapEvent(TblEvents a, TblEvents b) {
        TblEvents temp = a;
        a = b;
        b = temp;
    }

    private void InsertionSortEvent(TblEvents[] arrEvent, int n) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (arrEvent[j].getTblEnrollsCollection().size() > arrEvent[j - 1].getTblEnrollsCollection().size()) {
                    swapEvent(arrEvent[j], arrEvent[j - 1]);
                }
            }
        }
    }

    public nicky.entitybean.TblEvents[] getPopularEvent() {
        try {
            String ejbQL = "SELECT t FROM TblEvents t WHERE t.isDeleted = :isDeleted";
            Query query = em.createQuery(ejbQL);

            query.setParameter("isDeleted", false);
            List list = query.getResultList();
            TblEvents[] arrEvents = new TblEvents[list.size()];
            list.toArray(arrEvents);
            InsertionSortEvent(arrEvents, list.size());
            TblEvents[] arrEventSorted = new TblEvents[10];
            for (int i = 0; i < 10; i++) {
                arrEventSorted[i] = arrEvents[i];
            }
            return arrEventSorted;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public nicky.entitybean.TblEvents[] getLeastEvent() {
        try {
            String ejbQL = "SELECT t FROM TblEvents t WHERE t.isDeleted = :isDeleted";
            Query query = em.createQuery(ejbQL);

            query.setParameter("isDeleted", false);
            List list = query.getResultList();
            TblEvents[] arrEvents = new TblEvents[list.size()];
            list.toArray(arrEvents);
            InsertionSortEvent(arrEvents, list.size());
            TblEvents[] arrEventSorted = new TblEvents[10];
            int j = 0;
            for (int i = list.size() - 1; i > list.size() - 11; i--) {
                arrEventSorted[i] = arrEvents[j];
                j++;
            }
            return arrEventSorted;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void InsertionSortWinner(TblUsers[] arrUser, int n) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (arrUser[j].getTblEventsCollection().size() > arrUser[j - 1].getTblEventsCollection().size()) {
                    swapUser(arrUser[j], arrUser[j - 1]);
                }
            }
        }
    }

    public nicky.entitybean.TblUsers[] getTopWinner() {
        try {
            String ejbQL = "SELECT t FROM TblUsers t WHERE t.isDeleted = :isDeleted";
            Query query = em.createQuery(ejbQL);

            query.setParameter("isDeleted", false);
            List list = query.getResultList();
            TblUsers[] arrUser = new TblUsers[list.size()];
            list.toArray(arrUser);
            InsertionSortWinner(arrUser, list.size());
            TblUsers[] arrUserSorted = new TblUsers[10];
            for (int i = 0; i < 10; i++) {
                arrUserSorted[i] = arrUser[i];
            }
            return arrUserSorted;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean chkUserEnroll(String username, int eventID) {
        try {
            TblEvents event = getEventDetail(eventID);
            TblUsers users = getUserDetails(username);
            if (users == null) {
                return false;
            }
            Collection listEnrolls = users.getTblEnrollsCollection();
            if (listEnrolls.size() > 0) {
                TblEnrolls[] arrEnroll = new TblEnrolls[listEnrolls.size()];
                listEnrolls.toArray(arrEnroll);
                for (int i = 0; i < arrEnroll.length; i++) {
                    TblEnrolls enroll = arrEnroll[i];
                    TblEvents events = arrEnroll[i].getTblEvents();
                    if (events.equals(event) && !enroll.getIsDeleted()) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getTotalComment(int eventID) {
        try {
            TblEvents event = getEventDetail(eventID);
            return event.getTblCommentsCollection().size();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public nicky.entitybean.TblEvents[] getEventAuto() {
        String ejbQL = "SELECT t FROM TblEvents t WHERE t.isDeleted = :isDeleted";
        Query query = em.createQuery(ejbQL);

        query.setParameter("isDeleted", false);
        List list = query.getResultList();
        TblEvents[] arrUser = new TblEvents[list.size()];
        list.toArray(arrUser);
        return arrUser;
    }

    public void AutoEvent() {
        TblEvents[] events = getEventAuto();
        Calendar calNow = Calendar.getInstance();
        Calendar calEvent = Calendar.getInstance();
        for (int i = 0; i < events.length; i++) {
            calEvent.setTime(events[i].getEventDate());
            if (calEvent.compareTo(calNow) < 0) {
                TblEvents event = getEventDetail(events[i].getEventID());
                event.setEventStt(1);
                if (events[i].getTblEnrollsCollection().size() < events[i].getEventMin()) {
                    event.setEventStt(2);
                }
                persist(event);
            }
//            else if (calEvent.compareTo(calNow) > 0) {
//                TblEvents event = getEventDetail(events[i].getEventID());
//                event.setEventStt(0);
//                persist(event);
//            }
        }
    }

    public nicky.entitybean.TblEnrolls[] getEnrollAuto() {
        String ejbQL = "SELECT t FROM TblEnrolls t WHERE t.isDeleted = :isDeleted";
        Query query = em.createQuery(ejbQL);

        query.setParameter("isDeleted", false);
        List list = query.getResultList();
        TblEnrolls[] arrUser = new TblEnrolls[list.size()];
        list.toArray(arrUser);
        return arrUser;
    }

    public void autoEnroll() {
        TblEnrolls[] enrolls = getEnrollAuto();
        Calendar calNow = Calendar.getInstance();
        Calendar calEvent = Calendar.getInstance();

        for (int i = 0; i < enrolls.length; i++) {
            TblEvents event = enrolls[i].getTblEvents();
            calEvent.setTime(event.getEventDate());
            if (calNow.getTime().compareTo(calEvent.getTime()) <= 0) {
                if (enrolls[i].getEnrollStt() == 0) {
                    enrolls[i].setEnrollStt(3);
                    enrolls[i].setIsDeleted(true);

                }
                if (enrolls[i].getEnrollStt() == 1 && event.getTblEnrollsCollection().size() < event.getEventMin()) {
                    enrolls[i].setEnrollStt(2);
                }
                persist(enrolls[i]);
            }
        }

    }

    public int updateEnrollWinner(int enrollID) {
        try {
            TblEnrolls enroll = getEnrollDetails(enrollID);
            TblUsers user = enroll.getTblUsers();
            TblEvents event = enroll.getTblEvents();
            event.setTblUsers(user);
            persist(event);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public nicky.entitybean.TblEvents[] reportLeastEvent(String from, String to) {
        TblEvents[] events = searchFromToEvent(from, to);
        InsertionSortEvent(events, events.length);

        ArrayList<TblEvents> arrEvents = new ArrayList<TblEvents>();
        int b = events.length;
        if (b > 10) {
            b = 10;
        }
        for (int i = 0; i < b; i++) {
            arrEvents.add(events[i]);
        }
        TblEvents[] result = new TblEvents[b];
        arrEvents.toArray(result);
        return result;
    }

    public boolean chkExistedWinner(int eventID) {
        TblEvents event = getEventDetail(eventID);
        boolean haveWinner = false;
        if (event.getTblUsers().getUsername().equals("Default event")) {
            haveWinner = true;
        }
        return haveWinner;
    }

    public nicky.entitybean.TblEvents[] searchFromToMyEvent(String from, String to, String username) {
        try {
            String ejbQL = "SELECT t FROM TblEvents t WHERE t.eventDate >= :from and t.eventDate <= :to and t.isDeleted = :isDeleted Order by t.eventRegBegin Desc";
            Query query = em.createQuery(ejbQL);
            String[] fromArr = from.split("/");
            String[] toArr = to.split("/");// mm/dd/yyyy

            Calendar calFrom = Calendar.getInstance();
            Calendar calTo = Calendar.getInstance();
//Thang -1
            calFrom.set(Integer.parseInt(fromArr[2]), Integer.parseInt(fromArr[0]) - 1, Integer.parseInt(fromArr[1]), 0, 0, 0);
            calTo.set(Integer.parseInt(toArr[2]), Integer.parseInt(toArr[0]) - 1, Integer.parseInt(toArr[1]), 0, 0, 0);
            query.setParameter("from", calFrom.getTime());
            query.setParameter("to", calTo.getTime());
            query.setParameter("isDeleted", Boolean.FALSE);
            List list = query.getResultList();
            TblEvents[] events = new TblEvents[list.size()];
            list.toArray(events);
            ArrayList<TblEvents> arrEvents = new ArrayList<TblEvents>();

            TblEvents[] eventUser = getEventbyUser(username);

            for (int i = 0; i < events.length; i++) {
                for (int j = 0; j < eventUser.length; j++) {
                    if (events[i].equals(eventUser[j])) {
                        arrEvents.add(events[i]);
                    }
                }
            }

            events = new TblEvents[arrEvents.size()];
            arrEvents.toArray(events);
            return events;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public nicky.entitybean.TblEvents[] searchLikeEventNameMyEvent(String eventName, String username) {
        String ejbQL = "SELECT t FROM TblEvents t WHERE t.eventName LIKE :eventName and t.isDeleted = :isDeleted Order by t.eventRegBegin Desc";
        Query query = em.createQuery(ejbQL);

        query.setParameter("eventName", "%" + eventName + "%");
        query.setParameter("isDeleted", false);
        List list = query.getResultList();
        TblEvents[] events = new TblEvents[list.size()];
        list.toArray(events);
        TblEvents[] eventUser = getEventbyUser(username);
        ArrayList<TblEvents> arrEvents = new ArrayList<TblEvents>();

        for (int i = 0; i < events.length; i++) {
            for (int j = 0; j < eventUser.length; j++) {
                if (events[i].equals(eventUser[j])) {
                    arrEvents.add(events[i]);
                }
            }
        }
        events = new TblEvents[arrEvents.size()];
        arrEvents.toArray(events);
        return events;
    }

    public nicky.entitybean.PopEvent[] reportLeastEventFromTo(String from, String to) {
        try {
            String ejbQL = "SELECT p FROM PopEvent p WHERE p.eventDate >= :from and p.eventDate <= :to Order by p.eventCount Asc";
            Query query = em.createQuery(ejbQL);
            String[] fromArr = from.split("/");
            String[] toArr = to.split("/");// mm/dd/yyyy

            Calendar calFrom = Calendar.getInstance();
            Calendar calTo = Calendar.getInstance();
//Thang -1
            calFrom.set(Integer.parseInt(fromArr[2]), Integer.parseInt(fromArr[0]) - 1, Integer.parseInt(fromArr[1]), 0, 0, 0);
            calTo.set(Integer.parseInt(toArr[2]), Integer.parseInt(toArr[0]) - 1, Integer.parseInt(toArr[1]), 0, 0, 0);
            query.setParameter("from", calFrom.getTime());
            query.setParameter("to", calTo.getTime());
            List list = query.getResultList();
            PopEvent[] result = new PopEvent[list.size()];
            list.toArray(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getTopScoreEvent(int eventID) {
        try {
            TblEnrolls[] enrolls = getEventEnroll(eventID);
            int max = 0;
            for (int i = 0; i < enrolls.length; i++) {
                if (enrolls[i].getEventScore() != null && enrolls[i].getEventScore() > max) {
                    max = enrolls[i].getEventScore();
                }
            }
            return max;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;

        }
    }

    public nicky.entitybean.PopEvent[] reportTopEventFromTo(String from, String to) {
        try {
            String ejbQL = "SELECT p FROM PopEvent p WHERE p.eventDate >= :from and p.eventDate <= :to Order by p.eventCount Desc";
            Query query = em.createQuery(ejbQL);
            String[] fromArr = from.split("/");
            String[] toArr = to.split("/");// mm/dd/yyyy

            Calendar calFrom = Calendar.getInstance();
            Calendar calTo = Calendar.getInstance();
//Thang -1
            calFrom.set(Integer.parseInt(fromArr[2]), Integer.parseInt(fromArr[0]) - 1, Integer.parseInt(fromArr[1]), 0, 0, 0);
            calTo.set(Integer.parseInt(toArr[2]), Integer.parseInt(toArr[0]) - 1, Integer.parseInt(toArr[1]), 0, 0, 0);
            query.setParameter("from", calFrom.getTime());
            query.setParameter("to", calTo.getTime());
            List list = query.getResultList();
            PopEvent[] result = new PopEvent[list.size()];
            list.toArray(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateEventStt(int eventID) {
        try {
            TblEvents event = getEventDetail(eventID);
            event.setIsDeleted(true);
            persist(event);
            TblEnrolls[] enrolls = getEventEnroll(eventID);
            for (int i = 0; i < enrolls.length; i++) {
                enrolls[i].setEnrollStt(2);
                persist(enrolls[i]);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public nicky.entitybean.TopActive[] reportTopActiveFromTo(String from, String to) {
        try {
            String ejbQL = "SELECT p FROM TopActive p Where YearEnroll = :from and MonthEnroll = :to Order by CounUser desc";
            Query query = em.createQuery(ejbQL);
//            String[] fromArr = from.split("/");
//            String[] toArr = to.split("/");// mm/dd/yyyy
//
//            Calendar calFrom = Calendar.getInstance();
//            Calendar calTo = Calendar.getInstance();
//Thang -1
//            calFrom.set(Integer.parseInt(fromArr[2]), Integer.parseInt(fromArr[0]) - 1, Integer.parseInt(fromArr[1]), 0, 0, 0);
//            calTo.set(Integer.parseInt(toArr[2]), Integer.parseInt(toArr[0]) - 1, Integer.parseInt(toArr[1]), 0, 0, 0);
//            query.setParameter("from", calFrom.getTime());
//            query.setParameter("to", calTo.getTime());
            query.setParameter("from", Integer.parseInt(from));
            query.setParameter("to", Integer.parseInt(to));
            List list = query.getResultList();            
            TopActive[] result = new TopActive[list.size()];
            list.toArray(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
