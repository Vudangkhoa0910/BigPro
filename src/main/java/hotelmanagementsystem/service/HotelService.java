package hotelmanagementsystem.service;

import hotelmanagementsystem.model.Hotel;

import java.util.List;

public interface HotelService {


    Hotel saveHotel();

    Hotel getHotelById(Long id);


    void deleteHotelById(Long id);


    List<Hotel> getAllHotels();


    void updateHotelById(Long id, Hotel hotel);

}
