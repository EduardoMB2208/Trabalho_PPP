package cartas.uno;
import cartas.framework.Baralho;

// A classe BaralhoUno "é um" Baralho. Ela herda tudo o que fizemos no ‘framework’.
class BaralhoUno extends Baralho {

    // O construtor é chamado automaticamente quando criamos um "new BaralhoUno()"
    public BaralhoUno() {
        montarBaralho();
        embaralhar(); // Usamos o método do framework para já deixar pronto para jogo!
    }

    // Método privado porque apenas o próprio baralho precisa saber como se montar
    private void montarBaralho() {
        // Criamos um array (lista fixa) com as 4 cores principais para facilitar o laço
        CorUno[] coresPrincipais = {CorUno.VERMELHO, CorUno.AZUL, CorUno.VERDE, CorUno.AMARELO};

        // 1º Laço: Passa por cada uma das 4 cores
        for (CorUno cor : coresPrincipais) {
            // Adiciona a única carta '0' da cor atual
            adicionarCarta(new CartaUno(cor, TipoCartaUno.NUMERO, 0));

            // 2º Laço: Adiciona duas cópias dos números de 1 a 9 para a cor atual
            for (int numero = 1; numero <= 9; numero++) {
                adicionarCarta(new CartaUno(cor, TipoCartaUno.NUMERO, numero));
                adicionarCarta(new CartaUno(cor, TipoCartaUno.NUMERO, numero));
            }

            // 3º Laço: Adiciona duas cópias de cada carta de ação para a cor atual
            for (int i = 0; i < 2; i++) {
                // Passamos -1 no número porque cartas de ação não usam esse valor
                adicionarCarta(new CartaUno(cor, TipoCartaUno.MAIS_DOIS, -1));
                adicionarCarta(new CartaUno(cor, TipoCartaUno.BLOQUEIO, -1));
                adicionarCarta(new CartaUno(cor, TipoCartaUno.INVERTER, -1));
            }
        }

        // 4º Laço: Fora das cores, adicionamos os 4 Curingas e os 4 Mais Quatro
        for (int i = 0; i < 4; i++) {
            adicionarCarta(new CartaUno(CorUno.SEM_COR, TipoCartaUno.CURINGA, -1));
            adicionarCarta(new CartaUno(CorUno.SEM_COR, TipoCartaUno.MAIS_QUATRO, -1));
        }
    }
}