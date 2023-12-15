package com.scnu.chatbot.domain.ai;

import java.io.IOException;

/**
 * chatGPT openai 接口
 */
public interface IOpenAI {

    String doChatGPT(String openAiKey, String question) throws IOException;
}
