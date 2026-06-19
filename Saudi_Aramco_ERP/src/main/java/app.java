import datenbank.Database;
import io.javalin.Javalin;
import io.javalin.http.Context;
import erp_logic.Employee;
import erp_routes1.Employee_routes1;

import java.nio.file.Files;
import java.nio.file.Path;
public class app {
	public static void main(String[] args) {
		Javalin app=Javalin.create().start(8080);
		Employee_routes1.register(app);
		System.out.println("Saudi Oil ERP Server is running on Port 8080...");
		
		
	}

}
