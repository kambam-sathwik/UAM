package b6.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;

public class Resource {
	public boolean addResource(String rname) throws Exception{
		Connection c=Data_base.connect();
		String query="select count(*) from resources where resource_name=?";
		PreparedStatement ps=c.prepareStatement(query);
		ps.setString(1, rname);
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			if(rs.getInt(1)==1)return false;
		}
		String iquery="insert into resources (resource_name,no_of_users)values(?,?)";
    	PreparedStatement ips=c.prepareStatement(iquery);
    	ips.setString(1, rname);
    	ips.setInt(2, 0);
    	ips.executeUpdate();
		return true;
	}

	public String removeResourceClick() throws Exception {
	    StringBuilder options = new StringBuilder();
	    String query = "SELECT resource_name FROM resources";
	    Connection c = Data_base.connect();
	    PreparedStatement ps = c.prepareStatement(query);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
	    	String resourceName = rs.getString(1);
	    	options.append("<option value=\"").append(resourceName).append("\">")
	                   .append(resourceName).append("</option>\r\n");
	    }
	    
	    // Generate the HTML form with the options
	    String htmlc = "<form action=\"/project/webapi/myresource/remove_resource\" method=\"POST\">\r\n" + 
	                      "    <label for=\"resource-select\">Select Resource</label>\r\n" + 
	                      "    <select id=\"resource-select\" name=\"resource-name\" required>\r\n" + 
	                      "        <option value=\"\" disabled selected>Select a resource</option>\r\n" + 
	                      options.toString() + 
	                      "    </select>\r\n" + 
	                      "    <button type=\"submit\">Submit</button>\r\n" + 
	                      "</form>";
	    
	   return htmlc;
	}

	public String removeResource(String rname) throws Exception{
		Connection c=Data_base.connect();
		String query1="delete from resources where resource_name=?";
		String query2="delete from user_resource where resource_name=?";
		PreparedStatement ps1 = c.prepareStatement(query1);
		PreparedStatement ps2 = c.prepareStatement(query2);
		ps1.setString(1, rname);
		ps2.setString(1, rname);
		ps1.executeUpdate();
		ps2.executeUpdate();
		
		// TODO Auto-generated method stub
//		return null;
//		StringBuilder options = new StringBuilder();
//	    String query = "SELECT resource_name FROM resources";
////	    Connection c = Data_base.connect();
//	    PreparedStatement ps = c.prepareStatement(query);
//	    ResultSet rs = ps.executeQuery();
//	    while (rs.next()) {
//	    	String resourceName = rs.getString(1);
//	    	options.append("<option value=\"").append(resourceName).append("\">")
//	                   .append(resourceName).append("</option>\r\n");
//	    }
//	    
//	    // Generate the HTML form with the options
//	    String htmlc = "<form action=\"/project/webapi/myresource/remove_resource\" method=\"POST\">\r\n" + 
//	                      "    <label for=\"resource-select\">Select Resource</label>\r\n" + 
//	                      "    <select id=\"resource-select\" name=\"resource-name\" required>\r\n" + 
//	                      "        <option value=\"\" disabled selected>Select a resource</option>\r\n" + 
//	                      options.toString() + 
//	                      "    </select>\r\n" + 
//	                      "    <button type=\"submit\">Submit</button>\r\n" + 
//	                      "</form><br>Removed Successfully";
	    
	   return removeResourceClick()+"<br>Removed Successfully";
	}

	public String clickCheckUsers() throws Exception{
		//  Auto-generated method stub
//		return null;
		StringBuilder options = new StringBuilder();
	    String query = "SELECT resource_name FROM resources";
	    Connection c = Data_base.connect();
	    PreparedStatement ps = c.prepareStatement(query);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
	    	String resourceName = rs.getString(1);
	    	options.append("<option value=\"").append(resourceName).append("\">")
	                   .append(resourceName).append("</option>\r\n");
	    }
	    
	    // Generate the HTML form with the options
	    String htmlc = "<form action=\"/project/webapi/myresource/check_users\" method=\"POST\">\r\n" + 
	                      "    <label for=\"resource-select\">Select Resource</label>\r\n" + 
	                      "    <select id=\"resource-select\" name=\"resource-name\" required>\r\n" + 
	                      "        <option value=\"\" disabled selected>Select a resource</option>\r\n" + 
	                      options.toString() + 
	                      "    </select>\r\n" + 
	                      "    <button type=\"submit\">Submit</button>\r\n" + 
	                      "</form>";
	    
	   return htmlc;
	}

	public String checkUsers(String rname) throws Exception{
		// TODO Auto-generated method stub
//		return null;
		StringBuilder options = new StringBuilder();
	    String query = "SELECT resource_name FROM resources";
	    Connection c = Data_base.connect();
	    PreparedStatement ps = c.prepareStatement(query);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
	    	String resourceName = rs.getString(1);
	    	options.append("<option value=\"").append(resourceName).append("\">")
	                   .append(resourceName).append("</option>\r\n");
	    }
	    
	    // Generate the HTML form with the options
	    
	    StringBuilder allUsers=new StringBuilder();
		String queryUser="select uname from user_resource where resource_name=?";
		PreparedStatement psu=c.prepareStatement(queryUser);
		psu.setString(1, rname);
		ResultSet rsu=psu.executeQuery();
		while(rsu.next()) {
			allUsers.append(rsu.getString(1)).append("<br>");
		}
		String htmlc = "<form action=\"/project/webapi/myresource/check_users\" method=\"POST\">\r\n" + 
                "    <label for=\"resource-select\">Select Resource</label>\r\n" + 
                "    <select id=\"resource-select\" name=\"resource-name\" required>\r\n" + 
                "        <option value=\"\" disabled selected>Select a resource</option>\r\n" + 
                options.toString() + 
                "    </select>\r\n" + 
                "    <button type=\"submit\">Submit</button>\r\n" + 
                "</form><br>"+allUsers.toString();
	   return htmlc;
	}

	public String clickCheckUserResources() throws Exception{
		// TODO Auto-generated method stub
//		return null;
		StringBuilder options = new StringBuilder();
	    String query = "SELECT username FROM employees where userrole=?";
	    Connection c = Data_base.connect();
	    PreparedStatement ps = c.prepareStatement(query);
	    ps.setString(1, "Employee");
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
	    	String resourceName = rs.getString(1);
	    	options.append("<option value=\"").append(resourceName).append("\">")
	                   .append(resourceName).append("</option>\r\n");
	    }
	    
	    // Generate the HTML form with the options
	    String htmlc = "<form action=\"/project/webapi/myresource/check_user_resources\" method=\"POST\">\r\n" + 
	                      "    <label for=\"resource-select\">Select User Name</label>\r\n" + 
	                      "    <select id=\"resource-select\" name=\"user-name\" required>\r\n" + 
	                      "        <option value=\"\" disabled selected>Select a user</option>\r\n" + 
	                      options.toString() + 
	                      "    </select>\r\n" + 
	                      "    <button type=\"submit\">Submit</button>\r\n" + 
	                      "</form>";
	    
	   return htmlc;
	}

	public String checkUserResources(String uname) throws Exception{
		// TODO Auto-generated method stub
//		return null;
		Connection c = Data_base.connect();
		StringBuilder allResources=new StringBuilder();
		String queryUser="select resource_name from user_resource where uname=?";
		PreparedStatement psr=c.prepareStatement(queryUser);
		psr.setString(1, uname);
		ResultSet rsu=psr.executeQuery();
		while(rsu.next()) {
			allResources.append(rsu.getString(1)).append("<br>");
		}
		StringBuilder options = new StringBuilder();
	    String query = "SELECT username FROM employees where userrole=?";
//	    Connection c = Data_base.connect();
	    PreparedStatement ps = c.prepareStatement(query);
	    ps.setString(1, "Employee");
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
	    	String resourceName = rs.getString(1);
	    	options.append("<option value=\"").append(resourceName).append("\">")
	                   .append(resourceName).append("</option>\r\n");
	    }
	    
	    // Generate the HTML form with the options
	    String htmlc = "<form action=\"/project/webapi/myresource/check_user_resources\" method=\"POST\">\r\n" + 
	                      "    <label for=\"resource-select\">Select User Name</label>\r\n" + 
	                      "    <select id=\"resource-select\" name=\"user-name\" required>\r\n" + 
	                      "        <option value=\"\" disabled selected>Select a user</option>\r\n" + 
	                      options.toString() + 
	                      "    </select>\r\n" + 
	                      "    <button type=\"submit\">Submit</button>\r\n" + 
	                      "</form><br>"+allResources.toString();
	    
	   return htmlc;
		
	}
//	method for displaying data when clicked on remove resource user
	public String removeUserResourceClick() throws Exception {
	    StringBuilder html = new StringBuilder();
	    // Create HTML form
	    html.append("<form action=\"/project/webapi/myresource/remove_user_resource\" method=\"POST\">\r\n")
	        .append("    <label for=\"username\">Username</label>\r\n")
	        .append("    <input type=\"text\" id=\"username\" name=\"username\" required>\r\n")
	        .append("    <label for=\"resource-name\">Resource Name</label>\r\n")
	        .append("    <input type=\"text\" id=\"resource-name\" name=\"resource-name\" required>\r\n")
	        .append("    <button type=\"submit\">Submit</button>\r\n")
	        .append("</form><br>\r\n");
	    // Fetch data from the database and generate the table
	    String query = "SELECT * FROM user_resource";
	    
	    Connection c = Data_base.connect();
	    PreparedStatement ps = c.prepareStatement(query);
	    ResultSet rs = ps.executeQuery();

	    html.append("<div style=\"max-height: 300px; overflow-y: auto; border: 1px solid #ddd; padding: 5px;\">\r\n")
	        .append("    <table border=\"1\" style=\"width: 100%; border-collapse: collapse;\">\r\n")
	        .append("        <thead>\r\n")
	        .append("            <tr>\r\n")
	        .append("                <th>Username</th>\r\n")
	        .append("                <th>Resource Name</th>\r\n")
	        .append("            </tr>\r\n")
	        .append("        </thead>\r\n")
	        .append("        <tbody>\r\n");

	    while (rs.next()) {
	    	String username = rs.getString("uname");
	        String resourceName = rs.getString("resource_name");
	        html.append("            <tr>\r\n")
	        .append("                <td>").append(username).append("</td>\r\n")
            .append("                <td>").append(resourceName).append("</td>\r\n")
	        .append("            </tr>\r\n");
       }

	   html.append("        </tbody>\r\n")
	       .append("    </table>\r\n")
	       .append("</div>\r\n");

	   return html.toString();
	}
	public String removeUserResource(String username, String resourceName) throws Exception {
	    Connection connection = Data_base.connect();
	        // Step 1: Remove record from user_resource table
	    String deleteQuery = "DELETE FROM user_resource WHERE uname = ? AND resource_name = ?";
	    PreparedStatement psDelete = connection.prepareStatement(deleteQuery);
	    psDelete.setString(1, username);
	    psDelete.setString(2, resourceName);
	    psDelete.executeUpdate();    
	    // Step 2: Decrement the no_of_users value for the resource_name in the resources table
	    String updateQuery = "UPDATE resources SET no_of_users = no_of_users - 1 WHERE resource_name = ?";
	    PreparedStatement psUpdate = connection.prepareStatement(updateQuery);
	    psUpdate.setString(1, resourceName);
	    psUpdate.executeUpdate();
	    // Step 3: Call createFormAndDisplayData() to get updated HTML
	    return removeUserResourceClick();
	}
	
//	user page
	
	
	
	
	
	
	public String requestResourceClick(@Context HttpServletRequest req) throws Exception {
	    StringBuilder html = new StringBuilder();
	    String query = 
	        "SELECT r.resource_name " +
	        "FROM resources r " +
	        "WHERE r.resource_name NOT IN (" +
	        "    SELECT ur.resource_name FROM user_resource ur WHERE ur.uname = ? " +
	        ") AND r.resource_name NOT IN (" +
	        "    SELECT r.requested_type FROM request r WHERE r.status = 'pending'" +
	        ")";
	    HttpSession session1 = req.getSession();
        String username = (String) session1.getAttribute("username");
	    Connection connection = Data_base.connect();
	         PreparedStatement preparedStatement = connection.prepareStatement(query);
	        
	        // Set the username parameter
	        preparedStatement.setString(1, username);
	        ResultSet resultSet = preparedStatement.executeQuery();
	            html.append("<form action=\"/project/webapi/myresource/request_resource\" method=\"POST\">\r\n")
	                .append("    <label for=\"resource-select\">Resource Name</label>\r\n")
	                .append("    <select id=\"resource-select\" name=\"resource-name\" required>\r\n")
	                .append("        <option value=\"\" disabled selected>Select a resource</option>\r\n");
	            
	            while (resultSet.next()) {
	                String resourceName = resultSet.getString("resource_name");
	                html.append("        <option value=\"").append(resourceName).append("\">")
	                    .append(resourceName).append("</option>\r\n");
	            }
	            
	            html.append("    </select>\r\n")
	                .append("    <button type=\"submit\">Submit</button>\r\n")
	                .append("</form>\r\n");

	    return html.toString();
	}
	public String requestResource(String resourceName,@Context HttpServletRequest req) throws Exception {
		// SQL query to insert a new record into the request table
		String query = "INSERT INTO request (requested_from, requested_type, date_of_request, status) " +
				"VALUES (?, ?, ?, 'pending')";

		// Get the current date in the format 'yyyy-MM-dd'
		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		Connection connection = Data_base.connect();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		// Set parameters for the query
		HttpSession session1 = req.getSession();
        String username = (String) session1.getAttribute("username");
		preparedStatement.setString(1, username);           // requested_from
		preparedStatement.setString(2, resourceName);        // requested_type
		preparedStatement.setString(3, currentDate);         
		preparedStatement.executeUpdate();
		return requestResourceClick(req)+"<br>Request submitted";
	}

	public String removeOwnResourceClick(HttpServletRequest req) throws Exception{
		// TODO Auto-generated method stub
//		return null;
		StringBuilder html = new StringBuilder();
	    String query = 
	        "SELECT resource_name from user_resource where uname=?";
	    HttpSession session1 = req.getSession();
        String username = (String) session1.getAttribute("username");
	    Connection connection = Data_base.connect();
	         PreparedStatement preparedStatement = connection.prepareStatement(query);
	        
	        // Set the username parameter
	        preparedStatement.setString(1, username);
	        ResultSet resultSet = preparedStatement.executeQuery();
	            html.append("<form action=\"/project/webapi/myresource/remove_own_resource\" method=\"POST\">\r\n")
	                .append("    <label for=\"resource-select\">Resource Name</label>\r\n")
	                .append("    <select id=\"resource-select\" name=\"resource-name\" required>\r\n")
	                .append("        <option value=\"\" disabled selected>Select a resource</option>\r\n");
	            
	            while (resultSet.next()) {
	                String resourceName = resultSet.getString(1);
	                html.append("        <option value=\"").append(resourceName).append("\">")
	                    .append(resourceName).append("</option>\r\n");
	            }
	            
	            html.append("    </select>\r\n")
	                .append("    <button type=\"submit\">Submit</button>\r\n")
	                .append("</form>\r\n");

	    return html.toString();
	}


	public String removeOwnResource(String username, String rname,HttpServletRequest req) throws Exception{
		// TODO Auto-generated method stub
//		return null;
		Connection connection = Data_base.connect();
		String deleteQuery = "DELETE FROM user_resource WHERE uname = ? AND resource_name = ?";
	    PreparedStatement psDelete = connection.prepareStatement(deleteQuery);
	    psDelete.setString(1, username);
	    psDelete.setString(2, rname);
	    psDelete.executeUpdate();    
	    // Step 2: Decrement the no_of_users value for the resource_name in the resources table
	    String updateQuery = "UPDATE resources SET no_of_users = no_of_users - 1 WHERE resource_name = ?";
	    PreparedStatement psUpdate = connection.prepareStatement(updateQuery);
	    psUpdate.setString(1, rname);
	    psUpdate.executeUpdate();
	    String deleteQuery1 = "DELETE FROM request WHERE requested_from = ? AND requested_type = ?";
	    PreparedStatement psDelete1 = connection.prepareStatement(deleteQuery1);
	    psDelete1.setString(1, username);
	    psDelete1.setString(2, rname);
	    psDelete1.executeUpdate(); 
	    return removeOwnResourceClick(req)+"<br>Removed Successfully";
	}

	
	
	 public String requestAdminClick(HttpServletRequest req) throws Exception {
	        StringBuilder html = new StringBuilder();

	        // Define the roles to be displayed in the dropdown
//	        List<String> allRoles = List.of("Manager", "Admin");
	        List<String> allRoles = Arrays.asList("Manager", "Admin");

	        // SQL query to find roles with pending requests for the provided username
	        String query = 
	            "SELECT requested_type " +
	            "FROM request " +
	            "WHERE requested_from = ? AND status = 'pending'";
	        HttpSession session1 = req.getSession();
	        String username = (String) session1.getAttribute("username");

	        // Get the roles that have pending requests
	        List<String> pendingRoles = new ArrayList<>();
	        Connection connection = Data_base.connect();
	             PreparedStatement preparedStatement = connection.prepareStatement(query);
	            
	            preparedStatement.setString(1, username);
	            
	            ResultSet resultSet = preparedStatement.executeQuery();
	                while (resultSet.next()) {
	                    String requestedType = resultSet.getString("requested_type");
	                    pendingRoles.add(requestedType);
	                }
	            

	        // Build the HTML form with the filtered roles
	        html.append("<form action=\"/project/webapi/myresource/request_admin\" method=\"POST\">\r\n")
	            .append("    <label for=\"role-select\">Select Role</label>\r\n")
	            .append("    <select id=\"role-select\" name=\"role\" required>\r\n")
	            .append("        <option value=\"\" disabled selected>Select a role</option>\r\n");

	        // Add options to the dropdown
	        for (String role : allRoles) {
	            if (!pendingRoles.contains(role)) {
	                html.append("        <option value=\"").append(role).append("\">")
	                    .append(role).append("</option>\r\n");
	            }
	        }

	        html.append("    </select>\r\n")
	            .append("    <button type=\"submit\">Submit</button>\r\n")
	            .append("</form>\r\n");

	        return html.toString();
	    }

	public String requestAdmin(String role, HttpServletRequest req) throws Exception{
		// TODO Auto-generated method stub
		
		requestResource(role,req);
		return requestAdminClick(req)+"<br>Requested successfully";
	}
	public String Approvals(HttpServletRequest req)throws Exception{
		StringBuilder html = new StringBuilder();

        // SQL query to select data from the request table where requested_from matches the provided username
        String query = "SELECT requested_type, date_of_request, status FROM request WHERE requested_from = ?";

        // Execute the query and retrieve the data
        Connection connection = Data_base.connect();  // Ensure Data_base.connect() returns a valid Connection object
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        	HttpSession session1 = req.getSession();
	        String username = (String) session1.getAttribute("username");
            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Build the HTML table
                html.append("<table border=\"1\">\r\n")
                    .append("    <tr>\r\n")
                    .append("        <th>Resource Name</th>\r\n")
                    .append("        <th>Date of Request</th>\r\n")
                    .append("        <th>Status</th>\r\n")
                    .append("    </tr>\r\n");

                // Add rows to the table for each request
                while (resultSet.next()) {
                    String requestedType = resultSet.getString("requested_type");
                    String dateOfRequest = resultSet.getString("date_of_request");
                    String status = resultSet.getString("status");

                    html.append("    <tr>\r\n")
                        .append("        <td>").append(requestedType).append("</td>\r\n")
                        .append("        <td>").append(dateOfRequest).append("</td>\r\n")
                        .append("        <td>").append(status).append("</td>\r\n")
                        .append("    </tr>\r\n");
                }

                html.append("</table>\r\n");
            }

        return html.toString();
	}

	public String checkRequests() throws Exception {
        StringBuilder html = new StringBuilder();
        String query = "SELECT requested_from, requested_type, date_of_request, request_id FROM request WHERE status = 'pending'";

        try (Connection connection = Data_base.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            html.append("<h1>List of Pending Requests</h1>\r\n")
                .append("<form action=\"/project/webapi/myresource/handle_request\" method=\"POST\">\r\n")
                .append("<div style=\"overflow-y: auto; max-height: 400px; border: 1px solid #ccc;\">\r\n")
                .append("    <table style=\"border-collapse: collapse; width: 100%;\">\r\n")
                .append("        <thead>\r\n")
                .append("            <tr>\r\n")
                .append("                <th style=\"text-align: left; padding: 8px;\">Username</th>\r\n")
                .append("                <th style=\"text-align: left; padding: 8px;\">Resource Name</th>\r\n")
                .append("                <th style=\"text-align: left; padding: 8px;\">Date of Request</th>\r\n")
                .append("                <th style=\"text-align: left; padding: 8px;\">Actions</th>\r\n")
                .append("            </tr>\r\n")
                .append("        </thead>\r\n")
                .append("        <tbody>\r\n");

            while (resultSet.next()) {
                String username = resultSet.getString("requested_from");
                String resourceName = resultSet.getString("requested_type");
                String dateOfRequest = resultSet.getString("date_of_request");
                int requestId = resultSet.getInt("request_id");

                html.append("            <tr>\r\n")
                    .append("                <td style=\"padding: 8px;\">").append(username).append("</td>\r\n")
                    .append("                <td style=\"padding: 8px;\">").append(resourceName).append("</td>\r\n")
                    .append("                <td style=\"padding: 8px;\">").append(dateOfRequest).append("</td>\r\n")
                    .append("                <td style=\"padding: 8px;\">\r\n")
                    .append("                    <input type=\"hidden\" name=\"request_id\" value=\"").append(requestId).append("\"/>\r\n")
                    .append("                    <input type=\"hidden\" name=\"username\" value=\"").append(username).append("\"/>\r\n")
                    .append("                    <input type=\"hidden\" name=\"resource_name\" value=\"").append(resourceName).append("\"/>\r\n")
                    .append("                    <button type=\"submit\" name=\"action\" value=\"accepted\">Accept</button>\r\n")
                    .append("                    <button type=\"submit\" name=\"action\" value=\"rejected\">Reject</button>\r\n")
                    .append("                </td>\r\n")
                    .append("            </tr>\r\n");
            }

            html.append("        </tbody>\r\n")
                .append("    </table>\r\n")
                .append("</div>\r\n")
                .append("</form>\r\n");

        }

        return html.toString();
    }

	public String handleRequest(int requestId, String userName, String resourceName, String action) throws Exception {
	    try (Connection connection = Data_base.connect()) {
	        // Update request status
	        String updateRequestQuery = "UPDATE request SET status = ? WHERE request_id = ?";
	        try (PreparedStatement updateRequestStmt = connection.prepareStatement(updateRequestQuery)) {
	            updateRequestStmt.setString(1, action);
	            updateRequestStmt.setInt(2, requestId);
	            updateRequestStmt.executeUpdate();
	        }

	        // Perform actions based on the request
	        if ("accepted".equals(action)) {
	        	// Update employees table if resourceName is "Manager" or "Admin"
	            if ("Manager".equals(resourceName) || "Admin".equals(resourceName)) {
	                String updateEmployeesQuery = "UPDATE employees SET userrole = ? WHERE username = ?";
	                try (PreparedStatement updateEmployeesStmt = connection.prepareStatement(updateEmployeesQuery)) {
	                    updateEmployeesStmt.setString(1, resourceName);
	                    updateEmployeesStmt.setString(2, userName);
	                    updateEmployeesStmt.executeUpdate();
	                }
	            }
	            else {
	            // Insert into user_resource
	            String insertUserResourceQuery = "INSERT INTO user_resource (uname, resource_name) VALUES (?, ?)";
	            try (PreparedStatement insertUserResourceStmt = connection.prepareStatement(insertUserResourceQuery)) {
	                insertUserResourceStmt.setString(1, userName);
	                insertUserResourceStmt.setString(2, resourceName);
	                insertUserResourceStmt.executeUpdate();
	            }

	            // Update resources table
	            String updateResourcesQuery = "UPDATE resources SET no_of_users = no_of_users + 1 WHERE resource_name = ?";
	            try (PreparedStatement updateResourcesStmt = connection.prepareStatement(updateResourcesQuery)) {
	                updateResourcesStmt.setString(1, resourceName);
	                updateResourcesStmt.executeUpdate();
	            }
	            }

	        }

	        // Return the updated list of requests
	        return checkRequests();
	    }
	}
	public String knowManager(HttpServletRequest req) throws Exception{
		Connection connection = Data_base.connect();
		String query = "select managername from employees where username=?";
	    PreparedStatement psUpdate = connection.prepareStatement(query);
	    HttpSession session1 = req.getSession();
        String userName = (String) session1.getAttribute("username");
	    psUpdate.setString(1, userName);
	    ResultSet rs=psUpdate.executeQuery();
	    String mname="";
	    if(rs.next()) {
	    	mname=rs.getString(1);
	    }
	    return mname;
	}
	
	
//	manager page
	
	
	
	
	
	
	
	
	public String mrequestResourceClick(@Context HttpServletRequest req) throws Exception {
	    StringBuilder html = new StringBuilder();
	    String query = 
	        "SELECT r.resource_name " +
	        "FROM resources r " +
	        "WHERE r.resource_name NOT IN (" +
	        "    SELECT ur.resource_name FROM user_resource ur WHERE ur.uname = ? " +
	        ") AND r.resource_name NOT IN (" +
	        "    SELECT r.requested_type FROM request r WHERE r.status = 'pending'" +
	        ")";
	    HttpSession session1 = req.getSession();
        String username = (String) session1.getAttribute("username");
	    Connection connection = Data_base.connect();
	         PreparedStatement preparedStatement = connection.prepareStatement(query);
	        
	        // Set the username parameter
	        preparedStatement.setString(1, username);
	        ResultSet resultSet = preparedStatement.executeQuery();
	            html.append("<form action=\"/project/webapi/myresource/mrequest_resource\" method=\"POST\">\r\n")
	                .append("    <label for=\"resource-select\">Resource Name</label>\r\n")
	                .append("    <select id=\"resource-select\" name=\"resource-name\" required>\r\n")
	                .append("        <option value=\"\" disabled selected>Select a resource</option>\r\n");
	            
	            while (resultSet.next()) {
	                String resourceName = resultSet.getString("resource_name");
	                html.append("        <option value=\"").append(resourceName).append("\">")
	                    .append(resourceName).append("</option>\r\n");
	            }
	            
	            html.append("    </select>\r\n")
	                .append("    <button type=\"submit\">Submit</button>\r\n")
	                .append("</form>\r\n");

	    return html.toString();
	}
	public String mrequestResource(String resourceName,@Context HttpServletRequest req) throws Exception {
		// SQL query to insert a new record into the request table
		String query = "INSERT INTO request (requested_from, requested_type, date_of_request, status) " +
				"VALUES (?, ?, ?, 'pending')";

		// Get the current date in the format 'yyyy-MM-dd'
		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		Connection connection = Data_base.connect();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		// Set parameters for the query
		HttpSession session1 = req.getSession();
        String username = (String) session1.getAttribute("username");
		preparedStatement.setString(1, username);           // requested_from
		preparedStatement.setString(2, resourceName);        // requested_type
		preparedStatement.setString(3, currentDate);         
		preparedStatement.executeUpdate();
		return requestResourceClick(req)+"<br>Request submitted";
	}
	public String mremoveOwnResourceClick(HttpServletRequest req) throws Exception{
		// TODO Auto-generated method stub
//		return null;
		StringBuilder html = new StringBuilder();
	    String query = 
	        "SELECT resource_name from user_resource where uname=?";
	    HttpSession session1 = req.getSession();
        String username = (String) session1.getAttribute("username");
	    Connection connection = Data_base.connect();
	         PreparedStatement preparedStatement = connection.prepareStatement(query);
	        
	        // Set the username parameter
	        preparedStatement.setString(1, username);
	        ResultSet resultSet = preparedStatement.executeQuery();
	            html.append("<form action=\"/project/webapi/myresource/mremove_own_resource\" method=\"POST\">\r\n")
	                .append("    <label for=\"resource-select\">Resource Name</label>\r\n")
	                .append("    <select id=\"resource-select\" name=\"resource-name\" required>\r\n")
	                .append("        <option value=\"\" disabled selected>Select a resource</option>\r\n");
	            
	            while (resultSet.next()) {
	                String resourceName = resultSet.getString(1);
	                html.append("        <option value=\"").append(resourceName).append("\">")
	                    .append(resourceName).append("</option>\r\n");
	            }
	            
	            html.append("    </select>\r\n")
	                .append("    <button type=\"submit\">Submit</button>\r\n")
	                .append("</form>\r\n");

	    return html.toString();
	}
	public String mremoveOwnResource(String username, String rname,HttpServletRequest req) throws Exception{
		// TODO Auto-generated method stub
//		return null;
		Connection connection = Data_base.connect();
		String deleteQuery = "DELETE FROM user_resource WHERE uname = ? AND resource_name = ?";
	    PreparedStatement psDelete = connection.prepareStatement(deleteQuery);
	    psDelete.setString(1, username);
	    psDelete.setString(2, rname);
	    psDelete.executeUpdate();    
	    // Step 2: Decrement the no_of_users value for the resource_name in the resources table
	    String updateQuery = "UPDATE resources SET no_of_users = no_of_users - 1 WHERE resource_name = ?";
	    PreparedStatement psUpdate = connection.prepareStatement(updateQuery);
	    psUpdate.setString(1, rname);
	    psUpdate.executeUpdate();
	    String deleteQuery1 = "DELETE FROM request WHERE requested_from = ? AND requested_type = ?";
	    PreparedStatement psDelete1 = connection.prepareStatement(deleteQuery1);
	    psDelete1.setString(1, username);
	    psDelete1.setString(2, rname);
	    psDelete1.executeUpdate();  
	    return mremoveOwnResourceClick(req)+"<br>Removed Successfully";
	}

	public String showTeam(HttpServletRequest req) throws Exception{
		// TODO Auto-generated method stub
//		return null;
		Connection connection = Data_base.connect();
		String query = "select username from employees where managername=?";
	    PreparedStatement psDelete = connection.prepareStatement(query);
	    HttpSession session1 = req.getSession();
        String username = (String) session1.getAttribute("username");
	    psDelete.setString(1, username);
	    ResultSet rs=psDelete.executeQuery();
	    StringBuilder sb=new StringBuilder();
	    while(rs.next()) {
	    	sb.append("<br>");
	    	sb.append(rs.getString(1));
	    }
	    return sb.toString();
	}

	public String getTeamClick() throws Exception{
		// TODO Auto-generated method stub
//		return null;type name = new type();
		StringBuilder html = new StringBuilder();
	    String query = 
	        "SELECT username\r\n" + 
	        "FROM employees\r\n" + 
	        "WHERE managername IS NULL and userrole='Employee'";
//	    HttpSession session1 = req.getSession();
//        String username = (String) session1.getAttribute("username");
	    Connection connection = Data_base.connect();
	         PreparedStatement preparedStatement = connection.prepareStatement(query);
	        
	        // Set the username parameter
//	        preparedStatement.setString(1, username);
	        ResultSet resultSet = preparedStatement.executeQuery();
	            html.append("<form action=\"/project/webapi/myresource/get_team\" method=\"POST\">\r\n")
	                .append("    <label for=\"resource-select\">User Name</label>\r\n")
	                .append("    <select id=\"resource-select\" name=\"user-name\" required>\r\n")
	                .append("        <option value=\"\" disabled selected>Select a user</option>\r\n");
	            
	            while (resultSet.next()) {
	                String resourceName = resultSet.getString(1);
	                html.append("        <option value=\"").append(resourceName).append("\">")
	                    .append(resourceName).append("</option>\r\n");
	            }
	            
	            html.append("    </select>\r\n")
	                .append("    <button type=\"submit\">Submit</button>\r\n")
	                .append("</form>\r\n");

	    return html.toString();
	}

	public String getTeam(String uname, HttpServletRequest req) throws Exception{
		// TODO Auto-generated method stub
//		return null;
		Connection connection = Data_base.connect();
		String query = "update employees set managername=? where username=?";
	    PreparedStatement psUpdate = connection.prepareStatement(query);
	    HttpSession session1 = req.getSession();
        String managerName = (String) session1.getAttribute("username");
	    psUpdate.setString(1, managerName);
	    psUpdate.setString(2, uname);
	    psUpdate.executeUpdate();
	    return getTeamClick()+"<br>User added to the team successfully";
	}
}
