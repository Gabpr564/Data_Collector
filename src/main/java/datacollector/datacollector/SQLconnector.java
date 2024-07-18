package datacollector.datacollector;

import java.sql.*;

public class SQLconnector {
    public static void updateDatalist(String testName, double cpuLoad, double memoryUsed, int count){
        String uname = "root";
        String url = "jdbc:mysql://localhost:3306/datacollector";
        String password = "admin";
        String query = "INSERT INTO Data(TestName,CpuLoad,MemoryUsed,TestDuration) VALUES(?,?,?,?)";
        Connection conn = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try{
            conn = DriverManager.getConnection(url, uname, password);
            PreparedStatement pstate = conn.prepareStatement(query);
            pstate.setString(1,testName);
            pstate.setDouble(2,cpuLoad);
            pstate.setDouble(3,memoryUsed);
            pstate.setInt(4,count);
            pstate.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex){
                System.out.println(ex.getMessage());
            }
        }

    }

}

