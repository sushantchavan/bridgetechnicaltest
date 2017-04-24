package com.bridgeinternationalacademies.pupil.util;

/**
 * Created by sushantchavan on 24/04/17.
 */

public class SQLQuery {

    public static final String GET_PUPIL ="SELECT * FROM Pupils WHERE PupilId= ?";
    public static final String INSERT_PUPIL ="INSERT INTO Pupils (PupilId, Name, Country, Image, Latitude, Longitude) VALUES (?,?,?,?,?,?)";
    public static final String GET_ALL_PUPILS = "SELECT * FROM Pupils";
}
