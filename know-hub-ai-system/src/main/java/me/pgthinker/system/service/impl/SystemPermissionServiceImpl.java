package me.pgthinker.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.pgthinker.system.mapper.SystemPermissionMapper;
import me.pgthinker.system.model.entity.SystemPermission;
import me.pgthinker.system.service.SystemPermissionService;
import org.springframework.stereotype.Service;

/**
 * @author pgthinker
 * @description 针对表【system_permission(系统权限表)】的数据库操作Service实现
 * @createDate 2025-03-30 18:28:12
 */
@Service
public class SystemPermissionServiceImpl extends ServiceImpl<SystemPermissionMapper, SystemPermission>
		implements SystemPermissionService {

}
