import java.sql.*;

public class FilmBrowser {
    /**
     * Created By:
     * Aviv Eliyahu
     * Ariel Godlwaser
     * Matan Asraf
     */
    private String username, password;

    /**
     * Class's constructor
     *
     * @param username is the sakila username
     * @param password is the sakilla password
     */
    public FilmBrowser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * a method that receive a string and check for similar or equal title/s.
     *
     * @param query is the entered string
     * @return the title/s that has similar or equal name
     * @throws SQLException in case of input error
     */
    public String[] getTitles(String query) throws SQLException {
        int arrSize = getResultSize(query);
        if (arrSize == 0) { //if no results
            String emptyResults[] = new String[0];
            return emptyResults;
        } else {
            // connect to sakila using the username & password the user entered.
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sakila?serverTimezone=UTC",
                    this.username,
                    this.password);
            // create array in the same size as the results
            String[] results = new String[arrSize];

            // execute statement to add to this array
            PreparedStatement pstmt = con.prepareStatement(
                    "Select film.title from film " //dont forget space !!
                            + "where film.title like ?");
            pstmt.setString(1, "%" + query + "%");
            ResultSet rs = pstmt.executeQuery();

            // add results into the array
            rs.next();
            for (int i = 0; i < results.length; i++) {
                results[i] = rs.getString(1);
                rs.next();
            }
            // close connections
            pstmt.close();
            rs.close();
            con.close();

            // return results
            return results;

        }
    }

    /**
     * a method that receive a string and check for similar title/s
     * the method return the size of matching results
     * (used in order to build a similar size array)
     *
     * @param query the entered string
     * @return the size of the title/s that has similar or equal name
     * @throws SQLException in case of input error
     */
    private int getResultSize(String query) throws SQLException {
        // connect to sakila using the username & password the user entered.
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sakila?serverTimezone=UTC",
                this.username,
                this.password);

        // first we need to check how many results are there:
        PreparedStatement pstmt1 = con.prepareStatement(
                "Select film.title from film " //dont forget space !!
                        + "where film.title like ?", ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        pstmt1.setString(1, "%" + query + "%");

        // execute query first time , to get results size
        ResultSet rs1 = pstmt1.executeQuery();
        /**int arrSize = 0;
        while (rs1.next()) {
            arrSize++;
        }
         **/

        rs1.last();
        int arrSize = rs1.getRow();
        // close connections
        pstmt1.close();
        rs1.close();
        con.close();

        // return arr size
        return arrSize;
    }

    /**
     * a method that receives an array of strings and check for similar title/s but not equal names
     * the similar titles will be written in the same cell location (i.e. similar titles to title #1 will be in cell #1)
     *
     * @param titles is the array of strings
     * @return the similar (but not equal) titles
     * @throws SQLException in case of input error
     */
    public String[] getSimilarTitles(String[] titles) throws SQLException {
        String[] results = new String[titles.length];
        if(results.length==0){
            return results;
        }
        else {
            for (int i = 0; i < titles.length; i++) { // run as many cells that are in the array
                String[] splitLines = titles[i].split(" "); // create temp array with one word in each cell from the cell above
                String temp = "";
                for (int k = 0; k < splitLines.length; k++) { // check each cell from the array above
                    for (int p = 0; p < getTitles(splitLines[k]).length; p++) { // run as many answers the cell above has
                        if ((getTitles(splitLines[k])[p].equals(titles[i])) == false) { // make sure not equal to main cell title
                            temp = temp + getTitles(splitLines[k])[p] + ","; // if so, add it to a string
                        }
                    }
                }
                if(temp.length()==0){
                    //if no answers were found, do nothing
                    break;
                }
                else {
                    // after having all answers, remove last 1 char which is ","
                    // add the string to the matching
                    results[i] = '"' + temp.substring(0, temp.length() - 1) + '"';
                }
            }
            return results;
        }
    }
}