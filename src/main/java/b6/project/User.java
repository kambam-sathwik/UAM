package b6.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class User {
	public String first_name;
	public String last_name;
	public String email;
	public String phoneno;
	public String pwd;
	public String cpwd;
	private String paswd;
	public String uname;
	User(String first_name,String last_name,String email,String phoneno,String pwd,String cpwd){
		this.first_name=first_name;
		this.last_name=last_name;
		this.email=email;
		this.phoneno=phoneno;
		this.pwd=pwd;
		this.cpwd=cpwd;
	}
	User(String uname,String pswd){
		this.uname=uname;
		pwd=pswd;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	public String validateuser() throws Exception{
		Connection c=Data_base.connect();
		String cquery="select userrole from employees where username=? and passwrd=?";
		PreparedStatement ps=c.prepareStatement(cquery);
		ps.setString(1, uname);
		ps.setString(2, encrypt_pwd());
		ResultSet rs=ps.executeQuery();
		String utype="invalid";
		if(rs.next()) {
			utype=rs.getString(1);
		}
		return utype;
	}
	public boolean pwd_match() {
		if(pwd.equals(cpwd))return false;
		return true;
	}
	public String check_username() throws Exception {
		Connection c=Data_base.connect();
		String uname=first_name+"."+last_name;
		String cntquery="select count(*) from employees where username like ?";
		PreparedStatement pc=c.prepareStatement(cntquery);
		pc.setString(1,uname+"%");
		ResultSet rs=pc.executeQuery();
		int cnt=0;
		if(rs.next()) {
			cnt=rs.getInt(1);
		}
		if(cnt!=0)uname=uname+cnt;
		return uname;
	}
	public String define_role() throws Exception{
		Connection c=Data_base.connect();
		String userrole="Employee";
		String query="select count(*) from employees";
		PreparedStatement ps=c.prepareStatement(query);
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			if(rs.getInt(1)==0)userrole="Admin";
		}
		return userrole;
	}
	private String encrypt_pwd() {
		String check="ABCDEFGHI,JKLMNOPQR,STUVWXYZa,bcdefghij,klmnopqrs,tuvwxyz01,23456789`,~!@#$%^&*,()-_=+[{],}|;:',<.>,/?";
		String[] arr=check.split(",");
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<pwd.length();i++) {
			char ch=pwd.charAt(i);
			for(int j=0;j<arr.length;j++) {
				if(arr[j].indexOf(ch)!=-1) {
					sb.append(j);
					sb.append(arr[j].indexOf(ch));
				}
			}
		}
		return sb.toString();
		
	}
	public String createUser() throws Exception{
		Connection c=Data_base.connect();
		String uname=check_username();
		String userrole=define_role();
		paswd=encrypt_pwd();
    	String query="insert into employees (first_name,last_name,email,phno,passwrd,username,userrole)values(?,?,?,?,?,?,?)";
    	PreparedStatement ps=c.prepareStatement(query);
    	ps.setString(1, first_name);
    	ps.setString(2, last_name);
    	ps.setString(3, email);
    	ps.setString(4, phoneno);
    	ps.setString(5, paswd);
    	ps.setString(6, uname);
    	ps.setString(7, userrole);
    	ps.executeUpdate();
    	return uname;
	}
	public String view_resources() throws Exception{
		Connection c=Data_base.connect();
		String allresources="";
		String query="select resource_name from resources";
		PreparedStatement ps=c.prepareStatement(query);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			allresources+=rs.getString(1)+"<br>";
		}
		return allresources;
	}
	
}
