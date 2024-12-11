import java.util.Scanner;

public class Prodify{
    public static void main (String[] args){
        Scanner in = new Scanner (System.in);
        String[]  nomeProd = new String [20];
        String busca, buscaNome;
        float[] valorProd = new float [20];
        float[][] impostoProd = new float [20][3];
        float totalImposto=0, totalICMS=0, totalAvulso=0, totalIPI=0, mediaUnit=0;
        int[] estoqueProd = new int[20];
        int opcao, ultCad=0, espaco=0,quantProd=0;
        char opcao2;
        boolean prodencontrado = false;

        do{ //repetição infinita até que o usuário digite '0'
            System.out.println("\n\n><><>< P R O D I F Y ><><><\n");
            System.out.println("< 1 > Cadastrar produto");
            System.out.println("< 2 > Editar produto");
            System.out.println("< 3 > Calcular imposto");		
            System.out.println("< 4 > Relatório");
            System.out.println("< 0 > SAIR");
            System.out.print("Opção: ");
            opcao = in.nextInt();
            in.nextLine();
            System.out.println("\n\n\n\n\n\n");
            switch (opcao){
                case 1:
                    System.out.println("\n\n\n\n\n\n");
                    if(ultCad>=20){
                        System.out.print("Você já cadastrou todos os espaços do estoque. Edite um cadastro para cadastrar outro produto.");
                        break;
                    }
                    do{
                        System.out.println("< CADASTRO DE PRODUTO >");
                        System.out.print("Digite o nome do produto: ");
                        nomeProd[ultCad] = in.nextLine();
                        System.out.print("Digite o valor deste produto: ");
                        valorProd[ultCad] = in.nextFloat();
                        in.nextLine();
                        while(valorProd[ultCad]<0){
                            System.out.print("Valor não aceito. Digite um valor acima de zero: ");
                            valorProd[ultCad] = in.nextFloat();
                        }
                        System.out.print("Digite quantidade em estoque deste produto: ");
                        estoqueProd[ultCad] = in.nextInt();
                        System.out.print("Digite a alíquota de ICMS: ");
                        impostoProd[ultCad][0] = in.nextFloat();
                        System.out.print("Digite a alíquota de IPI: ");
                        impostoProd[ultCad][1] = in.nextFloat();
                        System.out.print("Digite a alíquota de um imposto avulso: ");
                        impostoProd[ultCad][2] = in.nextFloat();
                        in.nextLine();
                        ultCad++;
                        System.out.println("Produto cadastrado com sucesso!\n");

                        if (ultCad >= 20) {
                            System.out.println("Todos os espaços do estoque foram preenchidos.\n");
                            break; //finaliza a função de cadastro ao preencher todos os espaços
                        }
                        System.out.println("Deseja cadastrar outro produto? (S/N)\n");
                        opcao2 = in.next().charAt(0);
                        in.nextLine();
                    }while((opcao2 == 'S') || (opcao2 == 's'));
                    break;
                case 2:
                    System.out.println("< EDIÇÃO DE CADASTRO DE PRODUTO >");
                    System.out.print("Qual espaço do estoque deseja editar (1 a 20)? ");
                    espaco = in.nextInt() - 1;
                    while (espaco < 0 || espaco >= 20) {
                        System.out.print("Número inválido! Digite novamente (1 a 20): ");
                        espaco = in.nextInt() - 1;
                    }

                    if (nomeProd[espaco] == null) {
                        System.out.println("Não há produto cadastrado neste espaço!");
                    }else{
                        in.nextLine();
                        System.out.println("Produto selecionado: " + nomeProd[espaco]);
                        System.out.print("Digite o novo nome do produto: ");
                        nomeProd[espaco] = in.nextLine();

                        System.out.print("Digite o novo valor do produto: ");
                        valorProd[espaco] = in.nextFloat();
                        while(valorProd[espaco]<0){
                            System.out.print("Valor não aceito. Digite um valor acima de zero: ");
                            valorProd[espaco] = in.nextFloat();
                        }
                        System.out.print("Digite a nova quantidade em estoque: ");
                        estoqueProd[espaco] = in.nextInt();

                        System.out.print("Digite a nova alíquota de ICMS: ");
                        impostoProd[espaco][0] = in.nextFloat();

                        System.out.print("Digite a nova alíquota de IPI: ");
                        impostoProd[espaco][1] = in.nextFloat();

                        System.out.print("Digite a nova alíquota de imposto avulso: ");
                        impostoProd[espaco][2] = in.nextFloat();

                        System.out.println("Produto editado com sucesso!");
                    }
                    break;
                case 3:
                    System.out.println("< CÁLCULO DE IMPOSTO DE PRODUTO >");
                    System.out.println("Buscar produto por nome ou espaço de estoque?");
                    busca = in.nextLine();
                    if(busca.equalsIgnoreCase("nome")){
                        do{
                            prodencontrado = false;
                            System.out.print("Digite o nome do produto para calcular: ");
                            buscaNome = in.nextLine();
                    
                            for (int i = 0; i < ultCad; i++) {
                                if (nomeProd[i] != null && nomeProd[i].equalsIgnoreCase(buscaNome)) { //verificando se índice está vazio e se o nome inserido é igual ao índice                                
                                    prodencontrado = true;
                                    
                                    System.out.print("Quantidade de produtos: ");
                                    quantProd = in.nextInt();
                                    totalImposto = (valorProd[i] * quantProd) * (impostoProd[i][0] / 100 + impostoProd[i][1] / 100 + impostoProd[i][2] / 100); //calcula o valor do imposto total
                                    totalICMS = (valorProd[i] * quantProd) * (impostoProd[i][0]/100); //calcula o valor do ICMS separadamente
                                    totalIPI = (valorProd[i] * quantProd) * (impostoProd[i][1]/100); //calcula o valor do IPI separadamente
                                    totalAvulso = (valorProd[i] * quantProd) * (impostoProd[i][2]/100); //calcula o valor do imposto avulso separadamente
                                    System.out.println("\n\n");
                                    System.out.printf("Nome: %s\nValor: R$%.2f\nValor ICMS: R$%.2f\nValor IPI: R$%.2f\nValor avulso: R$%.2f\nTotal de Impostos: R$%.2f\n",
                                        nomeProd[i], valorProd[i]*quantProd, totalICMS, totalIPI, totalAvulso, totalImposto);
                                    break;
                                }
                            }

                            if (!prodencontrado) {
                                System.out.println("Produto não encontrado. Deseja tentar novamente? (S/N)");
                                opcao2 = in.next().charAt(0);
                                in.nextLine();
                                if (opcao2 != 'S' && opcao2 != 's') {
                                    break;
                                }
                            }
                        }while(!prodencontrado);
                    }else{
                        if(busca.equalsIgnoreCase("espaco")){
                        System.out.print("Digite o espaço do estoque (1 a 20): ");
                        espaco = in.nextInt() - 1;
                        in.nextLine();
                    
                            if (espaco >= 0 && espaco < ultCad && nomeProd[espaco] != null) {
                                System.out.print("Quantidade de produtos: ");
                                quantProd = in.nextInt();
                                totalImposto = (valorProd[espaco] * quantProd) * (impostoProd[espaco][0] / 100 + impostoProd[espaco][1] / 100 + impostoProd[espaco][2] / 100); //calcula o valor do imposto total
                                totalICMS = (valorProd[espaco] * quantProd) * (impostoProd[espaco][0]/100); //calcula o valor do ICMS separadamente
                                totalIPI = (valorProd[espaco] * quantProd) * (impostoProd[espaco][1]/100); //calcula o valor do IPI separadamente
                                totalAvulso = (valorProd[espaco] * quantProd) * (impostoProd[espaco][2]/100); //calcula o valor do imposto avulso separadamente
                                System.out.println("\n\n");
                                System.out.printf("Nome: %s\nValor: R$%.2f\nValor ICMS: R$%.2f\nValor IPI: R$%.2f\nValor avulso: R$%.2f\nTotal de Impostos: R$%.2f\n",
                                    nomeProd[espaco], valorProd[espaco]*quantProd, totalICMS, totalIPI, totalAvulso, totalImposto);
                                break;
                            } else {
                                System.out.println("Espaço inválido ou sem produto cadastrado.");
                            }
                        }else{
                            System.out.println("Opção de busca inválida!");
                        }
                    }
                    break;
                case 4:
                    System.out.println("< RELATÓRIO GERAL DO ESTOQUE >");
                    if(ultCad!=0){
                        for(int i=0;i<ultCad;i++){
                            System.out.println("\nProduto: "+nomeProd[i]+"\nValor unitário: R$ "+valorProd[i]+"\nQuantidade em estoque: "+estoqueProd[i]+"\nEspaço do estoque: "+(i+1));
                            mediaUnit += valorProd[i];
                        }
                        mediaUnit = mediaUnit/ultCad;
                        System.out.printf("\nValor unitário médio: R$%.2f", mediaUnit);
                    }else{
                        System.out.println("\nNenhum produto cadastrado. Cadastre um produto primeiro.");
                    }
                    break;
                case 0:
                    System.out.println("\n\n\n\n\n\n\n\n");
                    System.out.print("Encerrando o programa. Obrigado por utilizar Prodify!"); //quebra da repetição infinita resulta na impressao da mensagem de encerramento
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }while(opcao!=0);
        in.close();
        System.out.println("\n\n");
    }
}

