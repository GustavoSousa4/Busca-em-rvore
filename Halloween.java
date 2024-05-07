public class Halloween {
    private final class No {
        private No esquerda;
        private No direita;
        private int candy;

        public No(int candy, No esquerda, No direita) {
            this.candy = candy;
            this.esquerda = esquerda;
            this.direita = direita;
        }
    }

    private No raiz;
    private int index;
    private String line;

    public void solve(String line) {
        this.index = 0;
        this.line = line;
        raiz = parseTree();
        int candy = somaRecursiva(raiz);
        int ruas = arestasPercorridas();
        System.out.println(ruas + " " + candy);
    }

    private No parseTree() {
        if (index >= line.length())
            throw new IllegalArgumentException("The index cannot be greater than or equal to the length of the line.");

        while (line.charAt(index) == ' ')
            index++;

        if (line.charAt(index) == '(') {
            index++;
            No left = parseTree();
            No right = parseTree();
            index++;
            return new No(0, left, right);
        } else {
            int start = index;
            while (index < line.length() && Character.isDigit(line.charAt(index)))
                index++;
            int end = index;
            return new No(Integer.parseInt(line.substring(start, end)), null, null);
        }
    }

    /**
     * A solução para os métodos com referências a aresta, foi desenvolvido durante
     * uma conversa com colegas do período vespertino, 
     * antigos monitores do Departamento de Dados
     */
    public int arestasPercorridas() {

        No no = raiz;

        if (no == null)
            return 0;

        else {
            if (alturaRecursiva(no.esquerda) < alturaRecursiva(no.direita))
                return somaArestas(no.esquerda, true) + somaArestas(no.direita, false);
            else
                return somaArestas(no.esquerda, false) + somaArestas(no.direita, true);
        }
    }

    private int somaArestas(No atual, boolean isMenor) {
        if (atual == null)
            return 0;
        if (atual.candy != 0) {
            if (isMenor)
                return 2;
            else
                return 1;
        }

        No no = atual;

        int cont = 0;

        if (isMenor)
            cont += 2;
        else
            cont += 1;

        if (isMenor)
            return cont + somaArestas(no.esquerda, true) + somaArestas(no.direita, true);
        else if (alturaRecursiva(no.esquerda) < alturaRecursiva(no.direita))
            return cont + somaArestas(no.esquerda, true) + somaArestas(no.direita, false);
        else
            return cont + somaArestas(no.esquerda, false) + somaArestas(no.direita, true);
    }

    private int alturaRecursiva(No no) {
        /**
         * Método criadoa partir de referência em C
         * fonte:
         * https://wagnergaspar.com/como-calcular-a-altura-de-uma-arvore-binaria-de-busca/
         **/
        if (no == null)
            return 0;
        int esq = alturaRecursiva(no.esquerda);
        int dir = alturaRecursiva(no.direita);
        if (esq > dir)
            return esq + 1;
        return dir + 1;
    }

    private int somaRecursiva(No no) {
        /**
         * Método criado a partir de referência em C
         * fonte: https://acervolima.com/soma-de-todos-os-nos-em-uma-arvore-binaria/
         **/
        if (no == null)
            return 0;
        return (no.candy + somaRecursiva(no.esquerda) + somaRecursiva(no.direita));
    }
}
