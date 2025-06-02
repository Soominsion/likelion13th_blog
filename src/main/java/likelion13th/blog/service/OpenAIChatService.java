/*
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import likelion13th.blog.dto.request.ChatRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;


@Service
@RequiredArgsConstructor
public class OpenAIChatService {

    private final OpenAiService openAiService;

    public String getChatResponse(ChatRequest chatRequest){
        String question=chatRequest.getPrompt();
        ChatMessage userMessage=new ChatMessage("user",question);

        ChatCompletionRequest request=ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(List.of(userMessage))
                .temperature(0.7)
                .build();

        try {
            //OpenAI API를 호출하는 부분
            ChatCompletionResult result = openAiService.createChatCompletion(request);
            return result.getChoices().get(0).getMessage().getContent();
        } catch (Exception e) {
            // 예외가 발생하면 여기에 잡힌다. 오류 메시지를 상세하게 출력하기
            System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.err.println("OpenAI API 호출 중 예외 발생:");
            System.err.println("예외 타입: " + e.getClass().getName());
            System.err.println("예외 메시지: " + e.getMessage());
            System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

            // 클라이언트에게는 에러 메시지를 반환하거나, 적절한 예외를 다시 던질 수 있습니다.
            // 지금은 임시로 에러 메시지를 반환하도록 하겠습니다.
            return "OpenAI API 호출 중 오류가 발생했습니다: " + e.getMessage();
        }

    }

    public String getChatResponseWithContext(ChatRequest chatRequest){
        String question=chatRequest.getPrompt();

        //이전 대화 기로
        List<ChatMessage> messages=new ArrayList<>();

        // 1. 시스템 메시지 - 챗봇의 전반적인 행동 지침
        messages.add(new ChatMessage("system", "당신은 날씨를 알려주는 친절하고 유익한 챗봇입니다."));

        // 2. 이전 사용자 메시지
        messages.add(new ChatMessage("user","서울 날씨 어때?"));

        // 3. 이전 챗봇 메시지(2에 대한 응답)
        messages.add(new ChatMessage("assistant", "현재 서울은 맑고 기온은 20도 입니다"));

        // 4. 현재 사용자 메시지 (요청으로 들어온 새로운 질문)
        messages.add(new ChatMessage("user",question));

        ChatCompletionRequest request=ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(messages)
                .temperature(0.7)
                .n(2)
                .build();
        try {
            ChatCompletionResult result = openAiService.createChatCompletion(request);
            return result.getChoices().get(0).getMessage().getContent();
        } catch (Exception e) {
            throw new RuntimeException("OpenAI API 호출 중 오류가 발생했습니다: " + e.getMessage(), e);
        }
    }


}*/
