import java.util.Scanner;

public class Dolar
{
   public static void main(String args[])
   {
      Scanner  input = new Scanner(System.in);
      double   num_real;
      double   num_dolar;

      System.out.print("Digite um valor em real: ");
      num_real = input.nextDouble();

      num_dolar = num_real / 5.11;

      System.out.printf("O valor em dolar: %.2f", num_dolar);
   }
}
