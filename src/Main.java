import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

//import java.nio.file.Path;
//import java.time.LocalDate;
//
//public class Main {
//    public static void main(String[] args) {
//        try {
//            Path path = Paths.get("dolphinsBorn.txt");
//             String text = new String(Files.readAllBytes(path));
//             LocalDate date = LocalDate.parse(text);
//            System.out.println(date);
//             } catch (DateTimeParseException e) {
//             e.printStackTrace();
//             throw new RuntimeException(e);
//             } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//        }
//
//}
//// better approach multi catch
//public static void main(String[] args) {
//    try {
//        Path path = Paths.get("dolphinsBorn.txt");
//        String text = new String(Files.readAllBytes(path));
//        LocalDate date = LocalDate.parse(text);
//        System.out.println(date);
//    } catch (DateTimeParseException e) {
//        handleException(e);
//    } catch (IOException e) {
//        handleException(e);
//    }
//}
//private static void handleException(Exception e) {
//    e.printStackTrace();
//    throw new RuntimeException(e);
//}
//// best approach multicatch
//public static void main(String[] args) {
//    try {
//        Path path = Paths.get("dolphinsBorn.txt");
//        String text = new String(Files.readAllBytes(path));
//        LocalDate date = LocalDate.parse(text);
//        System.out.println(date);
//    } catch (DateTimeParseException | IOException e) {
//        e.printStackTrace();
//        throw new RuntimeException(e);
//    } }
//catch(Exception1 e | Exception2 e | Exception3 e) // DOES NOT COMPILE
//        catch(Exception1 e1 | Exception2 e2 | Exception3 e3) // DOES NOT COMPILE
//        catch(Exception1 | Exception2 | Exception3 e)
//// redundant
//        try {
//        throw new IOException();
//} catch (FileNotFoundException | IOException e) { } // DOES NOT COMPILE
//FileNotFoundException is a subclass of IOException. Specifying it in the multi-catch is
//redundant, and the compiler gives a message such as this:
//The exception FileNotFoundException is already caught by the alternative IOException
public class Main {
     public void oldApproach(Path path1, Path path2) throws IOException {
        BufferedReader in = null;
         BufferedWriter out = null;
         try {
             in = Files.newBufferedReader(path1);
             out = Files.newBufferedWriter(path2);
             out.write(in.readLine());
//             } finally {
//             if (in != null) in.close();// what if this line throws exceptions it may lead to data leakage in the outputline
//             //which means we need tohandle this in better approach
//            if (out != null) out.close();
//            }
         }finally {
             try{
                 in.close();

             }catch (IOException e){}
             try {
                 out.close();
             }catch (IOException e){}
         }
         }
         public void newApproach(Path path1,Path path2) throws IOException{
         try(BufferedReader in=Files.newBufferedReader(path1);
         BufferedWriter out=Files.newBufferedWriter(path2)) {
         out.write(in.readLine());// write in out path2 what you will find in in the path1
             //no need to close it as java will handle
         }
         }
    public class TurkeyCage implements AutoCloseable {
 public void close() {
             System.out.println("Close gate");
             }
public static void main(String[] args) {
           try (TurkeyCage t = new TurkeyCage()) {
                System.out.println("put turkeys in");
                 }
             }
 }
    public static void main(String[] args) {

    }
}
//suppressed autocloable in case the close throws an exception
//Assertions Syntax
assert boolean_expression;
 assert boolean_expression: error_message;
 // here you're not the throwing the error developers shouldn't java does so
if (!boolean_expression) throw new AssertionError();
// other example of hw to use it
 public class Assertions {
public static void main(String[] args) {
        int numGuests = 5;
         assert numGuests > 0;
      System.out.println(numGuests);
        }
}
// assertions enabled
java -ea:com.wiley.demos... my.programs.Main
//on specific classes
java -ea:com.wiley.demos.TestColors my.programs.Main
// some packages or classes disabled
java -ea:com.wiley.demos... -da:com.wiley.demos.TestColors my.programs.Main
// control flow assertions examble
 public class TestSeasons {
  public static void test(Seasons s) {
         switch (s) {
             case SPRING:
                case FALL:
                 System.out.println("Shorter hours");
                 break;
            case SUMMER:
                 System.out.println("Longer hours");
                 break;
             default:
                assert false: "Invalid season";
                }}}
// assertions aren't for changing statement
int x = 10;
assert ++x > 10; // Not a good design!
