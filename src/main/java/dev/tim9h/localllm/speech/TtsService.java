package dev.tim9h.localllm.speech;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class TtsService {

	private final RestClient restClient;

	private static final Logger logger = LogManager.getLogger(TtsService.class);

	public TtsService(RestClient.Builder builder,
			@Value("${CHATTERBOX_URL:http://chatterbox:5001}") String chatterboxUrl) {
		this.restClient = builder.baseUrl(chatterboxUrl).build();
	}

	public byte[] generateSpeech(String text) {
		logger.info(() -> "Attempting to synthesize speech");
		try {
			var response = restClient.post().uri("/v1/audio/speech").contentType(MediaType.APPLICATION_JSON)
					.body(Map.of("input", text, "voice", "default", "response_format", "wav")).retrieve()
					.body(byte[].class);
			logger.info(() -> "Generated audio data of length " + response.length);
			return response;
		} catch (Exception e) {
			logger.error(() -> "Failed to generate speech for voice " + e.getMessage());
			throw e;
		}
	}

}