import java.util.*;

public class Operators {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("? ");
    try {
      String pattern1 = "\\d+|\\d+/\\d*[1-9]\\d*|\\d+_\\d+/\\d*[1-9]\\d*";
      String pattern2 = "\\d*[1-9]\\d*|\\d*[1-9]\\d*/\\d*[1-9]\\d*|\\d+_\\d*[1-9]\\d*/\\d*[1-9]\\d*|\\d*[1-9]\\d*_\\d+/\\d*[1-9]\\d*";
      String num1 = scanner.next(pattern1);
      String opr = scanner.next("\\*|\\/|\\+|\\-");
      String num2 = !opr.equals("/") ? scanner.next(pattern1) : scanner.next(pattern2);
      String enterKey = scanner.nextLine();
      scanner.close();
      if (!enterKey.isBlank()) {
        throw new InputMismatchException();
      }
      Integer[] result = new Operators().calculateOperation(num1, opr, num2);
      Integer w3 = result[0];
      Integer n3 = result[1];
      Integer d3 = result[2];
      Integer sign = result[3];
      //System.out.println(result[0] + "_" + result[1] + "/" + result[2]);
      String output = 
      (sign > 0 ? "" : "-") + (w3 == 0 ? "" : (Integer.toString(w3) + (n3 == 0 ? "" : "_"))) + 
        (n3 == 0 ? "" : (Integer.toString(n3) + (d3 == 1 ? "" : "/" + Integer.toString(d3))));
      System.out.println("= " + output);
    } catch(InputMismatchException|IllegalStateException e) {
      System.out.println("The entered operation is in wrong format.");
    } catch(NumberFormatException e) {
      System.out.println("The entered number is too large.");
    } catch(ArithmeticException e) {
      System.out.println("The entered numbers result is too large for calculation.");
    }
  }
  
  public Integer[] calculateOperation(String num1, String opr, String num2) {
      String[] whl1 = num1.contains("_") ? num1.split("_") : new String[]{"0", num1};
      String[] frc1 = num1.contains("/") ? whl1[1].split("/") : new String[]{whl1[1], "1"};
      Integer n1 = Math.addExact(Math.multiplyExact(Integer.parseInt(whl1[0]),Integer.parseInt(frc1[1])),
          Integer.parseInt(frc1[0]));
      Integer d1 = Integer.parseInt(frc1[1]);
      String[] whl2 = num2.contains("_") ? num2.split("_") : new String[]{"0", num2};
      String[] frc2 = num2.contains("/") ? whl2[1].split("/") : new String[]{whl2[1], "1"};
      Integer n2 = Math.addExact(Math.multiplyExact(Integer.parseInt(whl2[0]),Integer.parseInt(frc2[1])),
          Integer.parseInt(frc2[0]));
      Integer d2 = Integer.parseInt(frc2[1]);
      //System.out.println("num1 " + Arrays.toString(whl1) + " " + Arrays.toString(frc1) + " - " + n1 + "/" + d1);
      //System.out.println("operator " + opr);
      //System.out.println("num2 " + Arrays.toString(whl2) + " " + Arrays.toString(frc2) + " - " + n2 + "/" + d2);
      Integer w3 = 0;
      Integer n3 = 1;
      Integer d3 = 1;
      if (opr.equals("*")) {
        n3 = Math.multiplyExact(n1,n2);
        d3 = Math.multiplyExact(d1,d2);
      }
      else if (opr.equals("/")) {
        n3 = Math.multiplyExact(n1,d2);
        d3 = Math.multiplyExact(n2,d1);
      }
      else if (opr.equals("+") || opr.equals("-")) {
        d3 = Math.multiplyExact(d1,d2)/GCD(d1,d2);
        n3 = Math.addExact(Math.multiplyExact((d3/d1),n1),
          Math.multiplyExact((opr.equals("+") ? 1 : -1), 
          Math.multiplyExact((d3/d2),n2)));
      }
      //System.out.println(w3 + "_" + n3 + "/" + d3);
      Integer sign = n3 >= 0 ? 1 : -1;
      n3 = Math.abs(n3);
      if (n3 > d3) {
        w3 = Math.floorDiv(n3,d3);
        n3 = Math.subtractExact(n3,Math.multiplyExact(w3,d3));
      }
      Integer gcd = GCD(n3,d3);
      if (gcd != 1) {
        n3 = Math.floorDiv(n3,gcd);
        d3 = Math.floorDiv(d3,gcd);
      }
      //System.out.println(w3 + "_" + n3 + "/" + d3);
      return new Integer[]{w3, n3, d3, sign};
  }

  public static Integer GCD(Integer a, Integer b) {
      if (b == 0) 
        return a;
      else
        return GCD(b, Math.floorMod(a,b));
    }
  }