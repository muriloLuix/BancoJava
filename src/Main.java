import java.util.*;

public class Main {

    // Armazenamento dos usuários em memória (conta -> senha)
    private static Map<String, String> contasESenhas = new HashMap<>();
    // Armazenamento dos saldos (conta -> saldo)
    private static Map<String, Double> saldos = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) { // Loop para manter o programa em execução
            // Mensagem de boas-vindas e opções
            System.out.println("Olá! Seja bem-vindo ao banco Java. Você possui uma " +
                    "conta com a gente?" + "\n" + "1. Sim, já possuo! (realizar login)" +
                    "\n" + "2. Não, (fazer cadastro)" + "\n" + "0. Sair");

            int optLogin = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (optLogin) {
                case 1:
                    // Realiza o login
                    System.out.println("Maravilha! Escreva abaixo o número da sua conta:");
                    String account = scanner.nextLine();
                    System.out.println("Agora... Escreva a senha da sua conta:");
                    String password = scanner.nextLine();

                    // Verifica se a conta existe e a senha está correta
                    if (contasESenhas.containsKey(account) && contasESenhas.get(account).equals(password)) {
                        System.out.println("Login bem-sucedido! Seu saldo é: R$ " + saldos.get(account));
                        boolean loggedIn = true;

                        while (loggedIn) { // Loop para as opções do usuário após o login
                            System.out.println("Menu:" + "\n" + "1. Visualizar saldo" + "\n" + "2. Adicionar Saldo" +
                                    "\n" + "3. Diminuir Saldo" + "\n" + "4. Visualizar informações pessoais " +
                                    "\n" + "5. Sair da conta");
                            int action = scanner.nextInt();
                            scanner.nextLine(); // Consumir a quebra de linha
                            switch (action) {
                                case 1:
                                    System.out.println("Seu saldo atual é de: R$ " + saldos.get(account));
                                    break;
                                case 2:
                                    System.out.println("Qual a quantidade de saldo que você deseja adicionar?");
                                    double balanceToAdd = scanner.nextDouble();
                                    // Atualiza o saldo da conta adicionando o novo valor
                                    saldos.put(account, saldos.get(account) + balanceToAdd);
                                    System.out.println("Novo saldo é: R$ " + saldos.get(account));
                                    break;
                                case 3:
                                    System.out.println("Qual a quantidade de saldo que você deseja retirar da sua conta?");
                                    double balanceToRemove = scanner.nextDouble();
                                    double currentBalance = saldos.get(account);
                                    // Verifica se o saldo é suficiente para a retirada
                                    if (currentBalance >= balanceToRemove) {
                                        saldos.put(account, currentBalance - balanceToRemove);
                                        System.out.println("O saldo atualizado é de: R$ " + saldos.get(account));
                                    } else {
                                        System.out.println("Saldo insuficiente. Você não pode retirar mais do que tem.");
                                    }
                                    break;
                                case 4:
                                    System.out.println("O número da sua conta é: " + account);
                                    break;
                                case 5:
                                    System.out.println("Saindo da conta...");
                                    loggedIn = false; // Sai do loop de login
                                    break;
                                default:
                                    System.out.println("Opção inválida.");
                                    break;
                            }
                        }
                    } else {
                        System.out.println("Conta não encontrada ou senha incorreta. Tente novamente.");
                        return;
                    }
                    break;

                case 2:
                    // Cadastro de nova conta
                    System.out.println("Você escolheu fazer cadastro.");
                    System.out.println("Digite um número para sua nova conta:");
                    String newAccount = scanner.nextLine();

                    if (contasESenhas.containsKey(newAccount)) {
                        System.out.println("Esta conta já existe. Tente novamente.");
                    } else {
                        System.out.println("Digite uma senha para sua nova conta:");
                        String newPassword = scanner.nextLine();

                        // Armazena a nova conta e senha
                        contasESenhas.put(newAccount, newPassword);
                        // Inicia o saldo da nova conta com 0.0
                        saldos.put(newAccount, 0.0);

                        System.out.println("Cadastro realizado com sucesso! Seu saldo inicial é R$ 0.0");
                    }
                    break;

                case 0:
                    System.out.println("Saindo do sistema. Até logo!");
                    scanner.close();
                    return; // Encerrando o programa

                default:
                    System.out.println("Opção inválida. Por favor, escolha 1, 2 ou 0.");
                    break;
            }
        }
    }
}
