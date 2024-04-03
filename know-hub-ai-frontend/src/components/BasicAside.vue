<template>
  <el-menu
    :default-active="defaultPath"
    class="aside-menu"
    :collapse="isCollapse"
  >
    <div class="aside-header">
      <div style="display: flex; justify-content: right">
        <el-icon :size="25" style="cursor: pointer" @click="openMenu">
          <Expand v-if="isCollapse" />
          <Fold v-else />
        </el-icon>
      </div>
    </div>

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
</template>

<script setup lang="ts">
import routes from "@/router/config.ts";
import router from "@/router";

const emit = defineEmits(['changeAside'])
const isCollapse = ref(false);
const path = router.currentRoute.value.fullPath;
const defaultPath = ref(path === "/" ? "/chat" : path);
const openMenu = () => {
  isCollapse.value = !isCollapse.value;
  emit('changeAside', isCollapse.value)
};

// 使用计算属性过滤不是菜单项的路由选项
const menuRouterList = computed(() => {
  return routes.filter((item) => {
    return item.meta?.isMenu;
  });
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
.aside-header {
  height: 100px;
  background-color: pink;
}
.aside-menu {
  border-right: none;
  border: 1px solid rgb(239, 239, 239);
  height: 100%;
  box-shadow: 1px 1px 1px 1px rgb(240, 239, 239);
}
</style>
