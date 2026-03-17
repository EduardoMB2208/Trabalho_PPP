package cartas.truco;

import cartas.framework.Baralho;
import java.util.Arrays;
import java.util.List;

public class BaralhoTruco extends Baralho {

    public BaralhoTruco() {
        montarBaralho();
        embaralhar();
    }

    private void montarBaralho() {
        List<String> naipes = Arrays.asList(Constants.COPAS, Constants.OUROS, Constants.ESPADAS, Constants.PAUS);
        List<String> valores = Arrays.asList(Constants.AS, Constants.DOIS, Constants.TRES, Constants.QUATRO,
                Constants.CINCO, Constants.SEIS, Constants.SETE,
                Constants.VALETE, Constants.DAMA, Constants.REI);

        for (String v : valores) {
            for (String n : naipes) {
                // No truco de manilha fixa, algumas combinações de 4 e 7 são removidas ou tratadas
                // Aqui adicionamos todas e a força tratará a importância delas
                adicionarCarta(new CartaTruco(v, n));
            }
        }
    }
}