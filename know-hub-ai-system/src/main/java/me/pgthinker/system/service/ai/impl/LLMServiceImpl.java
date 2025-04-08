package me.pgthinker.system.service.ai.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.pgthinker.system.service.ai.LLMService;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.document.MetadataMode;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.ai.openai.OpenAiEmbeddingOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.pgvector.PgVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @Project: me.pgthinker.service.doc.impl
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/3/30 03:08
 * @Description:
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class LLMServiceImpl implements LLMService {

	@Value("${chat.simple.base-url}")
	private String simpleBaseUrl;

	@Value("${chat.simple.api-key}")
	private String simpleApiKey;

	@Value("${chat.simple.model}")
	private String simpleModel;

	@Value("${chat.long.base-url}")
	private String longBaseUrl;

	@Value("${chat.long.api-key}")
	private String longApiKey;

	@Value("${chat.long.model}")
	private String longModel;

	@Value("${chat.multimodal.base-url}")
	private String multimodalBaseUrl;

	@Value("${chat.multimodal.api-key}")
	private String multimodalApiKey;

	@Value("${chat.multimodal.model}")
	private String multimodalModel;

	@Value("${embedding.base-url}")
	private String embeddingBaseUrl;

	@Value("${embedding.api-key}")
	private String embeddingApiKey;

	@Value("${embedding.model}")
	private String embeddingModel;

	private final JdbcTemplate jdbcTemplate;

	@Override
	public ChatModel getChatModel() {
		OpenAiApi openAiApi = OpenAiApi.builder().baseUrl(simpleBaseUrl).apiKey(simpleApiKey).build();
		return OpenAiChatModel.builder()
			.openAiApi(openAiApi)
			.defaultOptions(OpenAiChatOptions.builder().model(simpleModel).build())
			.build();
	}

	@Override
	public ChatModel getLongContextChatModel() {
		OpenAiApi openAiApi = OpenAiApi.builder().baseUrl(longBaseUrl).apiKey(longApiKey).build();
		return OpenAiChatModel.builder()
			.openAiApi(openAiApi)
			.defaultOptions(OpenAiChatOptions.builder().model(longModel).build())
			.build();
	}

	@Override
	public ChatModel getMultimodalModel() {
		OpenAiApi openAiApi = OpenAiApi.builder().baseUrl(multimodalBaseUrl).apiKey(multimodalApiKey).build();
		return OpenAiChatModel.builder()
			.openAiApi(openAiApi)
			.defaultOptions(OpenAiChatOptions.builder().model(multimodalModel).build())
			.build();
	}

	@Override
	public EmbeddingModel getEmbeddingModel() {
		OpenAiApi openAiApi = OpenAiApi.builder().baseUrl(embeddingBaseUrl).apiKey(embeddingApiKey).build();
		return new OpenAiEmbeddingModel(openAiApi, MetadataMode.EMBED, OpenAiEmbeddingOptions.builder().model(embeddingModel).build());
	}

	@Override
	public VectorStore getVectorStore() {
		return PgVectorStore.builder(jdbcTemplate, this.getEmbeddingModel()).build();
	}

}
