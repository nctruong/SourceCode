package db.excel;
import java.sql.*;

public class DemoDriver2
{
  public static void main(String[] args)
  {
    try
    {
      Class.forName("org.relique.jdbc.csv.CsvDriver");
      Connection conn = DriverManager.getConnection("jdbc:relique:csv:" + args[0]);

      // create a scrollable Statement so we can move forwards and backwards
      // through ResultSets
      Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
      ResultSet.CONCUR_READ_ONLY);
      ResultSet results = stmt.executeQuery("SELECT ID,NAME FROM sample");

      // dump out the last record in the result set, then the first record
      if (results.last())
      {
        System.out.println("ID= " + results.getString("ID") +
              "   NAME= " + results.getString("NAME"));
        if (results.first())
        {
          System.out.println("ID= " + results.getString("ID") +
              "   NAME= " + results.getString("NAME"));
        }
      }

      // clean up
      conn.close();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }
}