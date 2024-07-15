package datacollector.datacollector;

import java.sql.*;

public class SQLconnector {
    public static void updateDatalist(int cpuLoad, int memoryUsed){
        String uname = "root";
        String url = "jdbc:mysql://localhost:3306/datacollector";
        String password = "admin";
        String query = "INSERT INTO Data(CpuLoad,MemoryUsed) VALUES(?,?)";
        Connection conn = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try{
            conn = DriverManager.getConnection(url, uname, password);
            PreparedStatement pstate = conn.prepareStatement(query);
            pstate.setInt(1,cpuLoad);
            pstate.setInt(2,memoryUsed);
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
