import java.io.BufferedReader;
import java.io.FileReader;

public class Program {

    public static void main(String[] args) {
        long tempoInicial = System.currentTimeMillis();
        System.out.println("\033[H\033[2J");
        Halloween halloween = new Halloween();
        String arquvio = "input.txt";
        try {
            FileReader reader = new FileReader(arquvio);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                halloween.solve(line);
            }
            bufferedReader.close();
            reader.close();
        } catch (Exception e) {
            e.getMessage();
        }
        long tempoFinal = System.currentTimeMillis();

        System.out.println( "Tempo de execução: "+  (tempoFinal - tempoInicial) / 1000d + "s");
    }
}