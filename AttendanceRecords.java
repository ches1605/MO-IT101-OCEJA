
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class AttendanceRecords { 

    private static CharSequence logIn;
    private static CharSequence logOut;

    public static void attendance(String file) {
      try (BufferedReader reader = new BufferedReader(new FileReader(file))) { // Fixed 'ButteredReader' and 'ButterReader'
             String line = reader.readLine(); 
           
           while ((line = reader.readLine()) != null) {
               String[] row = line.split(",");
               
               if (row.length >= 6) {
                   try {
                       double hoursWorked = calculateHoursWorked(row[4], row[5]);
                       System.out.printf(
                       "|Employee ID: %-8s |Last Name: %-15s |First Name: %-15s |Log In: %-8s |Hours Worked: %-10.2f%n",
                               row[0],
                               row[1],
                               row[2], row[3],
                               row[4],
                               row[5],
                                hoursWorked);
               
                   }
                   catch (Exception e) {
                       System.err.println("Error parsing log in/log out times for row " + line);
                   }
               }
               System.out.println();
            }
       }
                       
        catch (FileNotFoundException e) {
            System.err.println("File not found: " + file);
        
        }   
           catch (IOException e) {
               System.err.println("Erros reading file: " + file);
           }
       }

// Calculate the hours worked between log in and log out times
public static double calculateHoursWorked(String login, String logout){
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
LocalTime logInTime = LocalTime.parse(logIn, formatter); // Fixed variable name
LocalTime logOutTime = LocalTime.parse(logOut, formatter); // Fixed variable name and typo 'ogOut'
long minutesWorked = ChronoUnit.MINUTES.between(logInTime, logOutTime);
return minutesWorked / 60.0;
    }
}
 