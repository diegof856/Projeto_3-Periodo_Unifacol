package servicos;

import java.util.List;

import entidade.Filme;
import repositorios.Filme_repositorio;

/*import entidade.Filme;
import java.util.Scanner;
import repositorios.Filme_repositorio;

public class Filme_servico {

    // SELEÇÃO DAS OPÇÕES PRINCIPAIS
    public static void menuFilme(Scanner scannerFilmes) {
        int opcao = 0;

        System.out.println("""
                POPCORN

                1.LISTA DE FILMES
                2.ADICIONAR

                OPÇÃO:\s
               """);

        opcao = scannerFilmes.nextInt();
       scannerFilmes.nextLine();

        switch (opcao) {
            case 1:
                Filme_repositorio.tabelafilme(scannerFilmes);
                break;
            case 2:
                addFilme(scannerFilmes);
                break;
            default:
                System.out.println("\nERRO, TENTE NOVAMENTE\n");
                menuFilme(scannerFilmes);
        }

    }

   // CADASTRO DE FILMES - RECEBER AS INFORMAÇÕES DO FILME QUE SERÁ ADICIONADO
    public static void addFilme(Scanner scannerFilmes) {

        System.out.println("\nCADASTRO DE FILMES\n");
        System.out.print("NOME DO FILME: ");
       String nomeFilme = scannerFilmes.nextLine();
        System.out.print("NOME DO FILME ORIGINAL: ");
        String nomeOriginal = scannerFilmes.nextLine();
       System.out.print("ANO DE LANÇAMENTO: ");
        Integer ano = Integer.valueOf(scannerFilmes.nextLine());
        System.out.print("DURAÇÃO DO FILME EM MINUTOS: ");
        String duracao = scannerFilmes.nextLine();
        System.out.print("GÊNERO: ");
        String genero = scannerFilmes.nextLine();
        System.out.println("""


                NOME DO FILME:"""+nomeFilme+"""

               NOME DO FILME ORIGINAL:"""+nomeOriginal+"""

                ANO DE LANÇAMENTO:"""+ano+"""

                DURAÇÃO DO FILME EM MINUTOS:"""+duracao+"""

               GÊNERO:"""+genero+"""

                """);

        //ADICIONAR O FILME CADASTRADO NO METODO addFilme NA CLASSE FILMES_DADOS

        Filme filmeadd = new Filme(nomeFilme,nomeOriginal,ano,duracao,genero);
       menulista(scannerFilmes);

    }

    // MENU DE OPÇÕES DA LISTA DE FILMES
    public static void menulista(Scanner scannerFilmes){
        int opcao = 0;

       System.out.print("""

                1.VOLTAR AO MENU
                2.ADICIONAR
                3.REMOVER

                OPÇÃO:""");


    opcao = scannerFilmes.nextInt();
        scannerFilmes.nextLine();

       switch (opcao) {
        case 1:
            menuFilme(scannerFilmes);
            break;
        case 2:
            addFilme(scannerFilmes);
            break;
            case 3:
                Filme_repositorio.removerFilme(scannerFilmes);
           break;
        default:
            System.out.println("\nERRO, TENTE NOVAMENTE\n");
            menulista(scannerFilmes);
           break;

   }

        }

    }
*/
public class Filme_servico{
	public void catalogo() {
		
		List <Filme> listaFilmes = new Filme_repositorio().colocarFilme();
		
		for(Filme filme: listaFilmes) {
			System.out.println(filme.getNomeFilme() + " Ano de lançamento "+ filme.getAno() + " Generos do Filme "+ filme.getGenero()+" \nO filme tem duração de aproximadamento de "+ filme.getDuracao()+ " \nDireção "+ filme.getDireção().getNome()+ " de "+ filme.getDireção().getIdade()+ " anos de idade");
		}
	}
	
	
}