import org.fluttercode.datafactory.impl.DataFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Random;

public class CSV {
    public static void main(String[] argv) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the amount of records to generate: ");
        int userChoice = 0;
        while(userChoice == 0){
            if(!input.hasNextInt()){
                System.out.print("That is not a valid input, please try again: ");
                input.nextLine();
            }
            else userChoice = input.nextInt();
        }

        PrintWriter pw = new PrintWriter(new File("test.csv"));
        StringBuilder sb = new StringBuilder();

        DataFactory df = new DataFactory();
        Random rand = new Random();

        String[] departments = {"Sales", "Marketing", "Finance", "IT", "Testing", "Design", "Assembly", "Administrative"};
        String[] positions = {"Manager", "Supervisor", "Chief", "Executive Manager",};

        for(int i = 0; i < userChoice; ++i) {
            sb.append(df.getFirstName()); sb.append(',');
            sb.append(df.getLastName()); sb.append(',');
            sb.append(rand.nextInt(60) + 18); sb.append(',');
            sb.append(df.getEmailAddress()); sb.append(',');
            sb.append(df.getNumberText(10)); sb.append(',');
            sb.append(df.getItem(departments,100, "Janitorial")); sb.append(',');
            sb.append(df.getItem(positions, 20, "Employee")); sb.append('\n');

            pw.write(sb.toString());
            sb.setLength(0);
        }
        pw.close();
        System.out.println("Data records have been generated.");
    }
}