package b6.project;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;


/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    @GET
    @Path("db")
    public String implement_db() throws Exception {
    	Connection c=Data_base.connect();
    	if(c!=null)return "connected";
    	return "not connected";
    	
    }
    
    @POST
    @Path("registration")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String implement_registration(@FormParam("firstname") String first_name,
    		@FormParam("lastname") String last_name,
    		@FormParam("mail") String email,
    		@FormParam("phno") String phoneno,
    		@FormParam("pwd") String pwd,
    		@FormParam("cpwd") String cpwd
    		) throws Exception{
    	User us=new User(first_name,last_name,email,phoneno,pwd,cpwd);
    	if(us.pwd_match())return "passwords did not match"+"<br><a href='http://localhost:8118/project/signup.html'>Register again</a>";
    	return "your username is : "+us.createUser()+"<a href='http://localhost:8118/project/'>proceed to login</a>";
    }
    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String implement_login(@FormParam("uname") String uname,@FormParam("pswd") String pswd,@Context HttpServletResponse resp,@Context HttpServletRequest req) throws Exception{
    	User us1=new User(uname,pswd);
    	String usertype=us1.validateuser();
    	FileUtils fu=new FileUtils();
    	HttpSession session = req.getSession();
        session.setAttribute("username", uname);
    	if(usertype.equals("Admin")) {
    		
//    		resp.sendRedirect("http://localhost:8118/project/admindashboard.html");
    		return fu.addDataAfter(105, uname, "webapp/admindashboard.html", req);
    	}
    	else if(usertype.equals("Employee")) {
    		
    		return fu.addDataAfter(140, uname, "webapp/userdashboard.html", req);
//    		resp.sendRedirect("http://localhost:8118/project/userdashboard.html");
    	}
    	else if(usertype.equals("invalid")) {
    		resp.sendRedirect("http://localhost:8118/project/ilogin.html");
    	}
//    	else resp.sendRedirect("http://localhost:8118/project/managerdashboard.html");
    	return fu.addDataAfter(108, uname, "webapp/managerdashboard.html", req);
    	
    	
    	
    }
//    admin page
    
    
    
    
//    adding resource to the database(admin page)
    @GET
    @Path("add_resources")
    public String addResourceForm(@Context HttpServletRequest req) throws Exception {
        return generateFormHtml("", "webapp/admindashboard.html", req);
    }

    @POST
    @Path("resource_add")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String addResource(@FormParam("resource-name") String rname, @Context HttpServletRequest req) throws Exception {
        Resource r = new Resource();
//        FileUtils fu = new FileUtils();
        String message;

        if (r.addResource(rname)) {
            message = "<p style=\"color: green;\">Resource added successfully</p>";
        } else {
            message = "<p style=\"color: red;\">Resource already exists</p>";
        }

        return generateFormHtml(message, "webapp/admindashboard.html", req);
    }

    private String generateFormHtml(String message, String filePath, HttpServletRequest req) throws Exception {
        FileUtils fu = new FileUtils();
        String htmlForm = "<form action=\"/project/webapi/myresource/resource_add\" method=\"POST\">\r\n" + 
                          "    <label for=\"resource-name\">Resource Name</label>\r\n" + 
                          "    <input type=\"text\" id=\"resource-name\" name=\"resource-name\" required>\r\n" + 
                          "    <button type=\"submit\">Submit</button>\r\n" + 
                          "</form><br>" + message;
        return fu.addDataAfter(122, htmlForm, filePath, req);
    }
//    removing resource from database(admin page)
    @GET
    @Path("remove_resource_click")
    public String clickRemoveResource(@Context HttpServletRequest req) throws Exception{
    	FileUtils fu=new FileUtils();
    	Resource r=new Resource();
    	return fu.addDataAfter(122, r.removeResourceClick(), "webapp/admindashboard.html", req);
    }
    @POST
    @Path("remove_resource")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String removeResource(@FormParam("resource-name") String rname,@Context HttpServletRequest req) throws Exception{
    	FileUtils fu=new FileUtils();
    	Resource r=new Resource();
    	return fu.addDataAfter(122, r.removeResource(rname), "webapp/admindashboard.html", req);
    }
//    check users for a resource(admin page)
    @GET
    @Path("check_users_click")
    public String clickCheckUsersMethod(@Context HttpServletRequest req) throws Exception{
    	FileUtils fu=new FileUtils();
    	Resource r=new Resource();
    	return fu.addDataAfter(122, r.clickCheckUsers(), "webapp/admindashboard.html", req);
    }
    @POST
    @Path("check_users")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String checkUsersMethod(@FormParam("resource-name") String rname,@Context HttpServletRequest req) throws Exception{
    	FileUtils fu=new FileUtils();
    	Resource r=new Resource();
    	return fu.addDataAfter(122, r.checkUsers(rname), "webapp/admindashboard.html", req);
    }
    //checking resources of a user(admin page)
    @GET
    @Path("check_user_resources_click")
    public String clickCheckUserResourcesMethod(@Context HttpServletRequest req) throws Exception{
    	FileUtils fu=new FileUtils();
    	Resource r=new Resource();
    	return fu.addDataAfter(122, r.clickCheckUserResources(), "webapp/admindashboard.html", req);
    }
    @POST
    @Path("check_user_resources")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String checkUserResourceMethod(@FormParam("user-name") String uname,@Context HttpServletRequest req) throws Exception{
    	FileUtils fu=new FileUtils();
    	Resource r=new Resource();
    	return fu.addDataAfter(122, r.checkUserResources(uname), "webapp/admindashboard.html", req);
    }
//    removing resource from user(admin page)
    @GET
    @Path("remove_user_resource_click")
    public String clickRemoveUserResource(@Context HttpServletRequest req) throws Exception{
    	FileUtils fu=new FileUtils();
    	Resource r=new Resource();
    	return fu.addDataAfter(122, r.removeUserResourceClick(), "webapp/admindashboard.html", req);
    }
    @POST
    @Path("remove_user_resource")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String removeUserResourceMethod(@FormParam("resource-name") String rname,@FormParam("username") String uname,@Context HttpServletRequest req) throws Exception{
    	FileUtils fu=new FileUtils();
    	Resource r=new Resource();
    	return fu.addDataAfter(122, r.removeUserResource(uname,rname), "webapp/admindashboard.html", req);
    }
    @GET
    @Path("check_requests")
    public String checkRequestsMethod(@Context HttpServletRequest req) throws Exception{
    	FileUtils fu=new FileUtils();
    	Resource r=new Resource();
    	return fu.addDataAfter(122, r.checkRequests(), "webapp/admindashboard.html", req);
    }
    @POST
    @Path("handle_request")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String handleRequestMethod(@FormParam("request_id") int requestId,
            @FormParam("username") String userName,
            @FormParam("resource_name") String resourceName,
            @FormParam("action") String action,@Context HttpServletRequest req) throws Exception{
    	FileUtils fu=new FileUtils();
    	Resource r=new Resource();
    	return fu.addDataAfter(122, r.handleRequest(requestId,userName,resourceName,action), "webapp/admindashboard.html", req);
    }
//    user page
    
    
    
    
    
    
    
    
    @GET 
    @Path("check_resources")
    public String checkResources(@Context HttpServletRequest req) throws Exception{
    	User us=new User();
    	FileUtils fu=new FileUtils();
//    	HttpSession session1 = req.getSession();
//        String username = (String) session1.getAttribute("username");
//    	fu.addDataAfter(130, username, "webapp/userdashboard.html", req);
    	return fu.addDataAfter(156, us.view_resources(), "webapp/userdashboard.html", req);
    }
    @GET 
    @Path("check_approvals")
    public String checkApprovals(@Context HttpServletRequest req) throws Exception{
//    	User us=new User();
    	Resource r=new Resource();
    	FileUtils fu=new FileUtils();
    	return fu.addDataAfter(156, r.Approvals(req), "webapp/userdashboard.html", req);
    	
    }
//    requesting a resource
    @GET
    @Path("request_resource_click")
    public String requestResourceClickMethod(@Context HttpServletRequest req) throws Exception{
    	FileUtils fu=new FileUtils();
    	Resource r=new Resource();
//    	HttpSession session1 = req.getSession();
//        String username = (String) session1.getAttribute("username");
    	return fu.addDataAfter(156, r.requestResourceClick(req), "webapp/userdashboard.html", req);
    }
    @POST
    @Path("request_resource")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String requestResourceMethod(@FormParam("resource-name") String rname,@Context HttpServletRequest req) throws Exception{
    	FileUtils fu=new FileUtils();
    	Resource r=new Resource();
    	
    	return fu.addDataAfter(156, r.requestResource(rname,req), "webapp/userdashboard.html", req);
    }
//    removing resource for self
    @GET
    @Path("remove_own_resource_click")
    public String removeOwnResourceClickMethod(@Context HttpServletRequest req) throws Exception{
    	FileUtils fu=new FileUtils();
    	Resource r=new Resource();
    	return fu.addDataAfter(156, r.removeOwnResourceClick(req), "webapp/userdashboard.html", req);
    }
    @POST
    @Path("remove_own_resource")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String removeOwnResourceMethod(@FormParam("resource-name") String rname,@Context HttpServletRequest req) throws Exception{
    	FileUtils fu=new FileUtils();
    	Resource r=new Resource();
    	HttpSession session1 = req.getSession();
        String username = (String) session1.getAttribute("username");
    	return fu.addDataAfter(156, r.removeOwnResource(username,rname,req), "webapp/userdashboard.html", req);
    }
//    requesting for manager or admin
    @GET
    @Path("request_admin_click")
    public String requestAdminClickMethod(@Context HttpServletRequest req) throws Exception{
    	FileUtils fu=new FileUtils();
    	Resource r=new Resource();
    	return fu.addDataAfter(156, r.requestAdminClick(req), "webapp/userdashboard.html", req);
    }
    @POST
    @Path("request_admin")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String requsetAdminMethod(@FormParam("role") String role,@Context HttpServletRequest req) throws Exception{
    	FileUtils fu=new FileUtils();
    	Resource r=new Resource();
    	return fu.addDataAfter(156, r.requestAdmin(role,req), "webapp/userdashboard.html", req);
    }
    @GET
    @Path("know_manager")
    public String knowManagerMethod(@Context HttpServletRequest req) throws Exception{
    	FileUtils fu=new FileUtils();
    	Resource r=new Resource();
    	return fu.addDataAfter(156, r.knowManager(req), "webapp/userdashboard.html", req);
    }
    
//    manager page
    
    
    
    
    @GET 
    @Path("mcheck_resources")
    public String checkResourcesmethod(@Context HttpServletRequest req) throws Exception{
    	User us=new User();
    	FileUtils fu=new FileUtils();
//    	HttpSession session1 = req.getSession();
//        String username = (String) session1.getAttribute("username");
//    	fu.addDataAfter(130, username, "webapp/userdashboard.html", req);
    	return fu.addDataAfter(125, us.view_resources(), "webapp/managerdashboard.html", req);
    }
    @GET
    @Path("mrequest_resource_click")
    public String mrequestResourceClickMethod(@Context HttpServletRequest req) throws Exception{
    	FileUtils fu=new FileUtils();
    	Resource r=new Resource();
    	return fu.addDataAfter(125, r.mrequestResourceClick(req), "webapp/managerdashboard.html", req);
    }
    @POST
    @Path("mrequest_resource")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String mrequestResourceMethod(@FormParam("resource-name") String rname,@Context HttpServletRequest req) throws Exception{
    	FileUtils fu=new FileUtils();
    	Resource r=new Resource();
    	
    	return fu.addDataAfter(125, r.mrequestResource(rname,req), "webapp/managerdashboard.html", req);
    }
    @GET 
    @Path("mcheck_approvals")
    public String mcheckApprovals(@Context HttpServletRequest req) throws Exception{
//    	User us=new User();
    	Resource r=new Resource();
    	FileUtils fu=new FileUtils();
    	return fu.addDataAfter(125, r.Approvals(req), "webapp/managerdashboard.html", req);
    	
    }
    @GET
    @Path("mremove_own_resource_click")
    public String mremoveOwnResourceClickMethod(@Context HttpServletRequest req) throws Exception{
    	FileUtils fu=new FileUtils();
    	Resource r=new Resource();
    	return fu.addDataAfter(125, r.mremoveOwnResourceClick(req), "webapp/managerdashboard.html", req);
    }
    @POST
    @Path("mremove_own_resource")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String mremoveOwnResourceMethod(@FormParam("resource-name") String rname,@Context HttpServletRequest req) throws Exception{
    	FileUtils fu=new FileUtils();
    	Resource r=new Resource();
    	HttpSession session1 = req.getSession();
        String username = (String) session1.getAttribute("username");
    	return fu.addDataAfter(125, r.mremoveOwnResource(username,rname,req), "webapp/managerdashboard.html", req);
    }
    @GET
    @Path("mrequest_admin_click")
    public String mrequestAdminClickMethod(@Context HttpServletRequest req) throws Exception{
    	FileUtils fu=new FileUtils();
    	Resource r=new Resource();
    	r.requestResource("Admin", req);
    	return fu.addDataAfter(125, "requested successfully", "webapp/managerdashboard.html", req);
    }
    @GET
    @Path("show_team")
    public String showTeamMethod(@Context HttpServletRequest req) throws Exception{
    	FileUtils fu=new FileUtils();
    	Resource r=new Resource();
    	return fu.addDataAfter(125, r.showTeam(req), "webapp/managerdashboard.html", req);
    }
    @GET
    @Path("get_team_click")
    public String getTeamClickMethod(@Context HttpServletRequest req) throws Exception{
    	FileUtils fu=new FileUtils();
    	Resource r=new Resource();
    	return fu.addDataAfter(125, r.getTeamClick(), "webapp/managerdashboard.html", req);
    }
    @POST
    @Path("get_team")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String getTeamMethod(@FormParam("user-name") String uname,@Context HttpServletRequest req) throws Exception{
    	FileUtils fu=new FileUtils();
    	Resource r=new Resource();
//    	HttpSession session1 = req.getSession();
//        String username = (String) session1.getAttribute("username");
    	return fu.addDataAfter(125, r.getTeam(uname,req), "webapp/managerdashboard.html", req);
    }
    
    
    
    
    
    
    
    
}
