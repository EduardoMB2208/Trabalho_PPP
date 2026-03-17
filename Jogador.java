package cartas.framework;

import java.util.ArrayList;
import java.util.List;

// Classe que representa um jogador genérico no framework
public class Jogador {
    private String nome;
    protected List<Carta> mao; // A "mão" de cartas do jogador

    public Jogador(String nome) {
        this.nome = nome;
        this.mao = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void receberCarta(Carta carta) {
        mao.add(carta);
    }

    public void jogarCarta(Carta carta) {
        mao.remove(carta);
    }

    // Método útil para verificar a condição de vitória do Uno depois!
    public int getQuantidadeCartas() {
        return mao.size();
    }

    public List<Carta> getMao() {
        return mao;
    }
}