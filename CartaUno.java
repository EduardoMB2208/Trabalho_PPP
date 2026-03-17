package cartas.uno;

import cartas.framework.Carta;

public class CartaUno extends Carta {
    private CorUno cor;
    private TipoCartaUno tipo;
    private int numero; // De 0 a 9. Se for carta de ação, podemos deixar como -1

    public CartaUno(CorUno cor, TipoCartaUno tipo, int numero) {
        this.cor = cor;
        this.tipo = tipo;
        this.numero = numero;
    }

    // Getters
    public CorUno getCor() { return cor; }
    public TipoCartaUno getTipo() { return tipo; }
    public int getNumero() { return numero; }

    // Setter para a cor (vital para quando jogarem um curinga e escolherem a nova cor!) [cite: 57]
    public void setCor(CorUno cor) { this.cor = cor; }

    // Cumprindo o contrato do framework (Polimorfismo!)
    @Override
    public String toString() {
        if (tipo == TipoCartaUno.NUMERO) {
            return cor + " " + numero;
        } else if (tipo == TipoCartaUno.CURINGA || tipo == TipoCartaUno.MAIS_QUATRO) {
            // Se for curinga mas já tiverem escolhido a cor, mostra a cor. Senão, só mostra o nome.
            return tipo.name() + (cor != CorUno.SEM_COR ? " (" + cor + ")" : "");
        } else {
            return cor + " " + tipo.name();
        }
    }
}