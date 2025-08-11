package estruturas.lineares.estaticas.vetor;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class VetorTamanhoFixo<T> implements TDAVetor<T>, Iterable<T> {

    private int tamanho;
    private T[] elementos;
    //O SuppressWarnings evita mensagens na hora da compilação .
    //O unchecked é usado porque estou criando um array genérico.
    @SuppressWarnings("unchecked")
    public VetorTamanhoFixo(int capacidade) {
        if (capacidade < 0) {
            throw new IllegalArgumentException("A capacidade do vetor não pode ser negativa");
        }
        this.tamanho = 0;
        this.elementos = (T[]) new Object[capacidade];
    }

    @Override
    public int tamanho() {
        return this.tamanho;
    }

    @Override
    public boolean estaVazio() {
        return this.tamanho == 0;
    }

    @Override
    public void verificarIndice(int indice, int minimo, int maximo) {
        if (indice < minimo || indice > maximo) {
            throw new IndexOutOfBoundsException(
                    String.format("Índice: %d, Tamanho: %d", indice, this.tamanho));
        }
    }

    @Override
    public void verificarCapacidade() {
        if (this.tamanho == this.elementos.length) {
            throw new IllegalStateException("Capacidade máxima atingida.");
        }
    }

    @Override
    public int obterIndiceDo(T elemento) {
        for (int indice = 0; indice < tamanho; indice++) {
            if (Objects.equals(elemento, this.elementos[indice])) {
                return indice;
            }
        }
        return -1;
    }

    @Override
    public boolean contem(T elemento) {
        return obterIndiceDo(elemento) != -1;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void limpar() {
        this.elementos = (T[]) new Object[this.tamanho];
        this.tamanho = 0;
    }

    @Override
    public T obter(int indice) throws IndexOutOfBoundsException {
        verificarIndice(indice, 0, this.tamanho - 1);
        return this.elementos[indice];
    }

    @Override
    public void atualizar(int indice, T elemento) throws IndexOutOfBoundsException {
        verificarIndice(indice, 0, this.tamanho - 1);
        this.elementos[indice] = elemento;
    }

    @Override
    public void adicionar(T elemento) {
        verificarCapacidade();
        this.elementos[this.tamanho++] = elemento;
    }

    @Override
    public void adicionar(int indice, T elemento) throws IndexOutOfBoundsException {
        verificarCapacidade();
        verificarIndice(indice, 0, this.tamanho);
        System.arraycopy(this.elementos, indice, this.elementos, indice + 1, this.tamanho - indice);
        this.elementos[indice] = elemento;
        this.tamanho++;
    }

    @Override
    public void adicionarInicio(T elemento) {
        adicionar(0, elemento);
    }

    @Override
    public T remover(int indice) throws IndexOutOfBoundsException {
        verificarIndice(indice, 0, this.tamanho - 1);
        T removido = this.elementos[indice];
        System.arraycopy(this.elementos, indice + 1, this.elementos, indice, this.tamanho - indice - 1);
        this.elementos[--this.tamanho] = null;
        return removido;
    }

    @Override
    public void removerElemento(T elemento) {
        int indice = obterIndiceDo(elemento);
        if (indice != -1) {
            remover(indice);
        }
    }

    @Override
    public void removerInicio() {
        remover(0);
    }

    @Override
    public void removerFim() {
        if (estaVazio()) {
            throw new IllegalStateException("Vetor está vazio.");
        }
        this.elementos[--this.tamanho] = null;
    }

    @Override
    public String imprimir() {
        return Arrays.toString(Arrays.copyOf(this.elementos, this.tamanho));
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int atual = 0;

            @Override
            public boolean hasNext() {
                return atual < tamanho;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException();
                }
                return elementos[atual++];
            }
        };
    }
}
