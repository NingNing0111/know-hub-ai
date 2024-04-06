<template>
  <div id="basic-layout">
    <el-container>
      <el-aside :style="asideStyle">
        <BasicAside @change-aside="changeAside" />
      </el-aside>
      <el-main style="margin-left: 10px; background-color: #f3f3f3">
        <RouterView />
      </el-main>
    </el-container>
  </div>
</template>

<script setup lang="ts">
const asideStyle = ref({
  height: "95vh",
  width: "200px",
  // transition: "width 0.5s",
});
const changeAside = (isCollapse: boolean) => {
  if (isCollapse) {
    asideStyle.value.width = "auto";
  } else {
    // 动态变到150px
    asideStyle.value.width = "200px";
  }
};
</script>

<style scoped lang="less">
#basic-layout {
  margin: 10px;
}
</style>
