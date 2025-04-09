package me.pgthinker.system.service.ai.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.pgthinker.core.pojo.PageParam;
import me.pgthinker.core.pojo.PageResult;
import me.pgthinker.system.controller.vo.KnowledgeBaseVO;
import me.pgthinker.system.controller.vo.KnowledgeFileVO;
import me.pgthinker.system.controller.vo.ListFileIdVO;
import me.pgthinker.system.controller.vo.SimpleBaseVO;
import me.pgthinker.system.mapper.DocumentEntityMapper;
import me.pgthinker.system.mapper.KnowledgeBaseMapper;
import me.pgthinker.system.mapper.SystemUserMapper;
import me.pgthinker.system.model.entity.ai.DocumentEntity;
import me.pgthinker.system.model.entity.ai.KnowledgeBase;
import me.pgthinker.system.model.entity.user.SystemUser;
import me.pgthinker.system.service.ai.KnowledgeBaseService;
import me.pgthinker.system.service.ai.LLMService;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.filter.Filter;
import org.springframework.ai.vectorstore.filter.FilterExpressionBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project: me.pgthinker.system.service.ai.impl
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/4/8 08:00
 * @Description:
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class KnowledgeBaseServiceImpl extends ServiceImpl<KnowledgeBaseMapper, KnowledgeBase>
		implements KnowledgeBaseService {

	private final SystemUserMapper userMapper;
	private final DocumentEntityMapper documentEntityMapper;
	private final LLMService llmService;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public String addKnowledgeBase(KnowledgeBaseVO knowledgeBaseVO) {
		KnowledgeBase knowledgeBase = new KnowledgeBase();
		knowledgeBase.setName(knowledgeBaseVO.getName());
		knowledgeBase.setDescription(knowledgeBaseVO.getDescription());
		this.save(knowledgeBase);
		return knowledgeBase.getId();
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Integer removeKnowledgeBase(KnowledgeBaseVO knowledgeBaseVO) {
		String id = knowledgeBaseVO.getId();
		return this.removeById(id) ? 1 : 0;
	}

	@Override
	public List<KnowledgeBaseVO> knowLedgelist() {
		List<KnowledgeBase> knowledgeBaseList = this.list();
		return transfer(knowledgeBaseList);
	}

	@Override
	public List<SimpleBaseVO> simpleList() {
		List<KnowledgeBase> knowledgeBaseList = this.list();
		return transfer2Simple(knowledgeBaseList);
	}

	@Override
	public PageResult getKnowledgeFile(Long knowledgeId) {
		PageParam pageParam = new PageParam();
		// 构造分页对象
		Page<DocumentEntity> page = new Page<>(pageParam.getPageNo(), pageParam.getPageSize());
		QueryWrapper<DocumentEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("base_id", knowledgeId);

		// 执行分页查询
		Page<DocumentEntity> files = documentEntityMapper.selectPage(page,queryWrapper);

		List<KnowledgeFileVO> records = files.getRecords().stream()
				.filter(file -> !file.getDeleted())
				.map(file -> {
					KnowledgeFileVO knowledgeFileVO = new KnowledgeFileVO();
					knowledgeFileVO.setFileName(file.getFileName());
					knowledgeFileVO.setPath(file.getPath());
					knowledgeFileVO.setId(file.getId());
					return knowledgeFileVO;
				}).collect(Collectors.toList());

		return new PageResult<>(
				files.getTotal(),     // 总条数
				records,              // 当前页数据
				files.getCurrent(),   // 当前页码
				files.getSize(),      // 每页条数
				files.getPages()      // 总页数
		);
	}

	@Override
	public Object deleteKnowledgeFile(ListFileIdVO listFileIdVO) {
		Long fileId = listFileIdVO.getFileId();
		Long baseId = listFileIdVO.getBaseId();

		QueryWrapper<DocumentEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("base_id", baseId);
		queryWrapper.eq("file_id", fileId);

		DocumentEntity document = documentEntityMapper.selectOne(queryWrapper);

		try{
			//删除文件
			document.setDeleted(true);
			documentEntityMapper.updateById(document);

			//删除向量数据
			VectorStore vectorStore = llmService.getVectorStore();

			Filter.Expression filterExpression = new FilterExpressionBuilder().eq("document_id",fileId)
					.build();

			vectorStore.delete(filterExpression);
			return "删除成功";
		}catch (Exception e){
			e.printStackTrace();
		}
		return "删除错误";
	}

	private List<KnowledgeBaseVO> transfer(List<KnowledgeBase> knowledgeBaseList) {
		return knowledgeBaseList.stream().map(this::transfer).toList();
	}

	private KnowledgeBaseVO transfer(KnowledgeBase knowledgeBase) {
		KnowledgeBaseVO knowledgeBaseVO = new KnowledgeBaseVO();
		knowledgeBaseVO.setDescription(knowledgeBase.getDescription());
		knowledgeBaseVO.setId(knowledgeBase.getId());
		knowledgeBaseVO.setName(knowledgeBase.getName());
		knowledgeBaseVO.setCreateTime(knowledgeBase.getCreateTime());
		String creator = knowledgeBase.getCreator();
		if (creator != null) {
			SystemUser user = userMapper.selectById(Long.parseLong(creator));
			knowledgeBaseVO.setAuthor(user.getId());
			knowledgeBaseVO.setAuthorName(user.getUsername());
		}
		return knowledgeBaseVO;
	}

	private List<SimpleBaseVO> transfer2Simple(List<KnowledgeBase> knowledgeBaseList) {
		return knowledgeBaseList.stream().map(item -> {
			SimpleBaseVO simpleBaseVO = new SimpleBaseVO();
			simpleBaseVO.setId(item.getId());
			simpleBaseVO.setName(item.getName());
			return simpleBaseVO;
		}).toList();
	}

}
