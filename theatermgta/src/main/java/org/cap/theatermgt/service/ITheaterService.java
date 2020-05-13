package org.cap.theatermgt.service;

import org.cap.theatermgt.entities.Theater;
import java.util.*;

public interface ITheaterService {

	Theater save(Theater t);
	
	List<Theater> fetchAll();
	
	Theater fetchById(int theaterId);

	String delete(int theaterId);
	
}
