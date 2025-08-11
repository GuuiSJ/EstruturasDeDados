package estruturas.lineares.dinamicas.pilha;

import java.util.Iterator;
import java.util.Objects;

import estruturas.lineares.dinamicas.lista.IListaSimplesmenteEncadeada;
import estruturas.lineares.dinamicas.lista.ListaSimplesmenteEncadeada;

public class PilhaComListaSimplesmenteEncadeada<T> implements IPilha<T> {

    private final IListaSimplesmenteEncadeada<T> lista;

    public PilhaComListaSimplesmenteEncadeada() {
        this.lista = new ListaSimplesmenteEncadeada<>();
    }

    @Override
    public int tamanho() {
        return lista.tamanho();
    }

    @Override
    public boolean estaVazia() {
        return lista.estaVazia();
    }

    @Override
    public void limpar() {
        lista.limpar();
    }

    @Override
    public boolean contem(T dado) {
        return lista.contem(dado);
    }

    @Override
    public T obterTopo() {
        if (estaVazia()) {
            throw new IllegalStateException("A pilha está vazia.");
        }
        return lista.posicao(T dado);   
    }

    @Override
    public void empilhar(T dado) {
        lista.adicionar(dado);
    }

    @Override
    public T desempilhar() {
        if (estaVazia()) {
            throw new IllegalStateException("A pilha está vazia.");
        }
        return lista.removerInicio();
    }

    @Override
    public int distancia(T dado) {
        return indexOf(dado);
    }

    @Override
    public String imprimir() {
        return lista.imprimir();
    }

    @Override
    public Iterator<T> iterator() {
        return lista.iterator();
    }
}