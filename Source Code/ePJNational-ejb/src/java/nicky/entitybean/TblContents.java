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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Nicky
 */
@Entity
@Table(name = "tblContents", catalog = "kscUniversity", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "TblContents.findAll", query = "SELECT t FROM TblContents t"),
    @NamedQuery(name = "TblContents.findByContentID", query = "SELECT t FROM TblContents t WHERE t.contentID = :contentID"),
    @NamedQuery(name = "TblContents.findByContentURL", query = "SELECT t FROM TblContents t WHERE t.contentURL = :contentURL"),
    @NamedQuery(name = "TblContents.findBySender", query = "SELECT t FROM TblContents t WHERE t.sender = :sender"),
    @NamedQuery(name = "TblContents.findByIsSelected", query = "SELECT t FROM TblContents t WHERE t.isSelected = :isSelected"),
    @NamedQuery(name = "TblContents.findByIsDeleted", query = "SELECT t FROM TblContents t WHERE t.isDeleted = :isDeleted")})
public class TblContents implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Basic(optional = false)
    @Column(name = "contentID", nullable = false)
    private Integer contentID;
    @Column(name = "contentURL", length = 150)
    private String contentURL;
    @Column(name = "Sender", length = 100)
    private String sender;
    @Column(name = "isSelected")
    private Integer isSelected;
    @Basic(optional = false)
    @Column(name = "isDeleted", nullable = false)
    private boolean isDeleted;
    @JoinColumn(name = "eventID", referencedColumnName = "eventID", nullable = false)
    @ManyToOne(optional = false)
    private TblEvents tblEvents;

    public TblContents() {
    }

    public TblContents(Integer contentID) {
        this.contentID = contentID;
    }

    public TblContents(Integer contentID, boolean isDeleted) {
        this.contentID = contentID;
        this.isDeleted = isDeleted;
    }

    public Integer getContentID() {
        return contentID;
    }

    public void setContentID(Integer contentID) {
        this.contentID = contentID;
    }

    public String getContentURL() {
        return contentURL;
    }

    public void setContentURL(String contentURL) {
        this.contentURL = contentURL;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Integer getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Integer isSelected) {
        this.isSelected = isSelected;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
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
        hash += (contentID != null ? contentID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblContents)) {
            return false;
        }
        TblContents other = (TblContents) object;
        if ((this.contentID == null && other.contentID != null) || (this.contentID != null && !this.contentID.equals(other.contentID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nicky.entitybean.TblContents[contentID=" + contentID + "]";
    }

}
