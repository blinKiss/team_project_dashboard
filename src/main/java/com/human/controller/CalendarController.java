package com.human.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CalendarController {
    @GetMapping("/calendar/calendar")
    public String showCalendar() {
        return "calendar/calendar";
    }
    
//    @Autowired
//    private CalendarService calendarService;
//
//    @PostMapping("/calendar/save-event")
//    public String saveEvent(@RequestBody EventDTO eventDTO) {
//        eventService.saveEvent(eventDTO);
//        return "redirect:/calendar/calendar";
//    }
}

