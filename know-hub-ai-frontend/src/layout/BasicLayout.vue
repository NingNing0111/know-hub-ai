<template>
  <div id="basic-layout">
    <el-container>
      <el-aside :style="asideStyle">
        <BasicAside @change-aside="changeAside"/>
      </el-aside>
      <el-main>
        <RouterView />
      </el-main>
    </el-container>
  </div>
</template>

<script setup lang="ts">

const asideStyle = ref({
  height: '95vh',
  // width: '300px',
  width: 'unset'
})
const changeAside = (isCollapse : boolean) => {

  if (isCollapse) {
    asideStyle.value.width = '100px'

  } else {
    asideStyle.value.width = '300px'

  }
}


</script>

<style scoped lang="less">
#basic-layout {
  margin: 10px;
}
</style>
