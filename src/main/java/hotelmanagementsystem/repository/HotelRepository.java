package hotelmanagementsystem.repository;

import hotelmanagementsystem.model.Hotel;

import java.util.List;

public interface HotelRepository {


    Hotel saveHotel(Hotel hotel);

    Hotel findHotel(Long id);

    void deleteHotelById(Long id);

    List<Hotel> findAllHotel();

    void updateHotel(Hotel hotel);


}
