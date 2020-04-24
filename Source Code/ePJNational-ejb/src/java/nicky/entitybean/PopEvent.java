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
import javax.persistence.Id;
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
@Table(name = "PopEvent", catalog = "kscUniversity", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "PopEvent.findAll", query = "SELECT p FROM PopEvent p"),
    @NamedQuery(name = "PopEvent.findByEventID", query = "SELECT p FROM PopEvent p WHERE p.eventID = :eventID"),
    @NamedQuery(name = "PopEvent.findByEventCount", query = "SELECT p FROM PopEvent p WHERE p.eventCount = :eventCount"),
    @NamedQuery(name = "PopEvent.findByEventDate", query = "SELECT p FROM PopEvent p WHERE p.eventDate = :eventDate")})
public class PopEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "eventID", nullable = false)
    @Id
    private int eventID;
    @Column(name = "EventCount")
    private Integer eventCount;
    @Basic(optional = false)
    @Column(name = "eventDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventDate;

    public PopEvent() {
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public Integer getEventCount() {
        return eventCount;
    }

    public void setEventCount(Integer eventCount) {
        this.eventCount = eventCount;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

}
