package erp_logic;
import datenbank.Database;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Employee {
	private int id;
	private String employerid;
	private String emloeyerpassword;
	private String firstname;
	private String surname;
	private LocalDate dateIfBirth;
	
	private String street;
	private String housenumber;
	private String zipcode;
	private String city;
	private String county;
	private  String country;
	
	private String emergencyContactPhone;
	private boolean euCitizen;
	private boolean WorkPermit;
	private boolean temporyWorkPermit;
	
	private LocalDate dateTemporaryWorkPermit;
	private String tayidentificationNumber;
	private boolean insurancePrivate;
	
	private String companyRole;
	private String departmentId;
	private String companyDevelopment;
	private String workCountry;
	private String currentAssetId;
	private String rotationSchedule;
	private String SalaryCurrency;
	 
	public Employee( String employerId,
            String employerPassword,
            String firstName,
            String surname,
            LocalDate dateOfBirth,
            String street,
            String houseNumber,
            String zipcode,
            String city,
            String county,
            String country,
            String emergencyContactPhone,
            boolean euCitizen,
            boolean workPermit,
            boolean temporaryWorkPermit,
            LocalDate dateTemporaryWorkPermit,
            String taxIdentificationNumber,
            boolean insurancePrivate,
            String companyRole,
            String departmentId,
            String companyDevelopment,
            String workCountry,
            String currentAssetId,
            String rotationSchedule,
            String salaryCurrency) {
		
		
		this.employerid=employerId;
		this.emloeyerpassword=employerPassword;
		this.firstname=firstName;
		this.surname=surname;
		this.dateIfBirth=dateOfBirth;
		this.street=street;
		this.housenumber=houseNumber;
		this.zipcode=zipcode;
		this.city=city;
		this.county=county;
		this.country=country;
		this.emergencyContactPhone=emergencyContactPhone;
		this.euCitizen=euCitizen;
		this.WorkPermit=workPermit;
		this.temporyWorkPermit=temporaryWorkPermit;
		this.dateTemporaryWorkPermit=dateTemporaryWorkPermit;
		this.tayidentificationNumber=taxIdentificationNumber;
		this.insurancePrivate=insurancePrivate;
		this.companyRole=companyRole;
		this.departmentId=departmentId;
		this.companyDevelopment=companyDevelopment;
		this.workCountry=workCountry;
		this.currentAssetId=currentAssetId;
		this.rotationSchedule=rotationSchedule;
		this.SalaryCurrency=salaryCurrency;
		
	}
	public Employee(String firstname,String surname, String companyRolle,String departmentId ) {
		this.firstname=firstname;
		this.surname=surname;
		this.companyRole=companyRolle;
		this.departmentId=departmentId;
		
	}
	public String getFirstname() {
		return this.firstname;		
	}
	public String getSurname() {
		return this.surname;
	}
	public String getcompanyRolle() {
		return this.companyRole;
	}
	public String getDepartmentId() {
		return this.departmentId;
	}
	public void saveEmpolye() {
		String sql="Insert into Employees_Oil("+
				    "employer_id,employer_password,first_name, surname, date_of_birth,street,house_number,zipcode,city,county,country,"+
				    "emergency_contact_phone,eu_citizen,work_permit,temporary_work_permit,date_temporary_work_permit,tax_identification_number, "+
				    "insurance_private,company_role,department_id,company_development,work_country,current_asset_id,rotation_schedule, "+
				    "salary_currency) "+
				    " Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try(Connection conn = Database.getConnection();
				PreparedStatement stmt=conn.prepareStatement(sql)){
			String hashedPassword=BCrypt.hashpw(this.emloeyerpassword,BCrypt.gensalt());
			stmt.setString(1,this.employerid);
			stmt.setBytes(2, hashedPassword.getBytes());
			stmt.setString(3,this.firstname);
			stmt.setString(4,this.surname);
			stmt.setDate(5,java.sql.Date.valueOf(this.dateIfBirth));
			stmt.setString(6,this.street);
			stmt.setString(7,this.housenumber);
			stmt.setString(8,this.zipcode);
			stmt.setString(9,this.city);
			stmt.setString(10,this.county);
			stmt.setString(11,this.country);
			stmt.setString(12,this.emergencyContactPhone);
			stmt.setBoolean(13, this.euCitizen);
			stmt.setBoolean(14, this.WorkPermit);
			stmt.setBoolean(15, this.temporyWorkPermit);
			if(this.dateTemporaryWorkPermit!=null) {
				stmt.setDate(16, java.sql.Date.valueOf(this.dateTemporaryWorkPermit));
				
			}else {
				stmt.setNull(16, java.sql.Types.DATE);
			}
			
			stmt.setString(17,this.tayidentificationNumber);
			stmt.setBoolean(18, this.insurancePrivate);
			stmt.setString(19,this.companyRole);
			stmt.setString(20,this.departmentId);
			stmt.setString(21,this.companyDevelopment);
			stmt.setString(22,this.workCountry);
			stmt.setString(23,this.currentAssetId);
			stmt.setString(24,this.rotationSchedule);
			stmt.setString(25,this.SalaryCurrency);
			
			stmt.executeUpdate();
			System.out.println("Employee successfully saved");
			
			
		}catch(Exception e) {
			 e.printStackTrace();
		}
	}
	public static boolean loginCheck(String id,String password) {
		// SQL query to get the hashed password for the given employee ID
		String sql="Select employer_password from Employees_Oil where employer_id=?";
		//Open database connection and prepare SQL statement
		try(Connection conn = Database.getConnection();
				PreparedStatement stmt=conn.prepareStatement(sql)){
			// set Employer Id as the query parameter
			stmt.setString(1, id);
			// Execute the query
			ResultSet rs=stmt.executeQuery();
			// Check if the employee exists
			if(rs.next()) {
				 // Get the stored password hash from the database
				String dbHash=rs.getString("employer_password");

	            // Compare entered password with the stored hash
				return BCrypt.checkpw(password, dbHash);
			}
			// Employee not found
			return false;
			
		}catch(SQLException e) {
			e.printStackTrace();
			// Login failed due to database error
			return false;
		}
		
	}
	public static Employee getDashboardData(String id) {
		// new method to put Comapny rolle,department id a first and surname in inital dashboard
		// SQL query to get firstName,Surname,comapny role,departmentid
		String sql="Select first_name,surname,company_role,department_id FROM Employees_Oil Where employer_id=?";
		try(Connection conn = Database.getConnection();
				PreparedStatement stmt=conn.prepareStatement(sql)){
			stmt.setString(1,id);
			ResultSet rs=stmt.executeQuery();
			
			if(rs.next()) {
				String f_name=rs.getString("first_name");
				String s_name=rs.getString("surname");
				String c_role=rs.getString("company_role");
				String d_id=rs.getString("department_id");
				
				return new Employee(f_name,s_name,c_role,d_id);
			}
			// Employee not found
			return null;
			
			
			}catch(SQLException e) {
				e.printStackTrace();
				return null;
			
			}
		
		}
		
		
	



}
