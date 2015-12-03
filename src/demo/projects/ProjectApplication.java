package demo.projects;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class ProjectApplication extends Application {

    private Set<Object> singletons = new HashSet<Object>();
    private Set<Class<?>> classes = new HashSet<Class<?>>();

    public ProjectApplication() {
        singletons.add(new ProjectResource());
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
