package demo.projects;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class ProjectApplication extends Application {

    private Set<Object> singletons = new HashSet<Object>();
    private Set<Class<?>> classes = new HashSet<Class<?>>();

    public ProjectApplication() {
        singletons.add(new ProjectResource());
        singletons.add(new JacksonFeature());
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
