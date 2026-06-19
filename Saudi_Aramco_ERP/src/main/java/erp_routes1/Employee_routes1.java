package erp_routes1;
import io.javalin.Javalin;
import io.javalin.http.Context;
import erp_logic.Employee;

import java.nio.file.Files;
import java.time.LocalDate;
import java.nio.file.Path;


public class Employee_routes1 {
	public static void register(Javalin app) {
		app.get("/register", ctx->{
			String html=  Files.readString(Path.of("src/main/resources/first_templates/Registration.html"));
			ctx.html(html);

			
		});
		app.post("/register", ctx ->{
			String empId=ctx.formParam("employer_id");
			String password=ctx.formParam("password");
			String firstname=ctx.formParam("first_name");
			String lastname=ctx.formParam("sur_name");
			LocalDate dob= LocalDate.parse(ctx.formParam("date_birth"));
			String street=ctx.formParam("street");
			String house_num=ctx.formParam("house_number");
			String zipcode=ctx.formParam("zipcode");
			String city=ctx.formParam("city");
			String county=ctx.formParam("county");
			String country=ctx.formParam("country");
			String emergency_contact=ctx.formParam("emergency_contact_phone");
			boolean isEucitizen=ctx.formParam("eu_citizen") !=null;
			boolean workpermisson=ctx.formParam("work_permit") !=null;
			boolean workpermisson_tempory=ctx.formParam("temporary_work_permit") !=null;
			//herewhere we safe the exoerie work data
			// in the begginging we dont have datum
			LocalDate wep=null;
			// No Eu citizen and no permananet work permit
			//-> temporary_work_permit
			if(!isEucitizen) {
				// Check whether neither a permanent nor a temporary work permit exists.
			    // If so, the registration must not proceed.
				if(!workpermisson && !workpermisson_tempory) {
					ctx.result("Not eu citizien need an work permissen or tempory work permison");
					return;
				}
				// Does the user have a temporary work permit?
				if(workpermisson_tempory) {
					// Read the entered date from the form.
					String tempDate=ctx.formParam("date_temporary_work_permit");
					// Check if the date field is empty.
					if(tempDate==null || tempDate.isBlank()) {
						ctx.result("Plesae put the expire date to the tempory work permits.");
						return; // cancel Registration
						
					}
					// Sting in tranfer in Data
					wep=LocalDate.parse(tempDate);
				}
			}
			String tax_id=ctx.formParam("tax_identification_number");
			boolean unsure_priv=ctx.formParam("insurance_private") !=null;
			String company_rol=ctx.formParam("company_role");
			String department_id=ctx.formParam("department_id");
			String company_dev=ctx.formParam("company_development");
			String work_county=ctx.formParam("work_country");
			String current_asset=ctx.formParam("current_asset_id");
			String rotation_schedule=ctx.formParam("rotation_schedule");
			String salary_currency=ctx.formParam("salary_currency");
			
			
			Employee emp= new Employee(empId,password,firstname,lastname,dob,street,house_num,zipcode,city,
					county,country,emergency_contact,isEucitizen,workpermisson,workpermisson_tempory,wep,
					tax_id,unsure_priv,company_rol,department_id,company_dev,work_county,
					current_asset,rotation_schedule,salary_currency);
			// save in the database
			emp.saveEmpolye();
			// 
			ctx.html("<h1>Registration Successful</h1><p>Employee has been saved to Saudi Oil ERP.</p>");
			
			
		});
		
	}

}
