package dev.tim9h.localllm.chat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

	private static final Logger logger = LogManager.getLogger(ChatService.class);

	@Value("${chatbot.context.text}")
	private String contextText;

	@Value("${chatbot.context.speech}")
	private String contextSpeech;

	private final ChatClient chatClient;

	public ChatService(ChatClient.Builder builder) {
		this.chatClient = builder.build();
	}

	public String chat(String message) {
		return chat(message, false);
	}

	public String chat(String message, boolean isSpeech) {
		logger.info(() -> "Message: " + message);
		var response = chatClient.prompt().system(isSpeech ? contextSpeech : contextText).user(message).call()
				.content();
		logger.info(() -> "Response: " + response);
		return response;
	}

}
