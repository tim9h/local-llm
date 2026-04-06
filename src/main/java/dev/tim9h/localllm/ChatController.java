package dev.tim9h.localllm;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

	private ChatService service;

	public ChatController(ChatService service) {
		this.service = service;
	}

	@GetMapping("/api/chat")
	public String chat(@RequestParam(value = "message", defaultValue = "Hello there!") String message) {
		return service.chat(message);
	}

}