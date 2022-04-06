package com.ti2cc;

import java.util.Scanner;

public class Principal {
	
	public static void main(String[] args) {
		
		DAO dao = new DAO();
		Produto produto;
		Produto[] estoque;
		Scanner sc = new Scanner(System.in);
		boolean loop = true;
		String tipo;
		int opcao, id, quantidade;
		
		
		dao.conectar();
		
		while(loop) {
			System.out.print("1 - listar \n2 - Inserir\n3 - Excluir \n4 - Atualizar \n5 - Sair\n");
			opcao = sc.nextInt();
			
			if(opcao == 1) {
				//Mostrar estoque		
				System.out.println("==== Mostrar Estoque === ");
				estoque = dao.getProdutos();
				for(int i = 0; i < estoque.length; i++) {
					System.out.println(estoque[i].toString());
				}
			}
			
			if(opcao == 2) {
				//Inserir um elemento na tabela
				System.out.println("Escreva o tipo");
				sc.nextLine();
				tipo = sc.nextLine();
				
				estoque = dao.getProdutos();
				id = 1 + estoque.length;
				
				System.out.println("Escreva a quantidade");
				quantidade = sc.nextInt();
				
				produto = new Produto(id, tipo, quantidade);
				if(dao.inserirProduto(produto) == true) {
					System.out.println("InserÃ§Ã£o com sucesso -> " + produto.toString());
				}
			}
			
			if(opcao == 3) {
				System.out.println("Escreva o código do produto que deseja excluir");
				id = sc.nextInt();
				dao.excluirProduto(id);
			}
			
			if(opcao == 4) {
				estoque = dao.getProdutos();
				System.out.println("Escreva o código do produto que deseja editar");
				id = sc.nextInt();
				if(id > estoque.length) {
					System.out.println("Codigo de produto maior que o esperado tente novamente");
				}
				else {
					for(int i = 0; i < estoque.length; i++) {
						if(id == estoque[i].getCodigo()) {
							produto = estoque[i];
							System.out.print("1 - Editar tipo \n2 - Editar quantidade\n");
							opcao = sc.nextInt();
							if(opcao == 1) {
								sc.nextLine();
								System.out.println("Escreva o novo tipo do produto");								
								tipo = sc.nextLine();
								produto.setTipo(tipo);
							}
							else {
								System.out.println("Escreva a nova quantidade do produto");	
								quantidade = sc.nextInt();
								produto.setQuantidade(quantidade);
							}
							dao.atualizarProduto(produto);
							i = estoque.length;
						}
					}
				}
				
			}
			
			if(opcao == 5) {
				System.out.println("Saindo...");
				loop = false;
				dao.close();
			}
		}
	}
}