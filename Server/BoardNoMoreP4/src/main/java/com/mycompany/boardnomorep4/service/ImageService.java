package com.mycompany.boardnomorep4.service;

import com.mycompany.boardnomorep4.db.DatabaseConnector;
import com.mycompany.boardnomorep4.db.DatabaseUtils;
import com.mycompany.boardnomorep4.model.Image;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ImageService {
    
    private final static String ALL_IMAGES_QUERY = "SELECT * FROM images";
    
    public static Image getImageById(int id) {
        //Get a new connection object before going forward with the JDBC invocation.
        Connection connection = DatabaseConnector.getConnection();
        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_IMAGES_QUERY + " WHERE imgId = " + id);

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    Image image = new Image();

                    image.setImgId(resultSet.getInt("imgId"));
                    image.setGid(resultSet.getInt("gid"));
                    image.setPath(resultSet.getString("path"));

                    return image;

                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {

                    // We will always close the connection once we are done interacting with the Database.
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
        
        
        } //end getImageById
    
    public static Image getFirstImageByGid(int gid) {
        //Get a new connection object before going forward with the JDBC invocation.
        Connection connection = DatabaseConnector.getConnection();
        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_IMAGES_QUERY + 
                " WHERE imgId = (SELECT min(imgId) FROM images WHERE gid = " + gid + ")");

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    Image image = new Image();

                    image.setImgId(resultSet.getInt("imgId"));
                    image.setGid(resultSet.getInt("gid"));
                    image.setPath(resultSet.getString("path"));

                    return image;

                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {

                    // We will always close the connection once we are done interacting with the Database.
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
        
        
        } //end getImageById
    
    public static List<Image> getAllImages() {
        List<Image> images = new ArrayList<>();

        Connection connection = DatabaseConnector.getConnection();
        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_IMAGES_QUERY);

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    Image image = new Image();

                    image.setImgId(resultSet.getInt("imgId"));
                    image.setGid(resultSet.getInt("gid"));
                    image.setPath(resultSet.getString("path"));

                    images.add(image);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return images;
    }
    
    public static List<Image> getAllImagesByGid(int gid) {
        List<Image> images = new ArrayList<>();

        Connection connection = DatabaseConnector.getConnection();
        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_IMAGES_QUERY + " WHERE gid = " + gid);

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    Image image = new Image();

                    image.setImgId(resultSet.getInt("imgId"));
                    image.setGid(resultSet.getInt("gid"));
                    image.setPath(resultSet.getString("path"));

                    images.add(image);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return images;
    }

    public static boolean AddImage(Image image) {

        String sql = "INSERT INTO images  (gid, path)" +
                "VALUES (?, ?)";
        Connection connection = DatabaseConnector.getConnection();
        return DatabaseUtils.performDBUpdate(connection, sql, Integer.toString(image.getGid()), image.getPath());

    }

    public static boolean updateImage(Image image) {

        String sql = "UPDATE images SET gid=?, path=? WHERE imgId=?;";

        Connection connection = DatabaseConnector.getConnection();

        boolean updateStatus = DatabaseUtils.performDBUpdate(connection, sql, Integer.toString(image.getGid()),
                image.getPath(), String.valueOf(image.getImgId()));

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updateStatus;

    }

    public static boolean deleteImage(Image image) {

        String sql = "DELETE FROM images WHERE imgId=?;";

        Connection connection = DatabaseConnector.getConnection();

        boolean updateStatus = DatabaseUtils.performDBUpdate(connection, sql, String.valueOf(image.getImgId()));

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updateStatus;
    }
    
}
