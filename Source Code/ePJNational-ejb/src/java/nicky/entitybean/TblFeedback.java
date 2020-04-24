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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Nicky
 */
@Entity
@Table(name = "tblFeedback", catalog = "kscUniversity", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "TblFeedback.findAll", query = "SELECT t FROM TblFeedback t"),
    @NamedQuery(name = "TblFeedback.findByFbID", query = "SELECT t FROM TblFeedback t WHERE t.fbID = :fbID"),
    @NamedQuery(name = "TblFeedback.findByFbFrom", query = "SELECT t FROM TblFeedback t WHERE t.fbFrom = :fbFrom"),
    @NamedQuery(name = "TblFeedback.findByFbDate", query = "SELECT t FROM TblFeedback t WHERE t.fbDate = :fbDate"),
    @NamedQuery(name = "TblFeedback.findByIsDeleted", query = "SELECT t FROM TblFeedback t WHERE t.isDeleted = :isDeleted")})
public class TblFeedback implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Basic(optional = false)
    @Column(name = "fbID", nullable = false)
    private Integer fbID;
    @Basic(optional = false)
    @Column(name = "fbFrom", nullable = false, length = 50)
    private String fbFrom;
    @Basic(optional = false)
    @Lob
    @Column(name = "fbQuestion", nullable = false, length = 2147483647)
    private String fbQuestion;
    @Lob
    @Column(name = "fbAns", length = 2147483647)
    private String fbAns;
    @Column(name = "fbDate", length = 50)
    private String fbDate;
    @Basic(optional = false)
    @Column(name = "isDeleted", nullable = false)
    private boolean isDeleted;

    public TblFeedback() {
    }

    public TblFeedback(Integer fbID) {
        this.fbID = fbID;
    }

    public TblFeedback(Integer fbID, String fbFrom, String fbQuestion, boolean isDeleted) {
        this.fbID = fbID;
        this.fbFrom = fbFrom;
        this.fbQuestion = fbQuestion;
        this.isDeleted = isDeleted;
    }

    public Integer getFbID() {
        return fbID;
    }

    public void setFbID(Integer fbID) {
        this.fbID = fbID;
    }

    public String getFbFrom() {
        return fbFrom;
    }

    public void setFbFrom(String fbFrom) {
        this.fbFrom = fbFrom;
    }

    public String getFbQuestion() {
        return fbQuestion;
    }

    public void setFbQuestion(String fbQuestion) {
        this.fbQuestion = fbQuestion;
    }

    public String getFbAns() {
        return fbAns;
    }

    public void setFbAns(String fbAns) {
        this.fbAns = fbAns;
    }

    public String getFbDate() {
        return fbDate;
    }

    public void setFbDate(String fbDate) {
        this.fbDate = fbDate;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fbID != null ? fbID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblFeedback)) {
            return false;
        }
        TblFeedback other = (TblFeedback) object;
        if ((this.fbID == null && other.fbID != null) || (this.fbID != null && !this.fbID.equals(other.fbID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nicky.entitybean.TblFeedback[fbID=" + fbID + "]";
    }

}
