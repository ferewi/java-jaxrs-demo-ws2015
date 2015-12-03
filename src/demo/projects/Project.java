package demo.projects;


public class Project implements Comparable<Project> {

    private int id;
    private String timestamp;
    private String name;

    public Project() {

    }

    public Project(String name) {
        this.name = name;
    }

    public Project(int id, String timestamp, String name) {
        this.id = id;
        this.timestamp = timestamp;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Project [id="+  id +", timestamp="+ timestamp +", name="+ name +"]";
    }

    @Override
    public int compareTo(Project project) {
        return Integer.valueOf(id)
                .compareTo(Integer.valueOf(project.id));
    }
}
