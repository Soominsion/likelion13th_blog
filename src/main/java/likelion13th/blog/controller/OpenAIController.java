/*
import likelion13th.blog.dto.request.ChatRequest;
import likelion13th.blog.dto.response.ChatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import likelion13th.blog.service.OpenAIChatService;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class OpenAIController {
    private final OpenAIChatService openAiChatService;

    @PostMapping
    public ResponseEntity<ChatResponse> chat(@RequestBody ChatRequest request){
        String response=openAiChatService.getChatResponse(request);
        return ResponseEntity.ok(new ChatResponse(response));
    }

}
*/
