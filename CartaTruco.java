package cartas.truco;

import cartas.framework.Carta;

public class CartaTruco extends Carta {
    private final String valor;
    private final String naipe;

    public CartaTruco(String valor, String naipe) {
        this.valor = valor;
        this.naipe = naipe;
    }

    public String getValor() { return valor; }
    public String getNaipe() { return naipe; }

    // Implementação obrigatória do framework
    @Override
    public String toString() {
        String simbolo = switch (naipe) {
            case Constants.COPAS -> Constants.SIMBOLO_COPAS;
            case Constants.OUROS -> Constants.SIMBOLO_OUROS;
            case Constants.ESPADAS -> Constants.SIMBOLO_ESPADAS;
            case Constants.PAUS -> Constants.SIMBOLO_PAUS;
            default -> "";
        };
        return valor + simbolo;
    }

    // Lógica de força baseada no documento (Manilhas Fixas)
    public int getForca() {
        // Manilhas fixas (Regra 2 do documento)
        if (valor.equals(Constants.QUATRO) && naipe.equals(Constants.PAUS)) return 28;    // Zape
        if (valor.equals(Constants.SETE) && naipe.equals(Constants.COPAS)) return 27;     // 7 de Copas
        if (valor.equals(Constants.ESPADAS) && valor.equals(Constants.QUATRO)) return 26; // Espadilha (ajustado conforme lógica original)
        // Nota: No seu código original, Espadilha era 4 de Espadas.
        if (valor.equals(Constants.QUATRO) && naipe.equals(Constants.ESPADAS)) return 26;
        if (valor.equals(Constants.SETE) && naipe.equals(Constants.OUROS)) return 25;     // 7 de Ouros

        // Outras cartas (Hierarquia decrescente)
        return switch (valor) {
            case Constants.TRES -> 24;
            case Constants.DOIS -> 23;
            case Constants.AS -> 22;
            case Constants.REI -> 21;
            case Constants.VALETE -> 20;
            case Constants.DAMA -> 19;
            case Constants.SETE -> 18;
            case Constants.SEIS -> 17;
            case Constants.CINCO -> 16;
            case Constants.QUATRO -> 15;
            default -> 0;
        };
    }
}