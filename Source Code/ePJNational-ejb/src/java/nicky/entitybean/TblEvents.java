/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nicky.entitybean;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Nicky
 */
@Entity
@Table(name = "tblEvents", catalog = "kscUniversity", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "TblEvents.findAll", query = "SELECT t FROM TblEvents t"),
    @NamedQuery(name = "TblEvents.findByEventID", query = "SELECT t FROM TblEvents t WHERE t.eventID = :eventID"),
    @NamedQuery(name = "TblEvents.findByEventDate", query = "SELECT t FROM TblEvents t WHERE t.eventDate = :eventDate"),
    @NamedQuery(name = "TblEvents.findByEventName", query = "SELECT t FROM TblEvents t WHERE t.eventName = :eventName"),
    @NamedQuery(name = "TblEvents.findByEventHost", query = "SELECT t FROM TblEvents t WHERE t.eventHost = :eventHost"),
    @NamedQuery(name = "TblEvents.findByEventMin", query = "SELECT t FROM TblEvents t WHERE t.eventMin = :eventMin"),
    @NamedQuery(name = "TblEvents.findByEventMax", query = "SELECT t FROM TblEvents t WHERE t.eventMax = :eventMax"),
    @NamedQuery(name = "TblEvents.findByEventPrice", query = "SELECT t FROM TblEvents t WHERE t.eventPrice = :eventPrice"),
    @NamedQuery(name = "TblEvents.findByEventImg", query = "SELECT t FROM TblEvents t WHERE t.eventImg = :eventImg"),
    @NamedQuery(name = "TblEvents.findByEventRegBegin", query = "SELECT t FROM TblEvents t WHERE t.eventRegBegin = :eventRegBegin"),
    @NamedQuery(name = "TblEvents.findByEventStt", query = "SELECT t FROM TblEvents t WHERE t.eventStt = :eventStt"),
    @NamedQuery(name = "TblEvents.findByIsDeleted", query = "SELECT t FROM TblEvents t WHERE t.isDeleted = :isDeleted")})
public class TblEvents implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Basic(optional = false)
    @Column(name = "eventID", nullable = false)
    private Integer eventID;
    @Basic(optional = false)
    @Column(name = "eventDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventDate;
    @Basic(optional = false)
    @Column(name = "eventName", nullable = false, length = 50)
    private String eventName;
    @Column(name = "eventHost", length = 50)
    private String eventHost;
    @Column(name = "eventMin")
    private Integer eventMin;
    @Column(name = "eventMax")
    private Integer eventMax;
    @Lob
    @Column(name = "eventDes", length = 2147483647)
    private String eventDes;
    @Column(name = "eventPrice", precision = 15)
    private Double eventPrice;
    @Column(name = "eventImg", length = 100)
    private String eventImg;
    @Column(name = "eventRegBegin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventRegBegin;
    @Basic(optional = false)
    @Column(name = "eventStt", nullable = false)
    private int eventStt;
    @Basic(optional = false)
    @Column(name = "isDeleted", nullable = false)
    private boolean isDeleted;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblEvents")
    private Collection<TblContents> tblContentsCollection;
    @JoinColumn(name = "eventWinner", referencedColumnName = "Username")
    @ManyToOne
    private TblUsers tblUsers;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblEvents")
    private Collection<TblEnrolls> tblEnrollsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblEvents")
    private Collection<TblComments> tblCommentsCollection;

    public TblEvents() {
    }

    public TblEvents(Integer eventID) {
        this.eventID = eventID;
    }

    public TblEvents(Integer eventID, Date eventDate, String eventName, int eventStt, boolean isDeleted) {
        this.eventID = eventID;
        this.eventDate = eventDate;
        this.eventName = eventName;
        this.eventStt = eventStt;
        this.isDeleted = isDeleted;
    }

    public Integer getEventID() {
        return eventID;
    }

    public void setEventID(Integer eventID) {
        this.eventID = eventID;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventHost() {
        return eventHost;
    }

    public void setEventHost(String eventHost) {
        this.eventHost = eventHost;
    }

    public Integer getEventMin() {
        return eventMin;
    }

    public void setEventMin(Integer eventMin) {
        this.eventMin = eventMin;
    }

    public Integer getEventMax() {
        return eventMax;
    }

    public void setEventMax(Integer eventMax) {
        this.eventMax = eventMax;
    }

    public String getEventDes() {
        return eventDes;
    }

    public void setEventDes(String eventDes) {
        this.eventDes = eventDes;
    }

    public Double getEventPrice() {
        return eventPrice;
    }

    public void setEventPrice(Double eventPrice) {
        this.eventPrice = eventPrice;
    }

    public String getEventImg() {
        return eventImg;
    }

    public void setEventImg(String eventImg) {
        this.eventImg = eventImg;
    }

    public Date getEventRegBegin() {
        return eventRegBegin;
    }

    public void setEventRegBegin(Date eventRegBegin) {
        this.eventRegBegin = eventRegBegin;
    }

    public int getEventStt() {
        return eventStt;
    }

    public void setEventStt(int eventStt) {
        this.eventStt = eventStt;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Collection<TblContents> getTblContentsCollection() {
        return tblContentsCollection;
    }

    public void setTblContentsCollection(Collection<TblContents> tblContentsCollection) {
        this.tblContentsCollection = tblContentsCollection;
    }

    public TblUsers getTblUsers() {
        return tblUsers;
    }

    public void setTblUsers(TblUsers tblUsers) {
        this.tblUsers = tblUsers;
    }

    public Collection<TblEnrolls> getTblEnrollsCollection() {
        return tblEnrollsCollection;
    }

    public void setTblEnrollsCollection(Collection<TblEnrolls> tblEnrollsCollection) {
        this.tblEnrollsCollection = tblEnrollsCollection;
    }

    public Collection<TblComments> getTblCommentsCollection() {
        return tblCommentsCollection;
    }

    public void setTblCommentsCollection(Collection<TblComments> tblCommentsCollection) {
        this.tblCommentsCollection = tblCommentsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eventID != null ? eventID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblEvents)) {
            return false;
        }
        TblEvents other = (TblEvents) object;
        if ((this.eventID == null && other.eventID != null) || (this.eventID != null && !this.eventID.equals(other.eventID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nicky.entitybean.TblEvents[eventID=" + eventID + "]";
    }

}
