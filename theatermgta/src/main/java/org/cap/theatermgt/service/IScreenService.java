package org.cap.theatermgt.service;

import org.cap.theatermgt.entities.Screen;
import org.cap.theatermgt.entities.Theater;

public interface IScreenService 
{

	Theater addScreen(Screen screen, int theaterId);
	
	Screen fetchScreenById(int screenId);
	
	String delete(int screenId);
}
