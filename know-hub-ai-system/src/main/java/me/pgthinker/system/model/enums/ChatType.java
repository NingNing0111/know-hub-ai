package me.pgthinker.system.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @Project: me.pgthinker.system.model.enums
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/4/10 08:40
 * @Description:
 */
@Getter
@RequiredArgsConstructor
public enum ChatType {

	// type ChatType = 'simple' | 'simpleRAG' | 'multimodal' | 'multimodalRAG';
	UNKNOWN("unknown"), SIMPLE("simple"), SIMPLE_RAG("simpleRAG"), MULTIMODAL("multimodal"),
	MULTIMODAL_RAG("multimodalRAG");

	private final String value;

	public static ChatType parse(String value) {
		return switch (value) {
			case "simple" -> ChatType.SIMPLE;
			case "simpleRAG" -> ChatType.SIMPLE_RAG;
			case "multimodal" -> ChatType.MULTIMODAL;
			case "multimodalRAG" -> ChatType.MULTIMODAL_RAG;
			default -> ChatType.UNKNOWN;
		};
	}

}
