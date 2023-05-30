import java.util.Scanner;

public class Farenheit
{
   public static void main(String args[])
   {
      Scanner  input = new Scanner(System.in);
      double   celsius;
      double   farenheit;
      
      System.out.print("Digite em Celsius: ");
      celsius = input.nextDouble();
      
      farenheit = 32 + celsius * 9.0 / 5;

      System.out.printf("A temperatura em Farenheit: %.1f", farenheit);
   }
}
