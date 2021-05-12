package org.studypj.persistence;


import lombok.extern.log4j.Log4j;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.Assert.fail;

@Log4j
public class JDBCTests {
    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch(Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void testConnection(){
        try(Connection con = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/studypj?autoReconnect=true&serverTimezone=UTC",
                "studypj",
                "studypj")){

            log.info(con);
        } catch(Exception e){
            fail(e.getMessage());
        }

    }


}
