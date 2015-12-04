package demo.projects;


import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Path("/projects")
public class ProjectResource {

    private static Map<Integer, Project> projects;
    private static int count;

    static {
        projects = new ConcurrentHashMap<Integer, Project>();
    }

    public ProjectResource() {
        System.out.println("Neue Instanz von ProjectResource: " + this);
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response newProject(@Context UriInfo info, Project project) {
        int id = getId();
        project.setId(id);
        project.setTimestamp(getTimestamp());
        projects.put(id, project);
        URI location = info.getAbsolutePathBuilder().path(String.valueOf(id)).build();

        return Response.created(location).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response newProject(@Context UriInfo info, @FormParam("name") String name) {
        int id = getId();
        Project project = new Project(id, getTimestamp(), name);
        projects.put(id, project);
        URI location = info.getAbsolutePathBuilder().path(String.valueOf(id)).build();

        return Response.created(location).status(Response.Status.MOVED_PERMANENTLY).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getProject(@PathParam("id") int id) {
        Project project = projects.get(id);
        if (project == null) {
            return Response.noContent().status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(project).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Projects getAllProjects() {
        Object[] values = projects.values().toArray();
        Arrays.sort(values);
        List<Project> list = new ArrayList<Project>();

        for (Object object : values) {
            list.add((Project) object);
        }
        Projects collection = new Projects(list);

        return collection;
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response updateProject(@PathParam("id") int id, Project project) {
        if (projects.get(id) == null) {
            return Response.noContent().status(Response.Status.NOT_FOUND).build();
        } else {
            project.setId(id);
            project.setTimestamp(getTimestamp());
            projects.put(id, project);

            return Response.noContent().status(Response.Status.NO_CONTENT).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteProject(@PathParam("id") int id) {
        if (projects.get(id) != null) {
            projects.remove(id);
            return Response.noContent().status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.noContent().status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    public void deleteAllProjects() {
        projects.clear();
    }

    private synchronized static int getId() {
        return ++count;
    }

    private static String getTimestamp() {
        return new Timestamp(System.currentTimeMillis()).toString();
    }
}
