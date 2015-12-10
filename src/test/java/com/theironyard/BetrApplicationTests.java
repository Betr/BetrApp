package com.theironyard;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BetrApplication.class)
@WebAppConfiguration
public class BetrApplicationTests {

//	public Connection startConnection() throws SQLException {
//		Connection con = DriverManager.getConnection("jdbc:h2:./test");
//		betr.createTables(con);
//		return con;
//	}

}
