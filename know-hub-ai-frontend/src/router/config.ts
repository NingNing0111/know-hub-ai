let routes = [
  {
    path: "/chat",
    name: "chat",
    component: () => import("@/view/chat/ChatView.vue"),
  },
];

export default routes;
