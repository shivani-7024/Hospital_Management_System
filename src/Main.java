import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String url = "jdbc:mysql://localhost:3306/Hospital";
    private static final String user = "your username";
    private static final String password = "your password";

    static Connection conn = null;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Connecting to database...");

        conn = DriverManager.getConnection(url, user, password);
        System.out.println("Connected!");

        Scanner sc = new Scanner(System.in);

        Patients patients = new Patients(conn,sc);
        Doctors doctors = new Doctors(conn);

        while(true){
            System.out.println("HOSPITAL MANAGEMENT SYSTEM");
            System.out.println("1. Add Patient");
            System.out.println("2. View Patient");
            System.out.println("3. View Doctor");
            System.out.println("4. Book Appointment");
            System.out.println("5. Exit");
            System.out.println("Enter your choice: ");
            int choice = sc.nextInt();
            switch(choice){
                case 1:
                    patients.add_patient();
                    System.out.println();
                    break;
                case 2:
                    patients.view_patient();
                    System.out.println();
                    break;

                case 3:
                    doctors.view_doctor();
                    System.out.println();
                    break;
                case 4:
                    book_appointment(patients, doctors,conn,sc);
                    System.out.println();
                    break;
                case 5:
                    System.out.println("THANK YOU! FOR USING HOSPITAL MANAGEMENT SYSTEM!!");
                    return;
                default:
                    System.out.println("Invalid choice!");

            }
        }

    }
    public static void book_appointment(Patients patients, Doctors doctors,  Connection conn, Scanner sc) throws SQLException {
        System.out.println("Enter Patient ID: ");
        int patient_id = sc.nextInt();
        System.out.println("Enter Doctor ID: ");
        int doctor_id = sc.nextInt();
        System.out.println("Enter Appointment Date (YYYY-MM-DD): ");
        String appointment_date = sc.next();

        if(patients.get_patient_by_id(patient_id) && doctors.get_doctor_by_id(doctor_id)){

            if(check_dortor_avilability(doctor_id, appointment_date, conn)){
                String sql = "INSERT INTO Appointments(doc_id, patient_id, date)  VALUES (?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, doctor_id);
                ps.setInt(2, patient_id);
                ps.setString(3, appointment_date);
                int affected = ps.executeUpdate();
                if(affected > 0){
                    System.out.println("Appointment Booked Successfully");
                }
                else{
                    System.out.println("Appointment Booking Failed");
                }
            }
            else {
                System.out.println("Doctor is not available on this Date");
            }
        }
        else{
            System.out.println("Either Doctor or Patient does not exist!!!");
        }

    }

    private static boolean check_dortor_avilability(int doctorId, String appointmentDate, Connection conn) throws SQLException {

        String sql = "SELECT COUNT(*) FROM Appointments WHERE doc_id=? AND date=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, doctorId);
        ps.setString(2, appointmentDate);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int count = rs.getInt(1);
            return count == 0;
        }
        return false;
    }

}