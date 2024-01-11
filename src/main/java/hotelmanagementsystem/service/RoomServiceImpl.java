package hotelmanagementsystem.service;

import hotelmanagementsystem.exception.HotelNotFoundResourceException;
import hotelmanagementsystem.exception.RoomNotFoundException;
import hotelmanagementsystem.model.Hotel;
import hotelmanagementsystem.model.Room;
import hotelmanagementsystem.repository.HotelRepository;
import hotelmanagementsystem.repository.RoomRepository;

import java.util.List;
import java.util.Scanner;

public class RoomServiceImpl implements RoomService{

    private static Scanner scanner;

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    public RoomServiceImpl(RoomRepository roomRepository, HotelRepository hotelRepository) {
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
    }

    @Override
    public Room saveRoom() {

        scanner = new Scanner(System.in);

        Room room = new Room();

        System.out.println("Please enter room id: ");
        room.setId(scanner.nextLong());
        scanner.nextLine();

        System.out.println("Please enter room number: ");
        room.setNumber(scanner.nextLine());
        System.out.println();

        System.out.println("Please enter room capacity: ");
        room.setCapacity(scanner.nextInt());
        System.out.println();



        System.out.println("Please enter hotel ID: ");
        Long hotelId = scanner.nextLong();
        scanner.nextLine();

        try {
            Hotel existHotel = hotelRepository.findHotel(hotelId);

            if (existHotel == null){
                throw new HotelNotFoundResourceException("Hotel cannot be found with id: " + hotelId);
            }


            room.setHotel(existHotel);


            Room savedRoom = roomRepository.saveRoom(room);


            existHotel.getRooms().add(savedRoom); // Cuz of cascade we do not have to write this line in fact

            System.out.println("Room is saved successfully Room Id: " + savedRoom.getId());

        } catch (HotelNotFoundResourceException e){
            System.out.println(e.getMessage());
        }
        return room;

    }

    @Override
    public Room findRoomById(Long id) {

        try {

            Room roomFound = roomRepository.findRoomById(id);
            if (roomFound != null){
                System.out.println("-----------------------");
                System.out.println(roomFound);
                return roomFound;
            }else throw new RoomNotFoundException("Room cannot be found with id: " + id);

        } catch (RoomNotFoundException e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public void deleteRoomById(Long id) {

        scanner = new Scanner(System.in);

        try {

            Room roomFound = roomRepository.findRoomById(id);
            if (roomFound == null){
                throw new RoomNotFoundException("Room cannot be found with id: " + id);
            }
            System.out.println("Are you sure to delete the room: " + roomFound + "?");
            System.out.println("Press Y for yes, N for no!");

            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("y")){

                roomRepository.deleteRoomById(roomFound.getId());
                System.out.println("Room is deleted successfully!");

            } else System.out.println("Delete operation is cancelled!");


        } catch (RoomNotFoundException e){
            System.out.println(e.getMessage());

        }

    }

    @Override
    public List<Room> getAllRooms() {

        try {
            List<Room> rooms = roomRepository.findAllRooms();

            if (rooms.isEmpty()) {

                throw new RoomNotFoundException("Room list is empty...");

            }
            System.out.println("--------- Room List ---------");
            for (Room w : rooms) {

                System.out.println(w);
                System.out.println("-------------------");

            }

            return rooms;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }


    }

}
