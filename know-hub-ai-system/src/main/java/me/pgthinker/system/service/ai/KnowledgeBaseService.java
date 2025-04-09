package me.pgthinker.system.service.ai;

import me.pgthinker.system.controller.vo.KnowledgeBaseVO;
import me.pgthinker.system.controller.vo.KnowledgeFileVO;
import me.pgthinker.system.controller.vo.ListFileIdVO;
import me.pgthinker.system.controller.vo.SimpleBaseVO;

import java.util.List;

/**
 * @Project: me.pgthinker.system.service.ai
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/4/8 07:56
 * @Description:
 */
public interface KnowledgeBaseService {

	// 添加知识库
	String addKnowledgeBase(KnowledgeBaseVO knowledgeBaseVO);

	// 删除知识库
	Integer removeKnowledgeBase(KnowledgeBaseVO knowledgeBaseVO);

	// 所有知识库
	List<KnowledgeBaseVO> knowLedgelist();

	// 简单的列表
	List<SimpleBaseVO> simpleList();

	List<KnowledgeFileVO> getKnowledgeFile(Long knowledgeId);

	Object deleteKnowledgeFile(ListFileIdVO listFileIdVO);
}
