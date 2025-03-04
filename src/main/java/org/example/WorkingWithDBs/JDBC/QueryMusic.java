package org.example.WorkingWithDBs.JDBC;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class QueryMusic {

    public static void main(String[] args) {

        Properties props = new Properties();
        try {
            props.load(Files.newInputStream(Path.of("music.properties"),
                    StandardOpenOption.READ));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        var dataSource = new MysqlDataSource();
        dataSource.setServerName(props.getProperty("serverName"));
        dataSource.setPort(Integer.parseInt(props.getProperty("port")));
        dataSource.setDatabaseName(props.getProperty("databaseName"));

//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter an Artist id: ");
////        String albumName = scanner.nextLine();
//        String artistID = scanner.nextLine();
//        int artistId = Integer.parseInt(artistID);
//        String query = "SELECT * FROM music.albumview WHERE album_name='%s'"
//                .formatted(albumName);
//        String query = "SELECT * FROM music.artists WHERE artist_id=%d"
//                .formatted(artistId);

        try {
            dataSource.setMaxRows(10);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String query = "SELECT * FROM music.artists limit 10";

//        String query = """
//                WITH RankedRows AS (
//                                    SELECT *,
//                                    ROW_NUMBER() OVER (ORDER BY artist_id) AS row_num
//                                    FROM music.artists
//                                    )
//                                    SELECT *
//                                        FROM RankedRows
//                                    WHERE row_num <= 10""";
        //data base agnostic and equivalent to LIMIT 10, but less effecient

        try (var connection = dataSource.getConnection(
                props.getProperty("user"),
                System.getenv("MYSQL_PASS"));
             Statement statement = connection.createStatement();
        ) {
            ResultSet resultSet = statement.executeQuery(query);

            var meta = resultSet.getMetaData();
//            for (int i = 1; i <= meta.getColumnCount(); i++) {
//                System.out.printf("%d %s %s%n",
//                        i,
//                        meta.getColumnName(i),
//                        meta.getColumnTypeName(i)
//                );
//            }

            System.out.println("===================");

            for (int i = 1; i <= meta.getColumnCount(); i++) {
                System.out.printf("%-15s", meta.getColumnName(i).toUpperCase());
            }
            System.out.println();

            while (resultSet.next()) {
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    System.out.printf("%-15s", resultSet.getString(i));
                }
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
