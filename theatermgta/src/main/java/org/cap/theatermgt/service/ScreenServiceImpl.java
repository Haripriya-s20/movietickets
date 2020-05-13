package org.cap.theatermgt.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.cap.theatermgt.dao.ScreenDao;
import org.cap.theatermgt.entities.Screen;
import org.cap.theatermgt.entities.Theater;
import org.cap.theatermgt.exception.ScreenNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ScreenServiceImpl implements IScreenService 
{

	@Autowired
	private ScreenDao screenDao;
	
	@Autowired
	private ITheaterService theaterService;

	@Override
	public Theater addScreen(Screen screen, int theaterId) 
	{
		screen.setTheaterId(theaterId);
		screenDao.save(screen);
		Theater theater = theaterService.fetchById(theaterId);
		List<Screen> screenList = theater.getScreenList();
		screenList.add(screen);
		theater.setScreenList(screenList);
		Theater theaters = theaterService.save(theater);
		return theaters;
	}

	@Override
	public Screen fetchScreenById(int screenId)
	{
		Optional<Screen> optional = screenDao.findById(screenId);
		if(optional.isPresent()) {
			Screen screen = optional.get();
			return screen;
		}
		throw new ScreenNotFoundException("Screen not found at id ="+screenId);
	}

	@Override
	public String delete(int screenId)
	{
		Screen screen = fetchScreenById(screenId);
		screenDao.delete(screen);
		return "Deleted Successfully";
	}
	
	
}
