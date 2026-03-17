package cartas.framework;

import java.util.ArrayList;
import java.util.List;

public abstract class MotorJogo {
    protected List<Jogador> jogadores = new ArrayList<>();
    protected int jogadorAtualIndice = 0;
    protected boolean jogoFinalizado = false;

    public void adicionarJogador(Jogador j) {
        jogadores.add(j);
    }

    public void iniciarTurno() {
        Jogador atual = jogadores.get(jogadorAtualIndice);
        System.out.println("Vez do jogador: " + atual.getNome());

        executarJogada(atual); // Definido pelo jogo (seja o Truco ou  o Uno)

        if (verificarVitoria(atual)) {
            finalizarJogo(atual);
        } else {
            passarVez();
        }
    }

    protected void passarVez() {
        // A lógica nais rigida que ele falou: 0 -> 1 -> 2 -> 3 -> 0
        jogadorAtualIndice = (jogadorAtualIndice + 1) % jogadores.size();
    }

    // Metodos abstratos que o Truco e o Uno implementarão de formas diferentes
    protected abstract void executarJogada(Jogador j);
    protected abstract boolean verificarVitoria(Jogador j);
    protected abstract void finalizarJogo(Jogador vencedor);
}