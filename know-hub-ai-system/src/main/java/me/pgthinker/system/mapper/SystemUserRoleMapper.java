package me.pgthinker.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.pgthinker.system.model.entity.user.SystemUserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author pgthinker
 * @description 针对表【system_user_role(用户-角色关联表)】的数据库操作Mapper
 * @createDate 2025-03-30 19:34:28
 * @Entity generator.domain.SystemUserRole
 */
@Mapper
public interface SystemUserRoleMapper extends BaseMapper<SystemUserRole> {

}
