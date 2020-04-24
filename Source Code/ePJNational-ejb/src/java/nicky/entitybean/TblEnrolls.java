/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nicky.entitybean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Nicky
 */
@Entity
@Table(name = "tblEnrolls", catalog = "kscUniversity", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "TblEnrolls.findAll", query = "SELECT t FROM TblEnrolls t"),
    @NamedQuery(name = "TblEnrolls.findByEnrollID", query = "SELECT t FROM TblEnrolls t WHERE t.enrollID = :enrollID"),
    @NamedQuery(name = "TblEnrolls.findByEventScore", query = "SELECT t FROM TblEnrolls t WHERE t.eventScore = :eventScore"),
    @NamedQuery(name = "TblEnrolls.findByEnrollStt", query = "SELECT t FROM TblEnrolls t WHERE t.enrollStt = :enrollStt"),
    @NamedQuery(name = "TblEnrolls.findByEnrollDate", query = "SELECT t FROM TblEnrolls t WHERE t.enrollDate = :enrollDate"),
    @NamedQuery(name = "TblEnrolls.findByPaymentDate", query = "SELECT t FROM TblEnrolls t WHERE t.paymentDate = :paymentDate"),
    @NamedQuery(name = "TblEnrolls.findByEnrollFee", query = "SELECT t FROM TblEnrolls t WHERE t.enrollFee = :enrollFee"),
    @NamedQuery(name = "TblEnrolls.findByIsDeleted", query = "SELECT t FROM TblEnrolls t WHERE t.isDeleted = :isDeleted")})
public class TblEnrolls implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Basic(optional = false)
    @Column(name = "enrollID", nullable = false)
    private Integer enrollID;
    @Column(name = "eventScore")
    private Integer eventScore;
    @Column(name = "enrollStt")
    private Integer enrollStt;
    @Column(name = "enrollDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date enrollDate;
    @Column(name = "paymentDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDate;
    @Column(name = "enrollFee", precision = 15)
    private Double enrollFee;
    @Basic(optional = false)
    @Column(name = "isDeleted", nullable = false)
    private boolean isDeleted;
    @JoinColumn(name = "Username", referencedColumnName = "Username", nullable = false)
    @ManyToOne(optional = false)
    private TblUsers tblUsers;
    @JoinColumn(name = "eventID", referencedColumnName = "eventID", nullable = false)
    @ManyToOne(optional = false)
    private TblEvents tblEvents;

    public TblEnrolls() {
    }

    public TblEnrolls(Integer enrollID) {
        this.enrollID = enrollID;
    }

    public TblEnrolls(Integer enrollID, boolean isDeleted) {
        this.enrollID = enrollID;
        this.isDeleted = isDeleted;
    }

    public Integer getEnrollID() {
        return enrollID;
    }

    public void setEnrollID(Integer enrollID) {
        this.enrollID = enrollID;
    }

    public Integer getEventScore() {
        return eventScore;
    }

    public void setEventScore(Integer eventScore) {
        this.eventScore = eventScore;
    }

    public Integer getEnrollStt() {
        return enrollStt;
    }

    public void setEnrollStt(Integer enrollStt) {
        this.enrollStt = enrollStt;
    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Double getEnrollFee() {
        return enrollFee;
    }

    public void setEnrollFee(Double enrollFee) {
        this.enrollFee = enrollFee;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public TblUsers getTblUsers() {
        return tblUsers;
    }

    public void setTblUsers(TblUsers tblUsers) {
        this.tblUsers = tblUsers;
    }

    public TblEvents getTblEvents() {
        return tblEvents;
    }

    public void setTblEvents(TblEvents tblEvents) {
        this.tblEvents = tblEvents;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (enrollID != null ? enrollID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblEnrolls)) {
            return false;
        }
        TblEnrolls other = (TblEnrolls) object;
        if ((this.enrollID == null && other.enrollID != null) || (this.enrollID != null && !this.enrollID.equals(other.enrollID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nicky.entitybean.TblEnrolls[enrollID=" + enrollID + "]";
    }

}
