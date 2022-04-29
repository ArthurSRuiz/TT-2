package service;

import java.util.Scanner;
import java.io.File;
import java.util.List;
import dao.funcionarioDAO;
import model.Funcionario;
import spark.Request;
import spark.Response;


public class FuncionarioService {

	private funcionarioDAO funcionarioDAO = new funcionarioDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_ID = 1;
	private final int FORM_ORDERBY_NOME = 2;
	private final int FORM_ORDERBY_CPF = 3;
	
	
	public FuncionarioService() {
		makeForm();
	}

	
	public void makeForm() {
		makeForm(FORM_INSERT, new Funcionario(), FORM_ORDERBY_NOME);
	}

	
	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new Funcionario(), orderBy);
	}

	
	public void makeForm(int tipo, Funcionario funcionario, int orderBy) {
		String nomeArquivo = "form.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String umFuncionario = "";
		if(tipo != FORM_INSERT) {
			umFuncionario += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umFuncionario += "\t\t<tr>";
			umFuncionario += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/funcionario/list/1\">Novo Funcionario</a></b></font></td>";
			umFuncionario += "\t\t</tr>";
			umFuncionario += "\t</table>";
			umFuncionario += "\t<br>";			
		}
		
		if(tipo == FORM_INSERT || tipo == FORM_UPDATE) {
			String action = "/funcionario/";
			String nome, descricao, buttonLabel;
			if (tipo == FORM_INSERT){
				action += "insert";
				nome = "Inserir Funcionario";
				descricao = "Carlos, Daniel, ...";
				buttonLabel = "Inserir";
			} else {
				action += "update/" + funcionario.getID();
				nome = "Atualizar Funcionario (ID " + funcionario.getID() + ")";
				descricao = funcionario.getNome();
				buttonLabel = "Atualizar";
			}
			umFuncionario += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
			umFuncionario += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umFuncionario += "\t\t<tr>";
			umFuncionario += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + nome + "</b></font></td>";
			umFuncionario += "\t\t</tr>";
			umFuncionario += "\t\t<tr>";
			umFuncionario += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umFuncionario += "\t\t</tr>";
			umFuncionario += "\t\t<tr>";
			umFuncionario += "\t\t\t<td>&nbsp;Nome: <input class=\"input--register\" type=\"text\" name=\"nome\" value=\"\" placehoder=\""+nome+"\"></td>";
			umFuncionario += "\t\t\t<td>Biografia: <input class=\"input--register\" type=\"text\" name=\"biografia\" value=\""+ funcionario.getBio() +"\"></td>";
			umFuncionario += "\t\t\t<td>CPF: <input class=\"input--register\" type=\"text\" name=\"cpf\" value=\""+ funcionario.getCpf() +"\"></td>";
			umFuncionario += "\t\t\t<td>RG: <input class=\"input--register\" type=\"text\" name=\"rg\" value=\""+ funcionario.getRg() +"\"></td>";
			umFuncionario += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\"></td>";
			umFuncionario += "\t\t</tr>";
			umFuncionario += "\t</table>";
			umFuncionario += "\t</form>";		
		} else if (tipo == FORM_DETAIL){
			umFuncionario += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umFuncionario += "\t\t<tr>";
			umFuncionario += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar Funcionario (ID " + funcionario.getID() + ")</b></font></td>";
			umFuncionario += "\t\t</tr>";
			umFuncionario += "\t\t<tr>";
			umFuncionario += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umFuncionario += "\t\t</tr>";
			umFuncionario += "\t\t<tr>";
			umFuncionario += "\t\t\t<td>&nbsp;Nome: <input class=\"input--register\" type=\"text\" name=\"nome\" value=\""+ funcionario.getNome() +"\"></td>";
			umFuncionario += "\t\t\t<td>Biografia: <input class=\"input--register\" type=\"text\" name=\"biografia\" value=\""+ funcionario.getBio() +"\"></td>";
			umFuncionario += "\t\t\t<td>CPF: <input class=\"input--register\" type=\"text\" name=\"cpf\" value=\""+ funcionario.getCpf() +"\"></td>";
			umFuncionario += "\t\t\t<td>RG: <input class=\"input--register\" type=\"text\" name=\"rg\" value=\""+ funcionario.getRg() +"\"></td>";
			umFuncionario += "\t\t\t<td>&nbsp;</td>";
			umFuncionario += "\t\t</tr>";
			umFuncionario += "\t</table>";		
		} else {
			System.out.println("ERRO! Tipo não identificado " + tipo);
		}
		form = form.replaceFirst("<UM-FUNCIONARIO>", umFuncionario);
		
		String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\">");
		list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Relação de Funcion�rios</b></font></td></tr>\n" +
				"\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
    			"\n<tr>\n" + 
        		"\t<td><a href=\"/funcionario/list/" + FORM_ORDERBY_ID + "\"><b>ID</b></a></td>\n" +
        		"\t<td><a href=\"/funcionario/list/" + FORM_ORDERBY_NOME + "\"><b>Descrição</b></a></td>\n" +
        		"\t<td><a href=\"/funcionario/list/" + FORM_ORDERBY_CPF + "\"><b>Preço</b></a></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
        		"</tr>\n";
		
		List<Funcionario> funcionarios;
		if (orderBy == FORM_ORDERBY_ID) {                 	funcionarios = funcionarioDAO.getOrderByID();
		} else if (orderBy == FORM_ORDERBY_NOME) {		funcionarios = funcionarioDAO.getOrderByNome();
		} else if (orderBy == FORM_ORDERBY_CPF) {			funcionarios = funcionarioDAO.getOrderByCpf();
		} else {											funcionarios = funcionarioDAO.get();
		}

		int i = 0;
		String bgcolor = "";
		for (Funcionario f : funcionarios) {
			bgcolor = (i++ % 2 == 0) ? "#fff5dd" : "#dddddd";
			list += "\n<tr bgcolor=\""+ bgcolor +"\">\n" + 
            		  "\t<td>" + f.getID() + "</td>\n" +
            		  "\t<td>" + f.getNome() + "</td>\n" +
            		  "\t<td>" + f.getCpf() + "</td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/funcionario/" + f.getID() + "\"><img src=\"/image/detail.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/funcionario/update/" + f.getID() + "\"><img src=\"/image/update.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeleteFuncionario('" + f.getID() + "', '" + f.getNome() + "', '" + f.getCpf() + "');\"><img src=\"/image/delete.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "</tr>\n";
		}
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-FUNCIONARIOS>", list);				
	}
	
	
	public Object insert(Request request, Response response) {
		String nome = request.queryParams("nome");
		String bio = request.queryParams("biografia");
		String rg = request.queryParams("rg");
		String cpf = request.queryParams("CPF");
		
		String resp = "";
		
		Funcionario funcionario = new Funcionario(-1, nome, bio, rg, cpf);
		
		if(funcionarioDAO.insert(funcionario) == true) {
            resp = "Funcionario (" + nome + ") inserido!";
            response.status(201); // 201 Created
		} else {
			resp = "Funcionario (" + nome + ") não inserido!";
			response.status(404); // 404 Not found
		}
			
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Funcionario funcionario = (Funcionario) funcionarioDAO.get(id);
		
		if (funcionario != null) {
			response.status(200); // success
			makeForm(FORM_DETAIL, funcionario, FORM_ORDERBY_NOME);
        } else {
            response.status(404); // 404 Not found
            String resp = "Funcionario " + id + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}

	
	public Object getToUpdate(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Funcionario funcionario = (Funcionario) funcionarioDAO.get(id);
		
		if (funcionario != null) {
			response.status(200); // success
			makeForm(FORM_UPDATE, funcionario, FORM_ORDERBY_NOME);
        } else {
            response.status(404); // 404 Not found
            String resp = "Funcionario " + id + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}
	
	
	public Object getAll(Request request, Response response) {
		int orderBy = Integer.parseInt(request.params(":orderby"));
		makeForm(orderBy);
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
		return form;
	}			
	
	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
		Funcionario funcionario = funcionarioDAO.get(id);
        String resp = "";       

        if (funcionario != null) {
        	funcionario.setNome(request.queryParams("nome"));
        	funcionario.setBio(request.queryParams("biografia"));
        	funcionario.setRg(request.queryParams("rg"));
        	funcionario.setCpf(request.queryParams("cpf"));
        	funcionarioDAO.update(funcionario);
        	response.status(200); // success
            resp = "Funcionario (ID " + funcionario.getID() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Funcionario (ID \"" + funcionario.getID() + "\") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object delete(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Funcionario funcionario = funcionarioDAO.get(id);
        String resp = "";       

        if (funcionario != null) {
        	funcionarioDAO.delete(id);
            response.status(200); // success
            resp = "Funcionario (" + id + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "Funcionario (" + id + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
}