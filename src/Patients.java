import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patients {
    Connection con;
    Scanner sc;

    public Patients(Connection con, Scanner sc) {
        this.con = con;
        this.sc = sc;
    }

    public void add_patient() throws SQLException {
        sc.nextLine();
        System.out.println("Enter Patient Name");
        String name = sc.nextLine();
        System.out.println("Enter Patient Age");
        int age = sc.nextInt();
        System.out.println("Enter Patient Gender");
        String gender = sc.next();

        String query = "insert into patients(name, age, gender) values(?,?,?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, name);
        ps.setInt(2, age);
        ps.setString(3, gender);

        int affected_row = ps.executeUpdate();
        if (affected_row > 0) {
            System.out.println("Patient Added");
        }
        else {
            System.out.println("Patient Not Added");
        }
    }

    public void view_patient() throws SQLException {
        String query = "select * from patients";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        System.out.println("|----------+------------------+----------+----------|");
        System.out.println("|Id        |Patient Name      |Age       |Gender    |");
        System.out.println("|----------+------------------+----------+----------|");

        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            String gender = rs.getString("gender");

            System.out.printf("| %-9s| %-17s| %-9s| %-9s|\n",id, name, age, gender);
            System.out.println("|----------+------------------+----------+----------|");

        }
    }

    public boolean get_patient_by_id(int id) throws SQLException {
        String query = "select * from patients where id = ?";
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
