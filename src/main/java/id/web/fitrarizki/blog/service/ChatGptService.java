package id.web.fitrarizki.blog.service;

import id.web.fitrarizki.blog.config.SecretPropertiesConfig;
import id.web.fitrarizki.blog.dto.openai.GenerateOpenAiRequest;
import id.web.fitrarizki.blog.dto.openai.GenerateOpenAiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class ChatGptService {

    private final RestTemplate restTemplate;
    private final SecretPropertiesConfig secretProp;

    public String generatePostByTitleAndLength(String title, Integer length) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer %s".formatted(secretProp.getOpenApi().getApiKey()));

        GenerateOpenAiRequest request = GenerateOpenAiRequest.builder()
                .model("deepseek-r1:1.5b")
                .messages(Collections.singletonList(GenerateOpenAiRequest.Message.builder()
                        .role("user")
                        .content("Tuliskan sebuah blog post sebanyak %d kata untuk title: %s. Jangan ikutkan tag <think> pada responsenya".formatted(length, title))
                        .build()))
                .build();

        HttpEntity<GenerateOpenAiRequest> requestEntity = new HttpEntity<>(request, headers);
        ResponseEntity<GenerateOpenAiResponse> response = restTemplate.exchange(secretProp.getOpenApi().getUrl(), HttpMethod.POST, requestEntity, GenerateOpenAiResponse.class);

        String[] content = new String[2];

        if (response.getStatusCode() == HttpStatus.OK) {
            if (response.getBody().getChoices().getFirst().getMessage().getContent().contains("</think>")) {
                content = response.getBody().getChoices().getFirst().getMessage().getContent().split("</think>");
            }
        }

        return content[1];
    }
}
