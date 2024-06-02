package servicos;

import entidade.Usuario;
import entidade.Usuario_Endereco;
import excecoes.Informacao_Excecoes;
import menus.Usuario_Premium_Menu;
import repositorios.Geral_Dados_Repositorios;

import java.sql.SQLException;
import java.util.Scanner;

public class Metodos_Gerais_Servicos {


    public static void cadastro_adicional(Scanner scanner, Usuario usuario) throws SQLException, Informacao_Excecoes {
        String cidade, estado, bairro, complemento;
        String id_usuario = Geral_Dados_Repositorios.Chamar_id(usuario);

        String email = usuario.getEmail();

        if (new Geral_Dados_Repositorios().verificarCadastroExistente(email)) {
            System.out.println("\nInformações Adicionais já estão Cadastradas\n");
            Usuario_Premium_Menu.informacao_menu(scanner, usuario);
        } else {
            while (true) {
                System.out.println("Digite o Estado: ");
                try {
                    estado = scanner.nextLine().trim();
                    if (validacao_Informacao(estado)) {
                        break;
                    }
                } catch (Informacao_Excecoes ex) {
                    System.out.println(ex.getMessage());
                }
            }

            while (true) {
                System.out.println("CIDADE: ");
                try {
                    cidade = scanner.nextLine().trim();
                    if (validacao_Informacao(cidade)) {
                        break;
                    }
                } catch (Informacao_Excecoes ex) {
                    System.out.println(ex.getMessage());
                }
            }

            while (true) {
                System.out.println("Bairro: ");
                try {
                    bairro = scanner.nextLine().trim();
                    if (validacao_Informacao(bairro)) {
                        break;
                    }
                } catch (Informacao_Excecoes ex) {
                    System.out.println(ex.getMessage());
                }
            }

            System.out.println("Complemento: ");
            complemento = scanner.nextLine().trim();

            Usuario_Endereco add_endereco = new Usuario_Endereco(estado, cidade, bairro, complemento, id_usuario);
            new Geral_Dados_Repositorios().Cadastro_Usuario(add_endereco);

            System.out.println("Informações Adicionais Cadastradas");
            Usuario_Premium_Menu.informacao_menu(scanner, usuario);
        }
    }




    public static boolean validarCpf(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verificar se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Verificar se todos os dígitos são iguais
        char firstDigit = cpf.charAt(0);
        boolean digitosIguais = true;
        for (int i = 1; i < cpf.length(); i++) {
            if (cpf.charAt(i) != firstDigit) {
                digitosIguais = false;
                break;
            }
        }
        if (digitosIguais) {
            return false;
        }

        // Se todas as verificações passarem, o CPF é válido
        return true;
    }



// VALIDAÇÃO DE CADASTRO DE INFORMAÇÕES EXTRAS

    public static boolean validacao_Informacao(String validar) throws Informacao_Excecoes {
        if (validar.isBlank() || validar.isEmpty()) {
            throw new Informacao_Excecoes("Não pode estar vazia.");
        }
        if (!validar.matches("[a-zA-ZÀ-ÿ\\s]+")) {
            throw new Informacao_Excecoes("Deve conter apenas letras.");
        }
        return true;
    }



}
