import java.util.*;
public class Ex1 {
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		//Declara��o
		int n1, n2, soma;
		
		//Leitura
		System.out.println("Digite um n�mero: ");
		n1 = sc.nextInt();
		System.out.println("Digite um n�mero: ");
		n2 = sc.nextInt();
		
		//Soma
		soma = n1 + n2;
		
		//Mostrar na tela
		System.out.println(soma);
	}
}
