package cartas.truco;

// cartas.truco.JogadorTruco.java
public class JogadorTruco {
    private final int id;
    private final Mao mao;
    private final int duplaId;

    public JogadorTruco(int id, int duplaId) {
        this.id = id;
        this.duplaId = duplaId;
        this.mao = new Mao();
    }

    public int getId() { return id; }
    public int getDuplaId() { return duplaId; }
    public Mao getMao() { return mao; }
}