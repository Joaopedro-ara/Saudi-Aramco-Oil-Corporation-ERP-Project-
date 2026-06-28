package datenbank;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;


public class Database {
    private static final String URL =
            "jdbc:mysql://localhost:3306/saudi_aramaco";

    private static final String USER = "root";

    private static final String PASSWORD =
            System.getenv("DB_PASSWORD");

    public static Connection getConnection()
            throws SQLException {

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD);
    }
	public static void main(String[] args) {
	
// Database Connection
	String url = "jdbc:mysql://localhost:3306/saudi_aramaco";
    String user = "root";
    String password = System.getenv("DB_PASSWORD"); 

    System.out.println("=== Saudi Aramco Oil Corporation Database. ===");
    try(Connection conn=DriverManager.getConnection(url,user,password);
    		Statement sa=conn.createStatement()){
    	// First Table: Employees
    	String createUser= "Create table if not Exists Employees_Oil("
    			+ "id Int AUTO_INCREMENT PRIMARY KEY, "
    			+"employer_id Varchar(250) Unique not null,"
    			+"employer_password Blob not null, "
    			+"first_name Varchar(100)  not null,"
    			+"surname Varchar(100)  not null,"
    			+"date_of_birth Date not null,"
    			+"street Varchar(50) not null,"
    			+"house_number Varchar(50) not null,"
    			+"zipcode Varchar (50) not null,"
    			+"city text not null,"
    			+"county text not null," // Staate Region
    			+"country text not null,"
    			+"emergency_contact_phone Text not null, "
    			+"eu_citizen boolean,"
    			+"work_permit boolean,"
    			+"temporary_work_permit boolean,"
    			+"date_temporary_work_permit Date ," // Work permit expiration date
    			+"tax_identification_number text,"
    			+"insurance_private boolean,"
    			+"company_role Varchar(50) not null,"
    			+"department_id Varchar(200) not null,"
    			+"company_development Text not null,"
    			+"work_country text not null,"
    			+"current_asset_id text not null,"
    			+"rotation_schedule text not null,"
    			+"salary_currency text"
    			+")";
    	sa.executeUpdate(createUser);
    	System.out.println("✅ Table 'Employers_Oil'  created successfully..");	
    	// creatuing database for oil_fields
    	String oilField="Create table if not exists OilField("
    			+"oil_field_id  Int AUTO_INCREMENT PRIMARY KEY, "
    			+"field_code Varchar(50) Unique,"
    			+"field_type Varchar(50)," //type of field(Gas,oil)
    			+"basin_name Varchar(100)," // name of the underground storage location
    			+"operation_company Varchar(100),"
    			+"discovery_date Date," // The date of oil field discovery
    			+"production_start_date Date,"
    			+"field_status Varchar(50)," //Current status (Exploration, Development, Producing, Suspended, Abandoned)
    			+"estimated_oil_reserves_barrels Decimal(40,2),"
    			+"estimated_gas_reserves_mmscf Decimal(40,2)," // cubikmeter
    			+"reservoir_pressure_bar Decimal(6,2)," // pessoure in der Underground oilfiel bar
    			+"reservoir_temperature_celsius Decimal(6,2),"
    			+"production_license_number Varchar(100),"
    			+"number_of_platforms Int," // Number of oil Plaforms in this field
    			+"number_of_active_wells Int,"
    			+"estimated_field_lifetime Int," // Estimated remaining field life (years)
    			+"last_reserve_assessment Date," // the Date of the last estimaded 
    			+"notes Text"
    			+")"; //19
    	sa.executeUpdate(oilField);
    	System.out.println("✅ Table 'OilField'  created successfully..");	
    	// oil Platform
    	
    	String oilPlatform="Create table if not exists oilPlatform("
    			+"platform_id Int AUTO_INCREMENT PRIMARY KEY,"
    			+"platform_code Varchar(50),"
    			+"platform_name Varchar(50),"
    			+"platform_type Varchar(50), " // Type of polatform(fixed platformm Jack-up Rig, Semi-submersile,Drilship etc)
    			+"oil_field_id Int,"
    			+"operating_company Varchar(50),"
    			+"country Varchar(50)," // in wchich countrx the platform is located
    			+"offshore_region Varchar(100)," // In which region is the offshore platform located
    			+"water_depth Decimal(8,2),"
    			+"installation_date Date," // On which date was this platform installed
    			+"commissioning_date Date," // Which day startet the production
    			+"operational_status Varchar(50),"
    			+"production_capacity_barel Decimal(40,2)," //max prodcution capacity in one day in barel
    			+"current_production_barel Decimal(40,2),"
    			+"gas_production_now Decimal(12,2),"
    			+"helipad_available boolean,"
    			+"accommodation_capacity int," // max workers that can live and work on this platform
    			+"last_major_inspection Date,"
    			+" next_major_inspection Date,"
    			+"lifeboat_capacity Int,"
    			+"picture BLOB,"
    			+"FOREIGN KEY (oil_field_id) REFERENCES OilField(oil_field_id)"
    			+")";
    	sa.executeUpdate(oilPlatform);
    	System.out.println("✅ Table 'OilPlatform'  created successfully..");
    			
    			
    			
    	
    }catch (SQLException e) {
        // Fehler abfangenl
        System.out.println("❌ SYSTEM FAILURE: Unable to create table. " + e.getMessage());
    }
 }
}