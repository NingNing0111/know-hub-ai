package me.pgthinker.system.service.ai.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.pgthinker.system.controller.vo.KnowledgeBaseVO;
import me.pgthinker.system.controller.vo.SimpleBaseVO;
import me.pgthinker.system.mapper.KnowledgeBaseMapper;
import me.pgthinker.system.mapper.SystemUserMapper;
import me.pgthinker.system.model.entity.ai.KnowledgeBase;
import me.pgthinker.system.model.entity.user.SystemUser;
import me.pgthinker.system.service.ai.KnowledgeBaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
		LambdaQueryWrapper<KnowledgeBase> qw = new LambdaQueryWrapper<>();
		qw.orderByDesc(KnowledgeBase::getCreateTime);
		List<KnowledgeBase> knowledgeBaseList = this.list(qw);
		return transfer(knowledgeBaseList);
	}

	@Override
	public List<SimpleBaseVO> simpleList() {
		List<KnowledgeBase> knowledgeBaseList = this.list();
		return transfer2Simple(knowledgeBaseList);
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
