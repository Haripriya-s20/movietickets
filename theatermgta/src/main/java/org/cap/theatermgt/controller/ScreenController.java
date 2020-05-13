package org.cap.theatermgt.controller;

import java.util.List;

import org.cap.theatermgt.dto.ScreenRequestDto;
import org.cap.theatermgt.dto.TheaterDetailsDto;
import org.cap.theatermgt.entities.Screen;
import org.cap.theatermgt.entities.Theater;
import org.cap.theatermgt.exception.ScreenNotFoundException;
import org.cap.theatermgt.service.IScreenService;
import org.cap.theatermgt.service.ITheaterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/screens")
public class ScreenController
{
	
	private static final Logger Log = LoggerFactory.getLogger(ScreenController.class);
	
	@Autowired
	private IScreenService screenService;
	
	@Autowired
	private ITheaterService theaterService;
	
	@PostMapping("/add")
	public ResponseEntity<TheaterDetailsDto> addScreen(@RequestBody ScreenRequestDto screenDto, @PathVariable("id") int theaterId)
	{
		Screen screen = convertScreen(screenDto);
		Theater theater = screenService.addScreen(screen, theaterId);
		TheaterDetailsDto detailsDto = convertTheaterDetails(theater);
		ResponseEntity<TheaterDetailsDto> response = new ResponseEntity<TheaterDetailsDto>(detailsDto, HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/get")
	public ResponseEntity<List<Screen>> fetchScreens(@PathVariable("id") int theaterId) 
	{
		Theater th = theaterService.fetchById(theaterId);
		List<Screen> screenList = th.getScreenList();
		ResponseEntity<List<Screen>> response = new ResponseEntity<List<Screen>>(screenList, HttpStatus.OK);
		return response;
	}

	@DeleteMapping("/delete")
	private ResponseEntity<String> deleteScreen(@PathVariable("id") int screenId)
	{
		String result = screenService.delete(screenId);
		ResponseEntity<String> response = new ResponseEntity<String>(result, HttpStatus.OK);
		return response;
	}
	
	private Screen convertScreen(ScreenRequestDto screenDto)
	{
		Screen screen = new Screen();
		screen.setColumn(screenDto.getColumn());
		screen.setRow(screenDto.getRow());
		screen.setScreenName(screenDto.getScreenName());
		return screen;
	}
	
	private TheaterDetailsDto convertTheaterDetails(Theater theater) 
	{
		TheaterDetailsDto detailsDto = new TheaterDetailsDto();
		detailsDto.setTheaterId(theater.getTheaterId());
		detailsDto.setTheaterName(theater.getTheaterName());
		detailsDto.setTheaterCity(theater.getTheaterCity());
		detailsDto.setScreenList(theater.getScreenList());
		return detailsDto;
	}
	
	@ExceptionHandler(ScreenNotFoundException.class)
	private ResponseEntity<String> handleTheaterNotFound(ScreenNotFoundException ex)
	{
		Log.error("handleScreenNotFound()",ex);
		String msg = ex.getMessage();
		ResponseEntity<String> response = new ResponseEntity<String>(msg, HttpStatus.OK);
		return response;
	}
	
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<String> handleAll(Throwable ex) 
	{
		Log.error("handleAll()", ex);
		String msg = ex.getMessage();
		ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		return response;
	}

}
