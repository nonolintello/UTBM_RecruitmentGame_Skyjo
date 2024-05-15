package game;
import java.util.Comparator;

public class ScoreComparator implements Comparator<Player> {
    @Override
    public int compare(Player P1, Player P2) {
        return Integer.compare(P1.getScore(), P2.getScore());
    }
}
