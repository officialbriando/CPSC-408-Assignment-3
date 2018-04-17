import org.apache.commons.csv.*;
import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class DataImport {

    public static void main(String[] argv){

        Connection con = null;

        try {
            con = DBConfig.getMySqlConnection();
            if(con.isClosed()) {
                System.out.println("Connection was closed; creating new connection to MySQL.");
                con = DBConfig.getMySqlConnection();
            }

            Scanner input = new Scanner(System.in);
            System.out.println("Enter the csv file path to import data from: ");
            String filepath = input.nextLine();

            File f = new File(filepath);
            while(!f.exists()) {
                System.out.println("File does not exist; enter the file path again: ");
                filepath = input.nextLine();
            }

            Reader in = new FileReader(filepath);
            Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
            for(CSVRecord record : records)
            {
                String firstname = record.get(0);
                String lastname = record.get(1);
                int age = Integer.parseInt(record.get(2));
                String email = record.get(3);
                String phone = record.get(4);
                String dept = record.get(5);
                String position = record.get(6);

                int c_id = -1;

                c_id = importUserInfo(con, firstname, lastname, age, c_id);
                System.out.println(c_id);

                if(c_id != -1) {
                    importUserEmail(con, email, c_id);
                    importUserPhone(con, phone, c_id);
                    importUserDept(con, dept, c_id);
                    importUserPosition(con, position, c_id);
                }
                else{
                    System.out.println("Data import unsuccessful, exiting program.");
                    break;
                }
            }
        }
        catch(Exception ex) {ex.printStackTrace(); }
        finally { try { if(con != null) con.close(); } catch(Exception ex) { ex.printStackTrace(); }
        }
    }

    private static int importUserInfo(Connection con, String firstname, String lastname, int age, int c_id)
    {
        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO userinfo(firstname, lastname, age) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pst.clearParameters();
            pst.setString(1, firstname);
            pst.setString(2, lastname);
            pst.setInt(3, age);
            pst.executeUpdate();

            ResultSet rs_key = pst.getGeneratedKeys();
            if(rs_key.next()){
                c_id = rs_key.getInt(1);
            }
            return c_id;
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        finally{
            if(c_id != -1){
                return c_id;
            }
            else{
                return -1;
            }
        }
    }

    private static void importUserEmail(Connection con, String email, int c_id)
    {
        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO useremail(ID, email) VALUES (?,?)");
            pst.clearParameters();
            pst.setInt(1, c_id);
            pst.setString(2, email);
            pst.executeUpdate();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void importUserPhone(Connection con, String phone, int c_id) {
        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO userphone(ID, phone) VALUES (?,?)");
            pst.clearParameters();
            pst.setInt(1, c_id);
            pst.setString(2, phone);
            pst.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void importUserDept(Connection con, String dept, int c_id) {
        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO userdepartment(ID, department) VALUES (?,?)");
            pst.clearParameters();
            pst.setInt(1, c_id);
            pst.setString(2, dept);
            pst.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void importUserPosition(Connection con, String position, int c_id) {
        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO userposition(ID, position) VALUES (?,?)");
            pst.clearParameters();
            pst.setInt(1, c_id);
            pst.setString(2, position);
            pst.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
