package algoritmos.recursivos;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {

    public static int fibonacciRecursivo(int n){
        if ((n == 0) || (n == 1)){
            return n;
        } else {
            return fibonacciRecursivo(n - 1) + fibonacciRecursivo(n - 2);
        }
    }
    public static int fibonacciIterativo(int n) {
        int i, fibonacci = 0;
        int fibAnterior = 0;
        int fibPosterior = 1;

        if ((n == 0) || (n == 1)){
            return n;
        } else {
            for (i = 2; i <= n; i++){
                fibonacci = fibAnterior + fibPosterior;
                fibAnterior = fibPosterior;
                fibPosterior = fibonacci;
            }
            return fibonacci;
        }
    }
    public static int fibonacciRecursivoMemorizado(int n) {
        Map<Integer, Integer> memo = new HashMap<>();
        return fibonacciMemoHelper(n, memo);
    }

    private static int fibonacciMemoHelper(int n, Map<Integer, Integer> memo) {
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        int resultado;
        if (n <= 1) {
            resultado = n;
        } else {
            resultado = fibonacciMemoHelper(n - 1, memo) + fibonacciMemoHelper(n - 2, memo);
        }

        memo.put(n, resultado);
        return resultado;
    }

    public static void main(String[] args) {
        int n = 16;
        long inicioRecursivo = System.nanoTime();
        int resultadoRecursivo = fibonacciRecursivo(n);
        long fimRecursivo = System.nanoTime();
        long tempoRecursivo = fimRecursivo - inicioRecursivo;
        long inicioIterativo = System.nanoTime();
        int resultadoIterativo = fibonacciIterativo(n);
        long fimIterativo = System.nanoTime();
        long tempoIterativo = fimIterativo - inicioIterativo;
        long inicioMemo = System.nanoTime();
        int resultadoMemo = fibonacciRecursivoMemorizado(n);
        long fimMemo = System.nanoTime();
        long tempoMemo = fimMemo - inicioMemo;
        System.out.println("Valor de n: " + n);
        System.out.println("\n--- Fibonacci Recursivo (Ingênuo) ---");
        System.out.println("Resultado: " + resultadoRecursivo);
        System.out.println("Tempo: " + tempoRecursivo + " ns");
        System.out.println("\n--- Fibonacci Iterativo ---");
        System.out.println("Resultado: " + resultadoIterativo);
        System.out.println("Tempo: " + tempoIterativo + " ns");
        System.out.println("\n--- Fibonacci com Memoização ---");
        System.out.println("Resultado: " + resultadoMemo);
        System.out.println("Tempo: " + tempoMemo + " ns");
        System.out.println("\n--- Diferenças de Tempo ---");
        System.out.println("Recursivo x Iterativo: " + (tempoRecursivo - tempoIterativo) + " ns");
        System.out.println("Recursivo x Memoização: " + (tempoRecursivo - tempoMemo) + " ns");
    }
}
