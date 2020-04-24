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
@Table(name = "tblFAQ", catalog = "kscUniversity", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "TblFAQ.findAll", query = "SELECT t FROM TblFAQ t"),
    @NamedQuery(name = "TblFAQ.findByFaqID", query = "SELECT t FROM TblFAQ t WHERE t.faqID = :faqID"),
    @NamedQuery(name = "TblFAQ.findByIsDeleted", query = "SELECT t FROM TblFAQ t WHERE t.isDeleted = :isDeleted")})
public class TblFAQ implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Basic(optional = false)
    @Column(name = "faqID", nullable = false)
    private Integer faqID;
    @Basic(optional = false)
    @Lob
    @Column(name = "faqQuestion", nullable = false, length = 2147483647)
    private String faqQuestion;
    @Lob
    @Column(name = "faqAns", length = 2147483647)
    private String faqAns;
    @Basic(optional = false)
    @Column(name = "isDeleted", nullable = false)
    private boolean isDeleted;

    public TblFAQ() {
    }

    public TblFAQ(Integer faqID) {
        this.faqID = faqID;
    }

    public TblFAQ(Integer faqID, String faqQuestion, boolean isDeleted) {
        this.faqID = faqID;
        this.faqQuestion = faqQuestion;
        this.isDeleted = isDeleted;
    }

    public Integer getFaqID() {
        return faqID;
    }

    public void setFaqID(Integer faqID) {
        this.faqID = faqID;
    }

    public String getFaqQuestion() {
        return faqQuestion;
    }

    public void setFaqQuestion(String faqQuestion) {
        this.faqQuestion = faqQuestion;
    }

    public String getFaqAns() {
        return faqAns;
    }

    public void setFaqAns(String faqAns) {
        this.faqAns = faqAns;
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
        hash += (faqID != null ? faqID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblFAQ)) {
            return false;
        }
        TblFAQ other = (TblFAQ) object;
        if ((this.faqID == null && other.faqID != null) || (this.faqID != null && !this.faqID.equals(other.faqID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nicky.entitybean.TblFAQ[faqID=" + faqID + "]";
    }

}
