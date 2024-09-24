### Hospital Management System (Java + MySQL)

This is a simple Hospital Management System built using Java with a MySQL database. The application allows users to manage patients, doctors, and appointments. Below are the key features, a brief overview of the structure, and setup instructions.

#### Features:
1. **Add Patient**: Input and store patient details in the MySQL database.
2. **View Patient**: Retrieve and display patient information from the database.
3. **View Doctor**: Retrieve and display doctor information.
4. **Book Appointment**: Schedule appointments between patients and doctors based on availability.
5. **Exit**: Terminate the application.

#### Core Classes:
- **`Main`**: Contains the main application loop and user interface for navigating through the different options.
- **`Patients` & `Doctors`**: Handles patient and doctor operations such as adding and viewing records.
- **`book_appointment`**: Function that allows users to book an appointment for a patient with a doctor on a specific date if both exist in the database and the doctor is available.

#### Database Interaction:
- **MySQL JDBC Connection**: The application connects to a MySQL database using JDBC. It uses SQL queries to interact with the database, inserting and retrieving information for patients, doctors, and appointments.
- **Appointment Availability Check**: Before booking an appointment, the system checks whether the doctor is available on the specified date.

---

### Setup

1. Clone this repository to your local machine:

   ```sh
   git clone https://github.com/shivani-7024/Hospital_Management_System.git
   ```

2. Configure your MySQL database settings in the `Main.java` file:

   ```java
   private static final String url = "jdbc:mysql://localhost:3306/Hospital";
   private static final String user = "your_username";
   private static final String password = "your_password";
   ```

3. Create a MySQL database named `Hospital` and ensure you have the necessary tables (`Patients`, `Doctors`, `Appointments`).

4. Compile and run the application:

   ```sh
   javac Main.java
   java Main
   ```

5. Follow the on-screen menu options to add/view patients, view doctors, and book appointments.

---

This description provides an overview of the system, its features, setup steps, and instructions for running the code.

