<template>
  <!--    @open="handleOpen"-->
  <!--    @close="handleClose"-->
  <el-menu
    default-active="1"
    class="aside-menu"
    :collapse="isCollapse"
  >
    <div class="aside-header">
      <div style="display: flex; justify-content: right">
        <el-icon :size="30" style="cursor: pointer" @click="openMenu"
          ><Fold
        /></el-icon>
      </div>
    </div>
    <el-menu-item index="1">
      <el-icon><icon-menu /></el-icon>
      <template #title>对话</template>
    </el-menu-item>
    <el-menu-item index="2">
      <el-icon><document /></el-icon>
      <template #title>知识库</template>
    </el-menu-item>
    <el-menu-item index="3">
      <el-icon><document /></el-icon>
      <template #title>对话配置</template>
    </el-menu-item>
  </el-menu>
</template>

<script setup lang="ts">
const emit = defineEmits(['changeAside'])
const isCollapse = ref(false);
const openMenu = () => {
  isCollapse.value = !isCollapse.value;
  emit('changeAside', isCollapse.value)
};
</script>

<style scoped lang="less">
.aside-header {
  height: 100px;
  // background-color: pink;
}
.aside-menu {
  // background-color: aqua;
}
</style>
