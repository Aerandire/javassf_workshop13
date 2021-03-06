package sg.edu.nus.iss.workshop13.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.nio.charset.*;
import java.nio.file.Path;
import java.nio.file.Files;

import org.springframework.boot.ApplicationArguments;
import org.springframework.ui.Model;

import sg.edu.nus.iss.workshop13.model.Contact;

public class Contacts {
        private Logger logger = Logger.getLogger(Contacts.class.getName());

        public void saveContacts(Contact contact, Model model, 
            ApplicationArguments applicationArguments){
                
                String dataFilename = contact.getId();
                Set<String> optNames = applicationArguments.getOptionNames();
                String[]optnamesArr = optNames.toArray(new String[optNames.size()]);
                List<String> optValues = applicationArguments.getOptionValues(new String(optnamesArr[0]));
                String [] optValuesArr = optValues.toArray(new String[optValues.size()]);
                PrintWriter printWriter = null;

                try{
                        FileWriter fileWriter = new FileWriter(optValuesArr[0] + "/" + dataFilename,
                        Charset.forName("UTF-8"));

                        printWriter = new PrintWriter(fileWriter);
                        printWriter.println(contact.getName());
                        printWriter.println(contact.getEmail());
                        printWriter.println(contact.getPhoneNumber());
                        
                        fileWriter.close();
                }catch(IOException e){
                    logger.log(Level.WARNING, e.getMessage());
                }finally{
                    printWriter.close();         
                }

            //Add attribute
            model.addAttribute("contact", new Contact(contact.getId(), contact.getName(), 
                                contact.getEmail(),
                                contact.getPhoneNumber()));


        }

        public void getContactById(Model model, 
                String contactId, ApplicationArguments applicationArguments){
                    Set<String> optNames = applicationArguments.getOptionNames();
                    String[]optnamesArr = optNames.toArray(new String[optNames.size()]);
                    List<String> optValues = applicationArguments.getOptionValues(optnamesArr[0]);
                    String [] optValuesArr = optValues.toArray(new String[optValues.size()]);

                    Contact cResp = new Contact();

                    try{
                        Path filePath = new File(optValuesArr[0] + "/" + contactId).toPath();
                        Charset charset = Charset.forName("UTF-8");
                        List<String> stringListVal = Files.readAllLines(filePath, charset);
                        cResp.setName(stringListVal.get(0));
                        cResp.setEmail(stringListVal.get(1));
                        cResp.setPhoneNumber(stringListVal.get(2));
                        cResp.setId(contactId);
                    }catch(IOException e){
                        logger.log(Level.WARNING, e.getMessage());
                    }
                    model.addAttribute("contact", cResp);
        }
}
