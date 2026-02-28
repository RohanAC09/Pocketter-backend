package com.rohan.timeline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.rohan.timeline.dto.response.AggregatedPosts;
import com.rohan.timeline.service.TimelineService;

@RestController
public class TimelineController {
	
	@Autowired
	private TimelineService timelineService;
	
	@GetMapping("/api/v1/timeline/{userId}")
	public ResponseEntity<AggregatedPosts> getTimeline(@PathVariable Long userId){
		return ResponseEntity.ok(timelineService.getTimeline(userId));
	}
}
