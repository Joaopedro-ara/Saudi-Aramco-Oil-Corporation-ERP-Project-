package datenbank;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;


public class Database {
	public static void main(String[] args) {
	
// Database Connection
	String url = "jdbc:mysql://localhost:3306/saudi_aramaco";
    String user = "root";
    String password = System.getenv("DB_PASSWORD"); 

    System.out.println("=== Saudi Aramco Oil Corporation Database. ===");
    try(Connection conn=DriverManager.getConnection(url,user,password);
    		Statement sa=conn.createStatement()){
    	// First Table: Employers
    	String createUser= "Create table if not Exists Employers_Oil("
    			+ "id Int AUTO_INCREMENT PRIMARY KEY, "
    			+"employer_id Varchar(250) Unique not null,"
    			+"employer_password Blob not null, "
    			+"first_name Varchar(100) Unique not null,"
    			+"surname Varchar(100) Unique not null,"
    			+"date_of_birth Date not null,"
    			+"street text not null,"
    			+"house_number text not null,"
    			+"zipcode text not null,"
    			+"city text not null,"
    			+"county text not null," // Staate Region
    			+"country text not null,"
    			+"emergency_contact_phone Text not null, "
    			+"eu_citizen boolean,"
    			+"work_permit boolean,"
    			+"temporary_work_permit boolean,"
    			+"date_temporary_work_permit Date not null," // Work permit expiration date
    			+"tax_identification_number text,"
    			+"insurance_private boolean,"
    			+"company_role Text not null,"
    			+"department_id Text not null,"
    			+"company_development Text not null,"
    			+"work_country text not null,"
    			+"current_asset_id text not null,"
    			+"rotation_schedule text not null,"
    			+"salary_currency text"
    			+")";
    	sa.executeUpdate(createUser);
    	System.out.println("✅ Table 'Employers_Oil'  created successfully..");	
    	
    }catch (SQLException e) {
        // Fehler abfangen
        System.out.println("❌ SYSTEM FAILURE: Unable to create table. " + e.getMessage());
    }
 }
}