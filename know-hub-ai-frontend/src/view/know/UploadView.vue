<template>
  <div>
    <el-upload
      class="upload-demo"
      drag
      action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
      multiple
    >
      <el-icon class="el-icon--upload"><upload-filled /></el-icon>
      <div class="el-upload__text">
        拖拽文件至此或<em>点击选择文件</em>进行上传
      </div>
      <template #tip>
        <div style="text-align: center">
          <el-text>文件最大可上传<em style="color: blue">20MB</em></el-text>
        </div>
      </template>
    </el-upload>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, computed } from "vue";
</script>

<style scoped lang="less"></style>
