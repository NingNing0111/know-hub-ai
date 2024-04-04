import { createApp } from "vue";
import "./main.css";
import App from "./App.vue";

// 倒入router
import router from "@/router";
// 导入Element Plus
import ElementPlus from "element-plus";
import "element-plus/dist/index.css";
import * as ElementPlusIconsVue from "@element-plus/icons-vue";
// 导入pinia
import { createPinia } from "pinia";
import hljs from "highlight.js";
import "highlight.js/styles/atom-one-dark.css"; //样式
const pinia = createPinia();

const app = createApp(App);
// 使用router
app.use(router);
// 使用pinia
app.use(pinia);
// 使用Element Plus
app.use(ElementPlus);
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component);
}

//创建v-highlight全局指令
app.directive("highlight", function (el) {
  let blocks = el.querySelectorAll("pre code");
  blocks.forEach((block: any) => {
    hljs.highlightBlock(block);
  });
});

app.mount("#app");
