package dev.tim9h.localllm;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

	@Value("${chatbot.context}")
	private String context;

	private final ChatClient chatClient;

	public ChatService(ChatClient.Builder builder) {
		this.chatClient = builder.build();
	}

	public String chat(String message) {
		return chatClient.prompt().system(context).user(message).call().content();
	}

}
