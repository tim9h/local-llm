package dev.tim9h.localllm.speech;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TtsController {

	private final TtsService ttsService;

	@Value("${tts.voice:default}")
	private String defaultVoice;

	public TtsController(TtsService ttsService) {
		this.ttsService = ttsService;
	}

	@GetMapping(value = "/api/tts", produces = "audio/wav")
	public ResponseEntity<byte[]> textToSpeech(@RequestParam(value = "text") String text) {
		var audioData = ttsService.generateSpeech(text);
		return ResponseEntity.ok().contentType(MediaType.valueOf("audio/wav")).body(audioData);
	}

}
