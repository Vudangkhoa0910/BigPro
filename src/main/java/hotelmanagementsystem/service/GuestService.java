package hotelmanagementsystem.service;

import hotelmanagementsystem.model.Guest;
import java.util.List;

public interface GuestService {


    Guest saveGuest();

    void findGuestById(Long id);

    void deleteGuestById(Long guestId);

    List<Guest> findAllGuest();
}
