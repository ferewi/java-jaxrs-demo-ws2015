package demo.projects;


import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.sql.Timestamp;
import java.util.Arrays;
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
    @Consumes(MediaType.TEXT_PLAIN)
    public Response newProject1(@Context UriInfo info, String name) {
        int id = getId();
        Project project = new Project(id, getTimestamp(), name);
        projects.put(id, project);
        URI location = info.getAbsolutePathBuilder().path(String.valueOf(id)).build();

        return Response.created(location).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response newProject2(@Context UriInfo info, @FormParam("text") String name) {
        int id = getId();
        Project project = new Project(id, getTimestamp(), name);
        projects.put(id, project);
        URI location = info.getAbsolutePathBuilder().path(String.valueOf(id)).build();

        return Response.created(location).status(Response.Status.MOVED_PERMANENTLY).build();
    }

    @GET
    @Path("{id}")
    public Response getProject(@PathParam("id") int id) {
        Project project = projects.get(id);
        if (project == null) {
            return Response.noContent().status(Response.Status.NOT_FOUND).build();
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\"?>");
        sb.append("<project id=\""+ project.getId() +"\">");
        sb.append("<timestamp>"+ project.getTimestamp() +"</timestamp>");
        sb.append("<name>"+ project.getName() +"</name>");
        sb.append("</project>");

        return Response.ok(sb.toString()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getAllProjects() {
        Object[] values = projects.values().toArray();
        Arrays.sort(values);
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\"?>");
        sb.append("<projects>");
        for (Object object : values) {
            Project project = (Project) object;
            sb.append("<project id=\""+ project.getId() +"\">");
            sb.append("<timestamp>"+ project.getTimestamp() +"</timestamp>");
            sb.append("<name>"+ project.getName() +"</name>");
            sb.append("</project>");
        }
        sb.append("</projects>");

        return Response.ok(sb.toString()).build();
    }

    @PUT
    @Path("{id}")
    public Response updateProject(@PathParam("id") int id, String name) {
        Project project = projects.get(id);
        if (project == null) {
            return Response.noContent().status(Response.Status.NOT_FOUND).build();
        } else {
            project.setTimestamp(getTimestamp());
            project.setName(name);
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
