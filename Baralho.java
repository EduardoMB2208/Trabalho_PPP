package cartas.framework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baralho {
    protected List<Carta> cartas = new ArrayList<>();

    public void adicionarCarta(Carta c) { cartas.add(c); }
    public void embaralhar() { Collections.shuffle(cartas); }
    public Carta comprar() {
        if (cartas.isEmpty()) return null;
        return cartas.remove(0);
    }
}