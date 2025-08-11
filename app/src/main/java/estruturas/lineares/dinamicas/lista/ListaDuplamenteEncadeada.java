package estruturas.lineares.dinamicas.lista;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaDuplamenteEncadeada<T> implements IListaDuplamenteEncadeada<T>, Iterable<T> {

    private INoListaDuplamenteEncadeada<T> noCabeca;
    private int quantidadeNos;

    public ListaDuplamenteEncadeada() {
        this.noCabeca = new NoListaDuplamenteEncadeada<T>(null);
        this.noCabeca.definirProximoNo(null);
        this.noCabeca.definirNoAnterior(null);
        this.quantidadeNos = 0;
    }

    @Override
    public INoListaDuplamenteEncadeada<T> obterPrimeiroNo() {
        return this.noCabeca.obterProximoNo();
    }

    @Override
    public INoListaDuplamenteEncadeada<T> obterUltimoNo() {
        return this.noCabeca.obterNoAnterior();
    }

    @Override
    public void limpar() {
        this.noCabeca.definirProximoNo(null);
        this.noCabeca.definirNoAnterior(null);
        this.quantidadeNos = 0;
    }

    @Override
    public int tamanho() {
        return this.quantidadeNos;
    }

    @Override
    public boolean contem(T dado) {
        INoListaDuplamenteEncadeada<T> atual = obterPrimeiroNo();
        while (atual != null) {
            if (atual.obterDado().equals(dado)) {
                return true;
            }
            atual = atual.obterProximoNo();
        }
        return false;
    }

    @Override
    public boolean estaVazia() {
        return this.quantidadeNos == 0;
    }

    @Override
    public void adicionar(T dado) {
        adicionarFim(dado);
    }

    @Override
    public void adicionar(T dado, int posicao) {
        if (posicao < 0 || posicao > quantidadeNos) {
            throw new IndexOutOfBoundsException("Posição inválida");
        }

        NoListaDuplamenteEncadeada<T> novoNo = new NoListaDuplamenteEncadeada<>(dado);

        // Inserir no início
        if (posicao == 0) {
            INoListaDuplamenteEncadeada<T> primeiro = obterPrimeiroNo();
            novoNo.definirProximoNo(primeiro);
            novoNo.definirNoAnterior(null);
            this.noCabeca.definirProximoNo(novoNo);

            if (primeiro != null) {
                ((NoListaDuplamenteEncadeada<T>) primeiro).definirNoAnterior(novoNo);
            }
            if (quantidadeNos == 0) {
                this.noCabeca.definirNoAnterior(novoNo);
            }
        }
        // Inserir no final
        else if (posicao == quantidadeNos) {
            adicionarFim(dado);
            return;
        }
        // Inserir no meio
        else {
            INoListaDuplamenteEncadeada<T> atual = obterPrimeiroNo();
            for (int i = 0; i < posicao - 1; i++) {
                atual = atual.obterProximoNo();
            }
            INoListaDuplamenteEncadeada<T> prox = atual.obterProximoNo();

            novoNo.definirProximoNo(prox);
            novoNo.definirNoAnterior(atual);
            ((NoListaDuplamenteEncadeada<T>) atual).definirProximoNo(novoNo);
            if (prox != null) {
                ((NoListaDuplamenteEncadeada<T>) prox).definirNoAnterior(novoNo);
            }
        }

        quantidadeNos++;
    }

    @Override
    public void adicionarFim(T dado) {
        NoListaDuplamenteEncadeada<T> novoNo = new NoListaDuplamenteEncadeada<>(dado);

        if (estaVazia()) {
            this.noCabeca.definirProximoNo(novoNo);
            this.noCabeca.definirNoAnterior(novoNo);
        } else {
            INoListaDuplamenteEncadeada<T> ultimo = obterUltimoNo();
            ((NoListaDuplamenteEncadeada<T>) ultimo).definirProximoNo(novoNo);
            novoNo.definirNoAnterior(ultimo);
            this.noCabeca.definirNoAnterior(novoNo);
        }
        quantidadeNos++;
    }

    @Override
    public void removerInicio() {
        if (estaVazia()) {
            throw new NoSuchElementException("Lista vazia");
        }

        INoListaDuplamenteEncadeada<T> primeiro = obterPrimeiroNo();
        INoListaDuplamenteEncadeada<T> proximo = primeiro.obterProximoNo();

        this.noCabeca.definirProximoNo(proximo);
        if (proximo != null) {
            ((NoListaDuplamenteEncadeada<T>) proximo).definirNoAnterior(null);
        } else {
            this.noCabeca.definirNoAnterior(null);
        }
        quantidadeNos--;
    }

    @Override
    public void removerFim() {
        if (estaVazia()) {
            throw new NoSuchElementException("Lista vazia");
        }

        INoListaDuplamenteEncadeada<T> ultimo = obterUltimoNo();
        INoListaDuplamenteEncadeada<T> penultimo = ultimo.obterNoAnterior();

        if (penultimo != null) {
            ((NoListaDuplamenteEncadeada<T>) penultimo).definirProximoNo(null);
            this.noCabeca.definirNoAnterior(penultimo);
        } else {
            this.noCabeca.definirProximoNo(null);
            this.noCabeca.definirNoAnterior(null);
        }
        quantidadeNos--;
    }

    @Override
    public void remover(int posicao) {
        if (posicao < 0 || posicao >= quantidadeNos) {
            throw new IndexOutOfBoundsException("Posição inválida");
        }

        if (posicao == 0) {
            removerInicio();
            return;
        }
        if (posicao == quantidadeNos - 1) {
            removerFim();
            return;
        }

        INoListaDuplamenteEncadeada<T> atual = obterPrimeiroNo();
        for (int i = 0; i < posicao; i++) {
            atual = atual.obterProximoNo();
        }

        INoListaDuplamenteEncadeada<T> anterior = atual.obterNoAnterior();
        INoListaDuplamenteEncadeada<T> proximo = atual.obterProximoNo();

        ((NoListaDuplamenteEncadeada<T>) anterior).definirProximoNo(proximo);
        ((NoListaDuplamenteEncadeada<T>) proximo).definirNoAnterior(anterior);

        quantidadeNos--;
    }

    @Override
    public String imprimir() {
        StringBuilder stringBuilder = new StringBuilder();
        INoListaDuplamenteEncadeada<T> noAtual = this.obterPrimeiroNo();
        while (noAtual != null) {
            stringBuilder.append(noAtual.obterDado().toString());
            noAtual = noAtual.obterProximoNo();
            if (noAtual != null) {
                stringBuilder.append(" -> ");
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            INoListaDuplamenteEncadeada<T> noAtual = obterPrimeiroNo();

            @Override
            public boolean hasNext() {
                return noAtual != null;
            }

            @Override
            public T next() throws NoSuchElementException {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else {
                    T dado = noAtual.obterDado();
                    noAtual = noAtual.obterProximoNo();
                    return dado;
                }
            }
        };
    }
}
