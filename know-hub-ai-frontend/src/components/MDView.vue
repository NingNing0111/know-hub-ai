<template>
  <div v-highlight v-html="marked(content)"></div>
</template>

<script setup lang="ts">
defineProps({
  content: {
    type: String,
    required: true,
  },
});

// 将marked 引入
import { marked } from "marked";
</script>

<style scoped></style>
