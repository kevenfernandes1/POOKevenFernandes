package lista3_colecoes_arquitetura.missao2;

import java.util.Vector;

// O Vector possui seus metodos sincronizados (synchronized), o que garante
// seguranca em ambientes multithread (thread-safe), bloqueando acessos simultaneos.
// Ja o ArrayList nao e sincronizado e pode gerar problemas se acessado
// por varias threads ao mesmo tempo, mas tem melhor desempenho em single-thread.
public class RegistradorLogsVector {
    private Vector<String> logsAuditoria;

    public RegistradorLogsVector() {
        this.logsAuditoria = new Vector<>();
    }

    public void registrarLog(String mensagemLog) {
        logsAuditoria.add(mensagemLog);
        System.out.println("Log registrado: " + mensagemLog);
    }

    public void exibirLogs() {
        System.out.println("\nLogs de auditoria (" + logsAuditoria.size() + "):");
        for (String log : logsAuditoria) {
            System.out.println("- " + log);
        }
    }

    public Vector<String> getLogsAuditoria() {
        return logsAuditoria;
    }
}
