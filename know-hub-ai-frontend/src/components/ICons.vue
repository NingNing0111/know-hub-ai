<template>
  <el-icon :size="size" :color="color">
    <component :is="props.name"></component>
  </el-icon>
</template>

<script setup>
const props = defineProps({
  props: {
    name: {
      type: String,
      required: true,
    },
    size: {
      type: String,
      default: "",
    },
    color: {
      type: String,
      default: "",
    },
  },
});
</script>
