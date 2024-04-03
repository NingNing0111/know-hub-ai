<template>
  <div id="basic-layout">
    <el-container>
      <el-aside style="width: unset; height: 95vh">
        <BasicAside />
      </el-aside>
      <el-main>
        <RouterView />
      </el-main>
    </el-container>
  </div>
</template>

<script setup lang="ts"></script>

<style scoped lang="less">
#basic-layout {
  margin: 10px;
}
</style>
