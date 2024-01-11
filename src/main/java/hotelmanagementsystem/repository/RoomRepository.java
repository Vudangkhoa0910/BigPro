package hotelmanagementsystem.repository;

import hotelmanagementsystem.model.Room;

import java.util.List;

public interface RoomRepository {

    Room saveRoom (Room room);

    Room findRoomById (Long RoomId);

    void deleteRoomById(Long id);

    List<Room> findAllRooms();
}
