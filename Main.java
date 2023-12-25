import java.sql.SQLException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws SQLException {
        FilmBrowser fb = new FilmBrowser("root", "12345");

        System.out.println(Arrays.toString(fb.getTitles("lover")));

        String[] names = new String[]{"IDENTITY LOVER", "LOVER TRUMAN", "LOVERBOY ATTACKS"};
        System.out.println(Arrays.toString(fb.getSimilarTitles((names))));
        String[] names1 = new String []{"AFRICAN EGG"};
        System.out.println(Arrays.toString(fb.getSimilarTitles(names1)));
        String[] names2 = new String []{"DADA","PAPA","AFRICAN EGG"};
        System.out.println(Arrays.toString(fb.getSimilarTitles(names2)));
        String[] arrtest = new String[3];
        System.out.println(Arrays.toString(arrtest));

    }

}
