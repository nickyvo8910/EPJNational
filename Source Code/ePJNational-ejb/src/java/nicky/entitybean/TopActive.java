/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nicky.entitybean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Nicky
 */
@Entity
@Table(name = "TopActive", catalog = "kscUniversity", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "TopActive.findAll", query = "SELECT t FROM TopActive t"),
    @NamedQuery(name = "TopActive.findByUsername", query = "SELECT t FROM TopActive t WHERE t.username = :username"),
    @NamedQuery(name = "TopActive.findByCounUser", query = "SELECT t FROM TopActive t WHERE t.counUser = :counUser"),
    @NamedQuery(name = "TopActive.findByMonthEnroll", query = "SELECT t FROM TopActive t WHERE t.monthEnroll = :monthEnroll"),
    @NamedQuery(name = "TopActive.findByYearEnroll", query = "SELECT t FROM TopActive t WHERE t.yearEnroll = :yearEnroll")})
public class TopActive implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "username", length = 50)
    @Id
    private String username;
    @Column(name = "CounUser")
    private Integer counUser;
    @Column(name = "MonthEnroll")
    private Integer monthEnroll;
    @Column(name = "YearEnroll")
    private Integer yearEnroll;

    public TopActive() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getCounUser() {
        return counUser;
    }

    public void setCounUser(Integer counUser) {
        this.counUser = counUser;
    }

    public Integer getMonthEnroll() {
        return monthEnroll;
    }

    public void setMonthEnroll(Integer monthEnroll) {
        this.monthEnroll = monthEnroll;
    }

    public Integer getYearEnroll() {
        return yearEnroll;
    }

    public void setYearEnroll(Integer yearEnroll) {
        this.yearEnroll = yearEnroll;
    }

}
