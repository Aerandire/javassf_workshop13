package sg.edu.nus.iss.workshop13;

import java.util.List;

import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static sg.edu.nus.iss.workshop13.util.IOUtil.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class Workshop13Application {
	private static final Logger logger = Logger.getLogger(Workshop13Application.class.getName());
	
	public static final String DATA_DIR = "dataDir";

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Workshop13Application.class);

		DefaultApplicationArguments appArgs = 
				new DefaultApplicationArguments(args);

		List<String> optVals = appArgs.getOptionValues(DATA_DIR);
		if(optVals == null){
			logger.log(Level.WARNING, "Data directory needed!");
			System.exit(1);
		}
		else if(optVals != null){
			createDir((String)optVals.get(0));
		}

		app.run(args);
	}

}
