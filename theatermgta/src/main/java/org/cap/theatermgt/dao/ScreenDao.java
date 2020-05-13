package org.cap.theatermgt.dao;

import org.cap.theatermgt.entities.Screen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenDao extends JpaRepository<Screen, Integer>
{

}
