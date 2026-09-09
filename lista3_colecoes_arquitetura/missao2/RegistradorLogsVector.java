package lista3_colecoes_arquitetura.missao2;

import java.util.Vector;

/**
 * Registrador de Auditoria e Logs do E-Commerce utilizando Vector.
 * 
 * [CONCEITO DE SYNCHRONIZED E CONCORRÊNCIA MULTITHREAD: Vector vs ArrayList]:
 * 
 * 1. O que é a sincronização (synchronized)?
 *    - Praticamente todos os métodos públicos de mutação da classe Vector (ex.: add, remove, get, size)
 *      são declarados com a palavra-chave 'synchronized'.
 *    - Isso significa que a JVM utiliza o lock intrínseco (Monitor) da instância do Vector. Apenas UMA
 *      thread pode executar um método sincronizado daquela instância por vez, bloqueando as demais threads
 *      concorrentes até a liberação do lock.
 * 
 * 2. Vector (Thread-Safe):
 *    - Seguro para cenários com múltiplas threads gravando e lendo concorrentemente sem corromper a estrutura
 *      interna de índices e contadores (modCount / elementCount).
 *    - Custo: Há um overhead de performance devido ao travamento/liberação de locks mesmo em cenários single-thread.
 * 
 * 3. ArrayList (Non-Thread-Safe):
 *    - Não possui nenhum mecanismo de sincronização interna em seus métodos.
 *    - Em ambiente multithread sem sincronização externa, duas threads adicionando itens simultaneamente
 *      podem sobrescrever o mesmo slot do array interno ou causar ArrayIndexOutOfBoundsException e
 *      ConcurrentModificationException.
 *    - Vantagem: É muito mais rápido em ambientes de thread única por não ter custo de locks.
 */
public class RegistradorLogsVector {
    private Vector<String> logsAuditoria;

    public RegistradorLogsVector() {
        this.logsAuditoria = new Vector<>();
    }

    /**
     * Registra um evento de auditoria de forma segura para múltiplas threads.
     * @param mensagemLog Mensagem descritiva da operação.
     */
    public void registrarLog(String mensagemLog) {
        String timestamp = java.time.LocalTime.now().toString().substring(0, 8);
        String logFormatado = String.format("[%s] %s", timestamp, mensagemLog);
        logsAuditoria.add(logFormatado); // Chamada a método sincronizado (thread-safe)
        System.out.println("[AUDITORIA VECTOR] Log registrado: " + logFormatado);
    }

    public void exibirLogs() {
        System.out.println("\n--- Histórico de Logs de Auditoria (Vector Thread-Safe: " + logsAuditoria.size() + " registros) ---");
        for (String log : logsAuditoria) {
            System.out.println("  " + log);
        }
    }

    public Vector<String> getLogsAuditoria() {
        return logsAuditoria;
    }
}
