
package testeabrirfilepassword;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;

public class TesteAbrirFilePassword {

    // Caminho absoluto da pasta
    public static final String caminho = "/Users/caiocosta/Documents/IFMG/Periodo 03/Arquetetura e Organização de Computadores/Computação Paralela/TP02/senha/arquivosTP/";

    public static boolean testaSenha(String senha) {
        //necessário trocar o nome do arquivo de maneira iterativa
       //sugiro add um parâmetro para tal...
        try {
            ZipFile zipFile = new ZipFile(new File(caminho + "doc1.zip"));
            if (zipFile.isEncrypted()) {
                zipFile.setPassword(senha.toCharArray());
            }
            List fileHeaderList = zipFile.getFileHeaders();

            for (int i = 0; i < fileHeaderList.size(); i++) {
                FileHeader fileHeader = (FileHeader) fileHeaderList.get(i);
                zipFile.extractFile(fileHeader, caminho);
                System.out.println("Encontramos a senha e o arquivo");
                System.out.println("senha=" + senha);
                return true;
            }

        } catch (ZipException ex) {
            // Erro na extração do arquivo
            return false;
        }

        return false;
    }

    public static void forcaBruta() {
        int minAscii = 32;  // Espaço
        int maxAscii = 126; // Til (~)
        int maxLength = 3;  // Comprimento máximo da senha

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        gerarSenhas(minAscii, maxAscii, new StringBuilder(), maxLength, executor);
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void gerarSenhas(int minAscii, int maxAscii, StringBuilder sb, int maxLength, ExecutorService executor) {
        if (sb.length() == maxLength) {
            return;
        }

        for (int ascii = minAscii; ascii <= maxAscii; ascii++) {
            sb.append((char) ascii);
            String senha = sb.toString();
            executor.submit(() -> {
                System.out.println("Testando senha: " + senha);
                if (testaSenha(senha)) {
                    System.out.println("Senha correta: " + senha);
                    System.exit(0); // Termina o programa se a senha correta for encontrada
                }
            });
            gerarSenhas(minAscii, maxAscii, sb, maxLength, executor);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        File directory = new File(caminho);
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("Diretório não encontrado: " + directory.getAbsolutePath());
            return;
        }

        File zipFile = new File(caminho + "doc1.zip");
        if (!zipFile.exists()) {
            System.out.println("Arquivo ZIP não encontrado: " + zipFile.getAbsolutePath());
            return;
        }

        System.out.println("Iniciando força bruta para encontrar a senha...");
        forcaBruta();
    }
}
