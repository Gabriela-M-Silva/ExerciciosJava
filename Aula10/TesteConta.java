import java.util.Scanner;

class Conta
{
   private int numero;
   private String titular;
   protected double saldo;
   
   public Conta()
   {
   }
   
   public Conta(int numero, String titular, double saldo)
   {
      this.numero = numero;
      setTitular(titular);
      setSaldo(saldo);
   }
   
   private void setTitular(String titular)
   {
      this.titular = titular;
   }
   
   public String getTitular()
   {
      return titular;
   }
   
   private void setSaldo(double saldo)
   {
      if (saldo > 0.0)
         this.saldo = saldo;
      else
         this.saldo = 0.0;
   }
   
   public double getSaldo()
   {
      return saldo;
   }
   
   public void saque(double valor)
   {
      if (valor < 0)
         System.out.printf("\nValor de saque invalido!\n");
      else if (saldo - valor < 0)
         System.out.printf("\nSaldo insuficiente!\n");
      else
         setSaldo(saldo - valor);
   }
      
   public void deposito(double valor)
   {
      if (valor < 0)
          System.out.printf("\nValor de deposito invalido!\n");
      else
         setSaldo(saldo + valor);
   }
   
   public void imprime()
   {
      System.out.printf("\nConta: %07d\n", numero);
      System.out.printf("Titular: %s\n", getTitular());
      System.out.printf("Saldo: R$ %.2f\n", getSaldo());
   }
}

class ContaPoupanca extends Conta
{
   private double taxaJuros;
   
   public ContaPoupanca(int numero, String titular, double saldo, double taxaJuros)
   {
      super(numero, titular, saldo);
      this.taxaJuros = taxaJuros;
   }
   
   public void atualizaSaldo()
   {
      saldo += saldo * taxaJuros/100;
   }
   
   public void imprime()
   {
      super.imprime();
      System.out.printf("Taxa de Juros da Poupanca: %.2f%%\n\n", taxaJuros);
   }
}

class ContaEmpresa extends Conta
{
   private double limiteEmprestimo;
   
   public ContaEmpresa(int numero, String titular, double saldo, double limiteEmprestimo)
   {
      super(numero, titular, saldo);
      this.limiteEmprestimo = limiteEmprestimo;
   }
   
   public void emprestimo(double valor)
   {
      if (valor <= limiteEmprestimo)
      {
         saldo += valor;
         limiteEmprestimo -= valor;
      }
      else
      {
         System.out.print("\nLimite insuficiente!\n");
      }
   }
   
   public void imprime()
   {
      super.imprime();
      System.out.printf("Limite de emprestimo: R$ %.2f\n\n", limiteEmprestimo);
   }
}

public class TesteConta
{
   public static void main(String args[])
   {
      int      qtd;
      int      opcao;
      int      numero;
      String   nome;
      double   saldo;

      Scanner  input = new Scanner(System.in);
      
      System.out.printf("Quantidade de contas para criar: ");
      qtd = input.nextInt();
      
      for (int i = 0; i < qtd; i++)
      {
         System.out.printf("\nSelecione o tipo da %da conta:\n[1] Conta Poupanca\n[2] Conta Empresa\n", i + 1);
         opcao = input.nextInt();
         
         System.out.printf("Digite os dados da Conta %s\n", opcao == 1? "Poupanca":"Empresa");
         System.out.printf("Numero: ");  
         numero = input.nextInt();
         input.nextLine();
         System.out.printf("Nome: ");
         nome = input.nextLine();
         System.out.printf("Saldo inicial: ");
         saldo = input.nextDouble();  
         switch (opcao)
         {
            case 1: criaPoupanca(input, numero, nome, saldo); break;
            case 2: criaEmpresa(input, numero, nome, saldo); break;
         }
      }
      System.exit(0);
   }
   
   public static void criaPoupanca(Scanner input, int numero, String nome, double saldo)
   {
      double   taxaJuros;
      
      System.out.printf("Taxa de juros: ");
      taxaJuros = input.nextDouble();
      ContaPoupanca cp1 = new ContaPoupanca(numero , nome, saldo, taxaJuros);
      cp1.imprime();
      
      System.out.printf("Dados da poupanca ao atualizar saldo com a taxa: \n");
      cp1.atualizaSaldo();
      cp1.imprime();
   }
   
   public static void criaEmpresa(Scanner input, int numero, String nome, double saldo)
   {
      double   limiteEmprestimo;
            
      System.out.printf("Limite de emprestimo: ");
      limiteEmprestimo = input.nextDouble();
      ContaEmpresa ce1 = new ContaEmpresa(numero, nome, saldo, limiteEmprestimo);
      ce1.imprime();

      System.out.printf("Pegar emprestimo? [s|n] ");
      input.nextLine();
      switch (input.nextLine().charAt(0))
      {
         case 's': System.out.printf("\nDigite o valor: "); ce1.emprestimo(input.nextDouble()); break;
         case 'n': break;
         default: System.out.printf("\nOpcao invalida!\n");
      }
      ce1.imprime();
   }
}
