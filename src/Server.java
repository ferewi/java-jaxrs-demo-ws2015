import demo.projects.ProjectApplication;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

public class Server {

    public static void main(String[] args) throws Exception {
        final String BASE_URL = "http://localhost:8090/rest";

        URI endpoint = new URI(BASE_URL);
        Set<Class<?>> classes = new HashSet<Class<?>>();
//        classes.add(MyApplication.class);
//        classes.add(ProjectApplication.class);
//        ResourceConfig rc = new ResourceConfig(classes);
        ResourceConfig rc = ResourceConfig.forApplicationClass(ProjectApplication.class);
        GrizzlyHttpServerFactory.createHttpServer(endpoint, rc);
    }
}
