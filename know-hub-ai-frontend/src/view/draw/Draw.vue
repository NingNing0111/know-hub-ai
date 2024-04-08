<template>
  <el-scrollbar height="90vh">
    <el-form :model="drawDto">
      <el-form-item label="图片描述:">
        <el-input
          v-model="drawDto.prompt"
          placeholder="请输入图片描述"
          clearable
          type="textarea"
          style="width: 500px"
          :autosize="{ minRows: 6, maxRows: 6 }"
          maxlength="300"
          resize="none"
          show-word-limit
        />
      </el-form-item>
      <el-form-item label="绘图模型:">
        <el-select
          v-model="drawDto.options.model"
          placeholder="Select"
          size="large"
          style="width: 240px"
        >
          <el-option
            v-for="item in models"
            :key="item"
            :label="item"
            :value="item"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="图片宽度:">
        <el-radio-group v-model="drawDto.options.width" class="ml-4">
          <template v-if="drawDto.options.model === 'dall-e-2'">
            <el-radio v-for="item in dalle2Size" :value="item" size="large">{{
              item
            }}</el-radio>
          </template>
          <template v-if="drawDto.options.model === 'dall-e-3'">
            <el-radio v-for="item in dalle3Size" :value="item" size="large">{{
              item
            }}</el-radio>
          </template>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="图片高度:">
        <el-radio-group v-model="drawDto.options.height" class="ml-4">
          <template v-if="drawDto.options.model === 'dall-e-2'">
            <el-radio v-for="item in dalle2Size" :value="item" size="large">{{
              item
            }}</el-radio>
          </template>
          <template v-if="drawDto.options.model === 'dall-e-3'">
            <el-radio v-for="item in dalle3Size" :value="item" size="large">{{
              item
            }}</el-radio>
          </template>
        </el-radio-group>
      </el-form-item>
      <el-button type="primary" @click="toDraw" :disabled="isDrawing"
        >绘制</el-button
      >
    </el-form>
    <el-divider />
    <div class="imageWindow">
      <!-- 存在图片 -->
      <template v-if="isImage">
        <el-image :style="imageSize" fit="cover" v-if="isDrawing">
          <template #error>
            <div class="image-slot">
              AI正在绘画中<span class="dot">...</span>
            </div>
          </template>
        </el-image>
        <el-image
          :style="imageSize"
          fit="cover"
          :preview-src-list="srcList"
          :src="imageUrl"
          v-else
        />
      </template>
      <!-- 不存在图片且没画的时候显示 -->
      <template v-if="!isImage && !isDrawing">
        <h1>还没开始绘制呢</h1>
      </template>
    </div>
  </el-scrollbar>
</template>

<script setup lang="ts">
import { ref, reactive, computed } from "vue";
import { DrawImageDto } from "@/api/dto.ts";
import { drawApi } from "@/api/DrawApi.ts";
import { ElMessage } from "element-plus";

const drawDto = ref<DrawImageDto>({
  prompt: "",
  options: {
    model: "dall-e-2",
    width: 256,
    height: 256,
    format: "url",
  },
});

const models = ref<string[]>(["dall-e-2", "dall-e-3"]);
const dalle2Size = ref<number[]>([256, 512, 1024]);
const dalle3Size = ref<number[]>([1024, 1792]);
const isDrawing = ref(false);
const isImage = ref(false);
const imageUrl = ref("");
const imageSize = ref({
  width: drawDto.value.options.width + "px;",
  height: drawDto.value.options.height + "px;",
});
const srcList = ref<string[]>([]);

const toDraw = () => {
  isDrawing.value = true;
  isImage.value = true;
  drawApi(drawDto.value)
    .then((res) => {
      let code = res.code;
      if (code == 0) {
        let imageInfo = res.data.at(0);
        imageUrl.value = imageInfo.output.url;
        srcList.value.push(imageUrl.value);
      }
    })
    .catch((err) => {
      ElMessage({
        type: "error",
        message: err,
      });
    })
    .finally(() => {
      isDrawing.value = false;
    });
};
</script>

<style scoped lang="less">
.imageWindow {
  display: flex;
  justify-content: center;
  margin: 10px;
  text-align: center;
  align-items: center;
  color: #656565;
}
</style>
