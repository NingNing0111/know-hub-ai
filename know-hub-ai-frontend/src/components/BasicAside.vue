<template>
  <div id="basic-aside">
    <el-menu
      :default-active="defaultPath"
      class="aside-menu"
      :collapse="isCollapse"
      background-color="#324157"
      text-color="#bfcbd9"
      active-text-color="#20a0ff"
    >
      <div class="menu-header">
        <h1>Know Hub AI</h1>
        <el-text style="color: #bfcbd9">基于个人知识库的AI对话</el-text>
      </div>
      <el-divider />

      <!-- <div class="aside-header">
      <el-icon :size="25" style="cursor: pointer" @click="openMenu">
        <Expand v-if="isCollapse" />
        <Fold v-else />
      </el-icon>
    </div> -->

      <el-menu-item
        v-for="item in menuRouterList"
        :index="item.path"
        @click="handleSelect(item)"
      >
        <el-icon>
          <component :is="item.meta?.icon"></component>
        </el-icon>
        <template #title>{{ item.meta?.description }}</template>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script setup lang="ts">
import routes from "@/router/config.ts";
import router from "@/router";

const emit = defineEmits(["changeAside"]);
const isCollapse = ref(false);
const path = router.currentRoute.value.fullPath;
const defaultPath = ref(path === "/" ? "/chat" : path);
const openMene = () => {
  isCollapse.value = !isCollapse.value;
  emit("changeAside", isCollapse.value);
};

// 使用计算属性过滤不是菜单项的路由选项
const menuRouterList = computed(() => {
  return routes.filter((item) => {
    return item.meta?.isMenu;
  });
});

router.afterEach((to) => {
  defaultPath.value = to.path;
});

onMounted(() => {
  console.log(defaultPath.value);
});

const handleSelect = (e: any) => {
  console.log(e);
  router.push({
    path: e.path,
  });
};
</script>

<style scoped lang="less">
#basic-aside {
  height: 100%;
}
:deep(.el-menu) {
  z-index: 10; // 配合 ChatView
}
.aside-header {
  display: flex;
  justify-content: right;
}
.menu-header {
  height: 80px;
  display: flex;
  justify-content: center;
  align-items: center;
  color: #bfcbd9;
  flex-wrap: wrap;
}
.aside-menu {
  border-right: none;
  border: 1px solid rgb(239, 239, 239);
  height: 100%;
  box-shadow: 1px 1px 1px 1px rgb(240, 239, 239);
}
</style>
