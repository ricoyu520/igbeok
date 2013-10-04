package com.igbeok.showcase;

import java.sql.Connection;
import java.sql.DriverManager;

import org.h2.tools.Server;
import org.junit.Test;

public class H2Test {

	@Test
	public void testWebServer() throws Exception {
		Server server = Server.createWebServer("-trace").start();
		
		Class.forName("org.h2.Driver");
		String url = "jdbc:h2:~/showcase2";
		String user = "sa";
		String pwds = "";
		Connection conn = DriverManager.getConnection(url, user, pwds);
		conn.createStatement().execute("create table item(id integer primary key, item_name varchar(30))");
		
		server.stop();
	}
	
	@Test
	public void testTCPServer() throws Exception {
		Server server = Server.createTcpServer();
        server.start();
		
		Class.forName("org.h2.Driver");
		String url = "jdbc:h2:~/showcase2";
		String user = "sa";
		String pwds = "";
		Connection conn = DriverManager.getConnection(url, user, pwds);
		conn.createStatement().execute("create table item2(id integer primary key, item_name varchar(30))");
		
		server.stop();
	}
	
	@Test
	public void testPGServer() throws Exception {
		Server server = Server.createPgServer("-pgAllowOthers");
		server.start();
		
		Class.forName("org.h2.Driver");
		String url = "jdbc:h2:~/showcase2";
		String user = "sa";
		String pwds = "";
		Connection conn = DriverManager.getConnection(url, user, pwds);
		conn.createStatement().execute("create table item3(id integer primary key, item_name varchar(30))");
		
		server.stop();
	}
}
