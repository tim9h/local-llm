# Build and Start:
```podman compose up --build -d```

## Verify GPU connection:
```podman exec -it ollama nvidia-smi```

## Download the model
```podman exec -it ollama ollama pull llama3.2:1b```

## Testing the backend
```curl "http://localhost:8080/api/chat?message=Hello"```

## When running backend outside of container
Set environment variable `SPRING_AI_OLLAMA_BASE_URL=http://localhost:11434` and `CHATTERBOX_URL=http://localhost:8004` 