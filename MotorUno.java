package cartas.uno;

import cartas.framework.MotorJogo;
import cartas.framework.Jogador;
import cartas.framework.Carta;
import java.util.Scanner;

public class MotorUno extends MotorJogo {
    private BaralhoUno baralho;
    private CartaUno cartaNoTopo;
    private boolean sentidoHorario = true;
    private int acumuloCartasParaComprar = 0;
    private Scanner leitor = new Scanner(System.in);

    public MotorUno() {
        this.baralho = new BaralhoUno();
    }

    public void prepararJogo() {
        for (Jogador j : jogadores) {
            for (int i = 0; i < 7; i++) {
                j.receberCarta(baralho.comprar());
            }
        }
        do {
            cartaNoTopo = (CartaUno) baralho.comprar();
        } while (cartaNoTopo.getTipo() == TipoCartaUno.CURINGA ||
                cartaNoTopo.getTipo() == TipoCartaUno.MAIS_QUATRO);

        System.out.println("Jogo Iniciado! Carta na mesa: " + cartaNoTopo);
    }

    @Override
    protected void executarJogada(Jogador j) {
        // Verifica se o jogador atual precisa sofrer penalidade de compra (+2 ou +4)
        if (acumuloCartasParaComprar > 0) {
            System.out.println(j.getNome() + " foi penalizado e comprou " + acumuloCartasParaComprar + " cartas!");
            for (int i = 0; i < acumuloCartasParaComprar; i++) {
                j.receberCarta(baralho.comprar());
            }
            acumuloCartasParaComprar = 0;
            return; // O jogador perde a vez após comprar (Regra 5.1.4)
        }

        System.out.println("\n--- Vez de: " + j.getNome() + " ---");
        System.out.println("Mesa: [" + cartaNoTopo + "]");
        exibirMao(j);

        boolean jogou = false;
        while (!jogou) {
            System.out.println("Escolha o índice (0 a " + (j.getMao().size()-1) + ") ou -1 para comprar:");
            int escolha = leitor.nextInt();

            if (escolha == -1) {
                CartaUno comprada = (CartaUno) baralho.comprar();
                System.out.println("Comprou: " + comprada);
                if (validarJogada(comprada)) {
                    System.out.println("Pode jogar " + comprada + "! Jogar? (1-Sim/0-Não)");
                    if (leitor.nextInt() == 1) {
                        aplicarJogada(j, comprada);
                        jogou = true;
                    } else {
                        j.receberCarta(comprada);
                        jogou = true;
                    }
                } else {
                    j.receberCarta(comprada);
                    System.out.println("Não pode jogar. Passando a vez...");
                    jogou = true;
                }
            } else if (escolha >= 0 && escolha < j.getMao().size()) {
                CartaUno escolhida = (CartaUno) j.getMao().get(escolha);
                if (validarJogada(escolhida)) {
                    j.jogarCarta(escolhida); // Remove da mão antes de aplicar efeito
                    aplicarJogada(j, escolhida);
                    jogou = true;
                } else {
                    System.out.println("Carta inválida!");
                }
            }
        }
    }

    private void exibirMao(Jogador j) {
        for (int i = 0; i < j.getMao().size(); i++) {
            System.out.println(i + ": " + j.getMao().get(i));
        }
    }

    private boolean validarJogada(CartaUno c) {
        return c.getCor() == CorUno.SEM_COR ||
                c.getCor() == cartaNoTopo.getCor() ||
                (c.getTipo() == cartaNoTopo.getTipo() && c.getTipo() != TipoCartaUno.NUMERO) ||
                (c.getTipo() == TipoCartaUno.NUMERO && c.getNumero() == cartaNoTopo.getNumero());
    }

    private void aplicarJogada(Jogador j, CartaUno c) {
        cartaNoTopo = c;

        // Trata escolha de cor para Curingas (Regra 5.1.4)
        if (c.getCor() == CorUno.SEM_COR) {
            System.out.println("Escolha a cor (1-VERMELHO, 2-AZUL, 3-VERDE, 4-AMARELO):");
            int corEsc = leitor.nextInt();
            c.setCor(CorUno.values()[corEsc - 1]);
        }

        // Aplica efeitos especiais
        switch (c.getTipo()) {
            case INVERTER -> {
                sentidoHorario = !sentidoHorario;
                System.out.println("SENTIDO INVERTIDO!");
            }
            case BLOQUEIO -> {
                passarVez(); // Pula o próximo jogador
                System.out.println("PRÓXIMO JOGADOR BLOQUEADO!");
            }
            case MAIS_DOIS -> acumuloCartasParaComprar = 2;
            case MAIS_QUATRO -> acumuloCartasParaComprar = 4;
            default -> {}
        }
    }

    @Override
    protected void passarVez() {
        int total = jogadores.size();
        if (sentidoHorario) {
            jogadorAtualIndice = (jogadorAtualIndice + 1) % total;
        } else {
            jogadorAtualIndice = (jogadorAtualIndice - 1 + total) % total;
        }
    }

    @Override
    protected boolean verificarVitoria(Jogador j) {
        return j.getQuantidadeCartas() == 0;
    }

    @Override
    protected void finalizarJogo(Jogador vencedor) {
        System.out.println("\n***************************");
        System.out.println("VENCEDOR: " + vencedor.getNome());
        System.out.println("***************************");
        jogoFinalizado = true;
    }
}