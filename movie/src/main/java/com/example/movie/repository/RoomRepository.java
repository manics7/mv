package com.example.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.movie.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

	Room findByThCodeAndRoomNo(Integer thCode, Integer roomNo);

	
}
