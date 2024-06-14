package arquivo;

import java.io.*;
import java.util.Scanner;

public class Main {
    static StringBuffer memoria = new StringBuffer();
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        char opcao, resp = 'N';

        do {
            opcao = menu();
            switch (opcao) {
                case '1':
                    inserirDados();
                    break;

                case '2':
                    alterarDados();
                    break;

                case '3':
                    excluirDados();
                    break;

                case '4':
                    consultaGeral();
                    break;

                case '5':
                    consultaEspecifica();
                    break;

                case '6':
                    System.out.println("\nDeseja sair do programa? S/N");
                    resp = Character.toUpperCase(scan.next().charAt(0));
                    break;
                default:
                    System.out.println("\nOpção Inválida, tente novamente!");
            }
        } while (resp != 'S');
        System.out.println("\nPrograma finalizado! \n" +
                "Feito por Catharina, Emanuel, Lucas e Yasmin");
        System.exit(0);
    }

    static char menu() {
        System.out.println("\n\n------ CLÍNICA DE FISIOTERAPIA ------\n" +
                "\n1. Inserção de Dados do Paciente\n" +
                "2. Alteração de Dados do Paciente\n" +
                "3. Exclusão de Dados do Paciente\n" +
                "4. Consulta Geral\n" +
                "5. Consulta Específica\n" +
                "6. Sair\n");

        return scan.next().charAt(0);
    }

    static void iniciarArquivo() {
        try {
            BufferedReader arquivoEntrada;
            arquivoEntrada = new BufferedReader(new FileReader("Clínica.txt"));
            String linha = "";
            memoria.delete(0, memoria.length());
            do {
                linha = arquivoEntrada.readLine();
                if (linha != null) {
                    memoria.append(linha + "\n");
                }
            } while (linha != null);
            arquivoEntrada.close();
        } catch (FileNotFoundException erro) {
            System.out.println("\nArquivo Não Encontrado! ");
        } catch (Exception e) {
            System.out.println("\nErro de Leitura! ");
        }
    }

    public static void gravarDados() {
        try {
            BufferedWriter arquivoSaida;
            arquivoSaida = new BufferedWriter(new FileWriter("Clínica.txt"));
            arquivoSaida.write(memoria.toString());
            arquivoSaida.flush();
            arquivoSaida.close();
        } catch (Exception e) {
            System.out.println("\nErro de Gravação!");
        }

    }

    static void inserirDados() {
        String nome, tell;
        int idade, codigo;
        try {
            System.out.println("------ INSERÇÃO DE DADOS ------\n");
            System.out.println("Insira o Código do Convênio do Paciente:");
            codigo = scan.nextInt();
            System.out.println("Insira o Nome do Paciente: ");
            nome = scan.next();
            System.out.println("Insira o Telefone do Paciente: ");
            tell = scan.next();
            System.out.println("Insira a Idade do Paciente: ");
            idade = scan.nextInt();
            System.out.println("Insira a Data: ");
            String data = scan.next();
            System.out.println("Insira o Horário: ");
            String horario = scan.next();

            Paciente reg = new Paciente(codigo, nome, idade, tell);
            memoria.append(reg.toString() + "\t");
            Horarios horarios = new Horarios(data, horario);
            memoria.append(horarios.toString() + "\t");
            gravarDados();
            System.out.println("Paciente cadastrado com Sucesso!\n");
        } catch (Exception e) {
            System.out.println("\nErro de gravacao");
        }

    }

    public static void alterarDados() {
        String codigo, nome, idade, tell;
        int inicio, fim, ultimo, primeiro;
        iniciarArquivo();
        try {
            if (memoria.length() != 0) {
                System.out.println("Digite o código para alteração:");
                codigo = scan.next();
                nome = "";
                idade = "";
                tell = "";
                inicio = memoria.indexOf(codigo);
                if (inicio != -1) {
                    ultimo = memoria.indexOf("\t", inicio);
                    codigo = memoria.substring(inicio, ultimo).trim();
                    primeiro = ultimo + 1;
                    ultimo = memoria.indexOf("\t", primeiro);
                    nome = memoria.substring(primeiro, ultimo).trim();
                    primeiro = ultimo + 1;
                    ultimo = memoria.indexOf("\t", primeiro);
                    idade = memoria.substring(primeiro, ultimo).trim();
                    primeiro = ultimo + 1;
                    fim = memoria.indexOf("\n", primeiro);
                    tell = memoria.substring(primeiro, fim).trim();
    
                    Paciente reg = new Paciente(Integer.parseInt(codigo), nome, Integer.parseInt(idade), tell);
    
                    System.out.println("Deseja alterar?" + "\n" +
                            "Digite S ou N" + "\n\n" +
                            "Nome do Paciente: " + reg.getNome() + "\n" +
                            "Idade: " + reg.getIdade() + "\n" +
                            "Tell: " + reg.getTelefone());
    
                    char resp = Character.toUpperCase(scan.next().charAt(0));
                    if (resp == 'S') {
                        System.out.println("Insira um novo Nome: ");
                        nome = scan.next();
                        reg.setNome(nome);
                        System.out.println("Insira uma nova Idade: ");
                        idade = scan.next();
                        reg.setIdade(Integer.parseInt(idade));
                        System.out.println("Insira um novo Telefone: ");
                        tell = scan.next();
                        reg.setTelefone(tell);
    
                        memoria.replace(inicio, fim + 1, reg.toString());
    
                        System.out.println("Registro alterado.");
                    } else {
                        System.out.println("Alteração cancelada.");
                    }
                    gravarDados();
                } else {
                    System.out.println("Código não encontrado");
                }
            } else {
                System.out.println("Arquivo vazio.");
            }
        } catch (Exception erro2) {
            System.out.println("Erro de leitura.");
        }
    }        
    public static void excluirDados() {
        String codigo, nome, idade, tell;
        int inicio, fim, ultimo, primeiro;
        iniciarArquivo();

        try {
            if (memoria.length() != 0) {
                System.out.println("Digite o código que deseja excluir:");
                codigo = scan.next();
                nome = "";
                idade = "";
                tell = "";
                inicio = memoria.indexOf(codigo);
                if (inicio != -1) {
                    ultimo = memoria.indexOf("\t", inicio);
                    codigo = memoria.substring(inicio, ultimo).trim();
                    primeiro = ultimo + 1;
                    ultimo = memoria.indexOf("\t", primeiro);
                    nome = memoria.substring(primeiro, ultimo).trim();
                    primeiro = ultimo + 1;
                    ultimo = memoria.indexOf("\t", primeiro);
                    idade = memoria.substring(primeiro, ultimo).trim();
                    primeiro = ultimo + 1;
                    fim = memoria.indexOf("\n", primeiro);
                    tell = memoria.substring(primeiro, fim).trim();
    
                    Paciente reg = new Paciente(Integer.parseInt(codigo), nome, Integer.parseInt(idade), tell);
    
                    System.out.println("Deseja excluir?" + "\n" +
                            "Digite S ou N" + "\n\n" +
                            "Nome do Paciente: " + reg.getNome() + "\n" +
                            "Idade: " + reg.getIdade() + "\n" +
                            "Telefone: " + reg.getTelefone());
    
                    char resp = Character.toUpperCase(scan.next().charAt(0));
					if (resp == 'S') {
						memoria.delete (inicio, fim + 1);

						System.out.println("Cadastro excluído!");

						gravarDados(); 
					} else {
						System.out.println("Exclusão cancelada.");
					}
					
				} else {
					System.out.println("Código não encontrado!");
				}
			} else {
				System.out.println("Arquivo vazio!");
			}
		} catch(Exception erro2){
			System.out.println("Erro de leitura!");
		}
	}       

    static private void consultaGeral() {
            String codigo, nome, idade, tell;
            String dados = "\n Dados: \n\n";
            int inicio, fim, ultimo, primeiro;
            iniciarArquivo();
            inicio = 0;
            try {
                if(memoria.length() != 0){
                    while(inicio != memoria.length()){
                        ultimo = memoria.indexOf("\t",inicio);
                        codigo = memoria.substring(inicio, ultimo);
                        primeiro = ultimo + 1;
                        ultimo = memoria.indexOf("\t",primeiro);
                        nome = memoria.substring(primeiro, ultimo);
                        primeiro = ultimo + 1;
                        ultimo = memoria.indexOf("\t", primeiro);
                        idade = memoria.substring(primeiro, ultimo);
                        primeiro = ultimo + 1;
                        fim = memoria.indexOf("\n", primeiro);
                        tell = memoria.substring(primeiro, fim);

                        Paciente reg = new Paciente(Integer.parseInt(codigo), nome, Integer.parseInt(idade), tell);
                        dados += "Código: " + reg.getCodigo() + "\n" + "Nome do Paciente: " + reg.getNome() + "\n" + "Idade do Paciente: "+ reg.getIdade() + "\n" + "Telefone do Paciente: "+ reg.getTelefone() + "\n\n";
                        inicio = fim + 1;
                    }
                    System.out.println(dados);
                } else {
                    System.out.println("Arquivo vazio.");
                }
            } catch(Exception erro2){
                System.out.println("Erro de leitura.");
            }
        } 

    public static void consultaEspecifica() {
		String codigo, nome, idade, tell;
		int inicio , fim, ultimo, primeiro;
		iniciarArquivo();
		try {
			if (memoria.length()!=0) {
				System.out.println("Digite o Código do Paciente que deseja pesquisar: ");
				codigo = scan.next();
				inicio = memoria.indexOf (codigo);
				if(inicio != -1){
					ultimo = memoria.indexOf("\t",inicio);
					codigo = memoria.substring(inicio, ultimo);
					primeiro = ultimo + 1;
					ultimo = memoria.indexOf("\t",primeiro);
					nome = memoria.substring(primeiro, ultimo);
					primeiro = ultimo + 1;
					ultimo = memoria.indexOf("\t", primeiro);
					idade = memoria.substring(primeiro, ultimo);
					primeiro = ultimo + 1;
					fim = memoria.indexOf("\n", primeiro);
					tell = memoria.substring(primeiro, fim);
					Paciente reg = new Paciente(Integer.parseInt(codigo), nome, Integer.parseInt(idade), tell);
					System.out.println("Código: " + reg.getCodigo() + "\n" + "Nome do Paciente: " + reg.getNome() + "\n" + "Idade do Paciente: "+ reg.getIdade() + "\n" + "Telefone do Paciente: "+ reg.getTelefone() + "\n\n");
				} else {
					System.out.println("Paciente não cadastrado.");
				}
			} else {
				System.out.println("Arquivo vazio.");
			}

		}catch(Exception erro2){
			System.out.println("Erro de leitura.");
		}
	}

}
//Não consigo alterar os dados do Horário ####VER COM A PROFESSORA####
// public static void alterarDados() {
//     String nome, tell, idade, codigo, data, horario;
//     int inicio, fim, ultimo, primeiro;
//     iniciarArquivo();
//     try {
//         if (memoria.length() != 0) {
//             System.out.println("Digite o código para alteração:");
//             codigo = scan.next();
//             nome = "";
//             tell = "";
//             idade = "";
//             data = "";
//             horario = "";

//             inicio = memoria.indexOf(codigo + "\t");
            
//             if (inicio != -1) {
//                 ultimo = memoria.indexOf("\t", inicio);
//                 codigo = memoria.substring(inicio, ultimo).trim(); //o trim remove os espaços em branco desnecessários
//                 primeiro = ultimo + 1;
//                 ultimo = memoria.indexOf("\t", primeiro);
//                 nome = memoria.substring(primeiro, ultimo).trim();
//                 primeiro = ultimo + 1;
//                 ultimo = memoria.indexOf("\t", primeiro);
//                 tell = memoria.substring(primeiro, ultimo).trim();
//                 primeiro = ultimo + 1;
//                 ultimo = memoria.indexOf("\t", primeiro);
//                 idade = memoria.substring(primeiro, ultimo).trim();
//                 primeiro = ultimo + 1;
//                 fim = memoria.indexOf("\n", primeiro);
//                 data = memoria.substring(primeiro, fim).trim(); 
//                 primeiro = fim + 1;
//                 ultimo = memoria.indexOf("\t", primeiro);
//                 horario = memoria.substring(primeiro, ultimo).trim();  

//                 Paciente reg = new Paciente(Integer.parseInt(codigo), nome, Integer.parseInt(idade), tell);
//                 Horarios hor = new Horarios(data, horario);

//                 System.out.println("Deseja alterar?" + "\n" +
//                         "Digite S ou N" + "\n\n" +
//                         "Nome do Paciente: " + reg.getNome() + "\n" +
//                         "Telefone: " + reg.getTelefone() + "\n" +
//                         "Idade: " + reg.getIdade() + "\n" +
//                         "Data: " + hor.getData() + "\n" +
//                         "Horário: " + hor.getHorario() + "\n");


//                 char resp = Character.toUpperCase(scan.next().charAt(0));
//                 if (resp == 'S') {
//                     System.out.println("Insira com o novo Nome: ");
//                     nome = scan.next();
//                     reg.setNome(nome);
//                     System.out.println("Insira com o novo Telefone: ");
//                     tell = scan.next();
//                     reg.setTelefone(tell);
//                     System.out.println("Insira com a nova Idade: ");
//                     idade = scan.next();
//                     reg.setIdade(Integer.parseInt(idade));
//                     System.out.println("Insira com a nova Data: ");
//                     data = scan.next();
//                     hor.setData(data);
//                     System.out.println("Insira com o novo Horário: ");
//                     horario = scan.next();
//                     hor.setHorario(horario);

//                     memoria.replace(inicio, fim, reg.toString());
//                     memoria.replace(primeiro, ultimo, hor.toString());

//                     System.out.println("Registro alterado.");
//                 } else {
//                     System.out.println("Alteração cancelada.");
//                 }
//                 gravarDados();
//             } else {
//                 System.out.println("Código não encontrado");
//             }
//         } else {
//             System.out.println("Arquivo vazio.");
//         }
//     } catch (Exception erro2) {
//         System.out.println("Erro de leitura.");
//     }
// }