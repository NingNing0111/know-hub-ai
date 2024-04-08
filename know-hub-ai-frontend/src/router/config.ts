let routes = [
  {
    path: "/",
    name: "index",
    redirect: "/chat",
    meta: {
      isMenu: false,
    },
  },
  {
    path: "/chat",
    name: "chat",
    component: () => import("@/view/chat/ChatView.vue"),
    meta: {
      isMenu: true,
      description: "AI对话",
      icon: "ChatDotRound",
    },
  },
  {
    path: "/draw",
    name: "绘图",
    component: () => import("@/view/draw/Draw.vue"),
    meta: {
      isMenu: true,
      description: "AI绘画",
      icon: "PictureRounded",
    },
  },
  {
    path: "/know-hub",
    name: "knowHub",
    component: () => import("@/view/know/KnowHubView.vue"),
    meta: {
      isMenu: true,
      description: "我的知识库",
      icon: "Collection",
    },
  },
  {
    path: "/manage",
    name: "manage",
    component: () => import("@/view/manage/ApiManageView.vue"),
    meta: {
      isMenu: true,
      description: "API管理",
      icon: "Key",
    },
  },
  {
    path: "/settings",
    name: "settings",
    component: () => import("@/view/chat/SettingView.vue"),
    meta: {
      isMenu: true,
      description: "设置",
      icon: "Setting",
    },
  },
];

export default routes;
