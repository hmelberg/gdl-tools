package se.cambio.cm.model.scenario.dto;

import se.cambio.cm.model.util.CMElement;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="cm_scenario")
public class ScenarioDTO implements CMElement {
    @Id
    private String id;
    @Column(columnDefinition="TEXT")
    private String source;
    private Date lastUpdate;

    public ScenarioDTO() { }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getSource() {
        return source;
    }

    @Override
    public Date getLastUpdate() {
        return lastUpdate;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
