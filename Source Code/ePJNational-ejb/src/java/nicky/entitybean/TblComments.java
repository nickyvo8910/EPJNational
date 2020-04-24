/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nicky.entitybean;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Nicky
 */
@Entity
@Table(name = "tblComments", catalog = "kscUniversity", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "TblComments.findAll", query = "SELECT t FROM TblComments t"),
    @NamedQuery(name = "TblComments.findByCmtID", query = "SELECT t FROM TblComments t WHERE t.cmtID = :cmtID"),
    @NamedQuery(name = "TblComments.findByCmtDate", query = "SELECT t FROM TblComments t WHERE t.cmtDate = :cmtDate"),
    @NamedQuery(name = "TblComments.findByIsDeleted", query = "SELECT t FROM TblComments t WHERE t.isDeleted = :isDeleted")})
public class TblComments implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Basic(optional = false)
    @Column(name = "cmtID", nullable = false)
    private Integer cmtID;
    @Basic(optional = false)
    @Column(name = "cmtDate", nullable = false, length = 50)
    private String cmtDate;
    @Basic(optional = false)
    @Lob
    @Column(name = "eventCom", nullable = false, length = 2147483647)
    private String eventCom;
    @Basic(optional = false)
    @Column(name = "isDeleted", nullable = false)
    private boolean isDeleted;
    @JoinColumn(name = "Username", referencedColumnName = "Username", nullable = false)
    @ManyToOne(optional = false)
    private TblUsers tblUsers;
    @JoinColumn(name = "eventID", referencedColumnName = "eventID", nullable = false)
    @ManyToOne(optional = false)
    private TblEvents tblEvents;

    public TblComments() {
    }

    public TblComments(Integer cmtID) {
        this.cmtID = cmtID;
    }

    public TblComments(Integer cmtID, String cmtDate, String eventCom, boolean isDeleted) {
        this.cmtID = cmtID;
        this.cmtDate = cmtDate;
        this.eventCom = eventCom;
        this.isDeleted = isDeleted;
    }

    public Integer getCmtID() {
        return cmtID;
    }

    public void setCmtID(Integer cmtID) {
        this.cmtID = cmtID;
    }

    public String getCmtDate() {
        return cmtDate;
    }

    public void setCmtDate(String cmtDate) {
        this.cmtDate = cmtDate;
    }

    public String getEventCom() {
        return eventCom;
    }

    public void setEventCom(String eventCom) {
        this.eventCom = eventCom;
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
        hash += (cmtID != null ? cmtID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblComments)) {
            return false;
        }
        TblComments other = (TblComments) object;
        if ((this.cmtID == null && other.cmtID != null) || (this.cmtID != null && !this.cmtID.equals(other.cmtID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nicky.entitybean.TblComments[cmtID=" + cmtID + "]";
    }

}
