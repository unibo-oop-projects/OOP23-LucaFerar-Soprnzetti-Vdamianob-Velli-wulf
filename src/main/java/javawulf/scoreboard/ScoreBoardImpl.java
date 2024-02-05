package javawulf.scoreboard;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ScoreBoardImpl is used to save all Results and store them.
 */
public final class ScoreBoardImpl implements Scoreboard {

    private final File file;
    private List<Result> scoreboard;

    /**
     * Creates a Scoreboard.
     */
    public ScoreBoardImpl() {
        this.file = new File(FILE_PATH);
        loadScoreBoard();
    }

    @Override
    public void addNewScore(final Result result) {
        this.scoreboard.add(result);
        orderScoreBoard();
        if (scoreboard.size() > SCOREBOARD_SIZE) {
            this.scoreboard.remove(SCOREBOARD_SIZE);
        }
    }

    @Override
    public void saveScoreBoard() {
        try (OutputStream file = new FileOutputStream(FILE_PATH);
            OutputStream bstream = new BufferedOutputStream(file);
            ObjectOutputStream ostream = new ObjectOutputStream(bstream);) {
            for (Result result : scoreboard) {
                ostream.writeObject(result);
                System.out.println(FILE_PATH);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Result> getAllScores() {
        return this.scoreboard;
    }

    private void loadScoreBoard() {
        if (this.file.exists()) {
            try (ObjectInputStream fileInputStream = new ObjectInputStream(new FileInputStream(file))) {
                this.scoreboard = (List<Result>) fileInputStream.readObject(); // NOPMD suppressed as it is a false positive
                //all objects in this file are Results
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            this.scoreboard = new ArrayList<Result>();
        }
    }

    private void orderScoreBoard() {
        scoreboard = scoreboard.stream()
            .sorted(Comparator.comparingInt(Result::getScore).reversed())
            .collect(Collectors.toList());
    }

}
