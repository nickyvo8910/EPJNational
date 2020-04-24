/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nicky.entitybean;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Nicky
 */
@Entity
@Table(name = "tblUsers", catalog = "kscUniversity", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "TblUsers.findAll", query = "SELECT t FROM TblUsers t"),
    @NamedQuery(name = "TblUsers.findByUsername", query = "SELECT t FROM TblUsers t WHERE t.username = :username"),
    @NamedQuery(name = "TblUsers.findByPassword", query = "SELECT t FROM TblUsers t WHERE t.password = :password"),
    @NamedQuery(name = "TblUsers.findByUserType", query = "SELECT t FROM TblUsers t WHERE t.userType = :userType"),
    @NamedQuery(name = "TblUsers.findByFullname", query = "SELECT t FROM TblUsers t WHERE t.fullname = :fullname"),
    @NamedQuery(name = "TblUsers.findByDob", query = "SELECT t FROM TblUsers t WHERE t.dob = :dob"),
    @NamedQuery(name = "TblUsers.findByGender", query = "SELECT t FROM TblUsers t WHERE t.gender = :gender"),
    @NamedQuery(name = "TblUsers.findByUserImg", query = "SELECT t FROM TblUsers t WHERE t.userImg = :userImg"),
    @NamedQuery(name = "TblUsers.findByUserScore", query = "SELECT t FROM TblUsers t WHERE t.userScore = :userScore"),
    @NamedQuery(name = "TblUsers.findByUserAddress", query = "SELECT t FROM TblUsers t WHERE t.userAddress = :userAddress"),
    @NamedQuery(name = "TblUsers.findByUserPhone", query = "SELECT t FROM TblUsers t WHERE t.userPhone = :userPhone"),
    @NamedQuery(name = "TblUsers.findByIsSubscribed", query = "SELECT t FROM TblUsers t WHERE t.isSubscribed = :isSubscribed"),
    @NamedQuery(name = "TblUsers.findByIsDeleted", query = "SELECT t FROM TblUsers t WHERE t.isDeleted = :isDeleted")})
public class TblUsers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Username", nullable = false, length = 50)
    private String username;
    @Basic(optional = false)
    @Column(name = "Password", nullable = false, length = 50)
    private String password;
    @Basic(optional = false)
    @Column(name = "userType", nullable = false)
    private int userType;
    @Column(name = "Fullname", length = 50)
    private String fullname;
    @Column(name = "DOB", length = 50)
    private String dob;
    @Column(name = "Gender")
    private Boolean gender;
    @Column(name = "userImg", length = 150)
    private String userImg;
    @Column(name = "userScore")
    private Integer userScore;
    @Column(name = "userAddress", length = 500)
    private String userAddress;
    @Column(name = "userPhone", length = 50)
    private String userPhone;
    @Column(name = "isSubscribed")
    private Boolean isSubscribed;
    @Basic(optional = false)
    @Column(name = "isDeleted", nullable = false)
    private boolean isDeleted;
    @OneToMany(mappedBy = "tblUsers")
    private Collection<TblEvents> tblEventsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblUsers")
    private Collection<TblEnrolls> tblEnrollsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblUsers")
    private Collection<TblComments> tblCommentsCollection;

    public TblUsers() {
    }

    public TblUsers(String username) {
        this.username = username;
    }

    public TblUsers(String username, String password, int userType, boolean isDeleted) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.isDeleted = isDeleted;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public Integer getUserScore() {
        return userScore;
    }

    public void setUserScore(Integer userScore) {
        this.userScore = userScore;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Boolean getIsSubscribed() {
        return isSubscribed;
    }

    public void setIsSubscribed(Boolean isSubscribed) {
        this.isSubscribed = isSubscribed;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Collection<TblEvents> getTblEventsCollection() {
        return tblEventsCollection;
    }

    public void setTblEventsCollection(Collection<TblEvents> tblEventsCollection) {
        this.tblEventsCollection = tblEventsCollection;
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
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblUsers)) {
            return false;
        }
        TblUsers other = (TblUsers) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nicky.entitybean.TblUsers[username=" + username + "]";
    }

}
