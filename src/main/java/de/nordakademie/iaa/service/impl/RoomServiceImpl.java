package de.nordakademie.iaa.service.impl;

import de.nordakademie.iaa.dao.RoomDAO;
import de.nordakademie.iaa.model.Room;
import de.nordakademie.iaa.model.RoomType;
import de.nordakademie.iaa.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    private RoomDAO roomDAO;

    @Autowired
    public RoomServiceImpl(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    @Override
    public void saveRoom(Room room) {
        roomDAO.save(room);
    }

    @Override
    public List<Room> listRooms() {
        return roomDAO.findAll();
    }

    @Override
    public Room loadRoom(Long id) {
        return roomDAO.findOne(id);
    }

    @Override
    public boolean deleteRoom(Long id) {
        Room room = loadRoom(id);
        if (room == null) {
            return false;
        }
        roomDAO.delete(room);
        return true;
    }

    @Override
    public Room findByBuildingAndNumber(String building, String number) {
        return roomDAO.findByBuildingAndNumber(building, number);
    }

    @Override
    public List<Room> findByRoomType(RoomType roomType) {
        return roomDAO.findByRoomType(roomType);
    }
}
