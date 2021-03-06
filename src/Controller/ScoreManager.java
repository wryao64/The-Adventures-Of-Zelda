package Controller;

import java.io.*;
import java.util.ArrayList;

public class ScoreManager {
    ArrayList<String> topScores = new ArrayList<>();
    String fileName = "./Resources/HighScores.txt";

    public ScoreManager() {
        String currentLine;

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((currentLine = bufferedReader.readLine()) != null && currentLine.trim().length() > 0) {
                topScores.add(currentLine);
            }

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Returns the top ten scores currently stored.
     */
    public ArrayList<String> getTopScores() {
        return sort(topScores);
    }

    /**
     * Takes a new score and if the score makes it into the high score list, adds it to the list.
     */
    public boolean newScore(String newScore) {
        if (topScores.size() < 10) {
            topScores.add(newScore);
        } else {
            if (Integer.parseInt(newScore) > Integer.parseInt(topScores.get(topScores.size() - 1))) {
                topScores.remove(topScores.size() - 1);
                topScores.add(newScore);
            } else {
                return false;
            }
        }

        //Sort the array again with the added score.
        topScores = sort(topScores);
        this.writeToFile();
        return true;
    }

    /**
     * Writing the high scores into a file. Rewrites the file every time.
     */
    private void writeToFile() {
        BufferedWriter outputWriter;

        try {
            outputWriter = new BufferedWriter(new FileWriter(fileName));

            for (String s : topScores) {
                String lineToWrite = s + "\n";
                outputWriter.write(lineToWrite);
            }

            outputWriter.flush();
            outputWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Sorts the high scores in ascending order.
     */
    private ArrayList<String> sort(ArrayList<String> scoreList) {
        for (int i = 0; i < scoreList.size(); i++) {
            String currentHighest = scoreList.get(i);
            int position = i;

            while (position > 0 && Integer.parseInt(currentHighest) >
                    Integer.parseInt(scoreList.get(position - 1))) {
                scoreList.set(position, scoreList.get(position - 1));
                scoreList.set(position - 1, currentHighest);
                position--;
            }
        }

        return scoreList;
    }
}
