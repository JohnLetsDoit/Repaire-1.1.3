import Vue from "vue";
import vuex from "vuex";
Vue.use(vuex);
const state = {
  metaInfo: {
    title: "张培跃",
    keywords: "玉树临风,风流倜傥,英俊潇洒,才高八斗,貌似番安",
    description: "这么神奇吗？都已经很难用言语来描述了"
  }
};
const mutations = {
  CAHNGE_META_INFO(state, metaInfo) {
    state.metaInfo = metaInfo;
  }
};
export default new vuex.Store({
  state,
  mutations,
})
