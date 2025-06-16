
import java.util.Random;
import javax.swing.*;

public class Game {

    private static final int MAX_ATTEMPTS = 4;
    private static final int MAX_ROUNDS = 4;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 100;

    public static void main(String[] args) {
        String team1 = JOptionPane.showInputDialog("Digite o nome da Equipe 1:");
        String team2 = JOptionPane.showInputDialog("Digite o nome da Equipe 2:");

        int team1Score = 0;
        int team2Score = 0;

        Random rand = new Random();

        for (int round = 1; round <= MAX_ROUNDS; round++) {
            int secretNumber = rand.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
            JOptionPane.showMessageDialog(null, "Rodada " + round + " - Um número entre " + MIN_NUMBER + " e " + MAX_NUMBER + " foi gerado!");

            // Decide aleatoriamente quem começa
            boolean team1Starts = rand.nextBoolean();
            String firstTeam = team1Starts ? team1 : team2;
            String secondTeam = team1Starts ? team2 : team1;
            int firstTeamScore = 0, secondTeamScore = 0;

            for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) {
                // Primeira equipe tenta
                int guess1 = getGuess(firstTeam, attempt);
                if (guess1 == secretNumber) {
                    firstTeamScore = MAX_ATTEMPTS - attempt + 1;
                    JOptionPane.showMessageDialog(null, firstTeam + " acertou o número e ganhou " + firstTeamScore + " pontos!");
                    break;
                } else if (guess1 < secretNumber) {
                    JOptionPane.showMessageDialog(null, "Dica: o número é maior!");
                } else {
                    JOptionPane.showMessageDialog(null, "Dica: o número é menor!");
                }

                // Segunda equipe tenta
                int guess2 = getGuess(secondTeam, attempt);
                if (guess2 == secretNumber) {
                    secondTeamScore = MAX_ATTEMPTS - attempt + 1;
                    JOptionPane.showMessageDialog(null, secondTeam + " acertou o número e ganhou " + secondTeamScore + " pontos!");
                    break;
                } else if (guess2 < secretNumber) {
                    JOptionPane.showMessageDialog(null, "Dica: o número é maior!");
                } else {
                    JOptionPane.showMessageDialog(null, "Dica: o número é menor!");
                }
            }

            if (team1Starts) {
                team1Score += firstTeamScore;
                team2Score += secondTeamScore;
            } else {
                team1Score += secondTeamScore;
                team2Score += firstTeamScore;
            }

        JOptionPane.showMessageDialog(null,
    "Placar após a rodada " + round + ":\n" +
    team1 + ": " + team1Score + " pontos\n" +
    team2 + ": " + team2Score + " pontos"
);
        }

        // Resultado final
        String winnerMessage;
        if (team1Score > team2Score) {
            winnerMessage = "🏆 " + team1 + " venceu com " + team1Score + " pontos!";
        } else if (team2Score > team1Score) {
            winnerMessage = "🏆 " + team2 + " venceu com " + team2Score + " pontos!";
        } else {
            winnerMessage = "🤝 Empate! Ambas as equipes marcaram " + team1Score + " pontos!";
        }

      JOptionPane.showMessageDialog(null, "Fim do jogo!\n" + winnerMessage);
    }

    private static int getGuess(String teamName, int attempt) {
        while (true) {
            try {
                String input = JOptionPane.showInputDialog(teamName + " - Tentativa " + attempt + ": Digite seu palpite");
                if (input == null) System.exit(0); // Cancelar fecha o jogo
                int guess = Integer.parseInt(input);
                if (guess >= MIN_NUMBER && guess <= MAX_NUMBER) {
                    return guess;
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, digite um número entre " + MIN_NUMBER + " e " + MAX_NUMBER);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida! Digite um número inteiro.");
            }
        }
    }
}
