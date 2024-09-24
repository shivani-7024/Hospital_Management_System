import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctors {
    Connection con;

    public Doctors(Connection con) {
        this.con = con;
    }



    public void view_doctor() throws SQLException {
        String query = "select * from Doctors";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        System.out.println("|----------+------------------+---------------------|");
        System.out.println("|Id        |Doctor Name       |Specializations      |");
        System.out.println("|----------+------------------+----------+----------|");

        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String specialization = rs.getString("specialization");

            System.out.printf("| %-9s| %-17s| %-20s|\n",id, name, specialization);
            System.out.println("|----------+------------------+---------------------|");

        }
    }

    public boolean get_doctor_by_id(int id) throws SQLException {
        String query = "select * from doctors where id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return true;
        }
        else {
            return false;
        }
    }
}
