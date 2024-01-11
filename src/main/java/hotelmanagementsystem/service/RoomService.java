package hotelmanagementsystem.service;

import hotelmanagementsystem.model.Room;

import java.util.List;

public interface RoomService {

    Room saveRoom();

    Room findRoomById(Long id);

    void deleteRoomById(Long id);

    List<Room> getAllRooms();
}
