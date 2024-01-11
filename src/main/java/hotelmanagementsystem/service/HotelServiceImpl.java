package hotelmanagementsystem.service;

import hotelmanagementsystem.exception.HotelNotFoundResourceException;
import hotelmanagementsystem.model.Hotel;
import hotelmanagementsystem.repository.HotelRepository;

import java.util.List;
import java.util.Scanner;

public class HotelServiceImpl implements HotelService{

    private static Scanner scanner;

    private final HotelRepository hotelRepository;

    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }


    @Override
    public Hotel saveHotel() {

        scanner = new Scanner(System.in);

        Hotel hotel = new Hotel();

        System.out.println("Please enter hotel ID: ");
        hotel.setId(scanner.nextLong());
        scanner.nextLine();

        System.out.println("Please enter Hotel's name: ");
        hotel.setName(scanner.nextLine());
        System.out.println();

        System.out.println("Please enter hotel's location: ");
        hotel.setLocation(scanner.nextLine());
        System.out.println();

        hotelRepository.saveHotel(hotel);

        System.out.println("Hotel is saved successfully!");
        System.out.println("Hotel ID: " + hotel.getId());
        System.out.println("Hotel Name: " + hotel.getName());
        System.out.println("Hotel Location: " + hotel.getLocation());

        return null;
    }

    @Override
    public Hotel getHotelById(Long id) {

        try {
            Hotel hotelSearched = hotelRepository.findHotel(id);
            if (hotelSearched != null){
                System.out.println(hotelSearched);
                return hotelSearched;
            } else {
                throw new HotelNotFoundResourceException("Hotel cannot be found with id: " + id);
            }
        } catch (HotelNotFoundResourceException e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public void deleteHotelById(Long id) {

        scanner = new Scanner(System.in);

        try{

            Hotel hotelToDelete = hotelRepository.findHotel(id);

            if (hotelToDelete == null){
                throw new HotelNotFoundResourceException("Hotel cannot be found with id: " + id);
            }

            System.out.println("Are you sure to delete the hotel: " + hotelToDelete + "?");
            System.out.println("Press Y for yes, N for no!");

            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("y")){

                hotelRepository.deleteHotelById(hotelToDelete.getId());
                System.out.println("Hotel is deleted successfully!");

            } else System.out.println("Delete operation is cancelled!");

        } catch (HotelNotFoundResourceException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List<Hotel> getAllHotels() {

        try {
            List<Hotel> hotels = hotelRepository.findAllHotel();
            if (!hotels.isEmpty()){
                System.out.println("List of Hotels: ");
                for (Hotel w : hotels){

                    System.out.println(w);
                    System.out.println("-------------------------");
                }
            } else throw new HotelNotFoundResourceException("Hotel list is empty...");

            return hotels;

        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void updateHotelById(Long id, Hotel hotel) {

        try {

            Hotel existHotel = hotelRepository.findHotel(id);
            if (existHotel == null){
                throw new HotelNotFoundResourceException("Hotel cannot be found");
            }

            existHotel.setName(hotel.getName());
            existHotel.setLocation(hotel.getLocation());
            hotelRepository.updateHotel(existHotel);

        } catch (HotelNotFoundResourceException e){
            System.out.println(e.getMessage());
        }

    }
}
