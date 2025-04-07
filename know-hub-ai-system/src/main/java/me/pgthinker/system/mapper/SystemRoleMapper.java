package me.pgthinker.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.pgthinker.system.model.entity.user.SystemRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author pgthinker
 * @description 针对表【system_role(系统角色表)】的数据库操作Mapper
 * @createDate 2025-03-30 18:28:12
 * @Entity generator.domain.SystemRole
 */
@Mapper
public interface SystemRoleMapper extends BaseMapper<SystemRole> {

}
