package demo.projects;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "projects")
public class Projects {
    private Date date = new Date();
    private List<Project> entry;

    public Projects() {
        entry = new ArrayList<Project>();
    }

    public Projects(List<Project> entry) {
        this.entry = entry;
    }

    @XmlAttribute
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @XmlElement(name = "project")
    public List<Project> getEntry() {
        return entry;
    }

    public void setEntry(List<Project> entry) {
        this.entry = entry;
    }

    @Override
    public String toString() {
        return "Projects [date="+ date +", entry="+ entry +"]";
    }
}
