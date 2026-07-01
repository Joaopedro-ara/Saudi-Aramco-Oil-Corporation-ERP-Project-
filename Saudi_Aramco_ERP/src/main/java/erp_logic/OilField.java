package erp_logic;
import datenbank.Database;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.math.BigDecimal;

public class OilField {
	private int oil_field_id;
	private String field_code;
	private String field_type;
	private String basin_name;
	private String operation_company;
	private LocalDate discovery_date;
	private LocalDate production_start_date;
	private String field_status;
	private BigDecimal estimated_oil_reserves_barrels;
	private BigDecimal estimated_gas_reserves_mmscf;
	private BigDecimal reservoir_pressure_bar;
	private BigDecimal reservoir_temperature_celsius;
	private String production_license_number;
	private int number_of_platforms;
	private int number_of_active_wells;
	private int estimated_field_lifetime;
	private LocalDate last_reserve_assessment;
	private String notes;
	
	public OilField(String field_code,
			String field_type,
			String basin_name,
			String operation_company,
			LocalDate discovery_date,
			LocalDate production_start_date,
			String field_status,
			BigDecimal estimated_oil_reserves_barrels,
			BigDecimal estimated_gas_reserves_mmscf,
			BigDecimal reservoir_pressure_bar,
			BigDecimal reservoir_temperature_celsius,
			String production_license_number,
			int number_of_platforms,
			int number_of_active_wells,
			int estimated_field_lifetime,
			LocalDate last_reserve_assessment,
			String notes) {
		
		this.field_code=field_code;
		this.field_type=field_type;
		this.basin_name=basin_name;
		this.operation_company=operation_company;
		this.discovery_date=discovery_date;
		this.production_start_date=production_start_date;
		this.field_status=field_status;
		this.estimated_oil_reserves_barrels=estimated_oil_reserves_barrels;
		this.estimated_gas_reserves_mmscf=estimated_gas_reserves_mmscf;
		this.reservoir_pressure_bar=reservoir_pressure_bar;
		this.reservoir_temperature_celsius=reservoir_temperature_celsius;
		this.production_license_number=production_license_number;
		this.number_of_platforms=number_of_platforms;
		this.number_of_active_wells=number_of_active_wells;
		this.estimated_field_lifetime=estimated_field_lifetime;
		this.last_reserve_assessment=last_reserve_assessment;
		this.notes=notes;
		
		
	}
	public void saveOilfield() {
		String sql="Insert into OilField("+
					"field_code,field_type,basin_name,operation_company,discovery_date,production_start_date,field_status,"+
					"estimated_oil_reserves_barrels,estimated_gas_reserves_mmscf,reservoir_pressure_bar,reservoir_temperature_celsius,"+
					"production_license_number,number_of_platforms,number_of_active_wells,estimated_field_lifetime,last_reserve_assessment,"+
					"notes)"+
					"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try(Connection conn = Database.getConnection();
				PreparedStatement stmt=conn.prepareStatement(sql)){
			stmt.setString(1,this.field_code);
			stmt.setString(2,this.field_type);
			stmt.setString(3,this.basin_name);
			stmt.setString(4,this.operation_company);
			stmt.setDate(5,java.sql.Date.valueOf(discovery_date));
			stmt.setDate(6, java.sql.Date.valueOf(production_start_date));
			stmt.setString(7, this.field_status);
			stmt.setBigDecimal(8, this.estimated_oil_reserves_barrels);
			stmt.setBigDecimal(9,this.estimated_gas_reserves_mmscf);
			stmt.setBigDecimal(10,this.reservoir_pressure_bar);
			stmt.setBigDecimal(11,this.reservoir_temperature_celsius);
			stmt.setString(12,this.production_license_number);
			stmt.setInt(13,this.number_of_platforms);
			stmt.setInt(14,this.number_of_active_wells);
			stmt.setInt(15, this.estimated_field_lifetime);
			stmt.setDate(16,java.sql.Date.valueOf(this.last_reserve_assessment));
			stmt.setString(17, this.notes);
			
			stmt.executeUpdate();
			System.out.println("Oilfield successfully saved");
			
		}
		catch(SQLException e) {
			 e.printStackTrace();
		}
	}

}
