import java.util.*;
public class Ex1 {
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		//Declaração
		int n1, n2, soma;
		
		//Leitura
		System.out.println("Digite um número: ");
		n1 = sc.nextInt();
		System.out.println("Digite um número: ");
		n2 = sc.nextInt();
		
		//Soma
		soma = n1 + n2;
		
		//Mostrar na tela
		System.out.println(soma);
	}
}
