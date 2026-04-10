package dev.tim9h.localllm.conversation;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.tim9h.localllm.chat.ChatService;
import dev.tim9h.localllm.speech.TtsService;

@RestController
public class ConversationController {

	private ChatService chatService;

	private TtsService ttsService;

	public ConversationController(ChatService chatService, TtsService ttsService) {
		this.chatService = chatService;
		this.ttsService = ttsService;
	}

	@GetMapping(value = "/api/converse", produces = "audio/wav")
	public ResponseEntity<byte[]> converse(@RequestParam(value = "message") String message) {
		var response = chatService.chat(message, true);
		var speech = ttsService.generateSpeech(response);
		return ResponseEntity.ok().contentType(MediaType.valueOf("audio/wav")).body(speech);
	}

}
